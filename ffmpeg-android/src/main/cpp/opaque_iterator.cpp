#include <jni.h>
#include <string>
#include "util.hpp"
#include "opaque_iterator.hpp"

OpaqueIterator::OpaqueIterator()
{
    opaque = nullptr;
}

void** OpaqueIterator:: GetOpaquePtr()
{
    return &opaque;
}

OpaqueIterator* OpaqueIterator::from(jlong pointer)
{
    return reinterpret_cast<OpaqueIterator*>(pointer);
}


JNI_FUNCTION(jlong, OpaqueIterator, createNative)(JNIEnv*, jclass)
{
    auto iterator = new OpaqueIterator();
    auto pointer = jlong(iterator);
    return pointer;
}

JNI_FUNCTION(void, OpaqueIterator, releaseNative)(JNIEnv*, jclass, jlong pointer)
{
    auto iterator = reinterpret_cast<OpaqueIterator*>(pointer);
    delete iterator;
}