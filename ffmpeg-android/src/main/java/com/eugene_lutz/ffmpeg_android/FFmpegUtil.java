package com.eugene_lutz.ffmpeg_android;

import com.eugene_lutz.ffmpeg_android.avutil.AVClassCategory;
import com.eugene_lutz.ffmpeg_android.avutil.AVSampleFormat;
import com.eugene_lutz.ffmpeg_android.swresample.SwrDitherType;
import com.eugene_lutz.ffmpeg_android.swresample.SwrEngine;
import com.eugene_lutz.ffmpeg_android.swresample.SwrFilterType;

public class FFmpegUtil
{
	public static AVClassCategory intToClassCategory(int category)
	{
		switch (category)
		{
			case 0: return AVClassCategory.AV_CLASS_CATEGORY_NA;
			case 1: return AVClassCategory.AV_CLASS_CATEGORY_INPUT;
			case 2: return AVClassCategory.AV_CLASS_CATEGORY_OUTPUT;
			case 3: return AVClassCategory.AV_CLASS_CATEGORY_MUXER;
			case 4: return AVClassCategory.AV_CLASS_CATEGORY_DEMUXER;
			case 5: return AVClassCategory.AV_CLASS_CATEGORY_ENCODER;
			case 6: return AVClassCategory.AV_CLASS_CATEGORY_DECODER;
			case 7: return AVClassCategory.AV_CLASS_CATEGORY_FILTER;
			case 8: return AVClassCategory.AV_CLASS_CATEGORY_BITSTREAM_FILTER;
			case 9: return AVClassCategory.AV_CLASS_CATEGORY_SWSCALER;
			case 10: return AVClassCategory.AV_CLASS_CATEGORY_SWRESAMPLER;
			case 40: return AVClassCategory.AV_CLASS_CATEGORY_DEVICE_VIDEO_OUTPUT;
			case 41: return AVClassCategory.AV_CLASS_CATEGORY_DEVICE_VIDEO_INPUT;
			case 42: return AVClassCategory.AV_CLASS_CATEGORY_DEVICE_AUDIO_OUTPUT;
			case 43: return AVClassCategory.AV_CLASS_CATEGORY_DEVICE_AUDIO_INPUT;
			case 44: return AVClassCategory.AV_CLASS_CATEGORY_DEVICE_OUTPUT;
			case 45: return AVClassCategory.AV_CLASS_CATEGORY_DEVICE_INPUT;
			default: return AVClassCategory.AV_CLASS_CATEGORY_NB;
		}
	}



	public static SwrDitherType intToSwrDitherType(int type)
	{
		switch (type)
		{
			case 0: return SwrDitherType.SWR_DITHER_NONE;
			case 1: return SwrDitherType.SWR_DITHER_RECTANGULAR;
			case 2: return SwrDitherType.SWR_DITHER_TRIANGULAR;
			case 3: return SwrDitherType.SWR_DITHER_TRIANGULAR_HIGHPASS;
			default: return SwrDitherType.SWR_DITHER_NONE;
		}
	}

	public static int SwrDitherTypeToInt(SwrDitherType type)
	{
		switch (type)
		{
			case SWR_DITHER_NONE: return 0;
			case SWR_DITHER_RECTANGULAR: return 1;
			case SWR_DITHER_TRIANGULAR: return 2;
			case SWR_DITHER_TRIANGULAR_HIGHPASS: return 3;
			default: return 0;
		}
	}



	public static SwrEngine intToSwrEngine(int engine)
	{
		switch (engine)
		{
			case 0: return SwrEngine.SWR_ENGINE_SWR;
			case 1: return SwrEngine.SWR_ENGINE_SOXR;
			default: return SwrEngine.SWR_ENGINE_SWR;
		}
	}

	public static int SwrEngineToInt(SwrEngine engine)
	{
		switch (engine)
		{
			case SWR_ENGINE_SWR: return 0;
			case SWR_ENGINE_SOXR: return 1;
			default: return 0;
		}
	}



	public static SwrFilterType intToSwrFilterType(int type)
	{
		switch (type)
		{
			case 0:return SwrFilterType.SWR_FILTER_TYPE_CUBIC;
			case 1:return SwrFilterType.SWR_FILTER_TYPE_BLACKMAN_NUTTALL;
			case 2:return SwrFilterType.SWR_FILTER_TYPE_KAISER;
			default: return SwrFilterType.SWR_FILTER_TYPE_CUBIC;
		}
	}

	public static int SwrFilterTypeToInt(SwrFilterType type)
	{
		switch (type)
		{
			case SWR_FILTER_TYPE_CUBIC: return 0;
			case SWR_FILTER_TYPE_BLACKMAN_NUTTALL: return 1;
			case SWR_FILTER_TYPE_KAISER: return 2;
			default: return 0;
		}
	}



	public static AVSampleFormat intToAVSampleFormat(int format)
	{
		switch (format)
		{
			case -1: return AVSampleFormat.AV_SAMPLE_FMT_NONE;
			case 0: return AVSampleFormat.AV_SAMPLE_FMT_U8;
			case 1: return AVSampleFormat.AV_SAMPLE_FMT_S16;
			case 2: return AVSampleFormat.AV_SAMPLE_FMT_S32;
			case 3: return AVSampleFormat.AV_SAMPLE_FMT_FLT;
			case 4: return AVSampleFormat.AV_SAMPLE_FMT_DBL;
			case 5: return AVSampleFormat.AV_SAMPLE_FMT_U8P;
			case 6: return AVSampleFormat.AV_SAMPLE_FMT_S16P;
			case 7: return AVSampleFormat.AV_SAMPLE_FMT_S32P;
			case 8: return AVSampleFormat.AV_SAMPLE_FMT_FLTP;
			case 9: return AVSampleFormat.AV_SAMPLE_FMT_DBLP;
			case 10: return AVSampleFormat.AV_SAMPLE_FMT_S64;
			case 11: return AVSampleFormat.AV_SAMPLE_FMT_S64P;
			default: return AVSampleFormat.AV_SAMPLE_FMT_NONE;
		}
	}

	public static int AVSampleFormatToInt(AVSampleFormat format)
	{
		switch (format)
		{
			case AV_SAMPLE_FMT_NONE: return -1;
			case AV_SAMPLE_FMT_U8: return 0;
			case AV_SAMPLE_FMT_S16: return 1;
			case AV_SAMPLE_FMT_S32: return 2;
			case AV_SAMPLE_FMT_FLT: return 3;
			case AV_SAMPLE_FMT_DBL: return 4;
			case AV_SAMPLE_FMT_U8P: return 5;
			case AV_SAMPLE_FMT_S16P: return 6;
			case AV_SAMPLE_FMT_S32P: return 7;
			case AV_SAMPLE_FMT_FLTP: return 8;
			case AV_SAMPLE_FMT_DBLP: return 9;
			case AV_SAMPLE_FMT_S64: return 10;
			case AV_SAMPLE_FMT_S64P: return 11;
			default: return 0;
		}
	}
}
