#include <jni.h>
#include <string>

extern "C" {
#include <libavformat/avformat.h>
#include <libavcodec/avcodec.h>
}

#include "util.hpp"
#include "avutil-helper.hpp"
#include "avcodec-helper.hpp"


JNI_FUNCTION(jint, avcodec_AVCodecParameters, copyToNative)(JNIEnv*, jclass, jlong sourcePointer, jlong destPointer)
{
    auto sourceParameters = getParameters(sourcePointer);
    auto destParameters = getParameters(destPointer);
    auto copyResult = avcodec_parameters_copy(destParameters, sourceParameters);
    return jint(copyResult);
}

JNI_FUNCTION(jint, avcodec_AVCodecParameters, getCodecTypeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto codecParameters = reinterpret_cast<AVCodecParameters*>(pointer);
    if (!codecParameters)
    {
        return 0;
    }

    auto typeIndex = AVMediaTypeToLong(codecParameters->codec_type);
    return jint(typeIndex);
}

JNI_FUNCTION(void, avcodec_AVCodecParameters, setCodecTagNative)(JNIEnv*, jclass, jlong pointer, jint tag)
{
    auto codecParameters = getParameters(pointer);
    auto tagInt = static_cast<uint32_t>(tag);
    codecParameters->codec_tag = tagInt;
}

JNI_FUNCTION(jint, avcodec_AVCodecParameters, fillFromContextNative)(JNIEnv*, jclass, jlong pointer, jlong contextPointer)
{
    auto codecParameters = getParameters(pointer);
    auto codecContext = getContext(contextPointer);
    auto result = avcodec_parameters_from_context(codecParameters, codecContext);
    return tojint(result);
}
