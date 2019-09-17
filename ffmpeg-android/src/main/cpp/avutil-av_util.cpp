#include <jni.h>
#include <string>

extern "C" {
#include <libavutil/avutil.h>
}

#include "util.h"
#include "avutil-helper.h"


JNI_FUNCTION(jstring, avutil_AVUtil, getMediaTypeStringNative)(JNIEnv* env, jclass, jlong index)
{
    auto mediaType = longToAVMediaType(tolong(index));
    auto string = av_get_media_type_string(mediaType);
    return env->NewStringUTF(string);
}
