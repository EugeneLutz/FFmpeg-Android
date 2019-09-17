#include <jni.h>
#include <string>

extern "C" {
#include <libavdevice/avdevice.h>
}

#include "util.h"
#include "opaque_iterator.h"


static AVDeviceInfoList* getDeviceInfoList(jlong pointer)
{
    return reinterpret_cast<AVDeviceInfoList*>(pointer);
}

JNI_FUNCTION(void, avdevice_AVDeviceInfoList, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto list = getDeviceInfoList(pointer);
    avdevice_free_list_devices(&list);
}

JNI_FUNCTION(jlong, avdevice_AVDeviceInfoList, getDeviceInfoNative)(JNIEnv*, jclass, jlong pointer, jint index)
{
    auto list = getDeviceInfoList(pointer);
    return asjlong(list->devices[index]);
}

JNI_FUNCTION(jint, avdevice_AVDeviceInfoList, getNumDevicesNative)(JNIEnv*, jclass, jlong pointer)
{
    auto list = getDeviceInfoList(pointer);
    return tojint(list->nb_devices);
}

JNI_FUNCTION(jint, avdevice_AVDeviceInfoList, getDefaultDeviceIndexNative)(JNIEnv*, jclass, jlong pointer)
{
    auto list = getDeviceInfoList(pointer);
    return tojint(list->default_device);
}
