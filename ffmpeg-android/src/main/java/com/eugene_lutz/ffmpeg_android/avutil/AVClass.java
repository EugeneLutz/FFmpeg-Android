package com.eugene_lutz.ffmpeg_android.avutil;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.FFmpegUtil;

public class AVClass extends CStructWrapper
{
	public AVClass(long pointer)
	{
		super(pointer, AllocationType.FROM_INSTANCE, 0);
	}



	public static AVClass from(long pointer)
	{
		return pointer == 0 ? null : new AVClass(pointer);
	}



	/**
	 * Get the name of the class; usually it is the same name as the
	 * context structure type to which the AVClass is associated.
	 */
	public String getClassName()
	{
		return getClassNameNative(pointer);
	}

	/**
	 * Get category used for visualization (like color)
	 * This is only set if the category is equal for all objects using this class.
	 * available since version (51 << 16 | 56 << 8 | 100)
	 */
	public AVClassCategory getClassCategory()
	{
		final int categoryInt = getClassCategoryNative(pointer);
		return FFmpegUtil.intToClassCategory(categoryInt);
	}



	private static native String getClassNameNative(long pointer);
	private static native int getClassCategoryNative(long pointer);
}
