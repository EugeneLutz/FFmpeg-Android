package com.eugene_lutz.ffmpeg_android.avformat;

import java.util.ArrayList;

public class AVIO
{
	//region Class related stuff

	/**
	 * Seeking works like for a local file.
	 */
	public static final int AVIO_SEEKABLE_NORMAL = (1 << 0);

	/**
	 * Seeking by timestamp with avio_seek_time() is possible.
	 */
	public static final int AVIO_SEEKABLE_TIME = (1 << 1);

	/**
	 * ORing this as the "whence" parameter to a seek function causes it to
	 * return the filesize without seeking anywhere. Supporting this is optional.
	 * If it is not supported then the seek function will return {@literal <} 0.
	 */
	public static final int AVSEEK_SIZE = 0x10000;

	/**
	 * Passing this flag as the "whence" parameter to a seek function causes it to
	 * seek by any means (like reopening and linear reading) or other normally unreasonable
	 * means that can be extremely slow.
	 * This may be ignored by the seek code.
	 */
	public static final int AVSEEK_FORCE = 0x20000;

	/** read-only */
	public static final int AVIO_FLAG_READ =  1;

	/** write-only */
	public static final int AVIO_FLAG_WRITE = 2;

	/** read-write pseudo flag */
	public static final int AVIO_FLAG_READ_WRITE = (AVIO_FLAG_READ | AVIO_FLAG_WRITE);

	/**
	 * Use non-blocking mode.
	 * If this flag is set, operations on the context will return
	 * AVERROR(EAGAIN) if they can not be performed immediately.
	 * If this flag is not set, operations on the context will never return
	 * AVERROR(EAGAIN).
	 * Note that this flag does not affect the opening/connecting of the
	 * context. Connecting a protocol will always block if necessary (e.g. on
	 * network protocols) but never hang (e.g. on busy devices).
	 * Warning: non-blocking protocols is work-in-progress; this flag may be
	 * silently ignored.
	 */
	public static final int AVIO_FLAG_NONBLOCK = 8;

	/**
	 * Use direct mode.
	 * avio_read and avio_write should if possible be satisfied directly
	 * instead of going through a buffer, and avio_seek will always
	 * call the underlying seek function directly.
	 */
	public static final int AVIO_FLAG_DIRECT = 0x8000;


	public static class ProtocolIterator
	{
		private final long pointer;
		private final boolean output;

		ProtocolIterator(long pointer, boolean output)
		{
			this.pointer = pointer;
			this.output = output;
		}

		@Override
		protected void finalize() /*throws Throwable*/
		{
			releaseNative(pointer);
		}

		/**
		 * Iterate through names of available protocols.
		 * @return A string containing the name of current protocol or null.
		 * */
		public String iterate()
		{
			return iterateNative(pointer, output ? 1 : 0);
		}

		/**
		 * Creates iterator through available protocols.
		 * @param output If set to true, creates iterator over output protocols,
		 *               otherwise over input protocols.
		 * */
		public static ProtocolIterator create(boolean output)
		{
			final long pointer = createNative();
			if (pointer == 0)
			{
				return null;
			}

			return new ProtocolIterator(pointer, output);
		}

		private static native long createNative();
		private static native void releaseNative(long pointer);
		private static native String iterateNative(long pointer, int output);
	}

	//endregion



	//region Static methods

	/**
	 * Generates a list of names of available protocols.
	 * @param output If set to true, iterate over output protocols,
	 *               otherwise over input protocols.
	 * @return A string list containing names of available protocols.
	 * */
	public static String[] enumProtocols(boolean output)
	{
		final String[] dummy = new String[0];
		final ProtocolIterator iterator = ProtocolIterator.create(output);
		if (iterator == null)
		{
			return dummy;
		}

		final ArrayList<String> protocols = new ArrayList<>();
		for (;;)
		{
			final String protocol = iterator.iterate();
			if (protocol == null)
			{
				break;
			}
			protocols.add(protocol);
		}


		return protocols.toArray(dummy);
	}

	//endregion



	//region Native methods

	//endregion
}
