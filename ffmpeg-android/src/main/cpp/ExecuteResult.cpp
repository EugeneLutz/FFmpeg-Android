#include "ExecuteResult.hpp"

ExecuteResult::ExecuteResult(jobject object, JNIEnv* environment) : JavaClass(object, environment)
{
    if (!obj) {
        succeededField = nullptr;
        codeField = nullptr;
        messageField = nullptr;
        return;
    }

    succeededField = GetBooleanField("succeeded");
    codeField = GetIntField("code");
    messageField = GetStringField("message");
}

void ExecuteResult::SetSucceeded(bool succeeded)
{
    if (!obj) {
        return;
    }

    env->SetBooleanField(obj, succeededField, tojboolean(succeeded));
}

void ExecuteResult::SetCode(int code)
{
    if (!obj) {
        return;
    }

    env->SetIntField(obj, codeField, tojint(code));
}

void ExecuteResult::SetMessage(const char* message)
{
    if (!obj) {
        return;
    }

    auto jmessage = env->NewStringUTF(message);
    env->SetObjectField(obj, messageField, jmessage);
}