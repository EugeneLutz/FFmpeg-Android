package com.eugene_lutz.ffmpeg_android.avutil;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

import java.nio.ByteBuffer;

/**
 * This structure describes decoded (raw) audio or video data.
 *
 * AVFrame must be allocated using av_frame_alloc(). Note that this only
 * allocates the AVFrame itself, the buffers for the data must be managed
 * through other means (see below).
 * AVFrame must be freed with av_frame_free().
 *
 * AVFrame is typically allocated once and then reused multiple times to hold
 * different data (e.g. a single AVFrame to hold frames received from a
 * decoder). In such a case, av_frame_unref() will free any references held by
 * the frame and reset it to its original clean state before it
 * is reused again.
 *
 * The data described by an AVFrame is usually reference counted through the
 * AVBuffer API. The underlying buffer references are stored in AVFrame.buf /
 * AVFrame.extended_buf. An AVFrame is considered to be reference counted if at
 * least one reference is set, i.e. if AVFrame.buf[0] != NULL. In such a case,
 * every single data plane must be contained in one of the buffers in
 * AVFrame.buf or AVFrame.extended_buf.
 * There may be a single buffer for all the data, or one separate buffer for
 * each plane, or anything in between.
 *
 * sizeof(AVFrame) is not a part of the public ABI, so new fields may be added
 * to the end with a minor bump.
 *
 * Fields can be accessed through AVOptions, the name string used, matches the
 * C structure field name for fields accessible through AVOptions. The AVClass
 * for AVFrame can be obtained from avcodec_get_frame_class()
 */
public class AVFrame extends CStructWrapper
{
	//region Constructor, Destructor, etc...

	private AVFrame(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}

	@Override
	protected void finalizeDefault()
	{
		freeNative(pointer);
	}

	private static AVFrame from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE);
	}

	private static AVFrame from(long pointer, AllocationType allocationType)
	{
		return pointer == 0 ? null : new AVFrame(pointer, allocationType, 0);
	}

	//endregion


	//region Static methods

	public static AVFrame create()
	{
		final long framePointer = createNative();
		return from(framePointer, AllocationType.ALLOC);
	}

	//endregion



	//region Instance methods

	//endregion



	//region Getters/Setters

	/**
	 * Returns byte buffer of picture/channel plane.
	 * This might be different from the first allocated byte
	 *
	 * Some decoders access areas outside 0,0 - width,height, please
	 * see avcodec_align_dimensions2(). Some filters and swscale can read
	 * up to 16 bytes beyond the planes, if these filters are to be used,
	 * then 16 extra bytes must be allocated.
	 *
	 * NOTE: Except for hwaccel formats, pointers not needed by the format
	 * MUST be set to NULL.
	 * @param planeIndex index of plane.
	 * @return           byte buffer of picture/channel plane.
	 */
	public ByteBuffer getData(int planeIndex)
	{
		return getDataNative(pointer, planeIndex);
	}

	/**
	 * For video, size in bytes of each picture line.
	 * For audio, size in bytes of each plane.
	 *
	 * For audio, only linesize[0] may be set. For planar audio, each channel
	 * plane must be the same size.
	 *
	 * For video the linesizes should be multiples of the CPUs alignment
	 * preference, this is 16 or 32 for modern desktop CPUs.
	 * Some code requires such alignment other code can be slower without
	 * correct alignment, for yet other it makes no difference.
	 *
	 * Note: The linesize may be larger than the size of usable data -- there
	 * may be extra padding present for performance reasons.
	 * @param planeIndex index of plane.
	 * @return           size in bytes.
	 */
	public int getLineSize(int planeIndex)
	{
		return getLineSizeNative(pointer, planeIndex);
	}

	/**
	 * Used with video frames only. The coded width (in pixels) of the video frame,
	 * i.e. the size of the rectangle that contains some well-defined values.
	 *
	 * Note: The part of the frame intended for display/presentation is further
	 * restricted by the cropping "Cropping rectangle".
	 *
	 * @return width of frame.
	 */
	public int getWidth()
	{
		return getWidthNative(pointer);
	}

	/**
	 * Used with video frames only. The coded height (in pixels) of the video frame,
	 * i.e. the size of the rectangle that contains some well-defined values.
	 *
	 * Note: The part of the frame intended for display/presentation is further
	 * restricted by the cropping "Cropping rectangle".
	 *
	 * @return height of frame.
	 */
	public int getHeight()
	{
		return getHeightNative(pointer);
	}

	/**
	 * Returns number of audio samples (per channel) described by this frame.
	 * @return number of audio samples.
	 */
	public int getNumberOfSamples()
	{
		return getNumberOfSamplesNative(pointer);
	}

	/**
	 * Returns format for video frame.
	 * @return format for video frame.
	 */
	public AVPixelFormat getPixelFormat()
	{
		final long pixelFormatLong = getPixelFormatNative(pointer);
		return AVUtilHelper.longToAVPixelFormat(pixelFormatLong);
	}

	/**
	 * Returns format for audio frame.
	 * @return format for audio frame.
	 */
	public AVSampleFormat getSampleFormat()
	{
		final long sampleFormatLong = getSampleFormatNative(pointer);
		return AVUtilHelper.longToAVSampleFormat(sampleFormatLong);
	}

	/**
	 * Returns whether frame is key frame.
	 * @return whether frame is key frame.
	 */
	public boolean isKeyFrame()
	{
		return getKeyFrameNative(pointer) == 1;
	}
	/**
	 * Returns picture type of the frame.
	 * @return picture type of the frame.
	 */
	public AVPictureType getPictureType()
	{
		final long pictureTypeLong = getPictureTypeNative(pointer);
		return AVUtilHelper.longToAVPictureType(pictureTypeLong);
	}

	//endregion


	//region Native methods

	private static native long createNative();
	private static native void freeNative(long pointer);
	private static native ByteBuffer getDataNative(long pointer, int planeIndex);
	private static native int getLineSizeNative(long pointer, int planeIndex);
	private static native int getWidthNative(long pointer);
	private static native int getHeightNative(long pointer);
	private static native int getNumberOfSamplesNative(long pointer);
	private static native long getPixelFormatNative(long pointer);
	private static native long getSampleFormatNative(long pointer);
	private static native int getKeyFrameNative(long pointer);
	private static native long getPictureTypeNative(long pointer);

	//endregion
}
