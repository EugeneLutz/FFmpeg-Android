#include <jni.h>
#include <string>
#include <libavutil/samplefmt.h>

extern "C" {
#include <libswresample/swresample.h>
}

#include "util.hpp"


static SwrContext* getContext(jlong pointer)
{
    return reinterpret_cast<SwrContext*>(pointer);
}

JNI_FUNCTION(jlong, swresample_SwrContext, getAVClassNative)(JNIEnv*, jclass)
{
    auto swrclass = swr_get_class();
    return asjlong(swrclass);
}

JNI_FUNCTION(jlong, swresample_SwrContext, createNative)(JNIEnv*, jclass)
{
    auto context = swr_alloc();
    return asjlong(context);
}

JNI_FUNCTION(jint, swresample_SwrContext, initNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto res = swr_init(context);
    return tojint(res);
}

JNI_FUNCTION(jint, swresample_SwrContext, isInitializedNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto res = swr_is_initialized(context);
    return tojint(res);
}

JNI_FUNCTION(void, swresample_SwrContext, setOptionsNative)
(JNIEnv*, jclass, jlong pointer, jlong outChannelLayout, jint outSampleFormat, jint outSampleRate,
        jlong inChannelLayout, jint inSampleFormat, jint inSampleRate)
{
    auto context = getContext(pointer);
    swr_alloc_set_opts(context,
            outChannelLayout, static_cast<AVSampleFormat>(outSampleFormat), outSampleRate,
            inChannelLayout, static_cast<AVSampleFormat>(inSampleFormat), inSampleRate,
            0, nullptr);
}

JNI_FUNCTION(void, swresample_SwrContext, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    swr_free(&context);
}

JNI_FUNCTION(void, swresample_SwrContext, closeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    swr_close(context);
}

JNI_FUNCTION(jlong, swresample_SwrContext, nextPtsNative)(JNIEnv*, jclass, jlong pointer, jlong pts)
{
    auto context = getContext(pointer);
    auto res = swr_next_pts(context, tolong(pts));
    return tojlong(res);
}

JNI_FUNCTION(jint, swresample_SwrContext, setCompensationNative)(JNIEnv*, jclass, jlong pointer, jint sampleDelta, jint compensationDistance)
{
    auto context = getContext(pointer);
    auto res = swr_set_compensation(context, toint(sampleDelta), toint(compensationDistance));
    return tojint(res);
}

JNI_FUNCTION(jint, swresample_SwrContext, convertFrameNative)(JNIEnv*, jclass, jlong pointer, jlong output, jlong input)
{
    auto context = getContext(pointer);
    auto out = reinterpret_cast<AVFrame*>(output);
    auto in = reinterpret_cast<AVFrame*>(input);
    auto res = swr_convert_frame(context, out, in);
    return tojint(res);
}

JNI_FUNCTION(jint, swresample_SwrContext, configFrameNative)(JNIEnv*, jclass, jlong pointer, jlong output, jlong input)
{
    auto context = getContext(pointer);
    auto out = reinterpret_cast<AVFrame*>(output);
    auto in = reinterpret_cast<AVFrame*>(input);
    auto res = swr_config_frame(context, out, in);
    return tojint(res);
}
