#ifndef HELLOFFMPEG_OPAQUE_ITERATOR_HPP
#define HELLOFFMPEG_OPAQUE_ITERATOR_HPP

#include <jni.h>
#include "util.hpp"

class OpaqueIterator
{
public:
    OpaqueIterator();
    void** GetOpaquePtr();

    static OpaqueIterator* from(jlong pointer);

private:
    void* opaque;
};

#endif //HELLOFFMPEG_OPAQUE_ITERATOR_HPP
