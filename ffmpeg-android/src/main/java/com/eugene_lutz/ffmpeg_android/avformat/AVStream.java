package com.eugene_lutz.ffmpeg_android.avformat;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.avcodec.AVCodecParameters;
import com.eugene_lutz.ffmpeg_android.avutil.AVRational;

public class AVStream extends CStructWrapper
{
	// stream parameters initialized in avformat_write_header
	public final static int AVSTREAM_INIT_IN_WRITE_HEADER = 0;

	// stream parameters initialized in avformat_init_output
	public final static int AVSTREAM_INIT_IN_INIT_OUTPUT = 1;



	private AVStream(long pointer)
	{
		super(pointer, AllocationType.FROM_INSTANCE, 0);
	}


	public static AVStream from(long pointer)
	{
		if (pointer == 0)
		{
			return  null;
		}

		return new AVStream(pointer);
	}


	public AVCodecParameters getCodecParameters()
	{
		final long cpPointer = getCodecParametersNative(pointer);
		return AVCodecParameters.from(cpPointer);
	}

	public AVRational getTimeBase()
	{
		final long timeBasePointer = getTimeBasePointerNative(pointer);
		return AVRational.from(timeBasePointer);
	}


	private static native long getCodecParametersNative(long pointer);
	private static native long getTimeBasePointerNative(long pointer);
}
