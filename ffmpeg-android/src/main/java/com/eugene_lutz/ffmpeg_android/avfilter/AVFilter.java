package com.eugene_lutz.ffmpeg_android.avfilter;

import android.support.annotation.NonNull;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.OpaqueIterator;

import java.util.ArrayList;

/**
 * Filter definition. This defines the pads a filter contains, and all the
 * callback functions used to interact with the filter.
 */
public class AVFilter extends CStructWrapper
{
	//region Class related stuff

	/**
	 * The number of the filter inputs is not determined just by AVFilter.inputs.
	 * The filter might add additional inputs during initialization depending on the
	 * options supplied to it.
	 */
	public static final int AVFILTER_FLAG_DYNAMIC_INPUTS = (1 << 0);

	/**
	 * The number of the filter outputs is not determined just by AVFilter.outputs.
	 * The filter might add additional outputs during initialization depending on
	 * the options supplied to it.
	 */
	public static final int AVFILTER_FLAG_DYNAMIC_OUTPUTS = (1 << 1);

	/**
	 * The filter supports multithreading by splitting frames into multiple parts
	 * and processing them concurrently.
	 */
	public static final int AVFILTER_FLAG_SLICE_THREADS = (1 << 2);

	/**
	 * Some filters support a generic "enable" expression option that can be used
	 * to enable or disable a filter in the timeline. Filters supporting this
	 * option have this flag set. When the enable expression is false, the default
	 * no-op filter_frame() function is called in place of the filter_frame()
	 * callback defined on each input pad, thus the frame is passed unchanged to
	 * the next filters.
	 */
	public static final int AVFILTER_FLAG_SUPPORT_TIMELINE_GENERIC = (1 << 16);

	/**
	 * Same as AVFILTER_FLAG_SUPPORT_TIMELINE_GENERIC, except that the filter will
	 * have its filter_frame() callback(s) called as usual even when the enable
	 * expression is false. The filter will disable filtering within the
	 * filter_frame() callback(s) itself, for example executing code depending on
	 * the AVFilterContext->is_disabled value.
	 */
	public static final int AVFILTER_FLAG_SUPPORT_TIMELINE_INTERNAL = (1 << 17);

	/**
	 * Handy mask to test whether the filter supports or no the timeline feature
	 * (internally or generically).
	 */
	public static final int AVFILTER_FLAG_SUPPORT_TIMELINE =
			(AVFILTER_FLAG_SUPPORT_TIMELINE_GENERIC | AVFILTER_FLAG_SUPPORT_TIMELINE_INTERNAL);

	//endregion



	//region Constructor, Destructor, etc...

	private AVFilter(long pointer, AllocationType allocType)
	{
		super(pointer, allocType, 0);
	}

	@Override
	protected void finalize() /*throws Throwable*/
	{
		switch (allocationType)
		{
			case FROM_INSTANCE: break;
			//case CREATE: freeNative(pointer);
			default: break;
		}
	}



	public static AVFilter from(long pointer)
	{
		return pointer == 0 ? null : new AVFilter(pointer, AllocationType.FROM_INSTANCE);
	}

	private static AVFilter from(long pointer, AllocationType allocType)
	{
		return pointer == 0 ? null : new AVFilter(pointer, allocType);
	}

	//endregion



	//region Static methods

	/**
	 * Return the LIBAVFILTER_VERSION_INT constant.
	 */
	public static int getVersion()
	{
		return getVersionNative();
	}

	/**
	 * Return the libavfilter build-time configuration.
	 */
	public static String getConfiguration()
	{
		return getConfigurationNative();
	}

	/**
	 * Return the libavfilter license.
	 */
	public static String getLicense()
	{
		return getLicenseNative();
	}

	/**
	 * Iterate over all registered filters.
	 *
	 * @param iterator an iterator where libavfilter will store the iteration state. Must
	 *		           be newly created to start the iteration.
	 *
	 * @return the next registered filter or null when the iteration is
	 *         finished
	 */
	public static AVFilter iterate(OpaqueIterator iterator)
	{
		final long filter = iterateNative(iterator.getPointer());
		return from(filter);
	}

	public static AVFilter[] iterateAll()
	{
		final AVFilter[] dummy = new AVFilter[0];

		final OpaqueIterator iterator = OpaqueIterator.create();
		if (iterator == null)
		{
			return dummy;
		}

		ArrayList<AVFilter> filters = new ArrayList<>();
		for (;;)
		{
			final AVFilter filter = iterate(iterator);
			if (filter == null)
			{
				break;
			}

			filters.add(filter);
		}

		return filters.toArray(dummy);
	}

	/**
	 * Get a filter definition matching the given name.
	 *
	 * @param name the filter name to find
	 * @return     the filter definition, if any matching one is registered.
	 *             NULL if none found.
	 */
	public static AVFilter getByName(String name)
	{
		final long filter = getByNameNative(name);
		return from(filter);
	}

	//endregion



	//region Instance methods

	//endregion



	//region Getters/Setters

	/**
	 * Уникальное название фильтра
	 * */
	@NonNull
	public String getName()
	{
		return getNameNative(pointer);
	}

	/**
	 * A description of the filter. May be NULL.
	 */
	public String getDescription()
	{
		return getDescriptionNative(pointer);
	}

	/**
	 * Get the number of of input AVFilterPads
	 */
	public int getNumInputFilterPads()
	{
		return getNumInputFilterPadsNative(pointer);
	}

	/* *
	 * Get the index-th input AVFilterPad
	 */
	/*public AVFilterPad getInputFilterPad(int index)
	{
		long filterPadPointer = getInputFilterPadNative(pointer, index);
		return AVFilterPad.from(filterPadPointer);
	}*/

	/**
	 * Get the number of of output AVFilterPads
	 */
	public int getNumOutputFilterPads()
	{
		return getNumOutputFilterPadsNative(pointer);
	}

	/* *
	 * Get the index-th output AVFilterPad
	 */
	/*public AVFilterPad getOutputFilterPad(int index)
	{
		long filterPadPointer = getOutputFilterPadNative(pointer, index);
		return AVFilterPad.from(filterPadPointer);
	}*/

	/**
	 * A combination of AVFilter.AVFILTER_FLAG_*
	 */
	public int getFlags()
	{
		return getFlagsNative(pointer);
	}

	//endregion



	//region Native methods

	private static native int getVersionNative();
	private static native String getConfigurationNative();
	private static native String getLicenseNative();
	private static native long iterateNative(long iteratorPointer);
	private static native long getByNameNative(String name);

	//

	private static native String getNameNative(long pointer);
	private static native String getDescriptionNative(long pointer);
	private static native int getNumInputFilterPadsNative(long pointer);
	//private static native long getInputFilterPadNative(long pointer, int index);
	private static native int getNumOutputFilterPadsNative(long pointer);
	//private static native long getOutputFilterPadNative(long pointer, int index);
	private static native int getFlagsNative(long pointer);

	//endregion
}
