#include <jni.h>
#include <string>

extern "C" {
#include <libavutil/mem.h>
}

#include "util.h"



JNI_FUNCTION(jlong, avutil_RawData, mallocNative)(JNIEnv*, jclass, jlong size)
{
    auto sizeT = static_cast<size_t>(size);
    auto mem = av_malloc(sizeT);
    return asjlong(mem);
}


JNI_FUNCTION(jlong, avutil_RawData, malloczNative)(JNIEnv*, jclass, jlong size)
{
    auto sizeT = static_cast<size_t>(size);
    auto mem = av_mallocz(sizeT);
    return asjlong(mem);
}


JNI_FUNCTION(void, avutil_RawData, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto pointerVoid = reinterpret_cast<void*>(pointer);
    av_free(pointerVoid);
}
