package com.eugene_lutz.ffmpeg_android.avutil;

/**
 * YUV colorspace type.
 * These values match the ones defined by ISO/IEC 23001-8_2013 § 7.3.
 */
public enum AVColorSpace
{
	AVCOL_SPC_RGB,			///< order of coefficients is actually GBR, also IEC 61966-2-1 (sRGB)
	AVCOL_SPC_BT709,		///< also ITU-R BT1361 / IEC 61966-2-4 xvYCC709 / SMPTE RP177 Annex B
	AVCOL_SPC_UNSPECIFIED,
	AVCOL_SPC_RESERVED,
	AVCOL_SPC_FCC,			///< FCC Title 47 Code of Federal Regulations 73.682 (a)(20)
	AVCOL_SPC_BT470BG,		///< also ITU-R BT601-6 625 / ITU-R BT1358 625 / ITU-R BT1700 625 PAL & SECAM / IEC 61966-2-4 xvYCC601
	AVCOL_SPC_SMPTE170M,	///< also ITU-R BT601-6 525 / ITU-R BT1358 525 / ITU-R BT1700 NTSC
	AVCOL_SPC_SMPTE240M,	///< functionally identical to above
	AVCOL_SPC_YCGCO_YCOCG,	///< Used by Dirac / VC-2 and H.264 FRext, see ITU-T SG16
	AVCOL_SPC_BT2020_NCL,	///< ITU-R BT2020 non-constant luminance system
	AVCOL_SPC_BT2020_CL,	///< ITU-R BT2020 constant luminance system
	AVCOL_SPC_SMPTE2085,	///< SMPTE 2085, Y'D'zD'x
	AVCOL_SPC_CHROMA_DERIVED_NCL,	///< Chromaticity-derived non-constant luminance system
	AVCOL_SPC_CHROMA_DERIVED_CL,	///< Chromaticity-derived constant luminance system
	AVCOL_SPC_ICTCP			///< ITU-R BT.2100-0, ICtCp
}
