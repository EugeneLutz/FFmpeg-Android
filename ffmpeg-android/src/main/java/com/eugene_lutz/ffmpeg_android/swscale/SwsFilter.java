package com.eugene_lutz.ffmpeg_android.swscale;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

public class SwsFilter extends CStructWrapper
{
	public SwsFilter(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}

	@Override
	protected void finalize() throws Throwable
	{
		switch (allocationType)
		{
			case FROM_INSTANCE: break;
			case ALLOC: freeNative(pointer); break;
			default: break;
		}
	}

	public static SwsFilter from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static SwsFilter from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new SwsFilter(pointer, allocationType, allocationFlag);
	}

	public static SwsFilter createDefaultFilter(float lumaGBlur, float chromaGBlur,
												float lumaSharpen, float chromaSharpen,
												float chromaHShift, float chromaVShift,
												int verbose)
	{
		final long filter = createDefaultFilterNative(lumaGBlur, chromaGBlur,
				lumaSharpen, chromaSharpen,
				chromaHShift, chromaVShift,
				verbose);
		return from(filter, AllocationType.ALLOC, 0);
	}




	public SwsVector getLumH()
	{
		final long vector = getLumHNative(pointer);
		return SwsVector.from(vector, AllocationType.FROM_INSTANCE);
	}

	public SwsVector getLumV()
	{
		final long vector = getLumVNative(pointer);
		return SwsVector.from(vector, AllocationType.FROM_INSTANCE);
	}

	public SwsVector getChrH()
	{
		final long vector = getChrHNative(pointer);
		return SwsVector.from(vector, AllocationType.FROM_INSTANCE);
	}

	public SwsVector getChrV()
	{
		final long vector = getChrVNative(pointer);
		return SwsVector.from(vector, AllocationType.FROM_INSTANCE);
	}

	public void setLumH(SwsVector value)
	{
		setLumHNative(pointer, value.getPointer());
	}

	public void setLumV(SwsVector value)
	{
		setLumVNative(pointer, value.getPointer());
	}

	public void setChrH(SwsVector value)
	{
		setChrHNative(pointer, value.getPointer());
	}

	public void setChrV(SwsVector value)
	{
		setChrVNative(pointer, value.getPointer());
	}




	private static native long createDefaultFilterNative(float lumaGBlur, float chromaGBlur,
														 float lumaSharpen, float chromaSharpen,
														 float chromaHShift, float chromaVShift,
														 int verbose);
	private static native void freeNative(long pointer);
	private static native long getLumHNative(long pointer);
	private static native long getLumVNative(long pointer);
	private static native long getChrHNative(long pointer);
	private static native long getChrVNative(long pointer);

	private static native void setLumHNative(long pointer, long value);
	private static native void setLumVNative(long pointer, long value);
	private static native void setChrHNative(long pointer, long value);
	private static native void setChrVNative(long pointer, long value);
}
