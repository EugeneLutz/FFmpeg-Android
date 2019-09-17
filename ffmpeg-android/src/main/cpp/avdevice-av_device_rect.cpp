#include <jni.h>
#include <string>

extern "C" {
#include <libavdevice/avdevice.h>
}

#include "util.h"
#include "avdevice-helper.h"


JNI_FUNCTION(jint, avdevice_AVDeviceRect, getXNative)(JNIEnv*, jclass, jlong pointer)
{
    auto rect = getDeviceRect(pointer);
    return tojint(rect->x);
}

JNI_FUNCTION(jint, avdevice_AVDeviceRect, getYNative)(JNIEnv*, jclass, jlong pointer)
{
    auto rect = getDeviceRect(pointer);
    return tojint(rect->y);
}

JNI_FUNCTION(jint, avdevice_AVDeviceRect, getWidthNative)(JNIEnv*, jclass, jlong pointer)
{
    auto rect = getDeviceRect(pointer);
    return tojint(rect->width);
}

JNI_FUNCTION(jint, avdevice_AVDeviceRect, getHeightNative)(JNIEnv*, jclass, jlong pointer)
{
    auto rect = getDeviceRect(pointer);
    return tojint(rect->height);
}


JNI_FUNCTION(void, avdevice_AVDeviceRect, setXNative)(JNIEnv*, jclass, jlong pointer, jint value)
{
    auto rect = getDeviceRect(pointer);
    rect->x = toint(value);
}

JNI_FUNCTION(void, avdevice_AVDeviceRect, setYNative)(JNIEnv*, jclass, jlong pointer, jint value)
{
    auto rect = getDeviceRect(pointer);
    rect->y = toint(value);
}

JNI_FUNCTION(void, avdevice_AVDeviceRect, setWidthNative)(JNIEnv*, jclass, jlong pointer, jint value)
{
    auto rect = getDeviceRect(pointer);
    rect->width = toint(value);
}

JNI_FUNCTION(void, avdevice_AVDeviceRect, setHeightNative)(JNIEnv*, jclass, jlong pointer, jint value)
{
    auto rect = getDeviceRect(pointer);
    rect->height = toint(value);
}
