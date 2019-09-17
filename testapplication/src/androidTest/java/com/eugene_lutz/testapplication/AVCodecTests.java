package com.eugene_lutz.testapplication;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.eugene_lutz.ffmpeg_android.avcodec.AVCodec;
import com.eugene_lutz.ffmpeg_android.avcodec.AVCodecContext;
import com.eugene_lutz.ffmpeg_android.avcodec.AVCodecID;
import com.eugene_lutz.ffmpeg_android.avdevice.AVDevice;
import com.eugene_lutz.ffmpeg_android.avutil.AVMediaType;
import com.eugene_lutz.ffmpeg_android.avutil.AVUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AVCodecTests
{
	private static final String TAG = "LuxuryTest";

	@Test
	public void testAVDevice()
	{
		final int version = AVDevice.getVersion();
		Log.d(TAG, String.format("AVDevice version = %d", version));
	}

	private void checkCodec(AVCodec codec, AVCodecID codecId) throws Exception
	{
		if (codec == null)
		{
			//Log.d(TAG, String.format("Codec %s not found", codecId.toString()));
			return;
		}

		final String name = codec.getCodecName();
		assertNotNull(name);

		final String description = codec.getCodecDescription();
		assertNotNull(description);

		final AVMediaType mediaType = codec.getCodecMediaType();
		final String mediaTypeString = AVUtil.getMediaTypeString(mediaType);

		final String group = codec.getCodecGroupName();

		Log.d(TAG, String.format("%s (%s): %s - %s. Group: %s",
				codecId.toString(), mediaTypeString, name, description, group));

		final AVCodecID receivedCodecId = codec.getCodecId();
		assertEquals(codecId, receivedCodecId);

		checkCodecContext(codec);
	}

	private void checkCodecContext(AVCodec codec) throws Exception
	{
		try (AVCodecContext context = codec.createContext())
		{
			assertNotNull(context);

			final AVCodec codecFromContext = context.getCodec();
			assertNotNull(codecFromContext);
			assertEquals(codecFromContext.getCodecId(), codec.getCodecId());
		}
	}

	@Test
	public void testCodecs()
	{
		Log.d(TAG, "Checking decoders");
		for (final AVCodecID codecId : AVCodecID.values())
		{
			try (AVCodec decoder = AVCodec.findDecoder(codecId))
			{
				checkCodec(decoder, codecId);

				if (decoder != null)
				{
					final String name = decoder.getCodecName();
					final AVCodec codecByName = AVCodec.findDecoderByName(name);
					assertNotNull(codecByName);
					assertEquals(codecByName.getCodecName(), name);
				}
			}
			catch (Exception e)
			{
				Log.d(TAG, String.format("An error occured while checking decoder %s: %s", codecId.toString(), e.getMessage()));
			}
		}

		Log.d(TAG, "Checking encoders");
		for (final AVCodecID codecId : AVCodecID.values())
		{
			try (AVCodec encoder = AVCodec.findEncoder(codecId))
			{
				checkCodec(encoder, codecId);

				if (encoder != null)
				{
					final String name = encoder.getCodecName();
					final AVCodec codecByName = AVCodec.findEncoderByName(name);
					assertNotNull(codecByName);
					assertEquals(codecByName.getCodecName(), name);
				}
			}
			catch (Exception e)
			{
				Log.d(TAG, String.format("An error occured while checking decoder %s: %s", codecId.toString(), e.getMessage()));
			}
		}

		Log.d(TAG, "All codecs checked");
	}

	private void dumpCodec(AVCodec codec)
	{
		if (codec == null)
		{
			return;
		}

		final String name = codec.getCodecName();

		StringBuilder builder = new StringBuilder();
		if (codec.isAnyFrameRateSupported())
		{
			builder.append(String.format("%s supports any framerate.\n", name));
		}
		else
		{
			final int numSupportedFramerates = codec.getNumSupportedFramerates();
			builder.append(String.format("Number of supported framerates: %d.\n", numSupportedFramerates));
		}

		Log.d(TAG, builder.toString());
	}

	@Test
	public void checkCodecCapabilities() throws Exception
	{
		final AVCodecID codecId = AVCodecID.AV_CODEC_ID_H264;
		try (AVCodec codec = AVCodec.findDecoder(codecId))
		{
			final int[] existingCapabilities = AVCodec.getAllExistingCapabilities();
			final int capabilities = codec.getCodecCapabilities();
			for (final int c : existingCapabilities)
			{
				if ((capabilities & c) == c)
				{
					Log.d(TAG, String.format("%s has capability %d", codecId.toString(), c));
				}
			}

			dumpCodec(codec);
		}
	}
}
