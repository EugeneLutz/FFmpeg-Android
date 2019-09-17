package com.eugene_lutz.ffmpeg_android.avfilter;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

public class AVFilterLink extends CStructWrapper
{
	//region Class related stuff

	//endregion



	//region Constructor, Destructor, etc...

	public AVFilterLink(long pointer, AllocationType allocationType, int allocationFlag)
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
		switch (customFlag)
		{
			case 0: break;
			case 1: break;
			default: break;
		}
	}

	public static AVFilterLink from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static AVFilterLink from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVFilterLink(pointer, allocationType, allocationFlag);
	}

	//endregion



	//region Static methods

	//endregion



	//region Instance methods

	/**
	 * Insert a filter in the middle of an existing link.
	 *
	 * @param context       the filter to be inserted
	 * @param filtSrcpadIdx the input pad on the filter to connect
	 * @param filtDstpadIdx the output pad on the filter to connect
	 * @return     zero on success
	 */
	public int insertFilter(AVFilterContext context, long filtSrcpadIdx, long filtDstpadIdx)
	{
		return insertFilterNative(pointer, context.getPointer(), filtSrcpadIdx, filtDstpadIdx);
	}

	//endregion



	//region Getters/Setters

	//endregion



	//region Native methods

	private static native void freeNative(long pointer);
	private static native int insertFilterNative(long pointer, long filterPointer, long filtSrcpadIdx, long filtDstpadIdx);

	//endregion
}
