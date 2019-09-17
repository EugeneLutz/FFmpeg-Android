#include <jni.h>
#include <string>

extern "C" {
#include <libavfilter/avfilter.h>
}

#include "util.h"
#include "avfilter-helper.h"


JNI_FUNCTION(jlong, avfilter_AVFilterInOut, allocNative)(JNIEnv* env, jclass, jstring jname, jlong jcontext, jint jpadIndex)
{
    auto graph = avfilter_inout_alloc();

    auto envName = env->GetStringUTFChars(jname, nullptr);
    auto envNameLength = env->GetStringUTFLength(jname);
    auto name = new char[envNameLength + 1];
    strcpy(name, envName);
    env->ReleaseStringUTFChars(jname, envName);

    graph->name = name;
    graph->filter_ctx = getFilterContext(jcontext);
    graph->pad_idx = tojint(jpadIndex);
    graph->next = nullptr;

    name[envNameLength] = 0;

    return asjlong(graph);
}

JNI_FUNCTION(void, avfilter_AVFilterInOut, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto inout = getFilterInOut(pointer);
    delete[] inout->name;
    avfilter_inout_free(&inout);
}

JNI_FUNCTION(void, avfilter_AVFilterInOut, setNextNative)(JNIEnv*, jclass, jlong pointer, jlong jnext)
{
    auto inout = getFilterInOut(pointer);
    inout->next = getFilterInOut(jnext);
    avfilter_inout_free(&inout);
}
