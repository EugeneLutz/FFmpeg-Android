#include <jni.h>
#include <string>

extern "C" {
#include <libavformat/avformat.h>
}

#include "util.hpp"


static AVRational* getRational(jlong pointer)
{
    return reinterpret_cast<AVRational*>(pointer);
}

JNI_FUNCTION(void, avutil_AVRational, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto rational = getRational(pointer);
    delete rational;
}

JNI_FUNCTION(jint, avutil_AVRational, getNumeratorNative)(JNIEnv*, jclass, jlong pointer)
{
    auto rational = getRational(pointer);
    return tojint(rational->num);
}

JNI_FUNCTION(jint, avutil_AVRational, getDenominatorNative)(JNIEnv*, jclass, jlong pointer)
{
    auto rational = getRational(pointer);
    return tojint(rational->den);
}
