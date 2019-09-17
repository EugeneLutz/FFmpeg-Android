package com.eugene_lutz.ffmpeg_android.avcodec;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.avutil.AVFrame;

public class AVCodecContext extends CStructWrapper
{
	public AVCodecContext(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}

	@Override
	protected void finalize() /*throws Throwable*/
	{
		switch (allocationType)
		{
			case FROM_INSTANCE: break;
			case ALLOC: free(pointer); break;
			default: break;
		}
	}

	public static AVCodecContext from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static AVCodecContext from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVCodecContext(pointer, allocationType, allocationFlag);
	}

	public static AVCodecContext create(AVCodec codec)
	{
		final long contextPointer = alloc3(codec.getPointer());
		return from(contextPointer, AllocationType.ALLOC, 0);
	}



	/**
	 * Fill the codec context based on the values from the supplied codec
	 * parameters. Any allocated fields in codec that have a corresponding field in
	 * par are freed and replaced with duplicates of the corresponding field in par.
	 * Fields in codec that do not have a counterpart in par are not touched.
	 *
	 * @return >= 0 on success, a negative AVERROR code on failure.
	 */
	public int fillFromParameters(AVCodecParameters parameters)
	{
		return fillFromParametersNative(pointer, parameters.getPointer());
	}

	/**
	 * Supply raw packet data as input to a decoder.
	 *
	 * Internally, this call will copy relevant AVCodecContext fields, which can
	 * influence decoding per-packet, and apply them when the packet is actually
	 * decoded. (For example AVCodecContext.skip_frame, which might direct the
	 * decoder to drop the frame contained by the packet sent with this function.)
	 *
	 * @warning The input buffer, avpkt->data must be AV_INPUT_BUFFER_PADDING_SIZE
	 *          larger than the actual read bytes because some optimized bitstream
	 *          readers read 32 or 64 bits at once and could read over the end.
	 *
	 * @warning Do not mix this API with the legacy API (like avcodec_decode_video2())
	 *          on the same AVCodecContext. It will return unexpected results now
	 *          or in future libavcodec versions.
	 *
	 * @note The AVCodecContext MUST have been opened with @ref avcodec_open2()
	 *       before packets may be fed to the decoder.
	 *
	 * @param[in] avpkt The input AVPacket. Usually, this will be a single video
	 *                  frame, or several complete audio frames.
	 *                  Ownership of the packet remains with the caller, and the
	 *                  decoder will not write to the packet. The decoder may create
	 *                  a reference to the packet data (or copy it if the packet is
	 *                  not reference-counted).
	 *                  Unlike with older APIs, the packet is always fully consumed,
	 *                  and if it contains multiple frames (e.g. some audio codecs),
	 *                  will require you to call avcodec_receive_frame() multiple
	 *                  times afterwards before you can send a new packet.
	 *                  It can be NULL (or an AVPacket with data set to NULL and
	 *                  size set to 0); in this case, it is considered a flush
	 *                  packet, which signals the end of the stream. Sending the
	 *                  first flush packet will return success. Subsequent ones are
	 *                  unnecessary and will return AVERROR_EOF. If the decoder
	 *                  still has frames buffered, it will return them after sending
	 *                  a flush packet.
	 *
	 * @return 0 on success, otherwise negative error code:
	 *      AVERROR(EAGAIN):   input is not accepted in the current state - user
	 *                         must read output with avcodec_receive_frame() (once
	 *                         all output is read, the packet should be resent, and
	 *                         the call will not fail with EAGAIN).
	 *      AVERROR_EOF:       the decoder has been flushed, and no new packets can
	 *                         be sent to it (also returned if more than 1 flush
	 *                         packet is sent)
	 *      AVERROR(EINVAL):   codec not opened, it is an encoder, or requires flush
	 *      AVERROR(ENOMEM):   failed to add packet to internal queue, or similar
	 *      other errors: legitimate decoding errors
	 */
	public int sendPacket(AVPacket packet)
	{
		return sendPacketNative(pointer, packet.getPointer());
	}

	/**
	 * Supply a raw video or audio frame to the encoder. Use avcodec_receive_packet()
	 * to retrieve buffered output packets.
	 *
	 * @param[in] frame AVFrame containing the raw audio or video frame to be encoded.
	 *                  Ownership of the frame remains with the caller, and the
	 *                  encoder will not write to the frame. The encoder may create
	 *                  a reference to the frame data (or copy it if the frame is
	 *                  not reference-counted).
	 *                  It can be NULL, in which case it is considered a flush
	 *                  packet.  This signals the end of the stream. If the encoder
	 *                  still has packets buffered, it will return them after this
	 *                  call. Once flushing mode has been entered, additional flush
	 *                  packets are ignored, and sending frames will return
	 *                  AVERROR_EOF.
	 *
	 *                  For audio:
	 *                  If AV_CODEC_CAP_VARIABLE_FRAME_SIZE is set, then each frame
	 *                  can have any number of samples.
	 *                  If it is not set, frame->nb_samples must be equal to
	 *                  avctx->frame_size for all frames except the last.
	 *                  The final frame may be smaller than avctx->frame_size.
	 * @return 0 on success, otherwise negative error code:
	 *      AVERROR(EAGAIN):   input is not accepted in the current state - user
	 *                         must read output with avcodec_receive_packet() (once
	 *                         all output is read, the packet should be resent, and
	 *                         the call will not fail with EAGAIN).
	 *      AVERROR_EOF:       the encoder has been flushed, and no new frames can
	 *                         be sent to it
	 *      AVERROR(EINVAL):   codec not opened, refcounted_frames not set, it is a
	 *                         decoder, or requires flush
	 *      AVERROR(ENOMEM):   failed to add packet to internal queue, or similar
	 *      other errors: legitimate decoding errors
	 */
	public int sendFrame(AVFrame frame)
	{
		return sendFrameNative(pointer, frame.getPointer());
	}



	private static native void free(long pointer);
	private static native long alloc3(long codecPointer);

	private static native int fillFromParametersNative(long pointer, long parameters);
	private static native int sendPacketNative(long pointer, long packetPointer);
	private static native int sendFrameNative(long pointer, long framePointer);
}
