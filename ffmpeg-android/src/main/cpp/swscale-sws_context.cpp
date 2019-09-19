#include <jni.h>
#include <string>

extern "C" {
#include <libswscale/swscale.h>
}

#include "util.hpp"


static SwsContext* getContext(jlong pointer)
{
    return reinterpret_cast<SwsContext*>(pointer);
}

static SwsFilter* getFilter(jlong pointer)
{
    return reinterpret_cast<SwsFilter*>(pointer);
}

JNI_FUNCTION(jlong, swscale_SwsContext, allocNative)(JNIEnv*, jclass)
{
    auto context = sws_alloc_context();
    return asjlong(context);
}

JNI_FUNCTION(void, swscale_SwsContext, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    sws_freeContext(context);
}

JNI_FUNCTION(jint, swscale_SwsContext, initNative)(JNIEnv*, jclass, jlong pointer, jlong sourceFilter, jlong destinationFilter)
{
    auto context = getContext(pointer);
    auto source = getFilter(sourceFilter);
    auto destination = getFilter(destinationFilter);
    return sws_init_context(context, source, destination);
}

JNI_FUNCTION(jint, swscale_SwsContext, scaleNative)(JNIEnv* env, jclass, jlong pointer, jobject jsliceArray, jobject jstrideArray, jint jsliceY, jint jsliceH, jobject joutSliceArray, jobject joutStrideArray)
{
    auto context = getContext(pointer);

    auto srcSlice = reinterpret_cast<uint8_t**>(env->GetDirectBufferAddress(jsliceArray));
    auto srcStride = reinterpret_cast<int*>(env->GetDirectBufferAddress(jstrideArray));
    int sliceY = toint(jsliceY);
    int sliceH = toint(jsliceH);
    auto dstSlice = reinterpret_cast<uint8_t**>(env->GetDirectBufferAddress(joutSliceArray));
    auto dstStride = reinterpret_cast<int*>(env->GetDirectBufferAddress(joutStrideArray));

    return sws_scale(context, srcSlice, srcStride, sliceY, sliceH, dstSlice, dstStride);
}
