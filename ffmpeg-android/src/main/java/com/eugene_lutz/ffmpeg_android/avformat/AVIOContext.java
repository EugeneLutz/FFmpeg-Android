package com.eugene_lutz.ffmpeg_android.avformat;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

public class AVIOContext extends CStructWrapper
{
	//region Class related stuff

	public static class CreateResult
	{
		private AllocationType allocationType;
		private boolean succeeded;
		private int code;
		private long pointer;
		private AVIOContext IOContext;

		private CreateResult(AllocationType allocationType)
		{
			this.allocationType = allocationType;
			succeeded = false;
			code = 0;
			pointer = 0;
			IOContext = null;
		}

		private void init()
		{
			IOContext = AVIOContext.from(pointer, allocationType, 0);
		}


		public boolean isSucceeded()
		{
			return succeeded;
		}

		public int getCode()
		{
			return code;
		}

		public AVIOContext getIOContext()
		{
			return IOContext;
		}
	}

	//endregion



	//region Constructor, Destructor, etc...

	private AVIOContext(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}

	@Override
	protected void finalize() /*throws Throwable*/
	{
		switch (allocationType)
		{
			case FROM_INSTANCE: break;
			case ALLOC: break;
			case CUSTOM: customFinalize(); break;
			default: break;
		}
	}

	private void customFinalize()
	{
		closeNative(pointer);

		switch (customFlag)
		{
			case 0: break;
			case 1: break;
			default: break;
		}
	}

	public static AVIOContext from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static AVIOContext from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVIOContext(pointer, allocationType, allocationFlag);
	}

	//endregion



	//region Static methods

	/*
	 * TODO: finish description
	 * Create and initialize a AVIOContext for accessing the
	 * resource indicated by url.
	 * <p>
	 * Note: When the resource indicated by url has been opened in
	 * read+write mode, the AVIOContext can be used only for writing.
	 *</p>
	 *
	 * @param path resource to access
	 * @param flags AVIO.AVIO_FLAG_* flags which control how the resource indicated by url
	 * is to be opened
	 * @return > = 0 in case of success, a negative value corresponding to an
	 * AVERROR code in case of failure
	 */
	public static CreateResult open(String path, int flags)
	{
		final CreateResult openResult = new CreateResult(AllocationType.CUSTOM);
		openNative(openResult, path, flags);
		openResult.init();
		return openResult;
	}

	//endregion



	//region Instance methods

	//endregion



	//region Getters/Setters

	//endregion



	//region Native methods

	private static native void openNative(CreateResult result, String path, int flags);
	private static native void closeNative(long pointer);

	//endregion
}
