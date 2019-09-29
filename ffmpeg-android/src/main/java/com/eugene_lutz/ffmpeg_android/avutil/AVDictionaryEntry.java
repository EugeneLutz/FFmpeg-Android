package com.eugene_lutz.ffmpeg_android.avutil;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

public class AVDictionaryEntry extends CStructWrapper
{
	//region Constructor, Destructor, etc...

	public AVDictionaryEntry(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}

	public static AVDictionaryEntry from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static AVDictionaryEntry from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVDictionaryEntry(pointer, allocationType, allocationFlag);
	}

	//endregion



	//region Static methods

	//endregion



	//region Instance methods

	//endregion



	//region Getters/Setters

	public String getKey()
	{
		return getKeyNative(pointer);
	}

	public String getValue()
	{
		return getValueNative(pointer);
	}

	//endregion



	//region Native methods

	private static native String getKeyNative(long pointer);
	private static native String getValueNative(long pointer);

	//endregion
}
