package com.eugene_lutz.ffmpeg_android.avutil;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

public class RawData extends CStructWrapper
{
	//region Constructor, Destructor, etc...

	public RawData(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}

	@Override
	protected void finalize() /*throws Throwable*/
	{
		switch (allocationType)
		{
			case FROM_INSTANCE: break;
			case ALLOC: freeNative(pointer); break;
			case CUSTOM: customFinalize(); break;
			default: break;
		}
	}

	private void customFinalize()
	{
		switch (allocationFlag)
		{
			case 0: break;
			case 1: break;
			default: break;
		}
	}

	public static RawData from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static RawData from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new RawData(pointer, allocationType, allocationFlag);
	}

	//endregion



	//region Static methods

	/**
	 * Allocate a memory block with alignment suitable for all memory accesses
	 * (including vectors if available on the CPU).
	 *
	 * @param size Size in bytes for the memory block to be allocated
	 * @return Pointer to the allocated block, or `NULL` if the block cannot
	 *         be allocated
	 */
	public static RawData malloc(long size)
	{
		final long memPointer = mallocNative(size);
		return from(memPointer, AllocationType.ALLOC, 0);
	}

	/**
	 * Allocate a memory block with alignment suitable for all memory accesses
	 * (including vectors if available on the CPU) and zero all the bytes of the
	 * block.
	 *
	 * @param size Size in bytes for the memory block to be allocated
	 * @return Pointer to the allocated block, or `NULL` if it cannot be allocated
	 */
	public static RawData mallocz(long size)
	{
		final long memPointer = malloczNative(size);
		return from(memPointer, AllocationType.ALLOC, 0);
	}

	//endregion



	//region Instance methods

	/**
	 * Equals to getPointer()
	 */
	public long getData()
	{
		return pointer;
	}

	//endregion



	//region Getters/Setters

	//endregion



	//region Native methods

	private static native long mallocNative(long size);
	private static native long malloczNative(long size);
	private static native void freeNative(long pointer);

	//endregion
}
