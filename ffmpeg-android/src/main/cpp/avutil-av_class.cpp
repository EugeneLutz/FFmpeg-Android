#include <jni.h>
#include <string>

extern "C" {
#include <libavutil/log.h>
}

#include "util.h"
#include "avutil-helper.h"


JNI_FUNCTION(jstring, avutil_AVClass, getClassNameNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto name = getClass(pointer)->class_name;
    return env->NewStringUTF(name);
}

JNI_FUNCTION(jint, avutil_AVClass, getClassCategoryNative)(JNIEnv*, jclass, jlong pointer)
{
    auto category = getClass(pointer)->category;
    return static_cast<jint>(category);
}