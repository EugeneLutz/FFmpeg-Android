#include <jni.h>
#include <string>

extern "C" {
#include <libswscale/swscale.h>
}

#include "util.hpp"
#include "opaque_iterator.hpp"


static SwsFilter* getFilter(jlong pointer)
{
    return reinterpret_cast<SwsFilter*>(pointer);
}

static SwsVector* getVector(jlong pointer)
{
    return reinterpret_cast<SwsVector*>(pointer);
}

JNI_FUNCTION(jlong, swscale_SwsFilter, createDefaultFilterNative)(JNIEnv*, jclass,
                                                                  jfloat lumaGBlur, jfloat chromaGBlur,
                                                                  jfloat lumaSharpen, jfloat chromaSharpen,
                                                                  jfloat chromaHShift, jfloat chromaVShift,
                                                                  jint verbose)
{
    auto filter = sws_getDefaultFilter(lumaGBlur, chromaGBlur,
                                       lumaSharpen, chromaSharpen,
                                       chromaHShift, chromaVShift,
                                       verbose);
    return asjlong(filter);
}

JNI_FUNCTION(void, swscale_SwsFilter, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto filter = getFilter(pointer);
    sws_freeFilter(filter);
}

JNI_FUNCTION(jlong, swscale_SwsFilter, getLumHNative)(JNIEnv*, jclass, jlong pointer)
{
    auto filter = getFilter(pointer);
    return asjlong(filter->lumH);
}

JNI_FUNCTION(jlong, swscale_SwsFilter, getLumVNative)(JNIEnv*, jclass, jlong pointer)
{
    auto filter = getFilter(pointer);
    return asjlong(filter->lumV);
}

JNI_FUNCTION(jlong, swscale_SwsFilter, getChrHNative)(JNIEnv*, jclass, jlong pointer)
{
    auto filter = getFilter(pointer);
    return asjlong(filter->chrH);
}

JNI_FUNCTION(jlong, swscale_SwsFilter, getChrVNative)(JNIEnv*, jclass, jlong pointer)
{
    auto filter = getFilter(pointer);
    return asjlong(filter->chrV);
}

JNI_FUNCTION(void, swscale_SwsFilter, setLumHNative)(JNIEnv*, jclass, jlong pointer, jlong value)
{
    auto filter = getFilter(pointer);
    auto vector = getVector(value);
    filter->lumH = vector;
}

JNI_FUNCTION(void, swscale_SwsFilter, setLumVNative)(JNIEnv*, jclass, jlong pointer, jlong value)
{
    auto filter = getFilter(pointer);
    auto vector = getVector(value);
    filter->lumV = vector;
}

JNI_FUNCTION(void, swscale_SwsFilter, setChrHNative)(JNIEnv*, jclass, jlong pointer, jlong value)
{
    auto filter = getFilter(pointer);
    auto vector = getVector(value);
    filter->chrH = vector;
}

JNI_FUNCTION(void, swscale_SwsFilter, setChrVNative)(JNIEnv*, jclass, jlong pointer, jlong value)
{
    auto filter = getFilter(pointer);
    auto vector = getVector(value);
    filter->chrV = vector;
}
