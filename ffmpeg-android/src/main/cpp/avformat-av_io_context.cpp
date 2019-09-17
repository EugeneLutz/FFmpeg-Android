#include <jni.h>
#include <string>

extern "C" {
#include <libavformat/avio.h>
}

#include "util.h"
#include "cstruct_wrapper.h"
#include "avformat-helper.h"


JNI_FUNCTION(void, avformat_AVIOContext, openNative)(JNIEnv* env, jclass, jobject result, jstring path, jint flags)
{
    AVIOContext* ioContext = nullptr;
    auto pathString = env->GetStringUTFChars(path, nullptr);
    auto flagsInt = static_cast<int>(flags);
    auto openResult = avio_open(&ioContext, pathString, flagsInt);

    JavaClass jc(result, env);
    jc.SetBoolean(openResult >= 0, "succeeded");
    jc.SetInt(openResult, "code");
    jc.SetLong(aslong(ioContext), "pointer");

    env->ReleaseStringUTFChars(path, pathString);
}

JNI_FUNCTION(void, avformat_AVIOContext, closeNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto context = getIOContext(pointer);
    avio_close(context);
}
