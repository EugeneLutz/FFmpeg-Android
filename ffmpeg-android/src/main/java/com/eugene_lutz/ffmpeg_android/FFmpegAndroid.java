package com.eugene_lutz.ffmpeg_android;

public class FFmpegAndroid
{
	static
	{
		loadLibraries();
	}

	private static boolean loaded = false;

	public static void loadLibraries()
	{
		if (loaded)
		{
			return;
		}

		System.loadLibrary("avutil");
		System.loadLibrary("swresample");
		System.loadLibrary("avcodec");
		System.loadLibrary("avformat");
		System.loadLibrary("swscale");
		System.loadLibrary("avfilter");
		System.loadLibrary("avdevice");
		System.loadLibrary("nativelib");
	}

	public static void check()
	{
		loadLibraries();
	}

	public static native int runTestNative(String input, String output);
}
