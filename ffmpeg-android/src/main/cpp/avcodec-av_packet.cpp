#include <jni.h>
#include <string>

extern "C" {
#include <libavcodec/avcodec.h>
}

#include "util.hpp"


JNI_FUNCTION(jlong, avcodec_AVPacket, createNative)(JNIEnv*, jclass)
{
    auto packet = av_packet_alloc();
    return jlong(packet);
}

JNI_FUNCTION(void, avcodec_AVPacket, releaseNative)(JNIEnv*, jclass, jlong pointer)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    av_packet_free(&packet);
}

JNI_FUNCTION(jint, avcodec_AVPacket, getStreamIndexNative)(JNIEnv*, jclass, jlong pointer)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    if (!packet)
    {
        return -1;
    }

    return jint(packet->stream_index);
}

JNI_FUNCTION(jlong, avcodec_AVPacket, getPresentationTimestampNative)(JNIEnv*, jclass, jlong pointer)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    if (!packet)
    {
        return -1;
    }

    return jlong(packet->pts);
}

JNI_FUNCTION(jlong, avcodec_AVPacket, getDecompressionTimestampNative)(JNIEnv*, jclass, jlong pointer)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    if (!packet)
    {
        return -1;
    }

    return jlong(packet->dts);
}

JNI_FUNCTION(jlong, avcodec_AVPacket, getDurationNative)(JNIEnv*, jclass, jlong pointer)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    if (!packet)
    {
        return -1;
    }

    return jlong(packet->duration);
}

JNI_FUNCTION(jlong, avcodec_AVPacket, getPositionNative)(JNIEnv*, jclass, jlong pointer)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    if (!packet)
    {
        return -1;
    }

    return jlong(packet->pos);
}

JNI_FUNCTION(void, avcodec_AVPacket, setStreamIndexNative)(JNIEnv*, jclass, jlong pointer, jint index)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    if (!packet)
    {
        return;
    }

    auto streamIndex = static_cast<int>(index);
    packet->stream_index = streamIndex;
}

JNI_FUNCTION(void, avcodec_AVPacket, setPresentationTimestampNative)(JNIEnv*, jclass, jlong pointer, jlong value)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    if (!packet)
    {
        return;
    }

    auto valueLong = static_cast<int>(value);
    packet->pts = valueLong;
}

JNI_FUNCTION(void, avcodec_AVPacket, setDecompressionTimestampNative)(JNIEnv*, jclass, jlong pointer, jlong value)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    if (!packet)
    {
        return;
    }

    auto valueLong = static_cast<int>(value);
    packet->dts = valueLong;
}

JNI_FUNCTION(void, avcodec_AVPacket, setDurationNative)(JNIEnv*, jclass, jlong pointer, jlong value)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    if (!packet)
    {
        return;
    }

    auto valueLong = static_cast<int>(value);
    packet->duration = valueLong;
}

JNI_FUNCTION(void, avcodec_AVPacket, setPositionNative)(JNIEnv*, jclass, jlong pointer, jlong value)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    if (!packet)
    {
        return;
    }

    auto valueLong = static_cast<int>(value);
    packet->pos = valueLong;
}

JNI_FUNCTION(jlong, avcodec_AVPacket, cloneNative)(JNIEnv*, jclass, jlong pointer)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    auto packetClone = av_packet_clone(packet);
    return asjlong(packetClone);
}

JNI_FUNCTION(void, avcodec_AVPacket, initNative)(JNIEnv*, jclass, jlong pointer)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    av_init_packet(packet);
}

JNI_FUNCTION(jint, avcodec_AVPacket, newPacketNative)(JNIEnv*, jclass, jlong pointer, jint size)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    auto sizeInt = toint(size);
    auto result = av_new_packet(packet, sizeInt);
    return tojint(result);
}

JNI_FUNCTION(void, avcodec_AVPacket, shrinkPacketNative)(JNIEnv*, jclass, jlong pointer, jint size)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    auto sizeInt = toint(size);
    av_shrink_packet(packet, sizeInt);
}

JNI_FUNCTION(jint, avcodec_AVPacket, growPacketNative)(JNIEnv*, jclass, jlong pointer, jint growBy)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    auto growByInt = toint(growBy);
    auto result = av_grow_packet(packet, growByInt);
    return tojint(result);
}

//int packetFromDataNative(long pointer, long dataPointer);
JNI_FUNCTION(jint, avcodec_AVPacket, packetFromDataNative)(JNIEnv*, jclass, jlong pointer, jlong dataPointer, jint size)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    auto data = reinterpret_cast<uint8_t*>(dataPointer);
    auto sizeInt = toint(size);
    auto result = av_packet_from_data(packet, data, sizeInt);
    return tojint(result);
}

JNI_FUNCTION(jint, avcodec_AVPacket, refNative)(JNIEnv*, jclass, jlong pointer, jlong srcPointer)
{
    auto dst = reinterpret_cast<AVPacket*>(pointer);
    auto src = reinterpret_cast<AVPacket*>(srcPointer);
    auto result = av_packet_ref(dst, src);
    return tojint(result);
}

JNI_FUNCTION(void, avcodec_AVPacket, unrefNative)(JNIEnv*, jclass, jlong pointer)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    av_packet_unref(packet);
}

JNI_FUNCTION(jint, avcodec_AVPacket, copyPropsNative)(JNIEnv*, jclass, jlong pointer, jlong srcPointer)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    auto src = reinterpret_cast<AVPacket*>(pointer);
    auto result = av_packet_copy_props(packet, src);
    return tojint(result);
}

JNI_FUNCTION(jint, avcodec_AVPacket, makeWritableNative)(JNIEnv*, jclass, jlong pointer)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    auto result = av_packet_make_writable(packet);
    return tojint(result);
}

JNI_FUNCTION(void, avcodec_AVPacket, rescaleTSNative)(JNIEnv*, jclass, jlong pointer, jlong tb_src, jlong tb_dst)
{
    auto packet = reinterpret_cast<AVPacket*>(pointer);
    auto src = reinterpret_cast<AVRational*>(tb_src);
    auto dst = reinterpret_cast<AVRational*>(tb_dst);
    av_packet_rescale_ts(packet, *src, *dst);
}
