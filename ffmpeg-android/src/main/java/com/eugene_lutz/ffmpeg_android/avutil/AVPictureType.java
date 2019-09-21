package com.eugene_lutz.ffmpeg_android.avutil;

/**
 * AVPicture types, pixel formats and basic image planes manipulation.
 */
public enum AVPictureType
{
	AV_PICTURE_TYPE_NONE,	///< Undefined
	AV_PICTURE_TYPE_I,		///< Intra
	AV_PICTURE_TYPE_P,		///< Predicted
	AV_PICTURE_TYPE_B,		///< Bi-dir predicted
	AV_PICTURE_TYPE_S,		///< S(GMC)-VOP MPEG-4
	AV_PICTURE_TYPE_SI,		///< Switching Intra
	AV_PICTURE_TYPE_SP,		///< Switching Predicted
	AV_PICTURE_TYPE_BI,		///< BI type
}
