package com.eugene_lutz.ffmpeg_android.avutil;

public enum AVMediaType
{
	/**
	 * Usually treated as AVMEDIA_TYPE_DATA
	 */
	AVMEDIA_TYPE_UNKNOWN,
	AVMEDIA_TYPE_VIDEO,
	AVMEDIA_TYPE_AUDIO,

	/**
	 * Opaque data information usually continuous
	 */
	AVMEDIA_TYPE_DATA,
	AVMEDIA_TYPE_SUBTITLE,

	/**
	 * Opaque data information usually sparse
 	 */
	AVMEDIA_TYPE_ATTACHMENT,
	//AVMEDIA_TYPE_NB
}
