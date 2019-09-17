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
