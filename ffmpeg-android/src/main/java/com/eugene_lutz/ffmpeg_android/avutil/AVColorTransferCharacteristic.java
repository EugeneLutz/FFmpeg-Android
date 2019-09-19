package com.eugene_lutz.ffmpeg_android.avutil;

/**
 * Color Transfer Characteristic.
 * These values match the ones defined by ISO/IEC 23001-8_2013 § 7.2.
 */
public enum AVColorTransferCharacteristic
{
	AVCOL_TRC_RESERVED0,
	AVCOL_TRC_BT709,		///< also ITU-R BT1361
	AVCOL_TRC_UNSPECIFIED,
	AVCOL_TRC_RESERVED,
	AVCOL_TRC_GAMMA22,		///< also ITU-R BT470M / ITU-R BT1700 625 PAL & SECAM
	AVCOL_TRC_GAMMA28,		///< also ITU-R BT470BG
	AVCOL_TRC_SMPTE170M,	///< also ITU-R BT601-6 525 or 625 / ITU-R BT1358 525 or 625 / ITU-R BT1700 NTSC
	AVCOL_TRC_SMPTE240M,
	AVCOL_TRC_LINEAR,		///< "Linear transfer characteristics"
	AVCOL_TRC_LOG,			///< "Logarithmic transfer characteristic (100:1 range)"
	AVCOL_TRC_LOG_SQRT,		///< "Logarithmic transfer characteristic (100 * Sqrt(10) : 1 range)"
	AVCOL_TRC_IEC61966_2_4,	///< IEC 61966-2-4
	AVCOL_TRC_BT1361_ECG,	///< ITU-R BT1361 Extended Colour Gamut
	AVCOL_TRC_IEC61966_2_1,	///< IEC 61966-2-1 (sRGB or sYCC)
	AVCOL_TRC_BT2020_10,	///< ITU-R BT2020 for 10-bit system
	AVCOL_TRC_BT2020_12,	///< ITU-R BT2020 for 12-bit system
	AVCOL_TRC_SMPTE2084,	///< SMPTE ST 2084 for 10-, 12-, 14- and 16-bit systems
	AVCOL_TRC_SMPTE428,		///< SMPTE ST 428-1
	AVCOL_TRC_ARIB_STD_B67	///< ARIB STD-B67, known as "Hybrid log-gamma"
}
