package com.eugene_lutz.ffmpeg_android.avutil;

import com.eugene_lutz.ffmpeg_android.IndexMap;


public class AVUtilHelper
{
	private static IndexMap<AVRounding>[] roundingMap = new IndexMap[]{
			new IndexMap<>(AVRounding.AV_ROUND_ZERO, 1),
			new IndexMap<>(AVRounding.AV_ROUND_INF, 2),
			new IndexMap<>(AVRounding.AV_ROUND_DOWN, 3),
			new IndexMap<>(AVRounding.AV_ROUND_UP, 4),
			new IndexMap<>(AVRounding.AV_ROUND_NEAR_INF, 5),
			new IndexMap<>(AVRounding.AV_ROUND_PASS_MINMAX, 6)
	};

	public static long AVRoundingToLong(AVRounding rounding)
	{
		return IndexMap.getIndexByValue(roundingMap, rounding);
	}

	public static AVRounding longToAVRounding(long value)
	{
		return IndexMap.getValueByIndex(roundingMap, value);
	}



	private static IndexMap<AVMediaType>[] mediaTypeMap = new IndexMap[] {
			new IndexMap<>(AVMediaType.AVMEDIA_TYPE_UNKNOWN, 1),
			new IndexMap<>(AVMediaType.AVMEDIA_TYPE_VIDEO, 2),
			new IndexMap<>(AVMediaType.AVMEDIA_TYPE_AUDIO, 3),
			new IndexMap<>(AVMediaType.AVMEDIA_TYPE_DATA, 4),
			new IndexMap<>(AVMediaType.AVMEDIA_TYPE_SUBTITLE, 5),
			new IndexMap<>(AVMediaType.AVMEDIA_TYPE_ATTACHMENT, 6),
			new IndexMap<>(AVMediaType.AVMEDIA_TYPE_NB, 7),
	};

	public static long AVMediaTypeToLong(AVMediaType mediaType)
	{
		return IndexMap.getIndexByValue(mediaTypeMap, mediaType);
	}

	public static AVMediaType longToAVMediaType(long value)
	{
		return IndexMap.getValueByIndex(mediaTypeMap, value);
	}
}
