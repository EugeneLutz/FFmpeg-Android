package com.eugene_lutz.ffmpeg_android.avdevice;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

public class AVDeviceInfoList extends CStructWrapper
{
	public AVDeviceInfoList(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}

	@Override
	protected void finalize() /*throws Throwable*/
	{
		switch (allocationType)
		{
			case FROM_INSTANCE: break;
			case CUSTOM: freeNative(pointer); break;
			default: break;
		}
	}



	public static AVDeviceInfoList from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static AVDeviceInfoList from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVDeviceInfoList(pointer, allocationType, allocationFlag);
	}



	/** List of autodetected devices */
	public AVDeviceInfo getDeviceInfo(int index)
	{
		final long info = getDeviceInfoNative(pointer, index);
		return AVDeviceInfo.from(info);
	}

	/** Number of autodetected devices */
	public int getNumDevices()
	{
		return getNumDevicesNative(pointer);
	}

	/** Index of default device or -1 if no default */
	public int getDefaultDeviceIndex()
	{
		return getDefaultDeviceIndexNative(pointer);
	}



	private static native void freeNative(long pointer);
	private static native long getDeviceInfoNative(long pointer, int index);
	private static native int getNumDevicesNative(long pointer);
	private static native int getDefaultDeviceIndexNative(long pointer);
}
