package com.eugene_lutz.ffmpeg_android.swscale;

import com.eugene_lutz.ffmpeg_android.avutil.AVClass;

public class SwScale
{
	public static int getVersion()
	{
		return getVersionNative();
	}

	/**
	 * Return the libswscale build-time configuration.
	 */
	public static String getConfiguration()
	{
		return getConfigurationNative();
	}

	/**
	 * Return the libswscale license.
	 */
	public static String getLicense()
	{
		return getLicenseNative();
	}

	/**
	 * Get the AVClass for swsContext. It can be used in combination with
	 * AV_OPT_SEARCH_FAKE_OBJ for examining options.
	 */
	public static AVClass getAVClass()
	{
		final long avclass = getAVClassNative();
		return AVClass.from(avclass);
	}



	public static native int getVersionNative();
	public static native String getConfigurationNative();
	public static native String getLicenseNative();
	public static native long getAVClassNative();
}
