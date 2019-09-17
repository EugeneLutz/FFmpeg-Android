#ifndef HELLOFFMPEG_AVFILTER_HELPER_H
#define HELLOFFMPEG_AVFILTER_HELPER_H

extern "C" {
#include <libavfilter/avfilter.h>
}

#include "IndexMap.h"

inline AVFilter* getFilter(jlong pointer)
{
    return reinterpret_cast<AVFilter*>(pointer);
}

inline AVFilterGraph* getFilterGraph(jlong pointer)
{
    return reinterpret_cast<AVFilterGraph*>(pointer);
}

inline AVFilterLink* getFilterLink(jlong pointer)
{
    return reinterpret_cast<AVFilterLink*>(pointer);
}

inline AVFilterPad* getFilterPad(jlong pointer)
{
    return reinterpret_cast<AVFilterPad*>(pointer);
}

inline AVFilterContext* getFilterContext(jlong pointer)
{
    return reinterpret_cast<AVFilterContext*>(pointer);
}

inline AVFilterInOut* getFilterInOut(jlong pointer)
{
    return reinterpret_cast<AVFilterInOut*>(pointer);
}

#endif //HELLOFFMPEG_AVFILTER_HELPER_H
