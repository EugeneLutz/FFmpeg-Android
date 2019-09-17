package com.eugene_lutz.ffmpeg_android;

import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

public class IndexMap<type>
{
	private final type value;
	private final long index;

	public IndexMap(type value, long index)
	{
		this.value = value;
		this.index = index;
	}

	public static <type> type getValueByIndex(ArrayList<IndexMap<type>> map, long index)
	{
		for (IndexMap<type> currentPair : map)
		{
			if (currentPair.index == index)
			{
				return currentPair.value;
			}
		}

		return map.get(map.size() - 1).value;
	}

	public static <type> long getIndexByValue(ArrayList<IndexMap<type>> map, type value)
	{
		for (IndexMap<type> currentPair : map)
		{
			if (currentPair.value == value)
			{
				return currentPair.index;
			}
		}

		return map.get(map.size() - 1).index;
	}
}
