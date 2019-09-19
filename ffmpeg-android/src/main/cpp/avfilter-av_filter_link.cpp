#include <jni.h>
#include <string>

extern "C" {
#include <libavfilter/avfilter.h>
}

#include "util.hpp"
#include "avfilter-helper.hpp"



JNI_FUNCTION(void, avfilter_AVFilterLink, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto link = getFilterLink(pointer);
    avfilter_link_free(&link);
}

JNI_FUNCTION(jint, avfilter_AVFilterLink, insertFilterNative)(JNIEnv*, jclass,
        jlong pointer, jlong jfilterPointer, jlong jfiltSrcpadIdx, jlong jfiltDstpadIdx)
{
    auto link = getFilterLink(pointer);
    auto context = getFilterContext(jfilterPointer);
    auto filtSrcpadIdx = static_cast<unsigned int>(jfiltSrcpadIdx);
    auto filtDstpadIdx = static_cast<unsigned int>(jfiltDstpadIdx);

    auto result = avfilter_insert_filter(link, context, filtSrcpadIdx, filtDstpadIdx);
    return tojint(result);
}