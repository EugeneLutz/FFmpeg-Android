#ifndef HELLOFFMPEG_CSTRUCT_WRAPPER_HPP
#define HELLOFFMPEG_CSTRUCT_WRAPPER_HPP

#include <jni.h>
#include "util.hpp"


class JavaClass
{
public:
    JavaClass(jobject object, JNIEnv* environment)
    {
        env = environment;
        obj = object;
        klazz = env->GetObjectClass(object);
    }


    bool GetBoolean(const char* fieldName)
    {
        auto field = env->GetFieldID(klazz, fieldName, "Z");
        const auto pointer = env->GetBooleanField(obj, field);
        return tobool(pointer);
    }

    void SetBoolean(bool value, const char* fieldName)
    {
        auto field = env->GetFieldID(klazz, fieldName, "Z");
        env->SetBooleanField(obj, field, tojboolean(value));
    }


    int GetInt(const char* fieldName)
    {
        auto field = env->GetFieldID(klazz, fieldName, "I");
        const auto pointer = env->GetIntField(obj, field);
        return toint(pointer);
    }

    void SetInt(int value, const char* fieldName)
    {
        auto field = env->GetFieldID(klazz, fieldName, "I");
        env->SetIntField(obj, field, value);
    }


    long GetLong(const char* fieldName)
    {
        auto field = env->GetFieldID(klazz, fieldName, "J");
        const auto pointer = env->GetLongField(obj, field);
        return tolong(pointer);
    }

    void SetLong(long value, const char* fieldName)
    {
        auto field = env->GetFieldID(klazz, fieldName, "J");
        env->SetLongField(obj, field, value);
    }


    std::string GetString(const char* fieldName)
    {
        auto field = env->GetFieldID(klazz, fieldName, "Ljava/lang/String;");
        auto string = static_cast<jstring>(env->GetObjectField(obj, field));
        return JNIString::getString(string, env);
    }

    void SetString(const char* value, const char* fieldName)
    {
        auto field = env->GetFieldID(klazz, fieldName, "Ljava/lang/String;");
        auto string = env->NewStringUTF(value);
        env->SetObjectField(obj, field, string);
    }


protected:
    JNIEnv* env;
    jobject obj;
    jclass klazz;
};


class CStructWrapper : public JavaClass
{
public:
    CStructWrapper(jobject object, JNIEnv* environment) : JavaClass(object, environment)
    {
        pointerField = env->GetFieldID(klazz, "pointer", "J");
    }

    ~CStructWrapper()
    {
        //
    }

    long getPointer()
    {
        const auto pointer = env->GetLongField(obj, pointerField);
        return tolong(pointer);
    }

    void setPointer(long value)
    {
        const auto pointer = tojlong(value);
        env->SetLongField(obj, pointerField, pointer);
    }

    void setPointer(void* value)
    {
        const auto pointer = asjlong(value);
        env->SetLongField(obj, pointerField, pointer);
    }

private:
    jfieldID pointerField;
};


#endif //HELLOFFMPEG_CSTRUCT_WRAPPER_HPP
