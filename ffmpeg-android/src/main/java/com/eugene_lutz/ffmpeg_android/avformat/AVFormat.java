package com.eugene_lutz.ffmpeg_android.avformat;

public class AVFormat
{
	public static final int AVFMT_NOFILE = 0x0001;

	/** Needs '%d' in filename. */
	public static final int AVFMT_NEEDNUMBER = 0x0002;

	/** Show format stream IDs numbers. */
	public static final int AVFMT_SHOW_IDS = 0x0008;

	/** Format wants global header. */
	public static final int AVFMT_GLOBALHEADER = 0x0040;

	/** Format does not need / have any timestamps. */
	public static final int AVFMT_NOTIMESTAMPS = 0x0080;

	/** Use generic index building code. */
	public static final int AVFMT_GENERIC_INDEX = 0x0100;

	/** Format allows timestamp discontinuities. Note,
	 * muxers always require valid (monotone) timestamps */
	public static final int AVFMT_TS_DISCONT = 0x0200;

	/** Format allows variable fps. */
	public static final int AVFMT_VARIABLE_FPS = 0x0400;

	/** Format does not need width/height */
	public static final int AVFMT_NODIMENSIONS = 0x0800;

	/** Format does not require any streams */
	public static final int AVFMT_NOSTREAMS = 0x1000;

	/** Format does not allow to fall back on binary search via read_timestamp */
	public static final int AVFMT_NOBINSEARCH = 0x2000;

	/** Format does not allow to fall back on generic search */
	public static final int AVFMT_NOGENSEARCH = 0x4000;

	/** Format does not allow seeking by bytes */
	public static final int AVFMT_NO_BYTE_SEEK = 0x8000;

	/** Format allows flushing. If not set, the muxer will not
	 * receive a NULL packet in the write_packet function. */
	public static final int AVFMT_ALLOW_FLUSH = 0x10000;

	/** Format does not require strictly increasing timestamps,
	 * but they must still be monotonic */
	public static final int AVFMT_TS_NONSTRICT = 0x20000;

	/** Format allows muxing negative timestamps. If not set the timestamp
	 will be shifted in av_write_frame and av_interleaved_write_frame so they
	 start from 0. The user or muxer can override this through
	 AVFormatContext.avoid_negative_ts */
	public static final int AVFMT_TS_NEGATIVE = 0x40000;

	/* * Seeking is based on PTS */
	public static final int AVFMT_SEEK_TO_PTS = 0x4000000;
}
