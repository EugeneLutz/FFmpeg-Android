#include <jni.h>
#include <string>

extern "C" {
#include <libavcodec/avcodec.h>
}

#include "util.h"
#include "avcodec-helper.h"
#include "avutil-helper.h"


JNI_FUNCTION(void, avcodec_AVCodecContext, free)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    avcodec_free_context(&context);
}


JNI_FUNCTION(jlong, avcodec_AVCodecContext, alloc3)(JNIEnv*, jclass, jlong codecPointer)
{
    auto codec = getCodec(codecPointer);
    auto context = avcodec_alloc_context3(codec);
    return asjlong(context);
}

JNI_FUNCTION(jint, avcodec_AVCodecContext, fillFromParametersNative)(JNIEnv*, jclass, jlong pointer, jlong parametersPointer)
{
    auto codecContext = getContext(pointer);
    auto codecParameters = getParameters(parametersPointer);
    auto result = avcodec_parameters_to_context(codecContext, codecParameters);
    return tojint(result);
}

JNI_FUNCTION(jint, avcodec_AVCodecContext, sendPacketNative)(JNIEnv*, jclass, jlong pointer, jlong packetPointer)
{
    auto context = getContext(pointer);
    auto packet = getPacket(packetPointer);
    auto result = avcodec_send_packet(context, packet);
    return tojint(result);
}

JNI_FUNCTION(jint, avcodec_AVCodecContext, sendFrameNative)(JNIEnv*, jclass, jlong pointer, jlong framePointer)
{
    auto context = getContext(pointer);
    auto frame = getFrame(framePointer);
    auto result = avcodec_send_frame(context, frame);
    return tojint(result);
}
