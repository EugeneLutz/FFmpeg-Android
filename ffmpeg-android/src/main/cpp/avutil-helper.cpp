#include <jni.h>
#include <string>

#include "util.h"
#include "avutil-helper.h"


static std::vector<IndexMap<AVRounding>> routingMap {
    { AV_ROUND_ZERO, 1},
    { AV_ROUND_INF, 2 },
    { AV_ROUND_DOWN, 3 },
    { AV_ROUND_UP, 4 },
    { AV_ROUND_NEAR_INF, 5 },
    { AV_ROUND_PASS_MINMAX, 6 }
};

long AVRoundingToLong(AVRounding rounding)
{
    return IndexMap<AVRounding>::GetIndexByValue(routingMap, rounding);
}

AVRounding longToAVRounding(long value)
{
    return IndexMap<AVRounding>::GetValueByIndex(routingMap, value);
}



static std::vector<IndexMap<AVMediaType>> mediaTypeMap {
        { AVMEDIA_TYPE_UNKNOWN, 1 },
        { AVMEDIA_TYPE_VIDEO, 2 },
        { AVMEDIA_TYPE_AUDIO, 3 },
        { AVMEDIA_TYPE_DATA, 4 },
        { AVMEDIA_TYPE_SUBTITLE, 5 },
        { AVMEDIA_TYPE_ATTACHMENT, 6 },
        { AVMEDIA_TYPE_NB, 7 }
};

long AVMediaTypeToLong(AVMediaType mediaType)
{
    return IndexMap<AVMediaType>::GetIndexByValue(mediaTypeMap, mediaType);
}

AVMediaType longToAVMediaType(long index)
{
    return IndexMap<AVMediaType>::GetValueByIndex(mediaTypeMap, index);
}
