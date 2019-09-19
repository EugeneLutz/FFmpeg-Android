#ifndef HELLOFFMPEG_AVFILTER_HELPER_HPP
#define HELLOFFMPEG_AVFILTER_HELPER_H

extern "C" {
#include <libavformat/avformat.h>
}

#include "IndexMap.hpp"

inline AVIOContext* getIOContext(jlong pointer)
{
    return reinterpret_cast<AVIOContext*>(pointer);
}

#endif //HELLOFFMPEG_AVFILTER_HELPER_HPP
