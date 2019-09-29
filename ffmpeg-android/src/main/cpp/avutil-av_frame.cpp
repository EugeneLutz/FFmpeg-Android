#include <jni.h>
#include <string>

extern "C" {
#include <libavutil/frame.h>
}

#include "util.hpp"
#include "avutil-helper.hpp"


JNI_FUNCTION(jlong, avutil_AVFrame, createNative)(JNIEnv*, jclass)
{
    auto frame = av_frame_alloc();
    return asjlong(frame);
}

JNI_FUNCTION(void, avutil_AVFrame, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto frame = getFrame(pointer);
    av_frame_free(&frame);
}

JNI_FUNCTION(jobject, avutil_AVFrame, getDataNative)(JNIEnv* env, jclass, jlong pointer, jint planeIndex)
{
    auto frame = getFrame(pointer);
    auto index = toint(planeIndex);
    return env->NewDirectByteBuffer(frame->data[index], frame->linesize[index]);
}

JNI_FUNCTION(jint, avutil_AVFrame, getClassCategoryNative)(JNIEnv*, jclass, jlong pointer)
{
    auto category = getClass(pointer)->category;
    return static_cast<jint>(category);
}