package com.eugene_lutz.ffmpeg_android.avutil;

/**
 * Location of chroma samples.
 *
 * Illustration showing the location of the first (top left) chroma sample of the
 * image, the left shows only luma, the right
 * shows the location of the chroma sample, the 2 could be imagined to overlay
 * each other but are drawn separately due to limitations of ASCII
 *
 *                1st 2nd       1st 2nd horizontal luma sample positions
 *                 v   v         v   v
 *                 ______        ______
 *1st luma line > |X   X ...    |3 4 X ...     X are luma samples,
 *                |             |1 2           1-6 are possible chroma positions
 *2nd luma line > |X   X ...    |5 6 X ...     0 is undefined/unknown position
 */
public enum AVChromaLocation
{
	AVCHROMA_LOC_UNSPECIFIED,
	AVCHROMA_LOC_LEFT,		///< MPEG-2/4 4:2:0, H.264 default for 4:2:0
	AVCHROMA_LOC_CENTER,	///< MPEG-1 4:2:0, JPEG 4:2:0, H.263 4:2:0
	AVCHROMA_LOC_TOPLEFT,	///< ITU-R 601, SMPTE 274M 296M S314M(DV 4:1:1), mpeg2 4:2:2
	AVCHROMA_LOC_TOP,
	AVCHROMA_LOC_BOTTOMLEFT,
	AVCHROMA_LOC_BOTTOM
}
