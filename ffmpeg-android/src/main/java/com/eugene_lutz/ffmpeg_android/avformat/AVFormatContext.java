package com.eugene_lutz.ffmpeg_android.avformat;

import android.util.Log;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.avcodec.AVCodec;
import com.eugene_lutz.ffmpeg_android.avcodec.AVPacket;
import com.eugene_lutz.ffmpeg_android.avutil.AVFrame;
import com.eugene_lutz.ffmpeg_android.avutil.AVRational;

/**
 * Format I/O context.
 * New fields can be added to the end with minor version bumps.
 * Removal, reordering and changes to existing fields require a major
 * version bump.
 * sizeof(AVFormatContext) must not be used outside libav*, use
 * avformat_alloc_context() to create an AVFormatContext.
 *
 * Fields can be accessed through AVOptions (av_opt*),
 * the name string used matches the associated command line parameter name and
 * can be found in libavformat/options_table.h.
 * The AVOption/command line parameter names differ in some cases from the C
 * structure field names for historic reasons or brevity.
 */
public class AVFormatContext extends CStructWrapper
{
	//region Class related stuff

	private static final String LOG_TAG = "AVFormatContext";

	public static class CreateResult
	{
		private boolean succeeded;
		private int errorCode;
		private long formatContextPointer;
		private AllocationType allocationType;
		private int allocationFlag;	// 0 - OPEN_INPUT, 1 - OPEN_OUTPUT
		private AVFormatContext formatContext;

		private CreateResult(AllocationType allocationType, int allocationFlag)
		{
			succeeded = false;
			errorCode = 0;
			formatContextPointer = 0;
			this.allocationType = allocationType;
			this.allocationFlag = allocationFlag;
			formatContext = null;
		}

		private void init()
		{
			formatContext = new AVFormatContext(formatContextPointer, allocationType, allocationFlag);
		}


		public boolean isSucceeded()
		{
			return succeeded;
		}

		public int getErrorCode()
		{
			return errorCode;
		}

		public AVFormatContext getFormatContext()
		{
			return formatContext;
		}
	}

	//endregion



	//region Constructor, Destructor, etc...

	private AVFormatContext(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}

	@Override
	protected void finalize() /*throws Throwable*/
	{
		if (pointer == 0)
		{
			return;
		}

		// Уничтожим объект нужным способом в зависимости от allocationType
		switch (allocationType)
		{
			case ALLOC:freeContextNative(pointer); break;
			case CUSTOM: closeInputNative(pointer); break;
			default: break;
		}

		Log.d(LOG_TAG, "AVFormatContext is dead");
	}

	//endregion



	//region Static methods

	/**
	 * Open an input stream and read the header. The codecs are not opened.
	 * Destructor of this class takes care of closing stream.
	 * */
	public static CreateResult openInput(String path)
	{
		// * The stream must be closed with avformat_close_input().
		//              ^ Эту задачу возьмёт на себя этот класс

		final CreateResult result = new CreateResult(AllocationType.CUSTOM, 0);
		openInputNative(path, result);
		result.init();

		return result;
	}

	public static CreateResult createOutputByOutputFormat(AVOutputFormat outputFormat)
	{
		final CreateResult result = new CreateResult(AllocationType.CUSTOM, 1);
		allocOutputContext2(result, outputFormat.getPointer(), null, null);
		result.init();

		return result;
	}

	public static CreateResult createOutputByFormatName(String formatName)
	{
		final CreateResult result = new CreateResult(AllocationType.CUSTOM, 1);
		allocOutputContext2(result, 0, formatName, null);
		result.init();

		return result;
	}

	public static CreateResult createOutputByFileName(String fileName)
	{
		final CreateResult result = new CreateResult(AllocationType.CUSTOM, 1);
		allocOutputContext2(result, 0, null, fileName);
		result.init();

		return result;
	}

	//endregion



	//region Instance methods

	/**
	* Read packets of a media file to get stream information. This
	* is useful for file formats with no headers such as MPEG. This
	* function also computes the real framerate in case of MPEG-2 repeat
	* frame mode.
	* The logical file position is not changed by this function;
	* examined packets may be buffered for later processing.
	* */
	public int findStreamInfo()
	{
		return findStreamInfoNative(pointer);
	}

	/**
	 * Print detailed information about the input or output format, such as
	 * duration, bitrate, streams, container, programs, metadata, side data,
	 * codec and time base.
	 * @param index		index of the stream to dump information about
	 * @param url		the URL to print, such as source or destination file
	 * @param isOutput	Select whether the specified context is an input(false) or output(true)
	 * */
	public void dumpFormat(int index, String url, boolean isOutput)
	{
		dumpFormatNative(pointer, index, url, isOutput);
	}

	public AVStream newStream(AVCodec codec)
	{
		final long codecPointer = codec == null ? 0 : codec.getPointer();
		final long streamPointer =  newStreamNative(pointer, codecPointer);
		return AVStream.from(streamPointer);
	}

	/**
	 * Allocate the stream private data and write the stream header to
	 * an output media file.
	 *
	 * @return AVSteram.AVSTREAM_INIT_IN_WRITE_HEADER on success if the codec had not already been fully initialized in avformat_init,
	 *         AVSteram.AVSTREAM_INIT_IN_INIT_OUTPUT  on success if the codec had already been fully initialized in avformat_init,
	 *         negative AVERROR on failure.
	 */
	public int writeHeader()
	{
		return writeHeaderNative(pointer);
	}

	public int readFrame(AVPacket destination)
	{
		return readFrameNative(pointer, destination.getPointer());
	}


	public int interleavedWriteFrameFromPacket(AVPacket sourcePacket)
	{
		return interleavedWriteFrameFromPacketNative(pointer, sourcePacket.getPointer());
	}

	/**
	 * Write the stream trailer to an output media file and free the
	 * file private data.
	 *
	 * May only be called after a successful call to avformat_write_header.
	 *
	 * @return 0 if OK, AVERROR_xxx on error
	 */
	public int writeTrailer()
	{
		return writeTrailerNative(pointer);
	}

	/**
	 * Guess the sample aspect ratio of a frame, based on both the stream and the
	 * frame aspect ratio.
	 *
	 * Since the frame aspect ratio is set by the codec but the stream aspect ratio
	 * is set by the demuxer, these two may not be equal. This function tries to
	 * return the value that you should use if you would like to display the frame.
	 *
	 * Basic logic is to use the stream aspect ratio if it is set to something sane
	 * otherwise use the frame aspect ratio. This way a container setting, which is
	 * usually easy to modify can override the coded value in the frames.
	 *
	 * @param stream the stream which the frame is part of
	 * @param frame the frame with the aspect ratio to be determined
	 * @return the guessed (valid) sample_aspect_ratio, 0/1 if no idea
	 */
	public AVRational guessAspectRatio(AVStream stream, AVFrame frame)
	{
		final long rational = guessAspectRatioNative(pointer, stream.getPointer(), frame.getPointer());
		return AVRational.from(rational, AVRational.AllocationType.ALLOC);
	}

	/**
	 * Guess the frame rate, based on both the container and codec information.
	 *
	 * @param stream the stream which the frame is part of
	 * @param frame the frame for which the frame rate should be determined, may be NULL
	 * @return the guessed (valid) frame rate, 0/1 if no idea
	 */
	public AVRational guessFrameRate(AVStream stream, AVFrame frame)
	{
		final long rational = guessFrameRateNative(pointer, stream.getPointer(), frame.getPointer());
		return AVRational.from(rational, AVRational.AllocationType.ALLOC);
	}

	/*
	 * Seek to the keyframe at timestamp.
	 * 'timestamp' in 'stream_index'.
	 *
	 * @param streamIndex If stream_index is (-1), a default
	 * stream is selected, and timestamp is automatically converted
	 * from AV_TIME_BASE units to the stream specific time_base.
	 * @param timestamp Timestamp in AVStream.time_base units
	 *        or, if no stream is specified, in AV_TIME_BASE units.
	 * @param flags flags which select direction and seeking mode
	 * @return >= 0 on success
	 */
	public int seekFrame(int streamIndex, long timestamp, int flags)
	{
		return seekFrameNative(pointer, streamIndex, timestamp, flags);
	}

	//endregion



	//region Getters/Setters

	/**
	 * Returns number of streams
	 * */
	public int getNumberOfStreams()
	{
		return getNumberOfStreamsNative(pointer);
	}

	public AVStream getStream(int index)
	{
		final long streamPointer = getStreamNative(pointer, index);
		return AVStream.from(streamPointer);
	}

	public AVInputFormat getInputFormat()
	{
		final long iFormatPointer = getInputFormatNative(pointer);
		return AVInputFormat.from(iFormatPointer, CStructWrapper.AllocationType.FROM_INSTANCE, 0);
	}

	public AVOutputFormat getOutputFormat()
	{
		final long oFormatPointer = getOutputFormatNative(pointer);
		return AVOutputFormat.from(oFormatPointer, CStructWrapper.AllocationType.FROM_INSTANCE, 0);
	}



	/**
	 * Set I/O context.
	 *
	 * - demuxing: either set by the user before avformat_open_input() (then
	 *             the user must close it manually) or set by avformat_open_input().
	 * - muxing: set by the user before avformat_write_header(). The caller must
	 *           take care of closing / freeing the IO context.
	 *
	 * Do NOT set this field if AVFMT_NOFILE flag is set in
	 * iformat/oformat.flags. In such a case, the (de)muxer will handle
	 * I/O in some other way and this field will be NULL.
	 */
	public void setIOContext(AVIOContext context)
	{
		setIOContextNative(pointer, context.getPointer());
	}

	//endregion



	//region Native methods

	private static native void openInputNative(String path, CreateResult result);
	private static native void allocOutputContext2(CreateResult result, long outputFormatPointer,
												   String formatName, String filename);

	//private static native long allocContextNative();
	private static native void freeContextNative(long pointer);
	private static native void closeInputNative(long pointer);
	private static native int findStreamInfoNative(long pointer);
	private static native void dumpFormatNative(long pointer, int index, String url, boolean isOutput);
	private static native long newStreamNative(long pointer, long avCodecPointer);
	private static native int writeHeaderNative(long pointer);
	private static native int readFrameNative(long pointer, long packetPointer);
	private static native int interleavedWriteFrameFromPacketNative(long pointer, long sourcePacketPointer);
	private static native int writeTrailerNative(long pointer);
	private static native long guessAspectRatioNative(long pointer, long stream, long frame);
	private static native long guessFrameRateNative(long pointer, long stream, long frame);
	private static native int seekFrameNative(long pointer, int streamIndex, long timestamp, int flags);

	private static native int getNumberOfStreamsNative(long pointer);
	private static native long getStreamNative(long pointer, int index);
	private static native long getInputFormatNative(long pointer);
	private static native long getOutputFormatNative(long pointer);

	private static native void setIOContextNative(long pointer, long contextPointer);

	//endregion
}
