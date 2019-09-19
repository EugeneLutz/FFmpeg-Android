#ifndef HELLOFFMPEG_AVDEVICE_HELPER_HPP
#define HELLOFFMPEG_AVDEVICE_HELPER_HPP

extern "C" {
#include <libavdevice/avdevice.h>
}

//#include "IndexMap.h"

inline AVDeviceRect* getDeviceRect(jlong pointer)
{
    return reinterpret_cast<AVDeviceRect*>(pointer);
}

inline AVDeviceInfo* getDeviceInfo(jlong pointer)
{
    return reinterpret_cast<AVDeviceInfo*>(pointer);
}

#endif //HELLOFFMPEG_AVDEVICE_HELPER_HPP
