package com.eugene_lutz.ffmpeg_android.avformat;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

import java.util.ArrayList;

public class AVOutputFormat extends CStructWrapper
{
	public static class Iterator
	{
		private long pointer;

		private Iterator(long pointer)
		{
			this.pointer = pointer;
		}

		@Override
		protected void finalize() /*throws Throwable*/
		{
			releaseIteratorNative(pointer);
		}

		public static Iterator create()
		{
			long pointer = createIteratorNative();
			if (pointer == 0)
			{
				return null;
			}

			return new Iterator(pointer);
		}

		public AVOutputFormat iterate()
		{
			long demuxerPointer = iterateNative(pointer);

			return AVOutputFormat.from(demuxerPointer, AllocationType.FROM_INSTANCE, 0);
		}

		private static native long createIteratorNative();
		private static native void releaseIteratorNative(long pointer);
		private static native long iterateNative(long pointer);
	}



	private AVOutputFormat(long pointer, AllocationType allocationType, int flags)
	{
		super(pointer, allocationType, flags);
	}


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
	 * can use flags: AVFMT_NOFILE, AVFMT_NEEDNUMBER,
	 * AVFMT_GLOBALHEADER, AVFMT_NOTIMESTAMPS, AVFMT_VARIABLE_FPS,
	 * AVFMT_NODIMENSIONS, AVFMT_NOSTREAMS, AVFMT_ALLOW_FLUSH,
	 * AVFMT_TS_NONSTRICT, AVFMT_TS_NEGATIVE
	 */
	public int getFlags()
	{
		return getFlagsNative(pointer);
	}

	/**
	 * can use flags: AVFMT_NOFILE, AVFMT_NEEDNUMBER,
	 * AVFMT_GLOBALHEADER, AVFMT_NOTIMESTAMPS, AVFMT_VARIABLE_FPS,
	 * AVFMT_NODIMENSIONS, AVFMT_NOSTREAMS, AVFMT_ALLOW_FLUSH,
	 * AVFMT_TS_NONSTRICT, AVFMT_TS_NEGATIVE
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



	public static AVOutputFormat from(long pointer, AllocationType allocationType, int flags)
	{
		return pointer == 0 ? null : new AVOutputFormat(pointer, allocationType, flags);
	}

	/**
	 * Генерирует список всех доступных мультиплексоров
	 * */
	public static AVOutputFormat[] listAvailable()
	{
		AVOutputFormat[] dummy = new AVOutputFormat[0];
		AVOutputFormat.Iterator iterator = AVOutputFormat.Iterator.create();
		if (iterator == null)
		{
			return dummy;
		}

		ArrayList<AVOutputFormat> formats = new ArrayList<>();
		for (;;)
		{
			AVOutputFormat inputFormat = iterator.iterate();
			if (inputFormat == null)
			{
				break;
			}
			formats.add(inputFormat);
		}


		return formats.toArray(dummy);
	}

	private static native String getNameNative(long pointer);
	private static native String getLongNameNative(long pointer);
	private static native String getExtensionsNative(long pointer);
	private static native int getFlagsNative(long pointer);
	private static native void setFlagsNative(long pointer, int flags);
	private static native String getMimeTypesNative(long pointer);
}
