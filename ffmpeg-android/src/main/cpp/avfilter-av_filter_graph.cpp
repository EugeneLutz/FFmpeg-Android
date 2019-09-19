#include <jni.h>
#include <string>

extern "C" {
#include <libavfilter/avfilter.h>
}

#include "util.hpp"
#include "avfilter-helper.hpp"


JNI_FUNCTION(jlong, avfilter_AVFilterGraph, allocNative)(JNIEnv*, jclass)
{
    auto graph = avfilter_graph_alloc();
    return asjlong(graph);
}

JNI_FUNCTION(void, avfilter_AVFilterGraph, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto graph = getFilterGraph(pointer);
    avfilter_graph_free(&graph);
}

JNI_FUNCTION(jint, avfilter_AVFilterGraph, configNative)(JNIEnv*, jclass, jlong pointer)
{
    auto graph = getFilterGraph(pointer);
    auto result = avfilter_graph_config(graph, nullptr);
    return tojint(result);
}

JNI_FUNCTION(jint, avfilter_AVFilterGraph, parseNative)(JNIEnv* env, jclass,
        jlong pointer, jstring jfilters, jlong jinputsPointer, jlong joutputsPointer)
{
    auto graph = getFilterGraph(pointer);
    auto filters = env->GetStringUTFChars(jfilters, nullptr);
    auto inputs = getFilterInOut(jinputsPointer);
    auto outputs = getFilterInOut(joutputsPointer);

    auto result = avfilter_graph_parse(graph, filters, inputs, outputs, nullptr);

    env->ReleaseStringUTFChars(jfilters, filters);

    return tojint(result);
}

JNI_FUNCTION(jint, avfilter_AVFilterGraph, sendCommandNative)(JNIEnv* env, jclass,
        jlong pointer, jstring jtarget, jstring jcmd, jstring jarg, jlong jresPointer, jint jres_len, jint jflags)
{
    auto graph = getFilterGraph(pointer);
    auto target = env->GetStringUTFChars(jtarget, nullptr);
    auto cmd = env->GetStringUTFChars(jcmd, nullptr);
    auto arg = env->GetStringUTFChars(jarg, nullptr);
    auto res = getRawCharData(jresPointer);
    auto res_len = toint(jres_len);
    auto flags = toint(jflags);

    auto result = avfilter_graph_send_command(graph, target, cmd, arg, res, res_len, flags);

    env->ReleaseStringUTFChars(jarg, arg);
    env->ReleaseStringUTFChars(jcmd, cmd);
    env->ReleaseStringUTFChars(jtarget, target);

    return tojint(result);
}

JNI_FUNCTION(jint, avfilter_AVFilterGraph, queueCommandNative)(JNIEnv* env, jclass,
        jlong pointer, jstring jtarget, jstring jcmd, jstring jarg, jint jflags, jdouble jts)
{
    auto graph = getFilterGraph(pointer);
    auto target = env->GetStringUTFChars(jtarget, nullptr);
    auto cmd = env->GetStringUTFChars(jcmd, nullptr);
    auto arg = env->GetStringUTFChars(jarg, nullptr);
    auto flags = toint(jflags);
    auto ts = todouble(jts);

    auto result = avfilter_graph_queue_command(graph, target, cmd, arg, flags, ts);

    env->ReleaseStringUTFChars(jarg, arg);
    env->ReleaseStringUTFChars(jcmd, cmd);
    env->ReleaseStringUTFChars(jtarget, target);

    return tojint(result);
}

JNI_FUNCTION(jstring, avfilter_AVFilterGraph, dumpNative)(JNIEnv* env, jclass,
        jlong pointer, jstring joptions)
{
    auto graph = getFilterGraph(pointer);
    auto options = env->GetStringUTFChars(joptions, nullptr);

    auto result = avfilter_graph_dump(graph, options);

    env->ReleaseStringUTFChars(joptions, options);

    auto dumpString = env->NewStringUTF(result);
    av_free(result);
    return dumpString;
}

JNI_FUNCTION(jint, avfilter_AVFilterGraph, requestOldestNative)(JNIEnv*, jclass, jlong pointer)
{
    auto graph = getFilterGraph(pointer);

    auto result = avfilter_graph_request_oldest(graph);

    return tojint(result);
}

JNI_FUNCTION(jlong, avfilter_AVFilterGraph, getFilterNative)(JNIEnv* env, jclass, jlong pointer, jstring jname)
{
    auto graph = getFilterGraph(pointer);
    auto name = jname ? env->GetStringUTFChars(jname, nullptr) : nullptr;

    auto context = avfilter_graph_get_filter(graph, name);

    if (name)
    {
        env->ReleaseStringUTFChars(jname, name);
    }

    return asjlong(context);
}

JNI_FUNCTION(void, avfilter_AVFilterGraph, setAutoConvertNative)(JNIEnv*, jclass, jlong pointer, jint jflags)
{
    auto graph = getFilterGraph(pointer);
    auto flags = touint(jflags);

    avfilter_graph_set_auto_convert(graph, flags);
}
