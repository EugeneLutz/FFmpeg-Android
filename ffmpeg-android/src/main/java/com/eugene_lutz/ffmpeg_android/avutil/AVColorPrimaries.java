package com.eugene_lutz.ffmpeg_android.avutil;

/**
 * Chromaticity coordinates of the source primaries.
 * These values match the ones defined by ISO/IEC 23001-8_2013 § 7.1.
 */
public enum AVColorPrimaries
{
	AVCOL_PRI_RESERVED0,
	AVCOL_PRI_BT709,		///< also ITU-R BT1361 / IEC 61966-2-4 / SMPTE RP177 Annex B
	AVCOL_PRI_UNSPECIFIED,
	AVCOL_PRI_RESERVED,
	AVCOL_PRI_BT470M,		///< also FCC Title 47 Code of Federal Regulations 73.682 (a)(20)

	AVCOL_PRI_BT470BG,		///< also ITU-R BT601-6 625 / ITU-R BT1358 625 / ITU-R BT1700 625 PAL & SECAM
	AVCOL_PRI_SMPTE170M,	///< also ITU-R BT601-6 525 / ITU-R BT1358 525 / ITU-R BT1700 NTSC
	AVCOL_PRI_SMPTE240M,	///< functionally identical to above
	AVCOL_PRI_FILM,			///< colour filters using Illuminant C
	AVCOL_PRI_BT2020,		///< ITU-R BT2020
	AVCOL_PRI_SMPTE428,		///< SMPTE ST 428-1 (CIE 1931 XYZ)
	AVCOL_PRI_SMPTE431,		///< SMPTE ST 431-2 (2011) / DCI P3
	AVCOL_PRI_SMPTE432,		///< SMPTE ST 432-1 (2010) / P3 D65 / Display P3
	AVCOL_PRI_JEDEC_P22		///< JEDEC P22 phosphors
}
