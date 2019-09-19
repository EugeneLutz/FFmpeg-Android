#include <jni.h>
#include <string>

extern "C" {
#include <libavfilter/avfilter.h>
}

#include "util.hpp"
#include "opaque_iterator.hpp"


JNI_FUNCTION(jint, avfilter_AVFilter, getVersionNative)(JNIEnv*, jclass)
{
    auto version = avfilter_version();
    return static_cast<jint>(version);
}

JNI_FUNCTION(jstring, avfilter_AVFilter, getConfigurationNative)(JNIEnv* env, jclass)
{
    auto configuration = avfilter_configuration();
    return env->NewStringUTF(configuration);
}

JNI_FUNCTION(jstring, avfilter_AVFilter, getLicenseNative)(JNIEnv* env, jclass)
{
    auto license = avfilter_license();
    return env->NewStringUTF(license);
}

JNI_FUNCTION(jlong, avfilter_AVFilter, iterateNative)(JNIEnv*, jclass, jlong iteratorPointer)
{
    auto iterator = OpaqueIterator::from(iteratorPointer);
    auto filter = av_filter_iterate(iterator->GetOpaquePtr());
    return asjlong(filter);
}

JNI_FUNCTION(jlong, avfilter_AVFilter, getByNameNative)(JNIEnv* env, jclass, jstring name)
{
    auto nameString = env->GetStringUTFChars(name, nullptr);
    auto filter = avfilter_get_by_name(nameString);
    env->ReleaseStringUTFChars(name, nameString);
    return asjlong(filter);
}

static AVFilter* getAVFilter(jlong pointer)
{
    return reinterpret_cast<AVFilter*>(pointer);
}

JNI_FUNCTION(jstring, avfilter_AVFilter, getNameNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto name = getAVFilter(pointer)->name;
    return env->NewStringUTF(name);
}

JNI_FUNCTION(jstring, avfilter_AVFilter, getDescriptionNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto description = getAVFilter(pointer)->description;
    return env->NewStringUTF(description);
}

JNI_FUNCTION(jint, avfilter_AVFilter, getNumInputFilterPadsNative)(JNIEnv*, jclass, jlong pointer)
{
    auto filterPads = getAVFilter(pointer)->inputs;
    auto num = avfilter_pad_count(filterPads);
    return static_cast<jint>(num);
}

/*JNI_FUNCTION(jlong, avfilter_AVFilter, getInputFilterPadNative)(JNIEnv*, jclass, jlong pointer, jint index)
{
    auto filterPads = getAVFilter(pointer)->inputs;
    auto filterPad = filterPads + index;//&(filterPads[index]);
    return reinterpret_cast<jlong>(filterPad);
}*/

JNI_FUNCTION(jint, avfilter_AVFilter, getNumOutputFilterPadsNative)(JNIEnv*, jclass, jlong pointer)
{
    auto filterPads = getAVFilter(pointer)->outputs;
    auto num = avfilter_pad_count(filterPads);
    return static_cast<jint>(num);
}

/*JNI_FUNCTION(jlong, avfilter_AVFilter, getOutputFilterPadNative)(JNIEnv*, jclass, jlong pointer, jint index)
{
    auto filterPads = getAVFilter(pointer)->outputs;
    auto filterPad = filterPads + index;//&(filterPads[index]);
    return reinterpret_cast<jlong>(filterPad);
}*/

JNI_FUNCTION(jint, avfilter_AVFilter, getFlagsNative)(JNIEnv*, jclass, jlong pointer)
{
    auto flags = getAVFilter(pointer)->flags;
    return static_cast<jint>(flags);
}
