package com.eugene_lutz.ffmpeg_android.avutil;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

public class AVRational extends CStructWrapper
{
	private AVRational(long pointer, AllocationType allocType)
	{
		super(pointer, allocType, 0);
	}

	@Override
	protected void finalize()
	{
		switch (allocationType)
		{
			case FROM_INSTANCE: break;
			case ALLOC: freeNative(pointer); break;
		}
	}

	public static AVRational from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE);
	}

	public static AVRational from(long pointer, AllocationType allocType)
	{
		return pointer == 0 ? null :new AVRational(pointer, allocType);
	}



	public int getNumerator()
	{
		return getNumeratorNative(pointer);
	}

	public int getDenominator()
	{
		return getDenominatorNative(pointer);
	}



	public static native void freeNative(long pointer);
	public static native int getNumeratorNative(long pointer);
	public static native int getDenominatorNative(long pointer);
}
