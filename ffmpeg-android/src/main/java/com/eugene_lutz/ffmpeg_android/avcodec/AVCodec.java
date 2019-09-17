package com.eugene_lutz.ffmpeg_android.avcodec;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.FFmpegAndroid;
import com.eugene_lutz.ffmpeg_android.avutil.AVMediaType;
import com.eugene_lutz.ffmpeg_android.avutil.AVUtilHelper;

public class AVCodec extends CStructWrapper
{
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

	//endregion
}
