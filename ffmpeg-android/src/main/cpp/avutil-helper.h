#ifndef HELLOFFMPEG_AVUTIL_HELPER_H
#define HELLOFFMPEG_AVUTIL_HELPER_H

extern "C" {
#include <libavutil/frame.h>
#include <libavutil/avutil.h>
#include <libavutil/mathematics.h>
#include <libavutil/dict.h>
}

#include "IndexMap.h"

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

long AVRoundingToLong(AVRounding rounding);
AVRounding longToAVRounding(long value);

long AVMediaTypeToLong(AVMediaType mediaType);
AVMediaType longToAVMediaType(long value);

#endif //HELLOFFMPEG_AVUTIL_HELPER_H
