package com.eugene_lutz.ffmpeg_android.avfilter;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

public class AVFilterCommand extends CStructWrapper
{
	public AVFilterCommand(long pointer)
	{
		super(pointer, AllocationType.FROM_INSTANCE, 0);
	}



	public static AVFilterCommand from(long pointer)
	{
		return pointer == 0 ? null : new AVFilterCommand(pointer);
	}
}
