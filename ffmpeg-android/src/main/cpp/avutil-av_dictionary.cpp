#include <jni.h>
#include <string>

extern "C" {
#include <libavutil/dict.h>
}

#include "cstruct_wrapper.hpp"
#include "util.hpp"
#include "avutil-helper.hpp"


JNI_FUNCTION(jlong, avutil_AVDictionary, getNative)(JNIEnv* env, jclass,
        jlong pointer, jstring key, jlong dictEntryPrev, jint flags)
{
    auto dict = getDictionary(pointer);
    auto keyCString = JNIString(key, env);
    auto prev = getDictionaryEntry(dictEntryPrev);
    auto flagsInt = toint(flags);

    auto entry = av_dict_get(dict, keyCString.getData(), prev, flagsInt);

    return asjlong(entry);
}


JNI_FUNCTION(jint, avutil_AVDictionary, countNative)(JNIEnv*, jclass, jlong pointer)
{
    auto dict = getDictionary(pointer);
    auto result = av_dict_count(dict);

    return tojint(result);
}


JNI_FUNCTION(jint, avutil_AVDictionary, setNative)(JNIEnv* env,
        jobject obj, jstring key, jstring value, jint flags)
{
    auto wrapper = CStructWrapper(obj, env);
    auto keyString = JNIString(key, env);
    auto valueString = JNIString(value, env);
    auto flagsInt = toint(flags);

    auto pointer = wrapper.getPointer();
    auto dictionary = getDictionary(pointer);
    auto result = av_dict_set(&dictionary, keyString.getData(), valueString.getData(), flagsInt);
    wrapper.setPointer(dictionary);

    return tojint(result);
}


JNI_FUNCTION(jint, avutil_AVDictionary, setIntNative)(JNIEnv* env,
        jobject obj, jstring key, jlong value, jint flags)
{
    auto wrapper = CStructWrapper(obj, env);
    auto keyString = JNIString(key, env);
    auto valueInt = static_cast<int64_t>(value);
    auto flagsInt = toint(flags);

    auto pointer = wrapper.getPointer();
    auto dictionary = getDictionary(pointer);
    auto result = av_dict_set_int(&dictionary, keyString.getData(), valueInt, flagsInt);
    wrapper.setPointer(dictionary);

    return tojint(result);
}


JNI_FUNCTION(jint, avutil_AVDictionary, parseStringNative)(JNIEnv* env,
        jobject obj, jstring str, jstring keyValSep, jstring pairsSep, jint flags)
{
    auto wrapper = CStructWrapper(obj, env);
    auto strString = JNIString(str, env);
    auto keyValSepString = JNIString(keyValSep, env);
    auto pairsSepString = JNIString(pairsSep, env);
    auto flagsInt = toint(flags);

    auto pointer = wrapper.getPointer();
    auto dictionary = getDictionary(pointer);
    auto result = av_dict_parse_string(&dictionary, strString.getData(),
            keyValSepString.getData(), pairsSepString.getData(), flagsInt);
    wrapper.setPointer(dictionary);

    return tojint(result);
}


JNI_FUNCTION(jint, avutil_AVDictionary, copyNative)(JNIEnv* env, jobject obj, jlong src, jint flags)
{
    auto wrapper = CStructWrapper(obj, env);
    auto dstDict = getDictionary(wrapper.getPointer());
    auto srcDict = getDictionary(src);
    auto flagsInt = toint(flags);
    auto result = av_dict_copy(&dstDict, srcDict, flagsInt);
    wrapper.setPointer(dstDict);

    return tojint(result);
}


JNI_FUNCTION(void, avutil_AVDictionary, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    if (!pointer)
    {
        return;
    }

    auto dict = getDictionary(pointer);
    av_dict_free(&dict);
}


JNI_FUNCTION(void, avutil_AVDictionary, getStringNative)(JNIEnv* env, jclass,
        jlong pointer, jchar keyValSep, jchar pairsSep, jobject result)
{
    auto dict = getDictionary(pointer);
    auto key_val_sep = tochar(keyValSep);
    auto pairs_sep = tochar(pairsSep);
    auto c = JavaClass(result, env);
    char* buffer = nullptr;
    auto res = av_dict_get_string(dict, &buffer, key_val_sep, pairs_sep);

    c.SetInt(res, "code");
    c.SetString(buffer, "string");

    av_free(buffer);
}
