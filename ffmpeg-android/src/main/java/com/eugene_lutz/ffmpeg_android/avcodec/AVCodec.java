package com.eugene_lutz.ffmpeg_android.avcodec;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

public class AVCodec extends CStructWrapper
{
	public AVCodec(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}



	public static AVCodec from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static AVCodec from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVCodec(pointer, allocationType, allocationFlag);
	}

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



	public AVCodecContext createContext()
	{
		return AVCodecContext.create(this);
	}



	private static native long findDecoderNative(long id);
	private static native long findDecoderByNameNative(String name);
}
