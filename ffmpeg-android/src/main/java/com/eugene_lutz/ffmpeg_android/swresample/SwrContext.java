package com.eugene_lutz.ffmpeg_android.swresample;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.FFmpegUtil;
import com.eugene_lutz.ffmpeg_android.avutil.AVClass;
import com.eugene_lutz.ffmpeg_android.avutil.AVFrame;
import com.eugene_lutz.ffmpeg_android.avutil.AVSampleFormat;

public class SwrContext extends CStructWrapper
{
	public SwrContext(long pointer, AllocationType allocType)
	{
		super(pointer, allocType, 0);
	}

	@Override
	protected void finalize() throws Throwable
	{
		switch (allocationType)
		{
			case FROM_INSTANCE: break;
			case ALLOC: freeNative(pointer);
			default: break;
		}
	}

	public static SwrContext from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE);
	}

	private static SwrContext from(long pointer, AllocationType allocType)
	{
		return pointer == 0 ? null : new SwrContext(pointer, allocType);
	}

	/**
	 * Get the AVClass for SwrContext. It can be used in combination with
	 * AV_OPT_SEARCH_FAKE_OBJ for examining options.
	 *
	 * @return the AVClass of SwrContext
	 */
	public static AVClass getAVClass()
	{
		final long avclassPointer = getAVClassNative();
		return AVClass.from(avclassPointer);
	}

	/**
	 * Allocate SwrContext.
	 *
	 * If you use this function you will need to set the parameters (manually or
	 * with swr_alloc_set_opts()) before calling swr_init().
	 *
	 * @return NULL on error, allocated context otherwise
	 */
	public SwrContext create()
	{
		final long context = createNative();
		return from(context, AllocationType.ALLOC);
	}



	/**
	 * Initialize context after user parameters have been set.
	 * @note The context must be configured using the AVOption API.
	 *
	 * @return AVERROR error code in case of failure.
	 */
	public int init()
	{
		return initNative(pointer);
	}

	/**
	 * Check whether an swr context has been initialized or not.
	 *
	 * @return true if it has been initialized, false if not initialized
	 */
	public boolean isIntitialized()
	{
		return isInitializedNative(pointer) > 0;
	}

	/**
	 * Set/reset common parameters.
	 *
	 * This function does not require s to be allocated with swr_alloc(). On the
	 * other hand, swr_alloc() can use swr_alloc_set_opts() to set the parameters
	 * on the allocated context.
	 *
	 * @param outChannelLayout	output channel layout (AV_CH_LAYOUT_*)
	 * @param outSampleFormat	output sample format (AV_SAMPLE_FMT_*).
	 * @param outSampleRate		output sample rate (frequency in Hz)
	 * @param inChannelLayout	input channel layout (AV_CH_LAYOUT_*)
	 * @param inSampleFormat	input sample format (AV_SAMPLE_FMT_*).
	 * @param inSampleRate		input sample rate (frequency in Hz)
	 */
	public void setOptions(long outChannelLayout, AVSampleFormat outSampleFormat, int outSampleRate,
						   long inChannelLayout, AVSampleFormat inSampleFormat, int inSampleRate)
	{
		final int outSFT = FFmpegUtil.AVSampleFormatToInt(outSampleFormat);
		final int inSFT = FFmpegUtil.AVSampleFormatToInt(inSampleFormat);
		setOptionsNative(pointer, outChannelLayout, outSFT, outSampleRate, inChannelLayout, inSFT, inSampleRate);
	}

	/**
	 * Closes the context so that swr_is_initialized() returns 0.
	 *
	 * The context can be brought back to life by running swr_init(),
	 * swr_init() can also be used without swr_close().
	 * This function is mainly provided for simplifying the usecase
	 * where one tries to support libavresample and libswresample.
	 */
	public void close()
	{
		closeNative(pointer);
	}

	/*public int convert()
	{
		return 0;
	}*/

	/**
	 * Convert the next timestamp from input to output
	 * timestamps are in 1/(in_sample_rate * out_sample_rate) units.
	 *
	 * @note There are 2 slightly differently behaving modes.
	 *         • When automatic timestamp compensation is not used, (min_compensation >= FLT_MAX)
	 *              in this case timestamps will be passed through with delays compensated
	 *         • When automatic timestamp compensation is used, (min_compensation < FLT_MAX)
	 *              in this case the output timestamps will match output sample numbers.
	 *              See ffmpeg-resampler(1) for the two modes of compensation.
	 *
	 * @param pts   timestamp for the next input sample, INT64_MIN if unknown
	 * @return the output timestamp for the next output sample
	 */
	public long nextPts(long pts)
	{
		return nextPtsNative(pointer, pts);
	}

	/**
	 * Activate resampling compensation ("soft" compensation). This function is
	 * internally called when needed in swr_next_pts().
	 *
	 * @param         sampleDelta  delta in PTS per sample
	 * @param         compensationDistance number of samples to compensate for
	 * @return    >= 0 on success, AVERROR error codes if:
	 *              • @c s is NULL,
	 *              • @c compensation_distance is less than 0,
	 *              • @c compensation_distance is 0 but sample_delta is not,
	 *              • compensation unsupported by resampler, or
	 *              • swr_init() fails when called.
	 */
	public int setCompensation(int sampleDelta, int compensationDistance)
	{
		return setCompensationNative(pointer, sampleDelta, compensationDistance);
	}

	/**
	 * Convert the samples in the input AVFrame and write them to the output AVFrame.
	 *
	 * Input and output AVFrames must have channel_layout, sample_rate and format set.
	 *
	 * If the output AVFrame does not have the data pointers allocated the nb_samples
	 * field will be set using av_frame_get_buffer()
	 * is called to allocate the frame.
	 *
	 * The output AVFrame can be NULL or have fewer allocated samples than required.
	 * In this case, any remaining samples not written to the output will be added
	 * to an internal FIFO buffer, to be returned at the next call to this function
	 * or to swr_convert().
	 *
	 * If converting sample rate, there may be data remaining in the internal
	 * resampling delay buffer. swr_get_delay() tells the number of
	 * remaining samples. To get this data as output, call this function or
	 * swr_convert() with NULL input.
	 *
	 * If the SwrContext configuration does not match the output and
	 * input AVFrame settings the conversion does not take place and depending on
	 * which AVFrame is not matching AVERROR_OUTPUT_CHANGED, AVERROR_INPUT_CHANGED
	 * or the result of a bitwise-OR of them is returned.

	 * @param output          output AVFrame
	 * @param input           input AVFrame
	 * @return                0 on success, AVERROR on failure or nonmatching
	 *                        configuration.
	 */
	public int convertFrame(AVFrame output, AVFrame input)
	{
		return convertFrameNative(pointer, output.getPointer(), input.getPointer());
	}

	/**
	 * Configure or reconfigure the SwrContext using the information
	 * provided by the AVFrames.
	 *
	 * The original resampling context is reset even on failure.
	 * The function calls swr_close() internally if the context is open.
	 *           audio resample context
	 * @param output          output AVFrame
	 * @param input           input AVFrame
	 * @return                0 on success, AVERROR on failure.
	 */
	public int configFrame(AVFrame output, AVFrame input)
	{
		return configFrameNative(pointer, output.getPointer(), input.getPointer());
	}



	public static native long getAVClassNative();
	public static native long createNative();

	public static native int initNative(long pointer);
	public static native int isInitializedNative(long pointer);
	public static native void setOptionsNative(long pointer, long outChannelLayout, int outSampleFormat, int outSampleRate,
											   long inChannelLayout, int inSampleFormat, int inSampleRate);
	public static native void freeNative(long pointer);
	public static native void closeNative(long pointer);
	public static native long nextPtsNative(long pointer, long pts);
	public static native int setCompensationNative(long pointer, int sampleDelta, int compensationDistance);
	public static native int convertFrameNative(long pointer, long output, long input);
	public static native int configFrameNative(long pointer, long output, long input);
}
