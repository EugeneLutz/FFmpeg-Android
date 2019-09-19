#include <jni.h>
#include <string>

extern "C" {
#include <libavdevice/avdevice.h>
}

#include "util.hpp"
#include "avdevice-helper.hpp"


JNI_FUNCTION(jstring, avdevice_AVDeviceInfo, getNameNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto info = getDeviceInfo(pointer);
    return env->NewStringUTF(info->device_name);
}

JNI_FUNCTION(jstring, avdevice_AVDeviceInfo, getDescriptionNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto info = getDeviceInfo(pointer);
    return env->NewStringUTF(info->device_description);
}
