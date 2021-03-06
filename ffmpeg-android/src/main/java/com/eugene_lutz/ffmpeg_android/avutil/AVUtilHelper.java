package com.eugene_lutz.ffmpeg_android.avutil;

import com.eugene_lutz.ffmpeg_android.IndexMap;

import java.util.ArrayList;


public class AVUtilHelper
{
	private static ArrayList<IndexMap<AVClassCategory>> classCategoryMap;
	private static ArrayList<IndexMap<AVRounding>> roundingMap;
	private static ArrayList<IndexMap<AVMediaType>> mediaTypeMap;
	private static ArrayList<IndexMap<AVPixelFormat>> pixelFormatMap;
	private static ArrayList<IndexMap<AVColorPrimaries>> colorPrimariesMap;
	private static ArrayList<IndexMap<AVColorTransferCharacteristic>> colorTransferCharacteristicMap;
	private static ArrayList<IndexMap<AVColorSpace>> colorSpaceMap;
	private static ArrayList<IndexMap<AVColorRange>> colorRangeMap;
	private static ArrayList<IndexMap<AVChromaLocation>> chromaLocationMap;
	private static ArrayList<IndexMap<AVPictureType>> pictureTypeMap;
	private static ArrayList<IndexMap<AVFrameSideDataType>> frameSideDataTypeMap;
	private static ArrayList<IndexMap<AVSampleFormat>> sampleFormatMap;

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

		pixelFormatMap = new ArrayList<>();
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_NONE, 1));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV420P, 2));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUYV422, 3));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGB24, 4));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGR24, 5));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV422P, 6));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV444P, 7));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV410P, 8));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV411P, 9));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GRAY8, 10));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_MONOWHITE, 11));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_MONOBLACK, 12));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_PAL8, 13));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVJ420P, 14));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVJ422P, 15));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVJ444P, 16));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_UYVY422, 17));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_UYYVYY411, 18));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGR8, 19));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGR4, 20));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGR4_BYTE, 21));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGB8, 22));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGB4, 23));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGB4_BYTE, 24));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_NV12, 25));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_NV21, 26));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_ARGB, 27));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGBA, 28));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_ABGR, 29));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGRA, 30));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GRAY16BE, 31));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GRAY16LE, 32));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV440P, 33));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVJ440P, 34));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA420P, 35));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGB48BE, 36));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGB48LE, 37));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGB565BE, 38));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGB565LE, 39));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGB555BE, 40));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGB555LE, 41));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGR565BE, 42));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGR565LE, 43));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGR555BE, 44));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGR555LE, 45));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_VAAPI_VLD, 46));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV420P16LE, 47));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV420P16BE, 48));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV422P16LE, 49));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV422P16BE, 50));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV444P16LE, 51));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV444P16BE, 52));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_DXVA2_VLD, 53));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGB444LE, 54));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGB444BE, 55));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGR444LE, 56));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGR444BE, 57));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YA8_Y400A_GRAY8A, 58));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGR48BE, 59));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGR48LE, 60));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV420P9BE, 61));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV420P9LE, 62));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV420P10BE, 63));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV420P10LE, 64));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV422P10BE, 65));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV422P10LE, 66));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV444P9BE, 67));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV444P9LE, 68));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV444P10BE, 69));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV444P10LE, 70));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV422P9BE, 71));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV422P9LE, 72));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRP_GBR24P, 73));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRP9BE, 74));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRP9LE, 75));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRP10BE, 76));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRP10LE, 77));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRP16BE, 78));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRP16LE, 79));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA422P, 80));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA444P, 81));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA420P9BE, 82));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA420P9LE, 83));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA422P9BE, 84));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA422P9LE, 85));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA444P9BE, 86));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA444P9LE, 87));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA420P10BE, 88));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA420P10LE, 89));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA422P10BE, 90));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA422P10LE, 91));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA444P10BE, 92));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA444P10LE, 93));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA420P16BE, 94));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA420P16LE, 95));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA422P16BE, 96));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA422P16LE, 97));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA444P16BE, 98));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA444P16LE, 99));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_VDPAU, 100));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_XYZ12LE, 101));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_XYZ12BE, 102));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_NV16, 103));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_NV20LE, 104));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_NV20BE, 105));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGBA64BE, 106));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGBA64LE, 107));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGRA64BE, 108));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGRA64LE, 109));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YVYU422, 110));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YA16BE, 111));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YA16LE, 112));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRAP, 113));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRAP16BE, 114));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRAP16LE, 115));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_QSV, 116));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_MMAL, 117));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_D3D11VA_VLD, 118));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_CUDA, 119));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_0RGB, 120));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_RGB0, 121));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_0BGR, 122));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BGR0, 123));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV420P12BE, 124));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV420P12LE, 125));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV420P14BE, 126));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV420P14LE, 127));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV422P12BE, 128));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV422P12LE, 129));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV422P14BE, 130));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV422P14LE, 131));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV444P12BE, 132));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV444P12LE, 133));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV444P14BE, 134));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV444P14LE, 135));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRP12BE, 136));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRP12LE, 137));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRP14BE, 138));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRP14LE, 139));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVJ411P, 140));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BAYER_BGGR8, 141));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BAYER_RGGB8, 142));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BAYER_GBRG8, 143));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BAYER_GRBG8, 144));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BAYER_BGGR16LE, 145));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BAYER_BGGR16BE, 146));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BAYER_RGGB16LE, 147));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BAYER_RGGB16BE, 148));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BAYER_GBRG16LE, 149));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BAYER_GBRG16BE, 150));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BAYER_GRBG16LE, 151));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_BAYER_GRBG16BE, 152));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_XVMC, 153));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV440P10LE, 154));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV440P10BE, 155));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV440P12LE, 156));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUV440P12BE, 157));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_AYUV64LE, 158));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_AYUV64BE, 159));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_VIDEOTOOLBOX, 160));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_P010LE, 161));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_P010BE, 162));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRAP12BE, 163));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRAP12LE, 164));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRAP10BE, 165));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRAP10LE, 166));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_MEDIACODEC, 167));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GRAY12BE, 168));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GRAY12LE, 169));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GRAY10BE, 170));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GRAY10LE, 171));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_P016LE, 172));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_P016BE, 173));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_D3D11, 174));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GRAY9BE, 175));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GRAY9LE, 176));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRPF32BE, 177));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRPF32LE, 178));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRAPF32BE, 179));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GBRAPF32LE, 180));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_DRM_PRIME, 181));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_OPENCL, 182));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GRAY14BE, 183));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GRAY14LE, 184));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GRAYF32BE, 185));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_GRAYF32LE, 186));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA422P12BE, 187));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA422P12LE, 188));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA444P12BE, 189));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_YUVA444P12LE, 190));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_NV24, 191));
		pixelFormatMap.add(new IndexMap<>(AVPixelFormat.AV_PIX_FMT_NV42, 192));

		colorPrimariesMap = new ArrayList<>();
		colorPrimariesMap.add(new IndexMap<>(AVColorPrimaries.AVCOL_PRI_RESERVED0, 1));
		colorPrimariesMap.add(new IndexMap<>(AVColorPrimaries.AVCOL_PRI_BT709, 2));
		colorPrimariesMap.add(new IndexMap<>(AVColorPrimaries.AVCOL_PRI_UNSPECIFIED, 3));
		colorPrimariesMap.add(new IndexMap<>(AVColorPrimaries.AVCOL_PRI_RESERVED, 4));
		colorPrimariesMap.add(new IndexMap<>(AVColorPrimaries.AVCOL_PRI_BT470M, 5));
		colorPrimariesMap.add(new IndexMap<>(AVColorPrimaries.AVCOL_PRI_BT470BG, 6));
		colorPrimariesMap.add(new IndexMap<>(AVColorPrimaries.AVCOL_PRI_SMPTE170M, 7));
		colorPrimariesMap.add(new IndexMap<>(AVColorPrimaries.AVCOL_PRI_SMPTE240M, 8));
		colorPrimariesMap.add(new IndexMap<>(AVColorPrimaries.AVCOL_PRI_FILM, 9));
		colorPrimariesMap.add(new IndexMap<>(AVColorPrimaries.AVCOL_PRI_BT2020, 10));
		colorPrimariesMap.add(new IndexMap<>(AVColorPrimaries.AVCOL_PRI_SMPTE428, 11));
		colorPrimariesMap.add(new IndexMap<>(AVColorPrimaries.AVCOL_PRI_SMPTE431, 12));
		colorPrimariesMap.add(new IndexMap<>(AVColorPrimaries.AVCOL_PRI_SMPTE432, 13));
		colorPrimariesMap.add(new IndexMap<>(AVColorPrimaries.AVCOL_PRI_JEDEC_P22, 14));

		colorTransferCharacteristicMap = new ArrayList<>();
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_RESERVED0, 1));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_BT709, 2));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_UNSPECIFIED, 3));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_RESERVED, 4));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_GAMMA22, 5));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_GAMMA28, 6));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_SMPTE170M, 7));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_SMPTE240M, 8));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_LINEAR, 9));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_LOG, 10));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_LOG_SQRT, 11));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_IEC61966_2_4, 12));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_BT1361_ECG, 13));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_IEC61966_2_1, 14));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_BT2020_10, 15));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_BT2020_12, 16));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_SMPTE2084, 17));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_SMPTE428, 18));
		colorTransferCharacteristicMap.add(new IndexMap<>(AVColorTransferCharacteristic.AVCOL_TRC_ARIB_STD_B67, 19));

		colorSpaceMap = new ArrayList<>();
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_RGB, 1));
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_BT709, 2));
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_UNSPECIFIED, 3));
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_RESERVED, 4));
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_FCC, 5));
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_BT470BG, 6));
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_SMPTE170M, 7));
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_SMPTE240M, 8));
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_YCGCO_YCOCG, 9));
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_BT2020_NCL, 10));
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_BT2020_CL, 11));
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_SMPTE2085, 12));
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_CHROMA_DERIVED_NCL, 13));
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_CHROMA_DERIVED_CL, 14));
		colorSpaceMap.add(new IndexMap<>(AVColorSpace.AVCOL_SPC_ICTCP, 15));

		colorRangeMap = new ArrayList<>();
		colorRangeMap.add(new IndexMap<>(AVColorRange.AVCOL_RANGE_UNSPECIFIED, 1));
		colorRangeMap.add(new IndexMap<>(AVColorRange.AVCOL_RANGE_MPEG, 2));
		colorRangeMap.add(new IndexMap<>(AVColorRange.AVCOL_RANGE_JPEG, 3));

		chromaLocationMap = new ArrayList<>();
		chromaLocationMap.add(new IndexMap<>(AVChromaLocation.AVCHROMA_LOC_UNSPECIFIED, 1));
		chromaLocationMap.add(new IndexMap<>(AVChromaLocation.AVCHROMA_LOC_LEFT, 2));
		chromaLocationMap.add(new IndexMap<>(AVChromaLocation.AVCHROMA_LOC_CENTER, 3));
		chromaLocationMap.add(new IndexMap<>(AVChromaLocation.AVCHROMA_LOC_TOPLEFT, 4));
		chromaLocationMap.add(new IndexMap<>(AVChromaLocation.AVCHROMA_LOC_TOP, 5));
		chromaLocationMap.add(new IndexMap<>(AVChromaLocation.AVCHROMA_LOC_BOTTOMLEFT, 6));
		chromaLocationMap.add(new IndexMap<>(AVChromaLocation.AVCHROMA_LOC_BOTTOM, 7));

		pictureTypeMap = new ArrayList<>();
		pictureTypeMap.add(new IndexMap<>(AVPictureType.AV_PICTURE_TYPE_NONE, 1));
		pictureTypeMap.add(new IndexMap<>(AVPictureType.AV_PICTURE_TYPE_I, 2));
		pictureTypeMap.add(new IndexMap<>(AVPictureType.AV_PICTURE_TYPE_P, 3));
		pictureTypeMap.add(new IndexMap<>(AVPictureType.AV_PICTURE_TYPE_B, 4));
		pictureTypeMap.add(new IndexMap<>(AVPictureType.AV_PICTURE_TYPE_S, 5));
		pictureTypeMap.add(new IndexMap<>(AVPictureType.AV_PICTURE_TYPE_SI, 6));
		pictureTypeMap.add(new IndexMap<>(AVPictureType.AV_PICTURE_TYPE_SP, 7));
		pictureTypeMap.add(new IndexMap<>(AVPictureType.AV_PICTURE_TYPE_BI, 8));

		frameSideDataTypeMap = new ArrayList<>();
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_PANSCAN, 1));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_A53_CC, 2));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_STEREO3D, 3));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_MATRIXENCODING, 4));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_DOWNMIX_INFO, 5));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_REPLAYGAIN, 6));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_DISPLAYMATRIX, 7));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_AFD, 8));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_MOTION_VECTORS, 9));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_SKIP_SAMPLES, 10));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_AUDIO_SERVICE_TYPE, 11));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_MASTERING_DISPLAY_METADATA, 12));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_GOP_TIMECODE, 13));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_SPHERICAL, 14));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_CONTENT_LIGHT_LEVEL, 15));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_ICC_PROFILE, 16));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_QP_TABLE_PROPERTIES, 17));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_QP_TABLE_DATA, 18));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_S12M_TIMECODE, 19));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_DYNAMIC_HDR_PLUS, 20));
		frameSideDataTypeMap.add(new IndexMap<>(AVFrameSideDataType.AV_FRAME_DATA_REGIONS_OF_INTEREST, 21));

		sampleFormatMap = new ArrayList<>();
		sampleFormatMap.add(new IndexMap<>(AVSampleFormat.AV_SAMPLE_FMT_NONE, 1));
		sampleFormatMap.add(new IndexMap<>(AVSampleFormat.AV_SAMPLE_FMT_U8, 2));
		sampleFormatMap.add(new IndexMap<>(AVSampleFormat.AV_SAMPLE_FMT_S16, 3));
		sampleFormatMap.add(new IndexMap<>(AVSampleFormat.AV_SAMPLE_FMT_S32, 4));
		sampleFormatMap.add(new IndexMap<>(AVSampleFormat.AV_SAMPLE_FMT_FLT, 5));
		sampleFormatMap.add(new IndexMap<>(AVSampleFormat.AV_SAMPLE_FMT_DBL, 6));
		sampleFormatMap.add(new IndexMap<>(AVSampleFormat.AV_SAMPLE_FMT_U8P, 7));
		sampleFormatMap.add(new IndexMap<>(AVSampleFormat.AV_SAMPLE_FMT_S16P, 8));
		sampleFormatMap.add(new IndexMap<>(AVSampleFormat.AV_SAMPLE_FMT_S32P, 9));
		sampleFormatMap.add(new IndexMap<>(AVSampleFormat.AV_SAMPLE_FMT_FLTP, 10));
		sampleFormatMap.add(new IndexMap<>(AVSampleFormat.AV_SAMPLE_FMT_DBLP, 11));
		sampleFormatMap.add(new IndexMap<>(AVSampleFormat.AV_SAMPLE_FMT_S64, 12));
		sampleFormatMap.add(new IndexMap<>(AVSampleFormat.AV_SAMPLE_FMT_S64P, 13));
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



	public static long AVPixelFormatToLong(AVPixelFormat pixelFormat)
	{
		return IndexMap.getIndexByValue(pixelFormatMap, pixelFormat);
	}

	public static AVPixelFormat longToAVPixelFormat(long value)
	{
		return IndexMap.getValueByIndex(pixelFormatMap, value);
	}



	public static long AVColorPrimariesToLong(AVColorPrimaries colorPrimaries)
	{
		return IndexMap.getIndexByValue(colorPrimariesMap, colorPrimaries);
	}

	public static AVColorPrimaries longToAVColorPrimaries(long value)
	{
		return IndexMap.getValueByIndex(colorPrimariesMap, value);
	}



	public static long AVColorTransferCharacteristicToLong(AVColorTransferCharacteristic colorTransferCharacteristic)
	{
		return IndexMap.getIndexByValue(colorTransferCharacteristicMap, colorTransferCharacteristic);
	}

	public static AVColorTransferCharacteristic longToAVColorTransferCharacteristic(long value)
	{
		return IndexMap.getValueByIndex(colorTransferCharacteristicMap, value);
	}



	public static long AVColorSpaceToLong(AVColorSpace colorSpace)
	{
		return IndexMap.getIndexByValue(colorSpaceMap, colorSpace);
	}

	public static AVColorSpace longToAVColorSpace(long value)
	{
		return IndexMap.getValueByIndex(colorSpaceMap, value);
	}



	public static long AVColorRangeToLong(AVColorRange colorRange)
	{
		return IndexMap.getIndexByValue(colorRangeMap, colorRange);
	}

	public static AVColorRange longToAVColorRange(long value)
	{
		return IndexMap.getValueByIndex(colorRangeMap, value);
	}



	public static long AVChromaLocationToLong(AVChromaLocation chromaLocation)
	{
		return IndexMap.getIndexByValue(chromaLocationMap, chromaLocation);
	}

	public static AVChromaLocation longToAVChromaLocation(long value)
	{
		return IndexMap.getValueByIndex(chromaLocationMap, value);
	}



	public static long AVPictureTypeToLong(AVPictureType pictureType)
	{
		return IndexMap.getIndexByValue(AVUtilHelper.pictureTypeMap, pictureType);
	}

	public static AVPictureType longToAVPictureType(long value)
	{
		return IndexMap.getValueByIndex(pictureTypeMap, value);
	}



	public static long AVFrameSideDataTypeToLong(AVFrameSideDataType frameSideDataType)
	{
		return IndexMap.getIndexByValue(AVUtilHelper.frameSideDataTypeMap, frameSideDataType);
	}

	public static AVFrameSideDataType longToAVFrameSideDataType(long value)
	{
		return IndexMap.getValueByIndex(frameSideDataTypeMap, value);
	}


	public static long AVSampleFormatToLong(AVSampleFormat sampleFormat)
	{
		return IndexMap.getIndexByValue(AVUtilHelper.sampleFormatMap, sampleFormat);
	}

	public static AVSampleFormat longToAVSampleFormat(long value)
	{
		return IndexMap.getValueByIndex(sampleFormatMap, value);
	}
}
