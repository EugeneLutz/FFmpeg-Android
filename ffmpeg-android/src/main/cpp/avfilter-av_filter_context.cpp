#include <jni.h>
#include <string>

extern "C" {
#include <libavfilter/avfilter.h>
}

#include "util.hpp"
#include "cstruct_wrapper.hpp"
#include "avutil-helper.hpp"
#include "avfilter-helper.hpp"


static AVFilterContext* getContext(jlong pointer)
{
    return reinterpret_cast<AVFilterContext*>(pointer);
}

JNI_FUNCTION(jint, avfilter_AVFilterContext, linkNative)(JNIEnv*, jclass,
        jlong src, jlong dst, jint srcIndex, jint dstIndex)
{
    auto sc = getContext(src);
    auto dc = getContext(dst);
    auto ret = avfilter_link(sc, touint(srcIndex), dc, touint(dstIndex));
    return toint(ret);
}

JNI_FUNCTION(jint, avfilter_AVFilterContext, configLinksNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    return avfilter_config_links(context);
}

JNI_FUNCTION(jint, avfilter_AVFilterContext, initWithDictionaryNative)(JNIEnv* env, jclass, jlong pointer, jobject joptions)
{
    auto context = getContext(pointer);
    auto optionsWrapper = CStructWrapper(joptions, env);
    auto optionsPointer = optionsWrapper.getPointer();
    auto options = getDictionary(optionsPointer);

    auto result = avfilter_init_dict(context, &options);
    optionsWrapper.setPointer(options);

    return tojint(result);
}

JNI_FUNCTION(jlong, avfilter_AVFilterContext, createInFilterGraphNative)(JNIEnv* env, jclass,
        jlong graphPointer, jlong filterPointer, jstring jname)
{
    auto graph = getFilterGraph(graphPointer);
    auto filter = getFilter(filterPointer);
    auto name = jname == nullptr ? nullptr : env->GetStringUTFChars(jname, nullptr);

    auto context = avfilter_graph_alloc_filter(graph, filter, name);

    if (name)
    {
        env->ReleaseStringUTFChars(jname, name);
    }

    return asjlong(context);
}

JNI_FUNCTION(jlong, avfilter_AVFilterContext, getAVClassNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto ret = context->av_class;
    return asjlong(ret);
}

JNI_FUNCTION(jlong, avfilter_AVFilterContext, getFilterNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto ret = context->filter;
    return asjlong(ret);
}

JNI_FUNCTION(jstring, avfilter_AVFilterContext, getNameNative)(JNIEnv* env, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto ret = context->name;
    return env->NewStringUTF(ret);
}

JNI_FUNCTION(jint, avfilter_AVFilterContext, getNumInputPadsNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto ret = context->nb_inputs;
    return tojint(ret);
}

JNI_FUNCTION(jlong, avfilter_AVFilterContext, getInputFilterPadNative)(JNIEnv*, jclass, jlong pointer, jint index)
{
    auto context = getContext(pointer);
    auto ret = context->input_pads;//&(context->input_pads[index]);
    return asjlong(ret);
}

JNI_FUNCTION(jint, avfilter_AVFilterContext, getNumInputFilterLinksNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto ret = context->nb_inputs;
    return tojint(ret);
}

JNI_FUNCTION(jlong, avfilter_AVFilterContext, getInputFilterLinkNative)(JNIEnv*, jclass, jlong pointer, jint index)
{
    auto context = getContext(pointer);
    auto ret = context->inputs[index];
    return asjlong(ret);
}

JNI_FUNCTION(jint, avfilter_AVFilterContext, getNumOutputPadsNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto ret = context->nb_inputs;
    return tojint(ret);
}

/*JNI_FUNCTION(jlong, avfilter_AVFilterContext, getOutputFilterPadNative)(JNIEnv*, jclass, jlong pointer, jint index)
{
    auto context = getContext(pointer);
    auto ret = &(context->output_pads[index]);
    return asjlong(ret);
}*/

JNI_FUNCTION(jint, avfilter_AVFilterContext, getNumOutputFilterLinksNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto ret = context->nb_outputs;
    return tojint(ret);
}

JNI_FUNCTION(jlong, avfilter_AVFilterContext, getOutputFilterLinkNative)(JNIEnv*, jclass, jlong pointer, jint index)
{
    auto context = getContext(pointer);
    auto ret = context->outputs[index];
    return asjlong(ret);
}

JNI_FUNCTION(jlong, avfilter_AVFilterContext, getFilterGraphNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto ret = context->graph;
    return asjlong(ret);
}

JNI_FUNCTION(jint, avfilter_AVFilterContext, getThreadTypeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto ret = context->thread_type;
    return tojint(ret);
}

JNI_FUNCTION(jlong, avfilter_AVFilterContext, getFilterCommandNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto ret = context->command_queue;
    return asjlong(ret);
}

JNI_FUNCTION(jlong, avfilter_AVFilterContext, getHardwareContextNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto ret = context->hw_device_ctx;
    return asjlong(ret);
}

JNI_FUNCTION(jint, avfilter_AVFilterContext, getMaxNumberOfThreadsNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto ret = context->nb_threads;
    return tojint(ret);
}

JNI_FUNCTION(jint, avfilter_AVFilterContext, getReadyStatusNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto ret = context->ready;
    return tojint(ret);
}

JNI_FUNCTION(jint, avfilter_AVFilterContext, getNumExtraHardwareFramesNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    auto ret = context->extra_hw_frames;
    return tojint(ret);
}

JNI_FUNCTION(jlong, avfilter_AVFilterContext, getAVFilterContextClassNative)(JNIEnv*, jclass)
{
    auto cl = avfilter_get_class();
    return asjlong(cl);
}

JNI_FUNCTION(jlong, avfilter_AVFilterContext, createWithParametersNative)(JNIEnv* env, jclass, jstring args)
{
    auto filter = new AVFilterContext();
    auto argsString = env->GetStringUTFChars(args, nullptr);
    auto result = avfilter_init_str(filter, argsString);
    if (result != 0)
    {
        delete filter;
        filter = nullptr;
    }
    env->ReleaseStringUTFChars(args, argsString);
    return asjlong(filter);
}

JNI_FUNCTION(void, avfilter_AVFilterContext, freeNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = getContext(pointer);
    avfilter_free(context);
    delete context;
}


JNI_FUNCTION(void, avfilter_AVFilterContext, setNameNative)(JNIEnv* /*env*/, jclass, jlong /*pointer*/, jstring /*value*/)
{
    //auto context = getContext(pointer);
    //context->name = env->GetStringUTFChars(value, nullptr);
}

JNI_FUNCTION(void, avfilter_AVFilterContext, setNumInputPadsNative)(JNIEnv*, jclass, jlong pointer, jint value)
{
    auto context = getContext(pointer);
    context->nb_inputs = touint(value);
}

/*JNI_FUNCTION(void, avfilter_AVFilterContext, setInputFilterPadNative)(JNIEnv*, jclass, jlong pointer, jint index, jlong value)
{
    auto context = getContext(pointer);
    context->input_pads[index] = *reinterpret_cast<AVFilterPad*>(value);
}*/

JNI_FUNCTION(void, avfilter_AVFilterContext, setNumInputFilterLinksNative)(JNIEnv*, jclass, jlong pointer, jint value)
{
    auto context = getContext(pointer);
    context->nb_inputs = touint(value);
}

JNI_FUNCTION(void, avfilter_AVFilterContext, setInputFilterLinkNative)(JNIEnv*, jclass, jlong pointer, jint index, jlong value)
{
    auto context = getContext(pointer);
    context->inputs[index] = reinterpret_cast<AVFilterLink*>(value);
}

JNI_FUNCTION(void, avfilter_AVFilterContext, setNumOutputPadsNative)(JNIEnv*, jclass, jlong pointer, jint value)
{
    auto context = getContext(pointer);
    context->nb_outputs = touint(value);
}

/*JNI_FUNCTION(void, avfilter_AVFilterContext, setOutputFilterPadNative)(JNIEnv*, jclass, jlong pointer, jint index, jlong value)
{
    auto context = getContext(pointer);
    context->output_pads[index] = *reinterpret_cast<AVFilterPad*>(value);
}*/

JNI_FUNCTION(void, avfilter_AVFilterContext, setNumOutputFilterLinksNative)(JNIEnv*, jclass, jlong pointer, jint value)
{
    auto context = getContext(pointer);
    context->nb_outputs = touint(value);
}

JNI_FUNCTION(void, avfilter_AVFilterContext, setOutputFilterLinkNative)(JNIEnv*, jclass, jlong pointer, jint index, jlong value)
{
    auto context = getContext(pointer);
    context->outputs[index] = reinterpret_cast<AVFilterLink*>(value);
}

JNI_FUNCTION(void, avfilter_AVFilterContext, setFilterGraphNative)(JNIEnv*, jclass, jlong pointer, jlong value)
{
    auto context = getContext(pointer);
    context->graph = reinterpret_cast<AVFilterGraph*>(value);
}

JNI_FUNCTION(void, avfilter_AVFilterContext, setThreadTypeNative)(JNIEnv*, jclass, jlong pointer, jint value)
{
    auto context = getContext(pointer);
    context->thread_type = toint(value);
}

/*JNI_FUNCTION(void, avfilter_AVFilterContext, setFilterCommandNative)(JNIEnv*, jclass, jlong pointer, jlong value)
{
    auto context = getContext(pointer);
    //context->command_queue = reinterpret_cast<AVFilterContext::AVFilterCommand*>(value);
}*/

JNI_FUNCTION(void, avfilter_AVFilterContext, setHardwareContextNative)(JNIEnv*, jclass, jlong pointer, jlong value)
{
    auto context = getContext(pointer);
    context->hw_device_ctx = reinterpret_cast<AVBufferRef*>(value);
}

JNI_FUNCTION(void, avfilter_AVFilterContext, setMaxNumberOfThreadsNative)(JNIEnv*, jclass, jlong pointer, jint value)
{
    auto context = getContext(pointer);
    context->nb_threads = toint(value);
}

JNI_FUNCTION(void, avfilter_AVFilterContext, setReadyStatusNative)(JNIEnv*, jclass, jlong pointer, jint value)
{
    auto context = getContext(pointer);
    context->ready = touint(value);
}

JNI_FUNCTION(void, avfilter_AVFilterContext, setNumExtraHardwareFramesNative)(JNIEnv*, jclass, jlong pointer, jint value)
{
    auto context = getContext(pointer);
    context->extra_hw_frames = toint(value);
}
