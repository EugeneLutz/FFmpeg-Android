#ifndef HELLOFFMPEG_UTIL_HPP
#define HELLOFFMPEG_UTIL_HPP


#include <jni.h>
#include <string>


#define JNI_FUNCTION(returnType, className, name) extern "C" JNIEXPORT returnType JNICALL \
Java_com_eugene_1lutz_ffmpeg_1android_ ## className ## _ ## name

#define asjlong(pointer) (reinterpret_cast<jlong>(pointer))
#define tojlong(value) (static_cast<jlong>(value))
#define tojint(value) (static_cast<jint>(value))
//#define tojchar(value) (static_cast<jchar>(value))
//#define tojdouble(value) (static_cast<jdouble>(value))
#define tojboolean(value) (static_cast<jboolean>(value))

#define toint(value) (static_cast<int>(value))
#define touint(value) (static_cast<unsigned int>(value))
#define tolong(value) (static_cast<long>(value))
#define aslong(value) (reinterpret_cast<long>(value))
//#define asint(value) (reinterpret_cast<int>(value))
#define tochar(value) (static_cast<char>(value))
#define todouble(value) (static_cast<double>(value))
#define tobool(value) (static_cast<bool>(value))


class JNIString
{
public:
    JNIString(jstring source, JNIEnv* env)
    {
        this->source = source;
        this->env = env;
        data = source ? env->GetStringUTFChars(source, nullptr) : nullptr;
    }

    ~JNIString()
    {
        env->ReleaseStringUTFChars(source, data);
    }

    const char* getData()
    {
        return data;
    }

    static std::string getString(jstring source, JNIEnv* env)
    {
        auto cstring = env->GetStringUTFChars(source, nullptr);
        std::string result = cstring;
        env->ReleaseStringUTFChars(source, cstring);
        return result;
    }

private:
    jstring source;
    JNIEnv* env;
    const char* data;
};


inline jfieldID GetJNIClassField(JNIEnv* env, jclass klazz, const char* name, const char* type)
{
    return env->GetFieldID(klazz, name, type);
}


inline char* getRawCharData(jlong pointer)
{
    return reinterpret_cast<char*>(pointer);
}


#endif //HELLOFFMPEG_UTIL_HPP
