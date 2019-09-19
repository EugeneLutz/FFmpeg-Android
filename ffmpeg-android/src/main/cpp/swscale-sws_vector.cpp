#include <jni.h>
#include <string>

extern "C" {
#include <libswscale/swscale.h>
}

#include "util.hpp"
#include "opaque_iterator.hpp"


static SwsVector* getVector(jlong pointer)
{
    return reinterpret_cast<SwsVector*>(pointer);
}

JNI_FUNCTION(jlong, swscale_SwsVector, createNative)(JNIEnv*, jclass, jint length)
{
    auto vector = sws_allocVec(length);
    return asjlong(vector);
}

JNI_FUNCTION(void, swscale_SwsVector, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto vector = getVector(pointer);
    sws_freeVec(vector);
}

JNI_FUNCTION(jdoubleArray , swscale_SwsVector, getCoefficientsNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto vector = getVector(pointer);
    auto result = env->NewDoubleArray(vector->length);
    env->SetDoubleArrayRegion(result, 0, vector->length, vector->coeff);
    return result;
}

JNI_FUNCTION(jint, swscale_SwsVector, getLengthNative)(JNIEnv*, jclass, jlong pointer)
{
    auto vector = getVector(pointer);
    return vector->length;
}

JNI_FUNCTION(void, swscale_SwsVector, setCoefficientNative)(JNIEnv*, jclass, jlong pointer, jdouble value, jint index)
{
    auto vector = getVector(pointer);
    vector->coeff[index] = value;
}

JNI_FUNCTION(jlong, swscale_SwsVector, getGaussianVecNative)(JNIEnv*, jclass, jdouble variance, jdouble quality)
{
    auto vector = sws_getGaussianVec(variance, quality);
    return asjlong(vector);
}

JNI_FUNCTION(void, swscale_SwsVector, scaleNative)(JNIEnv*, jclass, jlong pointer, jdouble scalar)
{
    auto vector = getVector(pointer);
    sws_scaleVec(vector, scalar);
}

JNI_FUNCTION(void, swscale_SwsVector, normalizeNative)(JNIEnv*, jclass, jlong pointer, jdouble scalar)
{
    auto vector = getVector(pointer);
    sws_normalizeVec(vector, scalar);
}
