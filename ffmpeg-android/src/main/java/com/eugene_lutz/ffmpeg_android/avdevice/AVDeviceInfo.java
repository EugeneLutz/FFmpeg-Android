package com.eugene_lutz.ffmpeg_android.avdevice;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

public class AVDeviceInfo extends CStructWrapper
{
	//region Constructor, Destructor, etc...

	public AVDeviceInfo(long pointer, AllocationType allocationType, int allocationFlag)
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
		switch (allocationFlag)
		{
			case 0: break;
			case 1: break;
			default: break;
		}
	}

	public static AVDeviceInfo from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static AVDeviceInfo from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVDeviceInfo(pointer, allocationType, allocationFlag);
	}

	//endregion



	//region Static methods

	//endregion



	//region Instance methods

	//endregion



	//region Getters/Setters

	public String getName()
	{
		return getNameNative(pointer);
	}

	public String getDescription()
	{
		return getDescriptionNative(pointer);
	}

	//endregion



	//region Native methods

	private static native String getNameNative(long pointer);
	private static native String getDescriptionNative(long pointer);

	//endregion
}
