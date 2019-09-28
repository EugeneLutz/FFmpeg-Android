#ifndef HELLOFFMPEG_AVFORMAT_HELPER_HPP
#define HELLOFFMPEG_AVFORMAT_HELPER_HPP

extern "C" {
#include <libavformat/avformat.h>
}

#include "IndexMap.hpp"

inline AVIOContext* getIOContext(jlong pointer)
{
    return reinterpret_cast<AVIOContext*>(pointer);
}

#endif //HELLOFFMPEG_AVFORMAT_HELPER_HPP
