#ifndef HELLOFFMPEG_AVUTIL_HELPER_HPP
#define HELLOFFMPEG_AVUTIL_HELPER_HPP

extern "C" {
#include <libavutil/frame.h>
#include <libavutil/avutil.h>
#include <libavutil/mathematics.h>
#include <libavutil/dict.h>
}

#include "IndexMap.hpp"

inline AVFrame* getFrame(jlong pointer)
{
    return reinterpret_cast<AVFrame*>(pointer);
}

inline AVDictionary* getDictionary(jlong pointer)
{
    return reinterpret_cast<AVDictionary*>(pointer);
}

inline AVDictionaryEntry* getDictionaryEntry(jlong pointer)
{
    return reinterpret_cast<AVDictionaryEntry*>(pointer);
}

inline AVClass* getClass(jlong pointer)
{
    return reinterpret_cast<AVClass*>(pointer);
}

inline AVRational* getRational(jlong pointer)
{
    return reinterpret_cast<AVRational*>(pointer);
}

long AVRoundingToLong(AVRounding rounding);
AVRounding longToAVRounding(long index);

long AVMediaTypeToLong(AVMediaType mediaType);
AVMediaType longToAVMediaType(long index);

long AVPixelFormatToLong(AVPixelFormat pixelFormat);
AVPixelFormat longToAVPixelFormat(long index);

long AVColorPrimariesToLong(AVColorPrimaries colorPrimaries);
AVColorPrimaries longToAVColorPrimaries(long index);

long AVColorPrimariesToLong(AVColorPrimaries colorPrimaries);
AVColorPrimaries longToAVColorPrimaries(long index);

long AVColorTransferCharacteristicToLong(AVColorTransferCharacteristic characteristic);
AVColorTransferCharacteristic longToAVColorTransferCharacteristic(long index);

long AVColorSpaceToLong(AVColorSpace colorSpace);
AVColorSpace longToAVColorSpace(long index);

long AVColorRangeToLong(AVColorRange colorRange);
AVColorRange longToAVColorRange(long index);

long AVChromaLocationToLong(AVChromaLocation chromaLocation);
AVChromaLocation longToAVChromaLocation(long index);

long AVPictureTypeToLong(AVPictureType chromaLocation);
AVPictureType longToAVPictureType(long index);

long AVFrameSideDataTypeToLong(AVFrameSideDataType frameSideDataType);
AVFrameSideDataType longToAVFrameSideDataType(long index);

long AVSampleFormatToLong(AVSampleFormat sampleFormat);
AVSampleFormat longToAVSampleFormat(long index);

#endif //HELLOFFMPEG_AVUTIL_HELPER_HPP
