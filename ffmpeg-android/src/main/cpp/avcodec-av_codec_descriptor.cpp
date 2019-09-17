#include <jni.h>
#include <string>

extern "C" {
#include <libavcodec/avcodec.h>
}

#include "util.h"
#include "avcodec-helper.h"
#include "avutil-helper.h"


static AVCodecDescriptor* getDescriptor(jlong pointer)
{
    return reinterpret_cast<AVCodecDescriptor*>(pointer);
}

JNI_FUNCTION(jlong, avcodec_AVCodecDescriptor, getIdNative)(JNIEnv*, jclass, jlong pointer)
{
    auto descriptor = getDescriptor(pointer);
    auto value = AVCodecIDToLong(descriptor->id);
    return jlong(value);
}

JNI_FUNCTION(jlong, avcodec_AVCodecDescriptor, getMediaTypeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto descriptor = getDescriptor(pointer);
    auto value = AVMediaTypeToLong(descriptor->type);
    return jlong(value);
}
