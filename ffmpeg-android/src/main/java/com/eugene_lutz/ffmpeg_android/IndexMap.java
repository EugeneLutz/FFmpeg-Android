package com.eugene_lutz.ffmpeg_android;

public class IndexMap<type>
{
	private final type value;
	private final long index;

	public IndexMap(type value, long index)
	{
		this.value = value;
		this.index = index;
	}

	public static <type> type getValueByIndex(IndexMap<type>[] map, long index)
	{
		for (IndexMap<type> currentPair : map)
		{
			if (currentPair.index == index)
			{
				return currentPair.value;
			}
		}

		return map[map.length - 1].value;
	}

	public static <type> long getIndexByValue(IndexMap<type>[] map, type value)
	{
		for (IndexMap<type> currentPair : map)
		{
			if (currentPair.value == value)
			{
				return currentPair.index;
			}
		}

		return map[map.length - 1].index;
	}
}
