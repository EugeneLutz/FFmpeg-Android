package com.eugene_lutz.ffmpeg_android.avfilter;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

/**
 * A linked-list of the inputs/outputs of the filter chain.
 *
 * This is mainly useful for avfilter_graph_parse() / avfilter_graph_parse2(),
 * where it is used to communicate open (unlinked) inputs and outputs from and
 * to the caller.
 * This struct specifies, per each not connected pad contained in the graph, the
 * filter context and the pad index required for establishing a link.
 */
public class AVFilterInOut extends CStructWrapper
{
	//region Class related stuff

	//endregion



	//region Constructor, Destructor, etc...

	public AVFilterInOut(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}

	@Override
	protected void finalize() /*throws Throwable*/
	{
		switch (allocationType)
		{
			case FROM_INSTANCE: break;
			case ALLOC: freeNative(pointer); break;
			case CUSTOM: customFinalize(); break;
			default: break;
		}
	}

	private void customFinalize()
	{
		switch (allocationFlag)
		{
			case 0: break;
			case 1: break;
			default: break;
		}
	}

	public static AVFilterInOut from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static AVFilterInOut from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVFilterInOut(pointer, allocationType, allocationFlag);
	}

	//endregion



	//region Static methods

	/**
	 * Allocate a single AVFilterInOut entry.
	 * Must be freed with avfilter_inout_free().
	 * @return allocated AVFilterInOut on success, null on failure.
	 */
	public AVFilterInOut create(String name, AVFilterContext context, int padIndex)
	{
		if (name == null || context == null)
		{
			return null;
		}

		final long filterPointer = allocNative(name, context.getPointer(), padIndex);
		return from(filterPointer, AllocationType.ALLOC, 0);
	}

	//endregion



	//region Instance methods

	public void setNext(AVFilterInOut next)
	{
		setNextNative(pointer, next == null ? 0 : next.pointer);
	}

	//endregion



	//region Getters/Setters

	//endregion



	//region Native methods

	private static native long allocNative(String name, long context, int padIndex);
	private static native void freeNative(long pointer);
	private static native void setNextNative(long pointer, long next);

	//endregion
}
