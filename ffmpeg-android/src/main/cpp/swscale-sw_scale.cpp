#include <jni.h>
#include <string>

extern "C" {
#include <libswscale/swscale.h>
}

#include "util.hpp"


JNI_FUNCTION(jint, swscale_SwScale, getVersionNative)(JNIEnv*, jclass)
{
    auto version = swscale_version();
    return tojint(version);
}

JNI_FUNCTION(jstring, swscale_SwScale, getConfigurationNative)(JNIEnv* env, jclass)
{
    auto configuration = swscale_configuration();
    return env->NewStringUTF(configuration);
}

JNI_FUNCTION(jstring, swscale_SwScale, getLicenseNative)(JNIEnv* env, jclass)
{
    auto license = swscale_license();
    return env->NewStringUTF(license);
}

JNI_FUNCTION(jlong, swscale_SwScale, getAVClassNative)(JNIEnv*, jclass)
{
    auto avclass = sws_get_class();
    return asjlong(avclass);
}
