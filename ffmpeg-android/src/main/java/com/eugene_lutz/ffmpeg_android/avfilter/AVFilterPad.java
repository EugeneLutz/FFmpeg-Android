package com.eugene_lutz.ffmpeg_android.avfilter;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.avutil.AVMediaType;
import com.eugene_lutz.ffmpeg_android.avutil.AVUtilHelper;

public class AVFilterPad extends CStructWrapper
{
	//region Class related stuff

	//endregion



	//region Constructor, Destructor, etc...

	private AVFilterPad(long pointer)
	{
		super(pointer, AllocationType.FROM_INSTANCE, 0);
	}


	public static AVFilterPad from(long pointer)
	{
		return pointer == 0 ? null : new AVFilterPad(pointer);
	}

	//endregion



	//region Static methods

	//endregion



	//region Instance methods

	//endregion



	//region Getters/Setters

	public String getName()
	{
		return getNameNative(pointer);
	}

	public AVMediaType getMediaType()
	{
		int type = getMediaTypeNative(pointer);
		//return FFmpegUtil.intToMediaType(type);
		return AVUtilHelper.longToAVMediaType(type);
	}

	//endregion



	//region Native methods

	private static native String getNameNative(long pointer);
	private static native int getMediaTypeNative(long pointer);

	//endregion
}
