#include <jni.h>
#include <string>

extern "C" {
#include <libavformat/avformat.h>
}

#include "util.h"


JNI_FUNCTION(jlong, avformat_AVStream, getCodecParametersNative)(JNIEnv*, jclass, jlong pointer)
{
    auto stream = reinterpret_cast<AVStream*>(pointer);
    if (!stream)
    {
        return 0;
    }

    return jlong(stream->codecpar);
}


JNI_FUNCTION(jlong, avformat_AVStream, getTimeBasePointerNative)(JNIEnv*, jclass, jlong pointer)
{
    auto stream = reinterpret_cast<AVStream*>(pointer);
    if (!stream)
    {
        return 0;
    }

    auto avRationalPointer = &(stream->time_base);
    return jlong(avRationalPointer);
}