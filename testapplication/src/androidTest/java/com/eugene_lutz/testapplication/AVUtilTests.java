package com.eugene_lutz.testapplication;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.eugene_lutz.ffmpeg_android.avcodec.AVCodec;
import com.eugene_lutz.ffmpeg_android.avcodec.AVCodecContext;
import com.eugene_lutz.ffmpeg_android.avcodec.AVCodecID;
import com.eugene_lutz.ffmpeg_android.avdevice.AVDevice;
import com.eugene_lutz.ffmpeg_android.avutil.AVMediaType;
import com.eugene_lutz.ffmpeg_android.avutil.AVRational;
import com.eugene_lutz.ffmpeg_android.avutil.AVUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AVUtilTests
{
	private static final String TAG = "LuxuryTest";

	@Test
	public void testMediaTypes()
	{
		for (final AVMediaType mediaType : AVMediaType.values())
		{
			final String mediaTypeString = AVUtil.getMediaTypeString(mediaType);
			if (mediaType == AVMediaType.AVMEDIA_TYPE_UNKNOWN)
			{
				assertNull(mediaTypeString);
				continue;
			}

			assertNotNull(mediaTypeString);
			Log.d(TAG, String.format("%s: %s", mediaType.toString(), mediaTypeString));
		}
	}

	@Test
	public void testGetBaseTimeQ() throws Exception
	{
		try (final AVRational baseTimeQ = AVUtil.getBaseTimeQ())
		{
			assertNotNull(baseTimeQ);
			final int num = baseTimeQ.getNumerator();
			final int den = baseTimeQ.getDenominator();
			Log.d(TAG, String.format("%d : %d", num, den));
		}
	}
}
