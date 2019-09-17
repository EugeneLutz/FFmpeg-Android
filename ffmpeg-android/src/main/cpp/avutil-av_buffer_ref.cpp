#include <jni.h>
#include <string>

extern "C" {
#include <libavutil/buffer.h>
#include <libavutil/mem.h>
}

#include "util.h"



JNI_FUNCTION(jlong, avutil_AVBufferRef, allocNative)(JNIEnv*, jclass, jint size)
{
    auto sizeInt = static_cast<int>(size);
    auto buffer = av_buffer_alloc(sizeInt);
    return reinterpret_cast<jlong>(buffer);
}

JNI_FUNCTION(jlong, avutil_AVBufferRef, alloczNative)(JNIEnv*, jclass, jint size)
{
    auto sizeInt = static_cast<int>(size);
    auto buffer = av_buffer_allocz(sizeInt);
    return reinterpret_cast<jlong>(buffer);
}

static AVBufferRef* getBufferRef(jlong pointer)
{
    return reinterpret_cast<AVBufferRef*>(pointer);
}

JNI_FUNCTION(void, avutil_AVBufferRef, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto buffer = getBufferRef(pointer);
    av_free(buffer);
}

JNI_FUNCTION(jlong, avutil_AVBufferRef, refNative)(JNIEnv*, jclass, jlong pointer)
{
    auto buffer = getBufferRef(pointer);
    auto ref = av_buffer_ref(buffer);
    return reinterpret_cast<jlong>(ref);
}

JNI_FUNCTION(void, avutil_AVBufferRef, unrefNative)(JNIEnv*, jclass, jlong pointer)
{
    auto buffer = getBufferRef(pointer);
    //av_free(buffer);
    av_buffer_unref(&buffer);
}

JNI_FUNCTION(jint, avutil_AVBufferRef, isWritableNative)(JNIEnv*, jclass, jlong pointer)
{
    auto bufferRef = getBufferRef(pointer);
    auto isWritable = av_buffer_is_writable(bufferRef);
    return static_cast<jint>(isWritable);
}

JNI_FUNCTION(jobject, avutil_AVBufferRef, getDataNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto bufferRef = getBufferRef(pointer);
    auto data = reinterpret_cast<void*>(bufferRef->data);
    auto size = static_cast<jlong>(bufferRef->size);
    return env->NewDirectByteBuffer(data, size);
}

JNI_FUNCTION(jlong, avutil_AVBufferRef, getDataPointerNative)(JNIEnv*, jclass, jlong pointer)
{
    auto bufferRef = getBufferRef(pointer);
    return reinterpret_cast<jlong>(bufferRef->data);
}

JNI_FUNCTION(jint, avutil_AVBufferRef, getDataSizeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto bufferRef = getBufferRef(pointer);
    return static_cast<jint>(bufferRef->size);
}

JNI_FUNCTION(jint, avutil_AVBufferRef, getRefCountNative)(JNIEnv*, jclass, jlong pointer)
{
    auto bufferRef = getBufferRef(pointer);
    auto count = av_buffer_get_ref_count(bufferRef);
    return static_cast<jint>(count);
}
