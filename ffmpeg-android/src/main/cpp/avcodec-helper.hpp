#ifndef HELLOFFMPEG_AVCODEC_HELPER_HPP
#define HELLOFFMPEG_AVCODEC_HELPER_HPP

extern "C" {
#include <libavcodec/avcodec.h>
#include <libavutil/mathematics.h>
}

#include "IndexMap.hpp"

inline AVCodecContext* getContext(jlong pointer)
{
    return reinterpret_cast<AVCodecContext*>(pointer);
}

inline AVCodec* getCodec(jlong pointer)
{
    return reinterpret_cast<AVCodec*>(pointer);
}

inline AVCodecParameters* getParameters(jlong pointer)
{
    return reinterpret_cast<AVCodecParameters*>(pointer);
}

inline AVPacket* getPacket(jlong pointer)
{
    return reinterpret_cast<AVPacket*>(pointer);
}

long AVCodecIDToLong(AVCodecID codec);
AVCodecID longToAVCodecID(long value);

#endif //HELLOFFMPEG_AVCODEC_HELPER_HPP
