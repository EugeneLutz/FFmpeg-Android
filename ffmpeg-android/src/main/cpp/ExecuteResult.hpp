#ifndef HELLOFFMPEG_EXECUTERESULT_HPP
#define HELLOFFMPEG_EXECUTERESULT_HPP

#include "cstruct_wrapper.hpp"

// C++ representation of ExecuteResult.java
class ExecuteResult : protected JavaClass
{
public:
    ExecuteResult(jobject object, JNIEnv* environment);

    void SetSucceeded(bool succeeded);
    void SetCode(int code);
    void SetMessage(const char* message);

private:
    jfieldID succeededField;
    jfieldID codeField;
    jfieldID messageField;
};


#endif //HELLOFFMPEG_EXECUTERESULT_HPP
