package com.eugene_lutz.ffmpeg_android.avutil;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

import java.nio.ByteBuffer;

/**
 * A reference to a data buffer.
 * */
public class AVBufferRef extends CStructWrapper
{
	public AVBufferRef(long pointer, AllocationType allocType)
	{
		super(pointer, allocType, 0);
	}

	@Override
	protected void finalizeDefault()
	{
		freeNative(pointer);
	}

	@Override
	protected void finalizeCustom(int flag)
	{
		unrefNative(pointer);
	}

	private static AVBufferRef from(long pointer, AllocationType allocType)
	{
		return pointer == 0 ? null : new AVBufferRef(pointer, allocType);
	}

	public static AVBufferRef from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE);
	}

	/**
	 * Allocate an AVBuffer of the given size using av_malloc().
	 * */
	public static AVBufferRef alloc(int size)
	{
		final long pointer = allocNative(size);
		return from(pointer, AllocationType.ALLOC);
	}

	/**
	 * Same as av_buffer_alloc(), except the returned buffer will be initialized
	 * to zero.
	 * */
	public static AVBufferRef allocz(int size)
	{
		final long pointer = alloczNative(size);
		return from(pointer, AllocationType.ALLOC);
	}

	public static AVBufferRef ref(AVBufferRef bufferRef)
	{
		final long refPointer = refNative(bufferRef.pointer);
		return from(refPointer, AllocationType.CUSTOM);
	}



	public boolean isWritable()
	{
		return isWritableNative(pointer) == 1;
	}

	public ByteBuffer getData()
	{
		return getDataNative(pointer);
	}

	public long getDataPointer()
	{
		return getDataPointerNative(pointer);
	}

	public int getDataSize()
	{
		return getDataSizeNative(pointer);
	}

	public int getRefCount()
	{
		return getRefCountNative(pointer);
	}



	private static native long allocNative(int size);
	private static native long alloczNative(int size);
	private static native void freeNative(long pointer);
	private static native long refNative(long pointer);
	private static native void unrefNative(long pointer);

	private static native int isWritableNative(long pointer);
	private static native ByteBuffer getDataNative(long pointer);
	private static native long getDataPointerNative(long pointer);
	private static native int getDataSizeNative(long pointer);
	private static native int getRefCountNative(long pointer);
}
