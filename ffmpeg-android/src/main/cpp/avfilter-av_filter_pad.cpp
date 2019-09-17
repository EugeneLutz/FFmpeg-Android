#include <jni.h>
#include <string>

extern "C" {
#include <libavfilter/avfilter.h>
}

#include "util.h"
#include "avfilter-helper.h"


JNI_FUNCTION(jstring, avfilter_AVFilterPad, getNameNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto filterPad = getFilterPad(pointer);
    auto name = avfilter_pad_get_name(filterPad, 0);
    return env->NewStringUTF(name);
}

JNI_FUNCTION(jint, avfilter_AVFilterPad, getMediaTypeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto filterPad = getFilterPad(pointer);
    auto mediaType = avfilter_pad_get_type(filterPad, 0);
    return static_cast<jint>(mediaType);
}