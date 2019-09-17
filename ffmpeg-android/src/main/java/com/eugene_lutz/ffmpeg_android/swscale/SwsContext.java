package com.eugene_lutz.ffmpeg_android.swscale;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

import java.nio.ByteBuffer;

public class SwsContext extends CStructWrapper
{
	private SwsContext(long pointer, AllocationType allocationType, int flags)
	{
		super(pointer, allocationType, flags);
	}

	@Override
	protected void finalize()
	{
		switch (allocationType)
		{
			case FROM_INSTANCE: break;
			case ALLOC: freeNative(pointer);
			default: break;
		}
	}



	public static SwsContext from(long pointer, AllocationType allocationType, int flags)
	{
		return pointer == 0 ? null : new SwsContext(pointer, allocationType, flags);
	}

	public static SwsContext create()
	{
		final long context = allocNative();
		return from(context, AllocationType.ALLOC, 0);
	}



	/*public int init(SwsFilter source, SwsFilter destination)
	{
		return initNative(pointer, source.getPointer(), destination.getPointer());
	}*/

	public int scale(ByteBuffer sliceArray, ByteBuffer strideArray, int sliceY, int sliceH, ByteBuffer outSliceArray, ByteBuffer outStrideArray)
	{
		return scaleNative(pointer, sliceArray, strideArray, sliceY, sliceH, outSliceArray, outStrideArray);
	}



	private static native long allocNative();
	private static native void freeNative(long pointer);
	//private static native int initNative(long pointer, long sourceFilter, long destinationFilter);
	private static native int scaleNative(long pointer, ByteBuffer sliceArray, ByteBuffer strideArray, int sliceY, int sliceH, ByteBuffer outSliceArray, ByteBuffer outStrideArray);
}
