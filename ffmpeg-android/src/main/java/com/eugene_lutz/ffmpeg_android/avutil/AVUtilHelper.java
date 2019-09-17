package com.eugene_lutz.ffmpeg_android.avutil;

import com.eugene_lutz.ffmpeg_android.IndexMap;

import java.util.ArrayList;


public class AVUtilHelper
{
	private static ArrayList<IndexMap<AVClassCategory>> classCategoryMap;
	private static ArrayList<IndexMap<AVRounding>> roundingMap;
	private static ArrayList<IndexMap<AVMediaType>> mediaTypeMap;

	static {
		classCategoryMap = new ArrayList<>();
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_NA, 1));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_INPUT, 2));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_OUTPUT, 3));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_MUXER, 4));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_DEMUXER, 5));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_ENCODER, 6));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_DECODER, 7));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_FILTER, 8));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_BITSTREAM_FILTER, 9));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_SWSCALER, 10));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_SWRESAMPLER, 11));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_DEVICE_VIDEO_OUTPUT, 12));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_DEVICE_VIDEO_INPUT, 13));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_DEVICE_AUDIO_OUTPUT, 14));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_DEVICE_AUDIO_INPUT, 15));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_DEVICE_OUTPUT, 16));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_DEVICE_INPUT, 17));
		classCategoryMap.add(new IndexMap<>(AVClassCategory.AV_CLASS_CATEGORY_NB, 18));

		roundingMap = new ArrayList<>();
		roundingMap.add(new IndexMap<>(AVRounding.AV_ROUND_ZERO, 1));
		roundingMap.add(new IndexMap<>(AVRounding.AV_ROUND_INF, 2));
		roundingMap.add(new IndexMap<>(AVRounding.AV_ROUND_DOWN, 3));
		roundingMap.add(new IndexMap<>(AVRounding.AV_ROUND_UP, 4));
		roundingMap.add(new IndexMap<>(AVRounding.AV_ROUND_NEAR_INF, 5));
		roundingMap.add(new IndexMap<>(AVRounding.AV_ROUND_PASS_MINMAX, 6));

		mediaTypeMap = new ArrayList<>();
		mediaTypeMap.add(new IndexMap<>(AVMediaType.AVMEDIA_TYPE_UNKNOWN, 1));
		mediaTypeMap.add(new IndexMap<>(AVMediaType.AVMEDIA_TYPE_VIDEO, 2));
		mediaTypeMap.add(new IndexMap<>(AVMediaType.AVMEDIA_TYPE_AUDIO, 3));
		mediaTypeMap.add(new IndexMap<>(AVMediaType.AVMEDIA_TYPE_DATA, 4));
		mediaTypeMap.add(new IndexMap<>(AVMediaType.AVMEDIA_TYPE_SUBTITLE, 5));
		mediaTypeMap.add(new IndexMap<>(AVMediaType.AVMEDIA_TYPE_ATTACHMENT, 6));
		//mediaTypeMap.add(new IndexMap<>(AVMediaType.AVMEDIA_TYPE_NB, 7));
	}


	public static long AVClassCategoryToLong(AVClassCategory category)
	{
		return IndexMap.getIndexByValue(classCategoryMap, category);
	}

	public static AVClassCategory longToAVClassCategory(long value)
	{
		return IndexMap.getValueByIndex(classCategoryMap, value);
	}


	public static long AVRoundingToLong(AVRounding rounding)
	{
		return IndexMap.getIndexByValue(roundingMap, rounding);
	}

	public static AVRounding longToAVRounding(long value)
	{
		return IndexMap.getValueByIndex(roundingMap, value);
	}



	public static long AVMediaTypeToLong(AVMediaType mediaType)
	{
		return IndexMap.getIndexByValue(mediaTypeMap, mediaType);
	}

	public static AVMediaType longToAVMediaType(long value)
	{
		return IndexMap.getValueByIndex(mediaTypeMap, value);
	}
}
