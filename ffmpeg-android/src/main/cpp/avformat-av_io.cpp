#include <jni.h>
#include <string>

extern "C" {
#include <libavformat/avformat.h>
#include <libavformat/avio.h>
}

#include "util.hpp"


/* * * * * * * * * * * * * * * * * * * *
 *                                     *
 *        AVIO.ProtocolIterator        *
 *                                     *
 * * * * * * * * * * * * * * * * * * * */

class AVIOProtocolIterator
{
public:
    AVIOProtocolIterator()
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

JNI_FUNCTION(jlong, avformat_AVIO_00024ProtocolIterator, createNative)(JNIEnv*, jclass)
{
    auto iterator = new AVIOProtocolIterator();
    auto pointer = jlong(iterator);
    return pointer;
}

JNI_FUNCTION(void, avformat_AVIO_00024ProtocolIterator, releaseNative)(JNIEnv*, jclass, jlong pointer)
{
    auto iterator = reinterpret_cast<AVIOProtocolIterator*>(pointer);
    delete iterator;
}

JNI_FUNCTION(jstring, avformat_AVIO_00024ProtocolIterator, iterateNative)(JNIEnv* env, jclass, jlong pointer, jint output)
{
    auto iterator = reinterpret_cast<AVIOProtocolIterator*>(pointer);
    auto outputInt = static_cast<int>(output);
    auto protocol = avio_enum_protocols(iterator->GetOpaquePtr(), outputInt);
    return env->NewStringUTF(protocol);
}
