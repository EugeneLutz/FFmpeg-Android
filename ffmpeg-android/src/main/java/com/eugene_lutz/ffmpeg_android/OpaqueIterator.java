package com.eugene_lutz.ffmpeg_android;

/**
 * Wrapper over <b>void*</b> pointer
 */
public final class OpaqueIterator
{
	private final long pointer;

	private OpaqueIterator(long pointer)
	{
		this.pointer = pointer;
	}

	@Override
	protected void finalize() /*throws Throwable*/
	{
		releaseNative(pointer);
	}

	public static OpaqueIterator create()
	{
		final long pointer = createNative();
		if (pointer == 0)
		{
			return null;
		}

		return new OpaqueIterator(pointer);
	}

	public long getPointer()
	{
		return pointer;
	}

	private static native long createNative();
	private static native void releaseNative(long pointer);
}
