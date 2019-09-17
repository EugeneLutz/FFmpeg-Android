#include <jni.h>
#include <string>

extern "C" {
#include <libavformat/avformat.h>
}

#include "util.h"


/* * * * * * * * * * * * * * * *
 *                             *
 *       AVFormatContext       *
 *                             *
 * * * * * * * * * * * * * * * */

/*JNI_FUNCTION(jlong, avformat_AVFormatContext, allocContextNative)(JNIEnv*, jclass)
{
    AVFormatContext* context = avformat_alloc_context();
    return reinterpret_cast<long>(context);
}*/

JNI_FUNCTION(void, avformat_AVFormatContext, freeContextNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    if (!context)
    {
        return;
    }

    avformat_free_context(context);
}


/*jlong openInput(const char* path)
{
    AVFormatContext* avFormatContext = nullptr;
    const int openResult = avformat_open_input(&avFormatContext, path, nullptr, nullptr);
    if (openResult != 0)
    {
        char errorMessage[AV_ERROR_MAX_STRING_SIZE];
        av_strerror(openResult, errorMessage, AV_ERROR_MAX_STRING_SIZE);
        return 0;
    }

    return jlong(avFormatContext);
}

JNI_FUNCTION(jlong, avformat_AVFormatContext, openInputNative)(JNIEnv* env, jclass, jstring filePath)
{
    auto path = env->GetStringUTFChars(filePath, nullptr);
    auto pointer = openInput(path);
    env->ReleaseStringUTFChars(filePath, path);
    return pointer;
}*/

static jfieldID GetField(JNIEnv* env, jclass klazz, const char* name, const char* type)
{
    return env->GetFieldID(klazz, name, type);
}

static void _fillOpenResult(JNIEnv *env, jobject result, bool succeeded, int errorCode,
                            AVFormatContext *formatContext)
{
    jclass klass = env->GetObjectClass(result);
    if (klass == nullptr)
    {
        return;
    }

    jfieldID succeededField = GetField(env, klass, "succeeded", "Z");
    jfieldID errorCodeField = GetField(env, klass, "errorCode", "I");
    jfieldID formatContextPointerField = GetField(env, klass, "formatContextPointer", "J");
    if (succeededField == nullptr ||
        errorCodeField == nullptr ||
        formatContextPointerField == nullptr)
    {
        return;
    }

    env->SetBooleanField(result, succeededField, jboolean(succeeded));
    env->SetIntField(result, errorCodeField, jint(errorCode));
    env->SetLongField(result, formatContextPointerField, jlong(formatContext));
}

void openInput(JNIEnv* env, const char* path, jobject result)
{
    AVFormatContext* avFormatContext = nullptr;
    const int openResult = avformat_open_input(&avFormatContext, path, nullptr, nullptr);
    if (openResult != 0)
    {
        _fillOpenResult(env, result, false, openResult, nullptr);

        //char errorMessage[AV_ERROR_MAX_STRING_SIZE];
        //av_strerror(openResult, errorMessage, AV_ERROR_MAX_STRING_SIZE);

        return;
    }

    _fillOpenResult(env, result, true, 0, avFormatContext);
}

/*
 * TODO: Create class EnvString:
 * EnvString(jstring);
 * virtual const char* operator = ();
 *
 * */

JNI_FUNCTION(void, avformat_AVFormatContext, openInputNative)(JNIEnv* env, jclass, jstring filePath, jobject result)
{
    /*struct ResultInfo
    {
        JNIEnv* env;
        jclass klass;
        jobject obj;
        jfieldID succeededField;
        jfieldID errorCodeField;
        jfieldID formatContextPointerField
    };*/



    auto path = env->GetStringUTFChars(filePath, nullptr);
    openInput(env, path, result);
    env->ReleaseStringUTFChars(filePath, path);
}

JNI_FUNCTION(void, avformat_AVFormatContext, closeInputNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    if (!context)
    {
        return;
    }

    avformat_close_input(&context);
}

// TODO: implement AVDictionary
JNI_FUNCTION(jint, avformat_AVFormatContext, findStreamInfoNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    if (!context)
    {
        return -1;
    }

    return avformat_find_stream_info(context, nullptr);
}

JNI_FUNCTION(void, avformat_AVFormatContext, dumpFormatNative)(JNIEnv* env, jclass,
        jlong pointer, jint index, jstring url, jboolean isOutput)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    if (!context)
    {
        return;
    }

    auto indexInt = static_cast<int>(index);
    auto urlString = env->GetStringUTFChars(url, nullptr);
    auto isOutputBool = static_cast<bool>(isOutput);
    av_dump_format(context, indexInt, urlString, isOutputBool ? 1 : 0);

    env->ReleaseStringUTFChars(url, urlString);
}

JNI_FUNCTION(void, avformat_AVFormatContext, allocOutputContext2)(JNIEnv* env, jclass,
        jobject result, jlong outputFormatPointer, jstring formatName, jstring filename)
{
    AVFormatContext* outputContext = nullptr;
    auto outputFormat = reinterpret_cast<AVOutputFormat*>(outputFormatPointer);
    auto formatNameString = formatName == nullptr ? nullptr : env->GetStringUTFChars(formatName, nullptr);
    auto filenameString = filename == nullptr ? nullptr :  env->GetStringUTFChars(filename, nullptr);

    auto allocResult = avformat_alloc_output_context2(&outputContext, outputFormat, formatNameString, filenameString);
    auto succeeded = allocResult >= 0;
    _fillOpenResult(env, result, succeeded, allocResult, outputContext);

    if (filenameString)
    {
        env->ReleaseStringUTFChars(filename, filenameString);
    }

    if (formatNameString)
    {
        env->ReleaseStringUTFChars(formatName, formatNameString);
    }
}

JNI_FUNCTION(jint, avformat_AVFormatContext, getNumberOfStreamsNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    if (!context)
    {
        return 0;
    }

    return jint(context->nb_streams);
}

JNI_FUNCTION(jlong, avformat_AVFormatContext, getStreamNative)(JNIEnv*, jclass, jlong pointer, jint index)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    if (!context || context->streams == nullptr || index >= context->nb_streams)
    {
        return 0;
    }

    return jlong(context->streams[index]);
}

// TODO: create cleanup code
JNI_FUNCTION(jlong, avformat_AVFormatContext, newStreamNative)(JNIEnv*, jclass, jlong pointer, jlong avCodecPointer)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    auto codec = reinterpret_cast<AVCodec*>(avCodecPointer);
    auto stream = avformat_new_stream(context, codec);
    return jlong(stream);
}

// TODO: implement AVDictionary
JNI_FUNCTION(jint , avformat_AVFormatContext, writeHeaderNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    if (!context)
    {
        return -1;
    }

    auto writeResult = avformat_write_header(context, nullptr);

    if (writeResult < 0)
    {
        char errbuf[1024];
        av_make_error_string(errbuf, sizeof(errbuf), AVERROR(writeResult));
        errbuf[1023] = 0;
    }

    return jint(writeResult);
}

JNI_FUNCTION(jint, avformat_AVFormatContext, readFrameNative)(JNIEnv*, jclass, jlong pointer, jlong packetPointer)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    if (!context)
    {
        return -1;
    }

    auto packet = reinterpret_cast<AVPacket*>(packetPointer);
    if (!packet)
    {
        return -1;
    }

    auto readResult = av_read_frame(context, packet);
    return jint(readResult);
}

JNI_FUNCTION(jint, avformat_AVFormatContext, interleavedWriteFrameFromPacketNative)
(JNIEnv*, jclass, jlong pointer, jlong sourcePacketPointer)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    if (!context)
    {
        return -1;
    }

    auto packet = reinterpret_cast<AVPacket*>(sourcePacketPointer);
    if (!packet)
    {
        return -1;
    }

    auto readResult = av_interleaved_write_frame(context, packet);
    if (readResult != 0)
    {
        char errorMessage[AV_ERROR_MAX_STRING_SIZE];
        av_strerror(readResult, errorMessage, AV_ERROR_MAX_STRING_SIZE);
        return jint (readResult);
    }
    return jint(readResult);
}

JNI_FUNCTION(jint, avformat_AVFormatContext, writeTrailerNative)(JNIEnv*, jclass, jlong pointer)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    if (!context)
    {
        return - 1;
    }

    auto writeResult = av_write_trailer(context);
    return jint(writeResult);
}

JNI_FUNCTION(jlong, avformat_AVFormatContext, guessAspectRatioNative)(JNIEnv*, jclass, jlong pointer, jlong stream, jlong frame)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    auto avstream = reinterpret_cast<AVStream*>(stream);
    auto avframe = reinterpret_cast<AVFrame*>(frame);

    auto rational = av_guess_sample_aspect_ratio(context, avstream, avframe);
    auto res = new AVRational;
    *res = rational;
    return asjlong(res);
}

JNI_FUNCTION(jlong, avformat_AVFormatContext, guessFrameRateNative)(JNIEnv*, jclass, jlong pointer, jlong stream, jlong frame)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    auto avstream = reinterpret_cast<AVStream*>(stream);
    auto avframe = reinterpret_cast<AVFrame*>(frame);

    auto rational = av_guess_frame_rate(context, avstream, avframe);
    auto res = new AVRational;
    *res = rational;
    return asjlong(res);
}

JNI_FUNCTION(jint, avformat_AVFormatContext, seekFrameNative)(JNIEnv*, jclass,
        jlong pointer, jint streamIndex, jlong timestamp, jint flags)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    return av_seek_frame(context, streamIndex, timestamp, flags);
}

JNI_FUNCTION(jlong, avformat_AVFormatContext, getInputFormatNative)(JNIEnv*, jclass, jlong pointer)
{
    auto format = reinterpret_cast<AVFormatContext*>(pointer);
    auto oFormat = format->iformat;
    return jlong(oFormat);
}

JNI_FUNCTION(jlong, avformat_AVFormatContext, getOutputFormatNative)(JNIEnv*, jclass, jlong pointer)
{
    auto format = reinterpret_cast<AVFormatContext*>(pointer);
    return jlong(format->oformat);
}

JNI_FUNCTION(void, avformat_AVFormatContext, setIOContextNative)(JNIEnv*, jclass, jlong pointer, jlong contextPointer)
{
    auto context = reinterpret_cast<AVFormatContext*>(pointer);
    if (!context)
    {
        return;
    }

    auto ioContext = reinterpret_cast<AVIOContext*>(contextPointer);
    if (!ioContext)
    {
        return;
    }

    context->pb = ioContext;
}
