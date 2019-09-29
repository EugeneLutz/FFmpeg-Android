package com.eugene_lutz.ffmpeg_android.avutil;

public enum AVSampleFormat
{
	AV_SAMPLE_FMT_NONE,

	///< unsigned 8 bits
	AV_SAMPLE_FMT_U8,

	///< signed 16 bits
	AV_SAMPLE_FMT_S16,

	///< signed 32 bits
	AV_SAMPLE_FMT_S32,

	///< float
	AV_SAMPLE_FMT_FLT,

	///< double
	AV_SAMPLE_FMT_DBL,

	///< unsigned 8 bits, planar
	AV_SAMPLE_FMT_U8P,

	///< signed 16 bits, planar
	AV_SAMPLE_FMT_S16P,

	///< signed 32 bits, planar
	AV_SAMPLE_FMT_S32P,

	///< float, planar
	AV_SAMPLE_FMT_FLTP,

	///< double, planar
	AV_SAMPLE_FMT_DBLP,

	///< signed 64 bits
	AV_SAMPLE_FMT_S64,

	///< signed 64 bits, planar
	AV_SAMPLE_FMT_S64P
}
