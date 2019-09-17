package com.eugene_lutz.ffmpeg_android.avcodec;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.avutil.AVRational;
import com.eugene_lutz.ffmpeg_android.avutil.RawData;

public class AVPacket extends CStructWrapper
{
	//region Constructor, Destructor, etc...

	private AVPacket(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}

	@Override
	protected void finalizeDefault()
	{
		releaseNative(pointer);
	}

	public static AVPacket from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static AVPacket from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVPacket(pointer, allocationType, allocationFlag);
	}

	//endregion


	//region Static methods

	public static AVPacket create()
	{
		final long pointer = createNative();
		if (pointer == 0)
		{
			return null;
		}

		return AVPacket.from(pointer, AllocationType.ALLOC, 0);
	}

	//endregion



	//region Getters/Setters

	public long getPointer()
	{
		return pointer;
	}

	public int getStreamIndex()
	{
		return getStreamIndexNative(pointer);
	}

	public long getPresentationTimestamp()
	{
		return getPresentationTimestampNative(pointer);
	}

	public long getDecompressionTimestamp()
	{
		return getDecompressionTimestampNative(pointer);
	}

	public long getDuration()
	{
		return getDurationNative(pointer);
	}

	public long getPosition()
	{
		return getPositionNative(pointer);
	}



	public void setStreamIndex(int index)
	{
		setStreamIndexNative(pointer, index);
	}

	public void setPresentationTimestamp(long value)
	{
		setPresentationTimestampNative(pointer, value);
	}

	public void setDecompressionTimestamp(long value)
	{
		setDecompressionTimestampNative(pointer, value);
	}

	public void setDuration(long value)
	{
		setDurationNative(pointer, value);
	}

	public void setPosition(long value)
	{
		setPositionNative(pointer, value);
	}

	//endregion



	//region Instance methods

	/**
	 * Create a new packet that references the same data as src.
	 *
	 * This is a shortcut for AVPacket.create()+.ref().
	 *
	 * @return newly created AVPacket on success, null on error.
	 */
	public AVPacket cloneInstance()
	{
		final long packetPointer = cloneNative(pointer);
		return from(packetPointer);
	}

	/**
	 * Initialize optional fields of a packet with default values.
	 *
	 * Note, this does not touch the data and size members, which have to be
	 * initialized separately.
	 */
	public void init()
	{
		initNative(pointer);
	}

	/**
	 * Allocate the payload of a packet and initialize its fields with
	 * default values.
	 *
	 * @param size wanted payload size
	 * @return 0 if OK, AVERROR_xxx otherwise
	 */
	public int newPacket(int size)
	{
		return newPacketNative(pointer, size);
	}

	/**
	 * Reduce packet size, correctly zeroing padding
	 *
	 * @param size new size
	 */
	public void shrinkPacket(int size)
	{
		shrinkPacketNative(pointer, size);
	}

	/**
	 * Increase packet size, correctly zeroing padding
	 *
	 * @param growBy number of bytes by which to increase the size of the packet
	 */
	public int growPacket(int growBy)
	{
		return growPacketNative(pointer, growBy);
	}

	/**
	 * Initialize a reference-counted packet from av_malloc()ed data.
	 * 		This function will set the data, size, buf and destruct fields,
	 * 		all others are left untouched.
	 * @param data Data allocated by RawData.malloc() to be used as packet data. If this
	 *        function returns successfully, the data is owned by the underlying AVBuffer.
	 *        The caller may not access the data through other means.
	 * @param size size of data in bytes, without the padding. I.e. the full buffer
	 *        size is assumed to be size + AV_INPUT_BUFFER_PADDING_SIZE.
	 *
	 * @return 0 on success, a negative AVERROR on error
	 */
	public int packetFromData(RawData data, int size)
	{
		final long dataPointer = data.getPointer();
		return packetFromDataNative(pointer, dataPointer, size);
	}

	/**
	 * Setup a new reference to the data described by a given packet
	 *
	 * If src is reference-counted, setup this object as a new reference to the
	 * buffer in src. Otherwise allocate a new buffer in this object and copy the
	 * data from src into it.
	 *
	 * All the other fields are copied from src.
	 *
	 * @param src Source packet
	 *
	 * @return 0 on success, a negative AVERROR on error.
	 */
	public int ref(AVPacket src)
	{
		return refNative(pointer, src.pointer);
	}

	/**
	 * Wipe the packet.
	 * Unreference the buffer referenced by the packet and reset the
	 * remaining packet fields to their default values.
	 */
	public void unref()
	{
		unrefNative(pointer);
	}

	/**
	 * Wipe the packet.
	 * Unreference the buffer referenced by the packet and reset the
	 * remaining packet fields to their default values.
	 */
	public int copyProps(AVPacket source)
	{
		return copyPropsNative(pointer, source.pointer);
	}

	/**
	 * Create a writable reference for the data described by this packet,
	 * avoiding data copy if possible.
	 *
	 * @return 0 on success, a negative AVERROR on failure. On failure, the
	 *         packet is unchanged.
	 */
	public int makeWritable()
	{
		return makeWritableNative(pointer);
	}

	/**
	 * Convert valid timing fields (timestamps / durations) in a packet from one
	 * timebase to another. Timestamps with unknown values (AV_NOPTS_VALUE) will be
	 * ignored.
	 *
	 * @param tb_src source timebase, in which the timing fields in pkt are
	 *               expressed
	 * @param tb_dst destination timebase, to which the timing fields will be
	 *               converted
	 */
	public void rescaleTS(AVRational tb_src, AVRational tb_dst)
	{
		rescaleTSNative(pointer, tb_src.getPointer(), tb_dst.getPointer());
	}

	//endregion



	//region Native methods

	//region other

	private static native long createNative();
	private static native void releaseNative(long pointer);

	private static native int getStreamIndexNative(long pointer);
	private static native long getPresentationTimestampNative(long pointer);
	private static native long getDecompressionTimestampNative(long pointer);
	private static native long getDurationNative(long pointer);
	private static native long getPositionNative(long pointer);

	private static native void setStreamIndexNative(long pointer, int index);
	private static native void setPresentationTimestampNative(long pointer, long value);
	private static native void setDecompressionTimestampNative(long pointer, long value);
	private static native void setDurationNative(long pointer, long value);
	private static native void setPositionNative(long pointer, long value);

	//endregion

	private static native long cloneNative(long pointer);
	private static native void initNative(long pointer);
	private static native int newPacketNative(long pointer, int size);
	private static native void shrinkPacketNative(long pointer, int size);
	private static native int growPacketNative(long pointer, int growBy);
	private static native int packetFromDataNative(long pointer, long dataPointer, int size);
	private static native int refNative(long pointer, long srcPointer);
	private static native void unrefNative(long pointer);
	private static native int copyPropsNative(long pointer, long sourcePointer);
	private static native int makeWritableNative(long pointer);
	private static native void rescaleTSNative(long pointer, long tb_src, long tb_dst);

	//endregion
}
