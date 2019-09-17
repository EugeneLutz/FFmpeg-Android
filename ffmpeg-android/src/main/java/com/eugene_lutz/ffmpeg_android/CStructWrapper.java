package com.eugene_lutz.ffmpeg_android;

public class CStructWrapper implements AutoCloseable
{
	static {
		FFmpegAndroid.loadLibraries();
	}

	public enum AllocationType
	{
		/**
		 * The object is obtained from instance
		 */
		FROM_INSTANCE,

		/**
		 * The object is obtained from memory allocation function
		 */
		ALLOC,

		/**
		 * Another cases
		 */
		CUSTOM
	}



	protected /*final*/ long pointer;
	protected final AllocationType allocationType;
	protected final int customFlag;

	protected CStructWrapper(long pointer, AllocationType allocationType, int customFlag)
	{
		this.pointer = pointer;
		this.allocationType = allocationType;
		this.customFlag = customFlag;
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
		if (pointer == 0)
		{
			return;
		}

		switch (allocationType)
		{
			//case FROM_INSTANCE: break;
			case ALLOC: finalizeDefault(); break;
			case CUSTOM: finalizeCustom(customFlag); break;
			default: break;
		}

		pointer = 0;
	}

	protected void finalizeDefault()
	{
		// Implement in derived classes if deeded
	}

	protected void finalizeCustom(int flag)
	{
		// Implement in derived classes if deeded
	}

	public long getPointer()
	{
		return pointer;
	}

	public AllocationType getAllocationType()
	{
		return allocationType;
	}

	public int getCustomFlag()
	{
		return customFlag;
	}
}
