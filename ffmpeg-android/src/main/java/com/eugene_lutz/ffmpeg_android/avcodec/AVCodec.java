package com.eugene_lutz.ffmpeg_android.avcodec;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.FFmpegAndroid;
import com.eugene_lutz.ffmpeg_android.avutil.AVMediaType;
import com.eugene_lutz.ffmpeg_android.avutil.AVUtilHelper;

public class AVCodec extends CStructWrapper
{
	static {
		FFmpegAndroid.loadLibraries();
	}

	//region Class related stuff

	//region Codec capabilities

	/**
	 * Decoder can use draw_horiz_band callback.
	 */
	public static final int AV_CODEC_CAP_DRAW_HORIZ_BAND = (1 << 0);
	/**
	 * Codec uses get_buffer() for allocating buffers and supports custom allocators.
	 * If not set, it might not use get_buffer() at all or use operations that
	 * assume the buffer was allocated by avcodec_default_get_buffer.
	 */
	public static final int AV_CODEC_CAP_DR1 = (1 << 1);
	public static final int AV_CODEC_CAP_TRUNCATED = (1 << 3);
	/**
	 * Encoder or decoder requires flushing with NULL input at the end in order to
	 * give the complete and correct output.
	 *
	 * NOTE: If this flag is not set, the codec is guaranteed to never be fed with
	 *       with NULL data. The user can still send NULL data to the public encode
	 *       or decode function, but libavcodec will not pass it along to the codec
	 *       unless this flag is set.
	 *
	 * Decoders:
	 * The decoder has a non-zero delay and needs to be fed with avpkt-{@literal >}data=NULL,
	 * avpkt-{@literal >}size=0 at the end to get the delayed data until the decoder no longer
	 * returns frames.
	 *
	 * Encoders:
	 * The encoder needs to be fed with NULL data at the end of encoding until the
	 * encoder no longer returns data.
	 *
	 * NOTE: For encoders implementing the AVCodec.encode2() function, setting this
	 *       flag also means that the encoder must set the pts and duration for
	 *       each output packet. If this flag is not set, the pts and duration will
	 *       be determined by libavcodec from the input frame.
	 */
	public static final int AV_CODEC_CAP_DELAY = (1 << 5);
	/**
	 * Codec can be fed a final frame with a smaller size.
	 * This can be used to prevent truncation of the last audio samples.
	 */
	public static final int AV_CODEC_CAP_SMALL_LAST_FRAME = (1 << 6);

	/**
	 * Codec can output multiple frames per AVPacket
	 * Normally demuxers return one frame at a time, demuxers which do not do
	 * are connected to a parser to split what they return into proper frames.
	 * This flag is reserved to the very rare category of codecs which have a
	 * bitstream that cannot be split into frames without timeconsuming
	 * operations like full decoding. Demuxers carrying such bitstreams thus
	 * may return multiple frames in a packet. This has many disadvantages like
	 * prohibiting stream copy in many cases thus it should only be considered
	 * as a last resort.
	 */
	public static final int AV_CODEC_CAP_SUBFRAMES = (1 << 8);
	/**
	 * Codec is experimental and is thus avoided in favor of non experimental
	 * encoders
	 */
	public static final int AV_CODEC_CAP_EXPERIMENTAL = (1 << 9);
	/**
	 * Codec should fill in channel configuration and samplerate instead of container
	 */
	public static final int AV_CODEC_CAP_CHANNEL_CONF = (1 << 10);
	/**
	 * Codec supports frame-level multithreading.
	 */
	public static final int AV_CODEC_CAP_FRAME_THREADS = (1 << 12);
	/**
	 * Codec supports slice-based (or partition-based) multithreading.
	 */
	public static final int AV_CODEC_CAP_SLICE_THREADS = (1 << 13);
	/**
	 * Codec supports changed parameters at any point.
	 */
	public static final int AV_CODEC_CAP_PARAM_CHANGE = (1 << 14);
	/**
	 * Codec supports avctx->thread_count == 0 (auto).
	 */
	public static final int AV_CODEC_CAP_AUTO_THREADS = (1 << 15);
	/**
	 * Audio encoder supports receiving a different number of samples in each call.
	 */
	public static final int AV_CODEC_CAP_VARIABLE_FRAME_SIZE = (1 << 16);
	/**
	 * Decoder is not a preferred choice for probing.
	 * This indicates that the decoder is not a good choice for probing.
	 * It could for example be an expensive to spin up hardware decoder,
	 * or it could simply not provide a lot of useful information about
	 * the stream.
	 * A decoder marked with this flag should only be used as last resort
	 * choice for probing.
	 */
	public static final int AV_CODEC_CAP_AVOID_PROBING = (1 << 17);
	/**
	 * Codec is intra only.
	 */
	public static final int AV_CODEC_CAP_INTRA_ONLY = 0x40000000;
	/**
	 * Codec is lossless.
	 */
	public static final int AV_CODEC_CAP_LOSSLESS = 0x80000000;

	/**
	 * Codec is backed by a hardware implementation. Typically used to
	 * identify a non-hwaccel hardware decoder. For information about hwaccels, use
	 * avcodec_get_hw_config() instead.
	 */
	public static final int AV_CODEC_CAP_HARDWARE = (1 << 18);

	/**
	 * Codec is potentially backed by a hardware implementation, but not
	 * necessarily. This is used instead of AV_CODEC_CAP_HARDWARE, if the
	 * implementation provides some sort of internal fallback.
	 */
	public static final int AV_CODEC_CAP_HYBRID = (1 << 19);

	/**
	 * This codec takes the reordered_opaque field from input AVFrames
	 * and returns it in the corresponding field in AVCodecContext after
	 * encoding.
	 */
	public static final int AV_CODEC_CAP_ENCODER_REORDERED_OPAQUE = (1 << 20);

	//endregion

	//endregion



	//region Constructor, Destructor, etc...

	private AVCodec(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}



	static AVCodec from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	static AVCodec from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVCodec(pointer, allocationType, allocationFlag);
	}

	//endregion



	//region Static methods

	/**
	 * Find a registered decoder with a matching codec ID.
	 *
	 * @param id AVCodecID of the requested decoder
	 * @return A decoder if one was found, NULL otherwise.
	 */
	public static AVCodec findDecoder(AVCodecID id)
	{
		final long codecId = AVCodecHelper.AVCodecIdToLong(id);
		final long decoderPointer = findDecoderNative(codecId);
		return from(decoderPointer, AllocationType.FROM_INSTANCE, 0);
	}

	/**
	 * Find a registered decoder with the specified name.
	 *
	 * @param name name of the requested decoder
	 * @return A decoder if one was found, null otherwise.
	 */
	public static AVCodec findDecoderByName(String name)
	{
		final long decoderPointer = findDecoderByNameNative(name);
		return from(decoderPointer, AllocationType.FROM_INSTANCE, 0);
	}

	/**
	 * Find a registered encoder with a matching codec ID.
	 *
	 * @param id AVCodecID of the requested encoder
	 * @return A encoder if one was found, NULL otherwise.
	 */
	public static AVCodec findEncoder(AVCodecID id)
	{
		final long codecId = AVCodecHelper.AVCodecIdToLong(id);
		final long encoderPointer = findEncoderNative(codecId);
		return from(encoderPointer, AllocationType.FROM_INSTANCE, 0);
	}

	/**
	 * Find a registered encoder with the specified name.
	 *
	 * @param name name of the requested encoder
	 * @return A encoder if one was found, null otherwise.
	 */
	public static AVCodec findEncoderByName(String name)
	{
		final long encoderPointer = findEncoderByNameNative(name);
		return from(encoderPointer, AllocationType.FROM_INSTANCE, 0);
	}

	/**
	 * Returns all existing in FFmpeg codec capabilities.
	 * @return All existing in FFmpeg codec capabilities.
	 */
	public static int[] getAllExistingCapabilities()
	{
		return new int[] {
				AV_CODEC_CAP_DRAW_HORIZ_BAND,
				AV_CODEC_CAP_DR1,
				AV_CODEC_CAP_TRUNCATED,
				AV_CODEC_CAP_DELAY,
				AV_CODEC_CAP_SMALL_LAST_FRAME,
				AV_CODEC_CAP_SUBFRAMES,
				AV_CODEC_CAP_EXPERIMENTAL,
				AV_CODEC_CAP_CHANNEL_CONF,
				AV_CODEC_CAP_FRAME_THREADS,
				AV_CODEC_CAP_SLICE_THREADS,
				AV_CODEC_CAP_PARAM_CHANGE,
				AV_CODEC_CAP_AUTO_THREADS,
				AV_CODEC_CAP_VARIABLE_FRAME_SIZE,
				AV_CODEC_CAP_AVOID_PROBING,
				AV_CODEC_CAP_INTRA_ONLY,
				AV_CODEC_CAP_LOSSLESS,
				AV_CODEC_CAP_HARDWARE,
				AV_CODEC_CAP_HYBRID,
				AV_CODEC_CAP_ENCODER_REORDERED_OPAQUE
		};
	}

	//endregion



	//region Instance methods

	public AVCodecContext createContext()
	{
		return AVCodecContext.create(this);
	}

	//endregion


	//region Getters/Setters

	public String getCodecName()
	{
		return getCodecNameNative(pointer);
	}

	public String getCodecDescription()
	{
		return getCodecDescriptionNative(pointer);
	}

	/**
	 * Returns media type of codec.
	 * @return Media type of codec.
	 */
	public AVMediaType getCodecMediaType()
	{
		final long mediaTypeIndex = getCodecMediaTypeNative(pointer);
		return AVUtilHelper.longToAVMediaType(mediaTypeIndex);
	}

	public AVCodecID getCodecId()
	{
		final long index = getCodecIdNative(pointer);
		return AVCodecHelper.longToAVCodecId(index);
	}

	/**
	 * Returns codec capabilities. See AV_CODEC_CAP_*.
	 * @return Codec capabilities.
	 */
	public int getCodecCapabilities()
	{
		return getCodecCapabilitiesNative(pointer);
	}


	/**
	 * Returns group name of the codec implementation.
	 * This is a short symbolic name of the wrapper backing this codec. A
	 * wrapper uses some kind of external implementation for the codec, such
	 * as an external library, or a codec implementation provided by the OS or
	 * the hardware.
	 * If this field is NULL, this is a builtin, libavcodec native codec.
	 * If non-NULL, this will be the suffix in AVCodec.name in most cases
	 * (usually AVCodec.name will be of the form "<codec_name>_<wrapper_name>").
	 * @return Group name of the codec implementation.
	 */
	public String getCodecGroupName()
	{
		return getCodecGroupNameNative(pointer);
	}

	/**
	 * Returns whether any framerate is supported.
	 * @return Whether any framerate is supported.
	 */
	public boolean isAnyFrameRateSupported()
	{
		return getNumSupportedFrameratesNative(pointer) == 0;
	}

	/**
	 * Returns number of supported framerates.
	 * @return Number of supported framerates.
	 */
	public int getNumSupportedFramerates()
	{
		return getNumSupportedFrameratesNative(pointer);
	}

	//endregion



	//region Native methods

	private static native long findDecoderNative(long id);
	private static native long findDecoderByNameNative(String name);

	private static native long findEncoderNative(long id);
	private static native long findEncoderByNameNative(String name);

	private static native String getCodecNameNative(long pointer);
	private static native String getCodecDescriptionNative(long pointer);
	private static native long getCodecMediaTypeNative(long pointer);
	private static native long getCodecIdNative(long pointer);
	private static native int getCodecCapabilitiesNative(long pointer);
	private static native int getNumSupportedFrameratesNative(long pointer);

	private static native String getCodecGroupNameNative(long pointer);

	//endregion
}
