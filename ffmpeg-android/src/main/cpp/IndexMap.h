#ifndef HELLOFFMPEG_INDEXMAP_H
#define HELLOFFMPEG_INDEXMAP_H

#include <vector>

template <typename T>
class IndexMap
{
public:
    IndexMap(T value, long index)
    {
        this->value = value;
        this->index = index;
    }

    static T GetValueByIndex(std::vector<IndexMap<T>>& map, long index)
    {
        for (auto& currentPair : map)
        {
            if (currentPair.index == index)
            {
                return currentPair.value;
            }
        }

        return map[map.size() - 1].value;
    }

    static long GetIndexByValue(std::vector<IndexMap<T>>& map, T value)
    {
        for (auto& currentPair : map)
        {
            if (currentPair.value == value)
            {
                return currentPair.index;
            }
        }

        return map[map.size() - 1].index;
    }

private:
    T value;
    long index;
};

#endif //HELLOFFMPEG_INDEXMAP_H
