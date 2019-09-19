package com.eugene_lutz.ffmpeg_android.avutil;

/**
 * MPEG vs JPEG YUV range.
 */
public enum AVColorRange
{
	AVCOL_RANGE_UNSPECIFIED,
	AVCOL_RANGE_MPEG,	///< the normal 219*2^(n-8) "MPEG" YUV ranges
	AVCOL_RANGE_JPEG	///< the normal     2^n-1   "JPEG" YUV ranges
}
