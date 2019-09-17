package com.eugene_lutz.ffmpeg_android.avformat;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

import java.util.ArrayList;

public class AVInputFormat extends CStructWrapper
{
	//region Class related stuff

	public static class Iterator
	{
		private final long pointer;

		private Iterator(long pointer)
		{
			this.pointer = pointer;
		}

		@Override
		protected void finalize() /*throws Throwable*/
		{
			releaseNative(pointer);
		}

		public static Iterator create()
		{
			final long pointer = createNative();
			if (pointer == 0)
			{
				return null;
			}

			return new Iterator(pointer);
		}

		public AVInputFormat iterate()
		{
			final long demuxerPointer = iterateNative(pointer);
			return AVInputFormat.from(demuxerPointer, AllocationType.FROM_INSTANCE, 0);
		}

		private static native long createNative();
		private static native void releaseNative(long pointer);
		private static native long iterateNative(long pointer);
	}

	//endregion



	//region Constructor, Destructor, etc...

	private AVInputFormat(long pointer, AllocationType allocationType, int flags)
	{
		super(pointer, allocationType, flags);
	}

	public static AVInputFormat from(long pointer, AllocationType allocationType, int flags)
	{
		return pointer == 0 ? null : new AVInputFormat(pointer, allocationType, flags);
	}

	//endregion



	//region Static methods

	/**
	 * Генерирует список всех доступных демультиплексоров
	 * */
	public static AVInputFormat[] listAvailable()
	{
		final AVInputFormat[] dummy = new AVInputFormat[0];
		final Iterator iterator = Iterator.create();
		if (iterator == null)
		{
			return dummy;
		}

		final ArrayList<AVInputFormat> formats = new ArrayList<>();
		for (;;)
		{
			final AVInputFormat inputFormat = iterator.iterate();
			if (inputFormat == null)
			{
				break;
			}
			formats.add(inputFormat);
		}


		return formats.toArray(dummy);
	}

	//endregion



	//region Instance methods

	//endregion



	//region Getters/Setters

	/**
	 * A comma separated list of short names for the format.
	 */
	public String getName()
	{
		return getNameNative(pointer);
	}

	/**
	 * Descriptive name for the format, meant to be more human-readable
	 * than name.
	 */
	public String getLongName()
	{
		return getLongNameNative(pointer);
	}

	/**
	 * Returns comma-separated filename extensions. If extensions are defined,
	 * then no probe is done. You should
	 * usually not use extension format guessing because it is not
	 * reliable enough
	 */
	public String getExtensions()
	{
		return getExtensionsNative(pointer);
	}

	/**
	 * Can use flags: AVFMT_NOFILE, AVFMT_NEEDNUMBER, AVFMT_SHOW_IDS,
	 * AVFMT_NOTIMESTAMPS, AVFMT_GENERIC_INDEX, AVFMT_TS_DISCONT, AVFMT_NOBINSEARCH,
	 * AVFMT_NOGENSEARCH, AVFMT_NO_BYTE_SEEK, AVFMT_SEEK_TO_PTS.
	 */
	public int getFlags()
	{
		return getFlagsNative(pointer);
	}

	/**
	 * Can use flags: AVFMT_NOFILE, AVFMT_NEEDNUMBER, AVFMT_SHOW_IDS,
	 * AVFMT_NOTIMESTAMPS, AVFMT_GENERIC_INDEX, AVFMT_TS_DISCONT, AVFMT_NOBINSEARCH,
	 * AVFMT_NOGENSEARCH, AVFMT_NO_BYTE_SEEK, AVFMT_SEEK_TO_PTS.
	 */
	public void setFlags(int flags)
	{
		setFlagsNative(pointer, flags);
	}

	/**
	 * Comma-separated list of mime types.
	 * It is used check for matching mime types while probing.
	 */
	public String getMimeTypes()
	{
		return getMimeTypesNative(pointer);
	}

	//endregion



	//region Native methods

	private static native String getNameNative(long pointer);
	private static native String getLongNameNative(long pointer);
	private static native String getExtensionsNative(long pointer);
	private static native int getFlagsNative(long pointer);
	private static native void setFlagsNative(long pointer, int flags);
	private static native String getMimeTypesNative(long pointer);

	//endregion
}
