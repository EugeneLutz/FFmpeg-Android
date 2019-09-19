#include <jni.h>
#include <string>

extern "C" {
#include <libavutil/dict.h>
}

#include "util.hpp"
#include "avutil-helper.hpp"


JNI_FUNCTION(jstring, avutil_AVDictionaryEntry, getKeyNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto key = getDictionaryEntry(pointer)->key;
    return env->NewStringUTF(key);
}


JNI_FUNCTION(jstring, avutil_AVDictionaryEntry, getValueNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto value = getDictionaryEntry(pointer)->value;
    return env->NewStringUTF(value);
}