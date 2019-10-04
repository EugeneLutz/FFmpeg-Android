package com.eugene_lutz.ffmpeg_android.avutil;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.FFmpegAndroid;

public final class AVUtil
{
	static {
		FFmpegAndroid.loadLibraries();
	}

	/**
	 * Return version of the avutil library.
	 * @return The LIBAVUTIL_VERSION_INT constant.
	 */
	public static long getVersion()
	{
		return getVersionNative();
	}

	/**
	 * Return an informative version string. This usually is the actual release
	 * version number or a git commit description. This string has no fixed format
	 * and can change any time. It should never be parsed by code.
	 * @return Informative version string.
	 */
	public static String getVersionInfo()
	{
		return getVersionInfoNative();
	}

	/**
	 * Return the libavutil build-time configuration.
	 * @return libavutil build-time configuration.
	 */
	public static String getConfiguration()
	{
		return getConfigurationNative();
	}

	/**
	 * Return the libavutil license.
	 * @return libavutil license.
	 */
	public static String getLicense()
	{
		return getLicenseNative();
	}

	/**
	 * Return a string describing the mediaType enum.
	 * @return String describing the mediaType enum, null if mediaType is unknown.
	 */
	public static String getMediaTypeString(AVMediaType mediaType)
	{
		final long mediaTypeIndex = AVUtilHelper.AVMediaTypeToLong(mediaType);
		return getMediaTypeStringNative(mediaTypeIndex);
	}

	/**
	 * Returns the fractional representation of the internal time base.
	 * @return the fractional representation of the internal time base.
	 */
	public static AVRational getBaseTimeQ()
	{
		final long rationalPointer = getBaseTimeQNative();
		return AVRational.from(rationalPointer, CStructWrapper.AllocationType.ALLOC);
	}

	private static native long getVersionNative();
	private static native String getVersionInfoNative();
	private static native String getConfigurationNative();
	private static native String getLicenseNative();
	private static native String getMediaTypeStringNative(long index);
	private static native long getBaseTimeQNative();
}
