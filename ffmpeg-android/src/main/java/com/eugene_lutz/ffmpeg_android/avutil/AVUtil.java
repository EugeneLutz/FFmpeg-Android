package com.eugene_lutz.ffmpeg_android.avutil;

public final class AVUtil
{
	/**
	 * Return a string describing the mediaType enum.
	 * @return String describing the mediaType enum, null if mediaType is unknown.
	 */
	public static String getMediaTypeString(AVMediaType mediaType)
	{
		final long mediaTypeIndex = AVUtilHelper.AVMediaTypeToLong(mediaType);
		return getMediaTypeStringNative(mediaTypeIndex);
	}

	private static native String getMediaTypeStringNative(long index);
}
