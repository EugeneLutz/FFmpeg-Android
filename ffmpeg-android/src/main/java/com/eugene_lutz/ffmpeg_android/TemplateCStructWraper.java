package com.eugene_lutz.ffmpeg_android;


public class TemplateCStructWraper extends CStructWrapper
{
	//region Class related stuff

	//endregion



	//region Constructor, Destructor, etc...

	private TemplateCStructWraper(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}

	@Override
	protected void finalizeDefault()
	{
		//
	}

	@Override
	protected void finalizeCustom(int flag)
	{
		switch (flag)
		{
			case 0: break;
			case 1: break;
			default: break;
		}
	}

	private static TemplateCStructWraper from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	private static TemplateCStructWraper from(long pointer, AllocationType allocationType, int allocationFlag)
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
