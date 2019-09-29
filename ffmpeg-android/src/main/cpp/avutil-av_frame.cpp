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

JNI_FUNCTION(jint, avutil_AVFrame, getLineSizeNative)(JNIEnv*, jclass, jlong pointer, jint planeIndex)
{
    auto frame = getFrame(pointer);
    auto index = toint(planeIndex);
    return tojint(frame->linesize[index]);
}

JNI_FUNCTION(jint, avutil_AVFrame, getWidthNative)(JNIEnv*, jclass, jlong pointer)
{
    auto frame = getFrame(pointer);
    return tojint(frame->width);
}

JNI_FUNCTION(jint, avutil_AVFrame, getHeightNative)(JNIEnv*, jclass, jlong pointer)
{
    auto frame = getFrame(pointer);
    return tojint(frame->height);
}

JNI_FUNCTION(jint, avutil_AVFrame, getNumberOfSamplesNative)(JNIEnv*, jclass, jlong pointer)
{
    auto frame = getFrame(pointer);
    return tojint(frame->nb_samples);
}

JNI_FUNCTION(jlong, avutil_AVFrame, getPixelFormatNative)(JNIEnv*, jclass, jlong pointer)
{
    auto frame = getFrame(pointer);
    if (frame->format == -1 || frame->format >= AVPixelFormat::AV_PIX_FMT_NB)
    {
        return tojlong(AVPixelFormatToLong(AVPixelFormat::AV_PIX_FMT_NONE));
    }

    return tojlong(AVPixelFormatToLong(static_cast<AVPixelFormat>(frame->format)));
}

JNI_FUNCTION(jlong, avutil_AVFrame, getSampleFormatNative)(JNIEnv*, jclass, jlong pointer)
{
    auto frame = getFrame(pointer);
    if (frame->format == -1 || frame->format >= AVSampleFormat::AV_SAMPLE_FMT_NB)
    {
        return tojlong(AVSampleFormatToLong(AVSampleFormat::AV_SAMPLE_FMT_NONE));
    }

    return tojlong(AVSampleFormatToLong(static_cast<AVSampleFormat>(frame->format)));
}

JNI_FUNCTION(jint, avutil_AVFrame, getKeyFrameNative)(JNIEnv*, jclass, jlong pointer)
{
    auto frame = getFrame(pointer);
    return tojint(frame->key_frame);
}

JNI_FUNCTION(jlong, avutil_AVFrame, getPictureTypeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto frame = getFrame(pointer);
    return tojlong(AVPictureTypeToLong(frame->pict_type));
}
