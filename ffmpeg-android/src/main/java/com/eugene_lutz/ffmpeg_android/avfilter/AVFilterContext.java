package com.eugene_lutz.ffmpeg_android.avfilter;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.avutil.AVBufferRef;
import com.eugene_lutz.ffmpeg_android.avutil.AVClass;
import com.eugene_lutz.ffmpeg_android.avutil.AVDictionary;

/**
 * An instance of a filter
 * */
public class AVFilterContext extends CStructWrapper
{
	//region Class related stuff

	public static final int AVFILTER_THREAD_SLICE = (1 << 0);

	//endregion



	//region Constructor, Destructor, etc...

	public AVFilterContext(long pointer, AllocationType allocType)
	{
		super(pointer, allocType, 0);
	}

	@Override
	protected void finalize() /*throws Throwable*/
	{
		switch (allocationType)
		{
			case FROM_INSTANCE: break;
			case ALLOC: freeNative(pointer);
			default: break;
		}
	}

	public static AVFilterContext from(long pointer)
	{
		return pointer == 0 ? null : new AVFilterContext(pointer, AllocationType.ALLOC);
	}

	private static AVFilterContext from(long pointer, AllocationType allocType)
	{
		return pointer == 0 ? null : new AVFilterContext(pointer, allocType);
	}

	//endregion



	//region Static methods

	/**
	 * @return AVClass for AVFilterContext.
	 */
	public static AVClass getAVFilterContextClass()
	{
		final long classPointer = getAVFilterContextClassNative();
		return AVClass.from(classPointer);
	}

	/**
	 * Initialize a filter with the supplied parameters.
	 *
	 * @param args Options to initialize the filter with. This must be a
	 *             ':'-separated list of options in the 'key=value' form.
	 *             May be NULL if the options have been set directly using the
	 *             AVOptions API or there are no options that need to be set.
	 * @return instance on success, null on failure
	 */
	public static AVFilterContext createWithParameters(String args)
	{
		final long filter = createWithParametersNative(args);
		return AVFilterContext.from(filter, AllocationType.ALLOC);
	}

	/**
	 * Create a new filter instance in a filter graph.
	 *
	 * @param graph graph in which the new filter will be used
	 * @param filter the filter to create an instance of
	 * @param name Name to give to the new instance (will be copied to
	 *             AVFilterContext.name). This may be used by the caller to identify
	 *             different filters, libavfilter itself assigns no semantics to
	 *             this parameter. May be null.
	 *
	 * @return the context of the newly created filter instance (note that it is
	 *         also retrievable directly through AVFilterGraph.filters or with
	 *         avfilter_graph_get_filter()) on success or NULL on failure.
	 */
	public static AVFilterContext createInFilterGraph(AVFilterGraph graph, AVFilter filter, String name)
	{
		final long pointer = createInFilterGraphNative(graph.getPointer(), filter.getPointer(), name);
		return from(pointer, AllocationType.ALLOC);
	}

	//endregion



	//region Instance methods

	/**
	 * Link two filters together.
	 *
	 * @param pad		    index of the output pad on this filter
	 * @param destination	the destination filter
	 * @param dstpad		index of the input pad on the destination filter
	 * @return				zero on success
	 */
	public boolean link(int pad, AVFilterContext destination, int dstpad)
	{
		return linkNative(pointer, destination.pointer, pad, dstpad) == 0;
	}

	/**
	 * Negotiate the media format, dimensions, etc of all inputs to a filter.
	 *
	 * @return zero on successful negotiation
	 */
	public boolean configLinks()
	{
		return configLinksNative(pointer) == 0;
	}

	/**
	 * Initialize filter with the supplied dictionary of options.
	 *
	 * @param options An AVDictionary filled with options for this filter. On
	 *                return this parameter will be destroyed and replaced with
	 *                a dict containing options that were not found.
	 * @return 0 on success, a negative AVERROR on failure
	 *
	 * note: This function and avfilter_init_str() do essentially the same thing,
	 * the difference is in manner in which the options are passed. It is up to the
	 * calling code to choose whichever is more preferable. The two functions also
	 * behave differently when some of the provided options are not declared as
	 * supported by the filter. In such a case, avfilter_init_str() will fail, but
	 * this function will leave those extra options in the options AVDictionary and
	 * continue as usual.
	 */
	public int initWithDictionary(AVDictionary options)
	{
		return initWithDictionaryNative(pointer, options);
	}

	//endregion



	//region Getters/Setters

	public AVClass getAVClass()
	{
		final long avclassPointer= getAVClassNative(pointer);
		return AVClass.from(avclassPointer);
	}

	/**
	 * The AVFilter of which this is an instance
	 * @return The AVFilter of which this is an instance
	 * */
	public AVFilter getFilter()
	{
		final long filterPointer = getFilterNative(pointer);
		return AVFilter.from(filterPointer);
	}

	/**
	 * Name of this filter instance
	 * @return Name of this filter instance
	 * */
	public String getName()
	{
		return getNameNative(pointer);
	}

	public int getNumInputPads()
	{
		return getNumInputPadsNative(pointer);
	}

	public AVFilterPad getInputFilterPad(int index)
	{
		final long filterPadPointer = getInputFilterPadNative(pointer, index);
		return AVFilterPad.from(filterPadPointer);
	}

	public int getNumInputFilterLinks()
	{
		return getNumInputFilterLinksNative(pointer);
	}

	public AVFilterLink getInputFilterLink(int index)
	{
		final long filterLinkPointer = getInputFilterLinkNative(pointer, index);
		return AVFilterLink.from(filterLinkPointer);
	}

	public int getNumOutputPads()
	{
		return getNumOutputPadsNative(pointer);
	}

	/*public AVFilterPad getOutputFilterPad(int index)
	{
		final long filterPadPointer = getOutputFilterPadNative(pointer, index);
		return AVFilterPad.from(filterPadPointer);
	}*/

	public int getNumOutputFilterLinks()
	{
		return getNumOutputFilterLinksNative(pointer);
	}

	public AVFilterLink getOutputFilterLink(int index)
	{
		final long filterLinkPointer = getOutputFilterLinkNative(pointer, index);
		return AVFilterLink.from(filterLinkPointer);
	}

	/**
	 * Filtergraph this filter belongs to
	 * @return the filter graph
	 * */
	public AVFilterGraph getFilterGraph()
	{
		final long filterGraphPointer = getFilterGraphNative(pointer);
		return AVFilterGraph.from(filterGraphPointer);
	}

	/**
	 * Type of multithreading being allowed/used. A combination of
	 * AVFILTER_THREAD_* flags.
	 *
	 * May be set by the caller before initializing the filter to forbid some
	 * or all kinds of multithreading for this filter. The default is allowing
	 * everything.
	 *
	 * When the filter is initialized, this field is combined using bit AND with
	 * AVFilterGraph.thread_type to get the final mask used for determining
	 * allowed threading types. I.e. a threading type needs to be set in both
	 * to be allowed.
	 *
	 * After the filter is initialized, libavfilter sets this field to the
	 * threading type that is actually used (0 for no multithreading).
	 * @return Type of multithreading being allowed/used
	 */
	public int getThreadType()
	{
		return getThreadTypeNative(pointer);
	}

	/*public AVFilterCommand getFilterCommand()
	{
		final long filterCommandPointer = getFilterCommandNative(pointer);
		return AVFilterCommand.from(filterCommandPointer);
	}*/

	/**
	 * For filters which will create hardware frames, sets the device the
	 * filter should create them in.  All other filters will ignore this field:
	 * in particular, a filter which consumes or processes hardware frames will
	 * instead use the hw_frames_ctx field in AVFilterLink to carry the
	 * hardware context information.
	 * @return hardware context
	 */
	public AVBufferRef getHardwareContext()
	{
		final long context = getHardwareContextNative(pointer);
		return AVBufferRef.from(context);
	}

	/**
	 * Max number of threads allowed in this filter instance.
	 * If {@literal <}= 0, its value is ignored.
	 * Overrides global number of threads set per filter graph.
	 * @return Max number of threads
	 */
	public int getMaxNumberOfThreads()
	{
		return getMaxNumberOfThreadsNative(pointer);
	}

	/**
	 * Ready status of the filter.
	 * A non-0 value means that the filter needs activating;
	 * a higher value suggests a more urgent activation.
	 * @return Ready status of the filter
	 */
	public int getReadyStatus()
	{
		return getReadyStatusNative(pointer);
	}

	/**
	 * Get the number of extra hardware frames which the filter will
	 * allocate on its output links for use in following filters or by
	 * the caller.
	 *
	 * Some hardware filters require all frames that they will use for
	 * output to be defined in advance before filtering starts.  For such
	 * filters, any hardware frame pools used for output must therefore be
	 * of fixed size.  The extra frames set here are on top of any number
	 * that the filter needs internally in order to operate normally.
	 *
	 * This field must be set before the graph containing this filter is
	 * configured.
	 * @return number of extra hardware frames
	 */
	public int getNumExtraHardwareFrames()
	{
		return getNumExtraHardwareFramesNative(pointer);
	}



	/* ************************************************************************************* */

	/**
	 * Name of this filter instance
	 * @param value value
	 * */
	public void setName(String value)
	{
		setNameNative(pointer, value);
	}

	public void setNumInputPads(int value)
	{
		setNumInputPadsNative(pointer, value);
	}

	/*public void setInputFilterPad(int index, AVFilterPad value)
	{
		setInputFilterPadNative(pointer, index, value.getPointer());
	}*/

	public void setNumInputFilterLinks(int value)
	{
		setNumInputFilterLinksNative(pointer, value);
	}

	public void setInputFilterLink(int index, AVFilterLink value)
	{
		setInputFilterLinkNative(pointer, index, getPointer());
	}

	public void setNumOutputPads(int value)
	{
		setNumOutputPadsNative(pointer, value);
	}

	/*public void setOutputFilterPad(int index, AVFilterPad value)
	{
		setOutputFilterPadNative(pointer, index, value.getPointer());
	}*/

	public void setNumOutputFilterLinks(int value)
	{
		setNumOutputFilterLinksNative(pointer, value);
	}

	public void setOutputFilterLink(int index, AVFilterLink value)
	{
		setOutputFilterLinkNative(pointer, index, value.getPointer());
	}

	/**
	 * Filtergraph this filter belongs to
	 * @param value value
	 * */
	public void setFilterGraph(AVFilterGraph value)
	{
		setFilterGraphNative(pointer, value.getPointer());
	}

	/**
	 * Type of multithreading being allowed/used. A combination of
	 * AVFILTER_THREAD_* flags.
	 *
	 * May be set by the caller before initializing the filter to forbid some
	 * or all kinds of multithreading for this filter. The default is allowing
	 * everything.
	 *
	 * When the filter is initialized, this field is combined using bit AND with
	 * AVFilterGraph.thread_type to get the final mask used for determining
	 * allowed threading types. I.e. a threading type needs to be set in both
	 * to be allowed.
	 *
	 * After the filter is initialized, libavfilter sets this field to the
	 * threading type that is actually used (0 for no multithreading).
	 * @param value value
	 */
	public void setThreadType(int value)
	{
		setThreadTypeNative(pointer, value);
	}

	/*public void setFilterCommand(AVFilterCommand value)
	{
		setFilterCommandNative(pointer, value.getPointer());
	}*/

	/**
	 * For filters which will create hardware frames, sets the device the
	 * filter should create them in.  All other filters will ignore this field:
	 * in particular, a filter which consumes or processes hardware frames will
	 * instead use the hw_frames_ctx field in AVFilterLink to carry the
	 * hardware context information.
	 * @param value value
	 */
	public void setHardwareContext(AVBufferRef value)
	{
		setHardwareContextNative(pointer, value.getPointer());
	}

	/**
	 * Max number of threads allowed in this filter instance.
	 * If {@literal <}= 0, its value is ignored.
	 * Overrides global number of threads set per filter graph.
	 * @param value value
	 */
	public void setMaxNumberOfThreads(int value)
	{
		setMaxNumberOfThreadsNative(pointer, value);
	}

	/**
	 * Ready status of the filter.
	 * A non-0 value means that the filter needs activating;
	 * a higher value suggests a more urgent activation.
	 * @param value value
	 */
	public void setReadyStatus(int value)
	{
		setReadyStatusNative(pointer, value);
	}

	/**
	 * Set the number of extra hardware frames which the filter will
	 * allocate on its output links for use in following filters or by
	 * the caller.
	 *
	 * Some hardware filters require all frames that they will use for
	 * output to be defined in advance before filtering starts.  For such
	 * filters, any hardware frame pools used for output must therefore be
	 * of fixed size.  The extra frames set here are on top of any number
	 * that the filter needs internally in order to operate normally.
	 *
	 * This field must be set before the graph containing this filter is
	 * configured.
	 * @param value value
	 */
	public void setNumExtraHardwareFrames(int value)
	{
		setNumExtraHardwareFramesNative(pointer, value);
	}

	//endregion



	//region Native methods

	private static native long getAVFilterContextClassNative();
	private static native long createWithParametersNative(String args);
	private static native void freeNative(long pointer);
	private static native int linkNative(long src, long dst, int srcIndex, int dstIndex);
	private static native int configLinksNative(long pointer);
	private static native int initWithDictionaryNative(long pointer, AVDictionary options);
	private static native long createInFilterGraphNative(long graphPointer, long filterPointer, String name);

	//region gettres/setters

	private static native long getAVClassNative(long pointer);
	private static native long getFilterNative(long pointer);
	private static native String getNameNative(long pointer);
	private static native int getNumInputPadsNative(long pointer);
	private static native long getInputFilterPadNative(long pointer, int index);
	private static native int getNumInputFilterLinksNative(long pointer);
	private static native long getInputFilterLinkNative(long pointer, int index);
	private static native int getNumOutputPadsNative(long pointer);
	//private static native long getOutputFilterPadNative(long pointer, int index);
	private static native int getNumOutputFilterLinksNative(long pointer);
	private static native long getOutputFilterLinkNative(long pointer, int index);
	private static native long getFilterGraphNative(long pointer);
	private static native int getThreadTypeNative(long pointer);
	//private static native long getFilterCommandNative(long pointer);
	private static native long getHardwareContextNative(long pointer);
	private static native int getMaxNumberOfThreadsNative(long pointer);
	private static native int getReadyStatusNative(long pointer);
	private static native int getNumExtraHardwareFramesNative(long pointer);

	private static native void setNameNative(long pointer, String value);
	private static native void setNumInputPadsNative(long pointer, int value);
	//private static native void setInputFilterPadNative(long pointer, int index, long value);
	private static native void setNumInputFilterLinksNative(long pointer, int value);
	private static native void setInputFilterLinkNative(long pointer, int index, long value);
	private static native void setNumOutputPadsNative(long pointer, int value);
	//private static native void setOutputFilterPadNative(long pointer, int index, long value);
	private static native void setNumOutputFilterLinksNative(long pointer, int value);
	private static native void setOutputFilterLinkNative(long pointer, int index, long value);
	private static native void setFilterGraphNative(long pointer, long value);
	private static native void setThreadTypeNative(long pointer, int value);
	//private static native void setFilterCommandNative(long pointer, long value);
	private static native void setHardwareContextNative(long pointer, long value);
	private static native void setMaxNumberOfThreadsNative(long pointer, int value);
	private static native void setReadyStatusNative(long pointer, int value);
	private static native void setNumExtraHardwareFramesNative(long pointer, int value);

	//endregion

	//endregion
}
