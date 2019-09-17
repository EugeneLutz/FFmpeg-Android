package com.eugene_lutz.ffmpeg_android.avcodec;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.avutil.AVMediaType;
import com.eugene_lutz.ffmpeg_android.avutil.AVUtilHelper;


/**
 * Describes the properties of a single codec described by an
 * AVCodecID.
 */
public class AVCodecDescriptor extends CStructWrapper
{
	public AVCodecDescriptor(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}



	public static AVCodecDescriptor from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static AVCodecDescriptor from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVCodecDescriptor(pointer, allocationType, allocationFlag);
	}


	public AVCodecID getID()
	{
		final long idLong = getIdNative(pointer);
		return AVCodecHelper.longToAVCodecId(idLong);
	}

	public AVMediaType getMediaType()
	{
		final long idLong = getMediaTypeNative(pointer);
		return AVUtilHelper.longToAVMediaType(idLong);
	}


	private static native long getIdNative(long pointer);
	private static native long getMediaTypeNative(long pointer);
}
