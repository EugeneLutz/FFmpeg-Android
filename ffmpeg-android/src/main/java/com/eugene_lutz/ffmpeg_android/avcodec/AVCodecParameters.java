package com.eugene_lutz.ffmpeg_android.avcodec;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.avutil.AVMediaType;
import com.eugene_lutz.ffmpeg_android.avutil.AVUtilHelper;

public class AVCodecParameters extends CStructWrapper
{
	private AVCodecParameters(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}



	public static AVCodecParameters from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static AVCodecParameters from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVCodecParameters(pointer, allocationType, allocationFlag);
	}



	public int copyTo(AVCodecParameters destination)
	{
		return copyToNative(pointer, destination.pointer);
	}

	public AVMediaType getCodecType()
	{
		final int codecTypeInt = getCodecTypeNative(pointer);
		return AVUtilHelper.longToAVMediaType(codecTypeInt);
	}

	public void setCodecTag(int tag)
	{
		setCodecTagNative(pointer, tag);
	}

	/**
	 * Fill the parameters struct based on the values from the supplied codec
	 * context. Any allocated fields in par are freed and replaced with duplicates
	 * of the corresponding fields in codec.
	 *
	 * @return {@literal >}= 0 on success, a negative AVERROR code on failure
	 */
	public int fillFromContext(AVCodecContext context)
	{
		return fillFromContextNative(pointer, context.getPointer());
	}



	private static native int copyToNative(long sourcePointer, long destPointer);
	private static native int getCodecTypeNative(long pointer);
	private static native void setCodecTagNative(long pointer, int tag);
	private static native int fillFromContextNative(long pointer, long contextPointer);
}
