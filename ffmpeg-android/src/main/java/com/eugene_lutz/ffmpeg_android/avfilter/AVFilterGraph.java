package com.eugene_lutz.ffmpeg_android.avfilter;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.avutil.RawData;

public class AVFilterGraph extends CStructWrapper
{
	//region Class related stuff

	//endregion



	//region Constructor, Destructor, etc...

	public AVFilterGraph(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}

	@Override
	protected void finalizeDefault()
	{
		freeNative(pointer);
	}

	public static AVFilterGraph from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static AVFilterGraph from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVFilterGraph(pointer, allocationType, allocationFlag);
	}

	//endregion



	//region Static methods

	/**
	 * Allocate a filter graph.
	 *
	 * @return the allocated filter graph on success or null.
	 */
	public static AVFilterGraph create()
	{
		final long pointer = allocNative();
		return from(pointer, AllocationType.ALLOC, 0);
	}

	//endregion



	//region Instance methods

	/**
	 * Check validity and configure all the links and formats in the graph.
	 *
	 * @return >= 0 in case of success, a negative AVERROR code otherwise
	 */
	int config()
	{
		return configNative(pointer);
	}

	/**
	 * Add a graph described by a string to a graph.
	 *
	 * @note The caller must provide the lists of inputs and outputs,
	 * which therefore must be known before calling the function.
	 *
	 * @note The inputs parameter describes inputs of the already existing
	 * part of the graph; i.e. from the point of view of the newly created
	 * part, they are outputs. Similarly the outputs parameter describes
	 * outputs of the already existing filters, which are provided as
	 * inputs to the parsed filters.
	 *
	 * @param graph   the filter graph where to link the parsed graph context
	 * @param filters string to be parsed
	 * @param inputs  linked list to the inputs of the graph
	 * @param outputs linked list to the outputs of the graph
	 * @return zero on success, a negative AVERROR code on error
	 */
	int parse(AVFilterGraph graph, String filters, AVFilterInOut inputs, AVFilterInOut outputs)
	{
		return parseNative(pointer, filters, inputs.getPointer(), outputs.getPointer());
	}

	/**
	 * Send a command to one or more filter instances.
	 *
	 * @param target the filter(s) to which the command should be sent
	 *               "all" sends to all filters
	 *               otherwise it can be a filter or filter instance name
	 *               which will send the command to all matching filters.
	 * @param cmd    the command to send, for handling simplicity all commands must be alphanumeric only
	 * @param arg    the argument for the command
	 * @param res    a buffer with size res_size where the filter(s) can return a response.
	 *
	 * @return >=0 on success otherwise an error code.
	 *              AVERROR(ENOSYS) on unsupported commands
	 */
	int sendCommand(String target, String cmd, String arg, RawData res, int res_len, int flags)
	{
		final long resPointer = res == null ? 0 : res.getPointer();
		return sendCommandNative(pointer, target, cmd, arg, resPointer, res_len, flags);
	}

	/**
	 * Queue a command for one or more filter instances.
	 *
	 * @param target the filter(s) to which the command should be sent
	 *               "all" sends to all filters
	 *               otherwise it can be a filter or filter instance name
	 *               which will send the command to all matching filters.
	 * @param cmd    the command to sent, for handling simplicity all commands must be alphanumeric only
	 * @param arg    the argument for the command
	 * @param ts     time at which the command should be sent to the filter
	 *
	 * @note As this executes commands after this function returns, no return code
	 *       from the filter is provided, also AVFILTER_CMD_FLAG_ONE is not supported.
	 */
	int queueCommand(String target, String cmd, String arg, int flags, double ts)
	{
		return queueCommandNative(pointer, target, cmd, arg, flags, ts);
	}

	/**
	 * Dump a graph into a human-readable string representation.
	 *
	 * @param options  formatting options; currently ignored
	 * @return  a string, or NULL in case of memory allocation failure;
	 *          the string must be freed using av_free
	 */
	String dump(String options)
	{
		return dumpNative(pointer, options);
	}

	/**
	 * Request a frame on the oldest sink link.
	 *
	 * If the request returns AVERROR_EOF, try the next.
	 *
	 * Note that this function is not meant to be the sole scheduling mechanism
	 * of a filtergraph, only a convenience function to help drain a filtergraph
	 * in a balanced way under normal circumstances.
	 *
	 * Also note that AVERROR_EOF does not mean that frames did not arrive on
	 * some of the sinks during the process.
	 * When there are multiple sink links, in case the requested link
	 * returns an EOF, this may cause a filter to flush pending frames
	 * which are sent to another sink link, although unrequested.
	 *
	 * @return  the return value of ff_request_frame(),
	 *          or AVERROR_EOF if all links returned AVERROR_EOF
	 */
	int requestOldest()
	{
		return requestOldestNative(pointer);
	}

	//endregion



	//region Getters/Setters

	/**
	 * Get a filter instance identified by instance name from graph.
	 *
	 * @param name filter instance name (should be unique in the graph).
	 * @return the pointer to the found filter instance or null if it
	 * cannot be found.
	 */
	AVFilterContext getFilter(String name)
	{
		final long filterPointer = getFilterNative(pointer, name);
		return AVFilterContext.from(filterPointer);
	}

	/**
	 * Enable or disable automatic format conversion inside the graph.
	 *
	 * Note that format conversion can still happen inside explicitly inserted
	 * scale and aresample filters.
	 *
	 * @param all  all automatic conversions enabled if true, otherwise disabled
	 */
	void setAutoConvert(boolean all)
	{
		setAutoConvertNative(pointer, all ? 0 : -1);
	}

	//endregion



	//region Native methods

	private static native long allocNative();
	private static native void freeNative(long pointer);

	private static native int configNative(long pointer);
	private static native int parseNative(long pointer, String filters, long inputsPointer, long outputsPointer);
	private static native int sendCommandNative(long pointer, String target, String cmd, String arg, long resPointer, int res_len, int flags);
	private static native int queueCommandNative(long pointer, String target, String cmd, String arg, int flags, double ts);
	private static native String dumpNative(long pointer, String options);
	private static native int requestOldestNative(long pointer);

	private static native long getFilterNative(long pointer, String name);
	private static native void setAutoConvertNative(long pointer, int flags);

	//endregion
}
