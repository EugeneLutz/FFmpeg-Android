package com.eugene_lutz.ffmpeg_android.avdevice;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

public class AVDeviceRect extends CStructWrapper
{
	//region Constructor, Destructor, etc...

	public AVDeviceRect(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}

	public static AVDeviceRect from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static AVDeviceRect from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVDeviceRect(pointer, allocationType, allocationFlag);
	}

	//endregion



	//region Static methods

	//endregion



	//region Instance methods

	//endregion



	//region Getters/Setters

	public int getX()
	{
		return getXNative(pointer);
	}

	public int getY()
	{
		return getYNative(pointer);
	}

	public int getWidth()
	{
		return getWidthNative(pointer);
	}

	public int getHeight()
	{
		return getHeightNative(pointer);
	}


	public void setX(int value)
	{
		setXNative(pointer, value);
	}

	public void setY(int value)
	{
		setYNative(pointer, value);
	}

	public void setWidth(int value)
	{
		setWidthNative(pointer, value);
	}

	public void setHeight(int value)
	{
		setHeightNative(pointer, value);
	}

	//endregion



	//region Native methods

	private static native int getXNative(long pointer);
	private static native int getYNative(long pointer);
	private static native int getWidthNative(long pointer);
	private static native int getHeightNative(long pointer);

	private static native void setXNative(long pointer, int value);
	private static native void setYNative(long pointer, int value);
	private static native void setWidthNative(long pointer, int value);
	private static native void setHeightNative(long pointer, int value);

	//endregion
}
