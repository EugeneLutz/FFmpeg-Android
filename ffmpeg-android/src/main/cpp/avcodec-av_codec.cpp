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
    auto name = env->NewStringUTF(charName);
    return name;
}


JNI_FUNCTION(jstring, avcodec_AVCodec, getCodecDescriptionNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto codec = getCodec(pointer);
    auto charName = codec->long_name;
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
