#include <jni.h>
#include <string>

extern "C" {
#include <libavformat/avformat.h>
}

#include "util.hpp"
#include "avutil-helper.hpp"

JNI_FUNCTION(jlong, avutil_AVRational, createNative)(JNIEnv*, jclass, jint jnumerator, jint jdenominator)
{
    auto numerator = toint(jnumerator);
    auto denominator = toint(jdenominator);
    auto rational = new AVRational();
    /*rational->num = numerator;
    rational->den = denominator;*/
    *rational = av_make_q(numerator, denominator);
    return asjlong(rational);
}

JNI_FUNCTION(void, avutil_AVRational, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto rational = getRational(pointer);
    delete rational;
}

JNI_FUNCTION(jint, avutil_AVRational, compareNative)(JNIEnv*, jclass, jlong ja, jlong jb)
{
    auto a = getRational(ja);
    auto b = getRational(jb);
    auto result = av_cmp_q(*a, *b);
    return tojint(result);
}

JNI_FUNCTION(jdouble, avutil_AVRational, toDoubleNative)(JNIEnv*, jclass, jlong pointer)
{
    auto rational = getRational(pointer);
    auto result = av_q2d(*rational);
    return todouble(result);
}

JNI_FUNCTION(jlong, avutil_AVRational, multiplyNative)(JNIEnv*, jclass, jlong ja, jlong jb)
{
    auto a = getRational(ja);
    auto b = getRational(jb);
    auto result = av_mul_q(*a, *b);
    auto rational = new AVRational();
    *rational = result;
    return asjlong(rational);
}

JNI_FUNCTION(jlong, avutil_AVRational, divideNative)(JNIEnv*, jclass, jlong ja, jlong jb)
{
    auto a = getRational(ja);
    auto b = getRational(jb);
    auto result = av_div_q(*a, *b);
    auto rational = new AVRational();
    *rational = result;
    return asjlong(rational);
}

JNI_FUNCTION(jlong, avutil_AVRational, addNative)(JNIEnv*, jclass, jlong ja, jlong jb)
{
    auto a = getRational(ja);
    auto b = getRational(jb);
    auto result = av_add_q(*a, *b);
    auto rational = new AVRational();
    *rational = result;
    return asjlong(rational);
}

JNI_FUNCTION(jlong, avutil_AVRational, subtractNative)(JNIEnv*, jclass, jlong ja, jlong jb)
{
    auto a = getRational(ja);
    auto b = getRational(jb);
    auto result = av_sub_q(*a, *b);
    auto rational = new AVRational();
    *rational = result;
    return asjlong(rational);
}

JNI_FUNCTION(jint, avutil_AVRational, getNumeratorNative)(JNIEnv*, jclass, jlong pointer)
{
    auto rational = getRational(pointer);
    return tojint(rational->num);
}

JNI_FUNCTION(void, avutil_AVRational, setNumeratorNative)(JNIEnv*, jclass, jlong pointer, jint jvalue)
{
    auto rational = getRational(pointer);
    auto value = tojint(jvalue);
    rational->num = value;
}

JNI_FUNCTION(jint, avutil_AVRational, getDenominatorNative)(JNIEnv*, jclass, jlong pointer)
{
    auto rational = getRational(pointer);
    return tojint(rational->den);
}

JNI_FUNCTION(void, avutil_AVRational, setDenominatorNative)(JNIEnv*, jclass, jlong pointer, jint jvalue)
{
    auto rational = getRational(pointer);
    auto value = toint(jvalue);
    rational->den = value;
}
