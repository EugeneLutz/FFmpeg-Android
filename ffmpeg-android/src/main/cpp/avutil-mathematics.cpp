#include <jni.h>
#include <string>

extern "C" {
#include <libavutil/mathematics.h>
}

#include "util.h"


JNI_FUNCTION(jlong, avutil_Mathematics, rescaleQRNDNative)(JNIEnv*, jclass, jlong a, jlong bqPointer, jlong cqPointer, jint flags)
{
    auto aLong = static_cast<long>(a);
    auto bq = reinterpret_cast<AVRational*>(bqPointer);
    auto cq = reinterpret_cast<AVRational*>(cqPointer);
    auto flagsInt = static_cast<AVRounding>(flags);

    auto result = av_rescale_q_rnd(aLong, *bq, *cq, flagsInt);
    return jlong(result);
}

JNI_FUNCTION(jlong, avutil_Mathematics, rescaleQNative)(JNIEnv*, jclass, jlong a, jlong bqPointer, jlong cqPointer)
{
    auto aLong = static_cast<long>(a);
    auto bq = reinterpret_cast<AVRational*>(bqPointer);
    auto cq = reinterpret_cast<AVRational*>(cqPointer);

    auto result = av_rescale_q(aLong, *bq, *cq);
    return jlong(result);
}

JNI_FUNCTION(jlong, avutil_Mathematics, gcdNative)(JNIEnv*, jclass, jlong a, jlong b)
{
    auto result = av_gcd(a, b);
    return tojlong(result);
}

JNI_FUNCTION(jlong, avutil_Mathematics, rescaleNative)(JNIEnv*, jclass, jlong a, jlong b, jlong c)
{
    auto result = av_rescale(a, b, c);
    return tojlong(result);
}
