#include <jni.h>
#include <string>

#include "util.h"
#include "avutil-helper.h"


static std::vector<IndexMap<AVRounding>> roundingMap;
static bool _roundingMapInitialized = false;
static void initializeRoundingMap()
{
    roundingMap.clear();
    roundingMap.emplace_back({ AV_ROUND_ZERO, 1});
    roundingMap.emplace_back({ AV_ROUND_INF, 2 });
    roundingMap.emplace_back({ AV_ROUND_DOWN, 3 });
    roundingMap.emplace_back({ AV_ROUND_UP, 4 });
    roundingMap.emplace_back({ AV_ROUND_NEAR_INF, 5 });
    roundingMap.emplace_back({ AV_ROUND_PASS_MINMAX, 6 });

    _roundingMapInitialized = true;
}

long AVRoundingToLong(AVRounding rounding)
{
    if (!_roundingMapInitialized)
    {
        initializeRoundingMap();
    }

    return IndexMap<AVRounding>::GetIndexByValue(roundingMap, rounding);
}

AVRounding longToAVRounding(long value)
{
    if (!_roundingMapInitialized)
    {
        initializeRoundingMap();
    }

    return IndexMap<AVRounding>::GetValueByIndex(roundingMap, value);
}



static std::vector<IndexMap<AVMediaType>> mediaTypeMap;
static bool _mediaTypeMapInitialized = false;
static void initializeMediaTypeMap()
{
    mediaTypeMap.clear();
    mediaTypeMap.emplace_back({ AVMEDIA_TYPE_UNKNOWN, 1 });
    mediaTypeMap.emplace_back({ AVMEDIA_TYPE_VIDEO, 2 });
    mediaTypeMap.emplace_back({ AVMEDIA_TYPE_AUDIO, 3 });
    mediaTypeMap.emplace_back({ AVMEDIA_TYPE_DATA, 4 });
    mediaTypeMap.emplace_back({ AVMEDIA_TYPE_SUBTITLE, 5 });
    mediaTypeMap.emplace_back({ AVMEDIA_TYPE_ATTACHMENT, 6 });
    mediaTypeMap.emplace_back({ AVMEDIA_TYPE_NB, 7 });

    _mediaTypeMapInitialized = true;
}

long AVMediaTypeToLong(AVMediaType mediaType)
{
    if (!_mediaTypeMapInitialized)
    {
        initializeMediaTypeMap();
    }

    return IndexMap<AVMediaType>::GetIndexByValue(mediaTypeMap, mediaType);
}

AVMediaType longToAVMediaType(long index)
{
    if (!_mediaTypeMapInitialized)
    {
        initializeMediaTypeMap();
    }

    return IndexMap<AVMediaType>::GetValueByIndex(mediaTypeMap, index);
}



static std::vector<IndexMap<AVPixelFormat>> pixelFormatMap;
static bool _pixelFormatInitialized = false;
static void initializePixelFormatMap()
{
    pixelFormatMap.clear();
    pixelFormatMap.emplace_back({ AV_PIX_FMT_NONE, 1 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV420P, 2 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUYV422, 3 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGB24, 4 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGR24, 5 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV422P, 6 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV444P, 7 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV410P, 8 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV411P, 9 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GRAY8, 10 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_MONOWHITE, 11 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_MONOBLACK, 12 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_PAL8, 13 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVJ420P, 14 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVJ422P, 15 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVJ444P, 16 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_UYVY422, 17 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_UYYVYY411, 18 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGR8, 19 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGR4, 20 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGR4_BYTE, 21 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGB8, 22 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGB4, 23 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGB4_BYTE, 24 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_NV12, 25 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_NV21, 26 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_ARGB, 27 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGBA, 28 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_ABGR, 29 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGRA, 30 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GRAY16BE, 31 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GRAY16LE, 32 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV440P, 33 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVJ440P, 34 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA420P, 35 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGB48BE, 36 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGB48LE, 37 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGB565BE, 38 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGB565LE, 39 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGB555BE, 40 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGB555LE, 41 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGR565BE, 42 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGR565LE, 43 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGR555BE, 44 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGR555LE, 45 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_VAAPI, 46 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV420P16LE, 47 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV420P16BE, 48 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV422P16LE, 49 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV422P16BE, 50 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV444P16LE, 51 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV444P16BE, 52 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_DXVA2_VLD, 53 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGB444LE, 54 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGB444BE, 55 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGR444LE, 56 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGR444BE, 57 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YA8, 58 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGR48BE, 59 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGR48LE, 60 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV420P9BE, 61 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV420P9LE, 62 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV420P10BE, 63 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV420P10LE, 64 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV422P10BE, 65 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV422P10LE, 66 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV444P9BE, 67 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV444P9LE, 68 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV444P10BE, 69 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV444P10LE, 70 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV422P9BE, 71 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV422P9LE, 72 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRP, 73 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRP9BE, 74 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRP9LE, 75 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRP10BE, 76 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRP10LE, 77 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRP16BE, 78 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRP16LE, 79 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA422P, 80 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA444P, 81 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA420P9BE, 82 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA420P9LE, 83 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA422P9BE, 84 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA422P9LE, 85 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA444P9BE, 86 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA444P9LE, 87 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA420P10BE, 88 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA420P10LE, 89 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA422P10BE, 90 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA422P10LE, 91 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA444P10BE, 92 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA444P10LE, 93 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA420P16BE, 94 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA420P16LE, 95 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA422P16BE, 96 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA422P16LE, 97 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA444P16BE, 98 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA444P16LE, 99 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_VDPAU, 100 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_XYZ12LE, 101 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_XYZ12BE, 102 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_NV16, 103 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_NV20LE, 104 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_NV20BE, 105 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGBA64BE, 106 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGBA64LE, 107 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGRA64BE, 108 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGRA64LE, 109 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YVYU422, 110 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YA16BE, 111 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YA16LE, 112 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRAP, 113 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRAP16BE, 114 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRAP16LE, 115 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_QSV, 116 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_MMAL, 117 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_D3D11VA_VLD, 118 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_CUDA, 119 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_0RGB, 120 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_RGB0, 121 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_0BGR, 122 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BGR0, 123 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV420P12BE, 124 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV420P12LE, 125 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV420P14BE, 126 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV420P14LE, 127 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV422P12BE, 128 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV422P12LE, 129 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV422P14BE, 130 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV422P14LE, 131 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV444P12BE, 132 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV444P12LE, 133 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV444P14BE, 134 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV444P14LE, 135 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRP12BE, 136 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRP12LE, 137 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRP14BE, 138 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRP14LE, 139 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVJ411P, 140 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BAYER_BGGR8, 141 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BAYER_RGGB8, 142 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BAYER_GBRG8, 143 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BAYER_GRBG8, 144 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BAYER_BGGR16LE, 145 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BAYER_BGGR16BE, 146 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BAYER_RGGB16LE, 147 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BAYER_RGGB16BE, 148 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BAYER_GBRG16LE, 149 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BAYER_GBRG16BE, 150 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BAYER_GRBG16LE, 151 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_BAYER_GRBG16BE, 152 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_XVMC, 153 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV440P10LE, 154 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV440P10BE, 155 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV440P12LE, 156 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUV440P12BE, 157 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_AYUV64LE, 158 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_AYUV64BE, 159 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_VIDEOTOOLBOX, 160 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_P010LE, 161 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_P010BE, 162 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRAP12BE, 163 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRAP12LE, 164 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRAP10BE, 165 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRAP10LE, 166 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_MEDIACODEC, 167 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GRAY12BE, 168 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GRAY12LE, 169 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GRAY10BE, 170 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GRAY10LE, 171 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_P016LE, 172 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_P016BE, 173 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_D3D11, 174 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GRAY9BE, 175 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GRAY9LE, 176 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRPF32BE, 177 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRPF32LE, 178 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRAPF32BE, 179 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GBRAPF32LE, 180 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_DRM_PRIME, 181 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_OPENCL, 182 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GRAY14BE, 183 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GRAY14LE, 184 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GRAYF32BE, 185 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_GRAYF32LE, 186 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA422P12BE, 187 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA422P12LE, 188 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA444P12BE, 189 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_YUVA444P12LE, 190 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_NV24, 191 });
    pixelFormatMap.emplace_back({ AV_PIX_FMT_NV42, 192 });

    _pixelFormatInitialized = true;
}

long AVPixelFormatToLong(AVPixelFormat pixelFormat)
{
    if (!_pixelFormatInitialized)
    {
        initializePixelFormatMap();
    }

    return IndexMap<AVPixelFormat>::GetIndexByValue(pixelFormatMap, pixelFormat);
}

AVPixelFormat longToAVPixelFormat(long value)
{
    if (!_pixelFormatInitialized)
    {
        initializePixelFormatMap();
    }

    return IndexMap<AVPixelFormat>::GetValueByIndex(pixelFormatMap, value);
}
