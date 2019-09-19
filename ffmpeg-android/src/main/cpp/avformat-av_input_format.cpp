#include <jni.h>
#include <string>

extern "C" {
#include <libavformat/avformat.h>
}

#include "util.hpp"


/* * * * * * * * * * * * * * * *
 *                             *
 *        AVInputFormat        *
 *                             *
 * * * * * * * * * * * * * * * */

JNI_FUNCTION(jstring, avformat_AVInputFormat, getNameNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto demuxer = reinterpret_cast<AVInputFormat*>(pointer);
    if (demuxer == nullptr || demuxer->name == nullptr)
    {
        return nullptr;
    }

    return env->NewStringUTF(demuxer->name);
}

JNI_FUNCTION(jstring, avformat_AVInputFormat, getLongNameNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto demuxer = reinterpret_cast<AVInputFormat*>(pointer);
    if (demuxer == nullptr || demuxer->long_name == nullptr)
    {
        return nullptr;
    }

    return env->NewStringUTF(demuxer->long_name);
}

JNI_FUNCTION(jstring, avformat_AVInputFormat, getExtensionsNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto demuxer = reinterpret_cast<AVInputFormat*>(pointer);
    if (demuxer == nullptr || demuxer->extensions == nullptr)
    {
        return nullptr;
    }

    return env->NewStringUTF(demuxer->extensions);
}

JNI_FUNCTION(jint, avformat_AVInputFormat, getFlagsNative)(JNIEnv*, jclass, jlong pointer)
{
    auto demuxer = reinterpret_cast<AVInputFormat*>(pointer);
    if (demuxer == nullptr)
    {
        return 0;
    }

    return jint(demuxer->flags);
}

JNI_FUNCTION(void, avformat_AVInputFormat, setFlagsNative)(JNIEnv*, jclass, jlong pointer, jint flags)
{
    auto demuxer = reinterpret_cast<AVInputFormat*>(pointer);
    if (demuxer == nullptr)
    {
        return;
    }

    auto newFlags = static_cast<int>(flags);
    demuxer->flags = newFlags;
}

JNI_FUNCTION(jstring, avformat_AVInputFormat, getMimeTypesNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto demuxer = reinterpret_cast<AVInputFormat*>(pointer);
    if (demuxer == nullptr || demuxer->mime_type == nullptr)
    {
        return nullptr;
    }

    return env->NewStringUTF(demuxer->mime_type);
}



/* * * * * * * * * * * * * * * * * * * *
 *                                     *
 *        AVInputFormat.Iterator       *
 *                                     *
 * * * * * * * * * * * * * * * * * * * */

class AVInputFormatIterator
{
public:
    AVInputFormatIterator()
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

JNI_FUNCTION(jlong, avformat_AVInputFormat_00024Iterator, createNative)(JNIEnv*, jclass)
{
    auto iterator = new AVInputFormatIterator();
    auto pointer = jlong(iterator);
    return pointer;
}

JNI_FUNCTION(void, avformat_AVInputFormat_00024Iterator, releaseNative)(JNIEnv*, jclass, jlong pointer)
{
    auto iterator = reinterpret_cast<AVInputFormatIterator*>(pointer);
    delete iterator;
}

JNI_FUNCTION(jlong, avformat_AVInputFormat_00024Iterator, iterateNative)(JNIEnv*, jclass, jlong pointer)
{
    auto iterator = reinterpret_cast<AVInputFormatIterator*>(pointer);
    auto demuxer = av_demuxer_iterate(iterator->GetOpaquePtr());
    return jlong(demuxer);
}
