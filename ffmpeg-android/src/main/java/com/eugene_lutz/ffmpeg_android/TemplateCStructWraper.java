package com.eugene_lutz.ffmpeg_android;

public class TemplateCStructWraper extends CStructWrapper
{
	//region Class related stuff

	//endregion



	//region Constructor, Destructor, etc...

	public TemplateCStructWraper(long pointer, AllocationType allocationType, int allocationFlag)
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
		switch (customFlag)
		{
			case 0: break;
			case 1: break;
			default: break;
		}
	}

	public static TemplateCStructWraper from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static TemplateCStructWraper from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new TemplateCStructWraper(pointer, allocationType, allocationFlag);
	}

	//endregion



	//region Static methods

	//endregion



	//region Instance methods

	//endregion



	//region Getters/Setters

	//endregion



	//region Native methods

	//endregion
}
