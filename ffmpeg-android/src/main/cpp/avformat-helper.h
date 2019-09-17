#ifndef HELLOFFMPEG_AVFILTER_HELPER_H
#define HELLOFFMPEG_AVFILTER_HELPER_H

extern "C" {
#include <libavformat/avformat.h>
}

#include "IndexMap.h"

inline AVIOContext* getIOContext(jlong pointer)
{
    return reinterpret_cast<AVIOContext*>(pointer);
}

#endif //HELLOFFMPEG_AVFILTER_HELPER_H
