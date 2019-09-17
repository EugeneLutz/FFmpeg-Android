package com.eugene_lutz.ffmpeg_android;

public class CStructWrapper implements AutoCloseable
{
	public enum AllocationType
	{
		FROM_INSTANCE,
		ALLOC,
		CUSTOM
	}



	protected final long pointer;
	protected final AllocationType allocationType;
	protected final int allocationFlag;

	protected CStructWrapper(long pointer, AllocationType allocationType, int allocationFlag)
	{
		this.pointer = pointer;
		this.allocationType = allocationType;
		this.allocationFlag = allocationFlag;
	}

	@Override
	protected void finalize() throws Throwable
	{
		_release();
	}

	@Override
	public void close() throws Exception
	{
		_release();
	}

	private void _release()
	{
		switch (allocationType)
		{
			case FROM_INSTANCE: break;
			case ALLOC: break;
			case CUSTOM: finalizeCustom(allocationFlag); break;
			default: break;
		}
	}

	protected void freeData()
	{
		//
	}

	protected void finalizeCustom(int flag)
	{
		//
	}

	public long getPointer()
	{
		return pointer;
	}

	public AllocationType getAllocationType()
	{
		return allocationType;
	}

	public int getAllocationFlag()
	{
		return allocationFlag;
	}
}
