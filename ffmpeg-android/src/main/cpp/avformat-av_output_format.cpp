#include <jni.h>
#include <string>

extern "C" {
#include <libavformat/avformat.h>
}

#include "util.hpp"


/* * * * * * * * * * * * * * * *
 *                             *
 *        AVOutputFormat       *
 *                             *
 * * * * * * * * * * * * * * * */

JNI_FUNCTION(jstring, avformat_AVOutputFormat, getNameNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto demuxer = reinterpret_cast<AVOutputFormat*>(pointer);
    if (demuxer == nullptr || demuxer->name == nullptr)
    {
        return nullptr;
    }

    return env->NewStringUTF(demuxer->name);
}

JNI_FUNCTION(jstring, avformat_AVOutputFormat, getLongNameNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto demuxer = reinterpret_cast<AVOutputFormat*>(pointer);
    if (demuxer == nullptr || demuxer->long_name == nullptr)
    {
        return nullptr;
    }

    return env->NewStringUTF(demuxer->long_name);
}

JNI_FUNCTION(jstring, avformat_AVOutputFormat, getExtensionsNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto demuxer = reinterpret_cast<AVOutputFormat*>(pointer);
    if (demuxer == nullptr || demuxer->extensions == nullptr)
    {
        return nullptr;
    }

    return env->NewStringUTF(demuxer->extensions);
}

JNI_FUNCTION(jint, avformat_AVOutputFormat, getFlagsNative)(JNIEnv*, jclass, jlong pointer)
{
    auto demuxer = reinterpret_cast<AVOutputFormat*>(pointer);
    if (demuxer == nullptr)
    {
        return 0;
    }

    return jint(demuxer->flags);
}

JNI_FUNCTION(void, avformat_AVOutputFormat, setFlagsNative)(JNIEnv*, jclass, jlong pointer, jint flags)
{
    auto demuxer = reinterpret_cast<AVOutputFormat*>(pointer);
    if (demuxer == nullptr)
    {
        return;
    }

    auto newFlags = static_cast<int>(flags);
    demuxer->flags = newFlags;
}

JNI_FUNCTION(jstring, avformat_AVOutputFormat, getMimeTypesNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto demuxer = reinterpret_cast<AVOutputFormat*>(pointer);
    if (demuxer == nullptr || demuxer->mime_type == nullptr)
    {
        return nullptr;
    }

    return env->NewStringUTF(demuxer->mime_type);
}



/* * * * * * * * * * * * * * * * * * * * *
 *                                       *
 *        AVOutputFormat.Iterator        *
 *                                       *
 * * * * * * * * * * * * * * * * * * * * */

class AVOutputFormatIterator
{
public:
    AVOutputFormatIterator()
    {
        opaque = nullptr;
    }

    void** GetOpaquePtr()
    {
        return &opaque;
    }

private:
    void* opaque;
};

JNI_FUNCTION(jlong, avformat_AVOutputFormat_00024Iterator, createIteratorNative)(JNIEnv*, jclass)
{
    auto iterator = new AVOutputFormatIterator();
    auto pointer = jlong(iterator);
    return pointer;
}

JNI_FUNCTION(void, avformat_AVOutputFormat_00024Iterator, releaseIteratorNative)(JNIEnv*, jclass, jlong pointer)
{
    auto iterator = reinterpret_cast<AVOutputFormatIterator*>(pointer);
    delete iterator;
}

JNI_FUNCTION(jlong, avformat_AVOutputFormat_00024Iterator, iterateNative)(JNIEnv*, jclass, jlong pointer)
{
    auto iterator = reinterpret_cast<AVOutputFormatIterator*>(pointer);
    auto demuxer = av_muxer_iterate(iterator->GetOpaquePtr());
    return jlong(demuxer);
}
