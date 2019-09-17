#ifndef HELLOFFMPEG_OPAQUE_ITERATOR_H
#define HELLOFFMPEG_OPAQUE_ITERATOR_H

#include <jni.h>
#include "util.h"

class OpaqueIterator
{
public:
    OpaqueIterator();
    void** GetOpaquePtr();

    static OpaqueIterator* from(jlong pointer);

private:
    void* opaque;
};

#endif //HELLOFFMPEG_OPAQUE_ITERATOR_H
