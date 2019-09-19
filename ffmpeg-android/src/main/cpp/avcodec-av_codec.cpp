#include <jni.h>
#include <string>

extern "C" {
#include <libavcodec/avcodec.h>
}

#include "util.h"
#include "avcodec-helper.h"
#include "avutil-helper.h"


JNI_FUNCTION(jlong, avcodec_AVCodec, findDecoderNative)(JNIEnv*, jclass, jlong id)
{
    auto idLong = tolong(id);
    auto codecId = longToAVCodecID(idLong);
    auto codec = avcodec_find_decoder(codecId);
    return asjlong(codec);
}


JNI_FUNCTION(jlong, avcodec_AVCodec, findDecoderByNameNative)(JNIEnv* env, jclass, jstring name)
{
    auto nameString = env->GetStringUTFChars(name, nullptr);
    auto codec = avcodec_find_decoder_by_name(nameString);
    env->ReleaseStringUTFChars(name, nameString);
    return asjlong(codec);
}


JNI_FUNCTION(jlong, avcodec_AVCodec, findEncoderNative)(JNIEnv*, jclass, jlong id)
{
    auto idLong = tolong(id);
    auto codecId = longToAVCodecID(idLong);
    auto codec = avcodec_find_encoder(codecId);
    return asjlong(codec);
}


JNI_FUNCTION(jlong, avcodec_AVCodec, findEncoderByNameNative)(JNIEnv* env, jclass, jstring name)
{
    auto nameString = env->GetStringUTFChars(name, nullptr);
    auto codec = avcodec_find_encoder_by_name(nameString);
    env->ReleaseStringUTFChars(name, nameString);
    return asjlong(codec);
}


JNI_FUNCTION(jstring, avcodec_AVCodec, getCodecNameNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto codec = getCodec(pointer);
    auto charName = codec->name;
    if (charName == nullptr)
    {
        return nullptr;
    }

    auto name = env->NewStringUTF(charName);
    return name;
}


JNI_FUNCTION(jstring, avcodec_AVCodec, getCodecDescriptionNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto codec = getCodec(pointer);
    auto charName = codec->long_name;
    if (charName == nullptr)
    {
        return nullptr;
    }

    auto name = env->NewStringUTF(charName);
    return name;
}


JNI_FUNCTION(jlong, avcodec_AVCodec, getCodecMediaTypeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto codec = getCodec(pointer);
    auto index = AVMediaTypeToLong(codec->type);
    return tojlong(index);
}


JNI_FUNCTION(jlong, avcodec_AVCodec, getCodecIdNative)(JNIEnv*, jclass, jlong pointer)
{
    auto codec = getCodec(pointer);
    auto index = AVCodecIDToLong(codec->id);
    return tojlong(index);
}


JNI_FUNCTION(jint, avcodec_AVCodec, getCodecCapabilitiesNative)(JNIEnv*, jclass, jlong pointer)
{
    auto codec = getCodec(pointer);
    return tojint(codec->capabilities);
}


JNI_FUNCTION(jint, avcodec_AVCodec, getNumSupportedFrameratesNative)(JNIEnv*, jclass, jlong pointer)
{
    auto codec = getCodec(pointer);
    auto numSupportedFramerates = 0;
    if (codec->supported_framerates == nullptr)
    {
        return 0;
    }

    while (codec->supported_framerates[numSupportedFramerates].den != 0 &&
           codec->supported_framerates[numSupportedFramerates].num != 0)
    {
        numSupportedFramerates++;
    }

    return tojint(numSupportedFramerates);
}


JNI_FUNCTION(jint, avcodec_AVCodec, getNumSupportedPixelFormatsNative)(JNIEnv*, jclass, jlong pointer)
{
    auto codec = getCodec(pointer);
    auto numSupportedPixelformats = 0;
    if (codec->pix_fmts == nullptr)
    {
        return 0;
    }

    while (codec->pix_fmts[numSupportedPixelformats] != -1)
    {
        numSupportedPixelformats++;
    }

    return tojint(numSupportedPixelformats);
}


JNI_FUNCTION(jlong, avcodec_AVCodec, getSupportedPixelFormatNative)(JNIEnv*, jclass, jlong pointer, jint jindex)
{
    auto codec = getCodec(pointer);
    auto index = tojint(jindex);
    auto format = codec->pix_fmts[index];
    auto formatIndex = AVPixelFormatToLong(format);

    return tojlong(formatIndex);
}


JNI_FUNCTION(jstring, avcodec_AVCodec, getCodecGroupNameNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto codec = getCodec(pointer);
    auto charName = codec->wrapper_name;
    if (charName == nullptr)
    {
        return nullptr;
    }

    auto name = env->NewStringUTF(charName);
    return name;
}
