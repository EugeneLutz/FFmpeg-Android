#include <jni.h>
#include <string>

extern "C" {
#include <libavdevice/avdevice.h>
}

#include "util.h"
#include "opaque_iterator.h"
#include "avdevice-helper.h"


static AVFormatContext* getFormatContext(jlong pointer)
{
    return reinterpret_cast<AVFormatContext*>(pointer);
}

static AVInputFormat* getInputFormat(jlong pointer)
{
    return reinterpret_cast<AVInputFormat*>(pointer);
}

static AVOutputFormat* getOutputFormat(jlong pointer)
{
    return reinterpret_cast<AVOutputFormat*>(pointer);
}

JNI_FUNCTION(jint, avdevice_AVDevice, getVersionNative)(JNIEnv*, jclass)
{
    auto version = avdevice_version();
    return tojint(version);
}

JNI_FUNCTION(jstring, avdevice_AVDevice, getConfigurationNative)(JNIEnv* env, jclass)
{
    auto configuration = avdevice_configuration();
    return env->NewStringUTF(configuration);
}

JNI_FUNCTION(jstring, avdevice_AVDevice, getLicenseNative)(JNIEnv* env, jclass)
{
    auto license = avdevice_license();
    return env->NewStringUTF(license);
}

JNI_FUNCTION(void, avdevice_AVDevice, registerAllNative)(JNIEnv*, jclass)
{
    avdevice_register_all();
}

JNI_FUNCTION(jlong, avdevice_AVDevice, nextInputAudioDeviceNative)(JNIEnv*, jclass, jlong format)
{
    auto fmt = getInputFormat(format);
    auto next = av_input_audio_device_next(fmt);
    return asjlong(next);
}

JNI_FUNCTION(jlong, avdevice_AVDevice, nextInputVideoDeviceNative)(JNIEnv*, jclass, jlong format)
{
    auto fmt = getInputFormat(format);
    auto next = av_input_video_device_next(fmt);
    return asjlong(next);
}

JNI_FUNCTION(jlong, avdevice_AVDevice, nextOutputAudioDeviceNative)(JNIEnv*, jclass, jlong format)
{
    auto fmt = getOutputFormat(format);
    auto next = av_output_audio_device_next(fmt);
    return asjlong(next);
}

JNI_FUNCTION(jlong, avdevice_AVDevice, nextOutputVideoDeviceNative)(JNIEnv*, jclass, jlong format)
{
    auto fmt = getOutputFormat(format);
    auto next = av_output_video_device_next(fmt);
    return asjlong(next);
}

JNI_FUNCTION(jlong, avdevice_AVDevice, listDevicesNative)(JNIEnv*, jclass, jlong format)
{
    auto context = getFormatContext(format);
    AVDeviceInfoList* list = nullptr;
    auto result = avdevice_list_devices(context, &list);
    if (result < 0)
    {
        delete list;
        return 0;
    }

    return asjlong(list);
}



JNI_FUNCTION(jint, avdevice_AVDevice, appToDevNoneNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_app_to_dev_control_message(context, AVAppToDevMessageType::AV_APP_TO_DEV_NONE, nullptr, 0);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, appToDevWindowSizeNative)(JNIEnv*, jclass, jlong pointer, jlong rectPointer)
{
    auto context = getFormatContext(pointer);
    auto rect = getDeviceRect(rectPointer);
    auto rectSize = rect ? sizeof(typeof(rect)) : 0;
    auto result = avdevice_app_to_dev_control_message(context, AVAppToDevMessageType::AV_APP_TO_DEV_WINDOW_SIZE, rect, rectSize);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, appToDevWindowRepaintNative)(JNIEnv*, jclass, jlong pointer, jlong rectPointer)
{
    auto context = getFormatContext(pointer);
    auto rect = getDeviceRect(rectPointer);
    auto rectSize = rect ? sizeof(typeof(rect)) : 0;
    auto result = avdevice_app_to_dev_control_message(context, AVAppToDevMessageType::AV_APP_TO_DEV_WINDOW_REPAINT, rect, rectSize);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, appToDevPauseNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_app_to_dev_control_message(context, AVAppToDevMessageType::AV_APP_TO_DEV_PAUSE, nullptr, 0);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, appToDevPlayNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_app_to_dev_control_message(context, AVAppToDevMessageType::AV_APP_TO_DEV_PLAY, nullptr, 0);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, appToDevTogglePauseNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_app_to_dev_control_message(context, AVAppToDevMessageType::AV_APP_TO_DEV_TOGGLE_PAUSE, nullptr, 0);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, appToDevSetVolumeNative)(JNIEnv*, jclass, jlong pointer, jdouble volume)
{
    auto context = getFormatContext(pointer);
    auto volumeDouble = todouble(volume);
    auto volumeSize = sizeof(typeof(volumeDouble));
    auto result = avdevice_app_to_dev_control_message(context, AVAppToDevMessageType::AV_APP_TO_DEV_SET_VOLUME, &volumeDouble, volumeSize);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, appToDevMuteNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_app_to_dev_control_message(context, AVAppToDevMessageType::AV_APP_TO_DEV_MUTE, nullptr, 0);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, appToDevUnmuteNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_app_to_dev_control_message(context, AVAppToDevMessageType::AV_APP_TO_DEV_UNMUTE, nullptr, 0);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, appToDevToggleMuteNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_app_to_dev_control_message(context, AVAppToDevMessageType::AV_APP_TO_DEV_TOGGLE_MUTE, nullptr, 0);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, appToDevGetVolumeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_app_to_dev_control_message(context, AVAppToDevMessageType::AV_APP_TO_DEV_GET_VOLUME, nullptr, 0);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, appToDevGetMuteNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_app_to_dev_control_message(context, AVAppToDevMessageType::AV_APP_TO_DEV_GET_MUTE, nullptr, 0);
    return tojint(result);
}



JNI_FUNCTION(jint, avdevice_AVDevice, devToAppNoneNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_dev_to_app_control_message(context, AVDevToAppMessageType::AV_DEV_TO_APP_NONE, nullptr, 0);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, devToAppCreateWindowBufferNative)(JNIEnv*, jclass, jlong pointer, jlong rectPointer)
{
    auto context = getFormatContext(pointer);
    auto rect = getDeviceRect(rectPointer);
    auto rectSize = rect ? sizeof(typeof(rect)) : 0;
    auto result = avdevice_dev_to_app_control_message(context, AVDevToAppMessageType::AV_DEV_TO_APP_CREATE_WINDOW_BUFFER, rect, rectSize);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, devToAppPrepareWindowBufferNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_dev_to_app_control_message(context, AVDevToAppMessageType::AV_DEV_TO_APP_PREPARE_WINDOW_BUFFER, nullptr, 0);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, devToAppDisplayWindowBufferNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_dev_to_app_control_message(context, AVDevToAppMessageType::AV_DEV_TO_APP_DISPLAY_WINDOW_BUFFER, nullptr, 0);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, devToAppDestroyWindowBufferNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_dev_to_app_control_message(context, AVDevToAppMessageType::AV_DEV_TO_APP_DESTROY_WINDOW_BUFFER, nullptr, 0);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, devToAppBufferOverflowNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_dev_to_app_control_message(context, AVDevToAppMessageType::AV_DEV_TO_APP_BUFFER_OVERFLOW, nullptr, 0);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, devToAppBufferUnderflowNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getFormatContext(pointer);
    auto result = avdevice_dev_to_app_control_message(context, AVDevToAppMessageType::AV_DEV_TO_APP_BUFFER_UNDERFLOW, nullptr, 0);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, devToAppBufferReadableNative)(JNIEnv*, jclass, jlong pointer, jboolean jamountAvailable, jlong jamount)
{
    auto context = getFormatContext(pointer);
    auto amountAvailable = tobool(jamountAvailable);
    auto amount = tolong(jamount);
    auto amountPointer = amountAvailable ? &amount : nullptr;
    auto amountSize = amountAvailable ? sizeof(typeof(amount)) : 0;
    auto result = avdevice_dev_to_app_control_message(context, AVDevToAppMessageType::AV_DEV_TO_APP_BUFFER_READABLE, amountPointer, amountSize);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, devToAppBufferWritableNative)(JNIEnv*, jclass, jlong pointer, jboolean jamountAvailable, jlong jamount)
{
    auto context = getFormatContext(pointer);
    auto amountAvailable = tobool(jamountAvailable);
    auto amount = tolong(jamount);
    auto amountPointer = amountAvailable ? &amount : nullptr;
    auto amountSize = amountAvailable ? sizeof(typeof(amount)) : 0;
    auto result = avdevice_dev_to_app_control_message(context, AVDevToAppMessageType::AV_DEV_TO_APP_BUFFER_WRITABLE, amountPointer, amountSize);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, devToAppMuteStateChangedNative)(JNIEnv*, jclass, jlong pointer, jint jstate)
{
    auto context = getFormatContext(pointer);
    auto state = toint(jstate);
    auto stateSize = sizeof(typeof(state));
    auto result = avdevice_dev_to_app_control_message(context, AVDevToAppMessageType::AV_DEV_TO_APP_MUTE_STATE_CHANGED, &state, stateSize);
    return tojint(result);
}

JNI_FUNCTION(jint, avdevice_AVDevice, devToAppVolumeLevelChangedNative)(JNIEnv*, jclass, jlong pointer, jdouble jvolume)
{
    auto context = getFormatContext(pointer);
    auto volume = todouble(jvolume);
    auto volumeSize = sizeof(typeof(volume));
    auto result = avdevice_dev_to_app_control_message(context, AVDevToAppMessageType::AV_DEV_TO_APP_VOLUME_LEVEL_CHANGED, &volume, volumeSize);
    return tojint(result);
}

