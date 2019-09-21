#include <jni.h>
#include <string>

extern "C" {
#include <libavutil/avutil.h>
}

#include "util.hpp"
#include "avutil-helper.hpp"


JNI_FUNCTION(jlong, avutil_AVUtil, getVersionNative)(JNIEnv*, jclass)
{
    auto version = avutil_version();
    return tojlong(version);
}

JNI_FUNCTION(jstring, avutil_AVUtil, getVersionInfoNative)(JNIEnv* env, jclass)
{
    auto versionInfo = av_version_info();
    return env->NewStringUTF(versionInfo);
}

JNI_FUNCTION(jstring, avutil_AVUtil, getConfigurationNative)(JNIEnv* env, jclass)
{
    auto configuration = avutil_configuration();
    return env->NewStringUTF(configuration);
}

JNI_FUNCTION(jstring, avutil_AVUtil, getLicenseNative)(JNIEnv* env, jclass)
{
    auto license = avutil_license();
    return env->NewStringUTF(license);
}

JNI_FUNCTION(jstring, avutil_AVUtil, getMediaTypeStringNative)(JNIEnv* env, jclass, jlong index)
{
    auto mediaType = longToAVMediaType(tolong(index));
    auto string = av_get_media_type_string(mediaType);
    return env->NewStringUTF(string);
}
