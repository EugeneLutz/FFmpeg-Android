package com.eugene_lutz.testapplication;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.eugene_lutz.ffmpeg_android.avutil.AVDictionary;
import com.eugene_lutz.ffmpeg_android.avutil.AVDictionaryEntry;
import com.eugene_lutz.ffmpeg_android.avutil.AVMediaType;
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
public class AVDictionaryTests
{
	private static final String TAG = "LuxuryTest";

	@Test
	public void testDictionary() throws Exception
	{
		try (AVDictionary dictionary = AVDictionary.create())
		{
			dictionary.set("greetings", "Hello ", AVDictionary.AV_DICT_APPEND);
			dictionary.set("greetings", "World", AVDictionary.AV_DICT_APPEND);

			try (final AVDictionaryEntry entry = dictionary.get("greetings", null, 0))
			{
				assertNotNull(entry);
				assertEquals(entry.getValue(), "Hello World");
			}

			final String result = dictionary.getString(':', ';', null);
			assertEquals(result, "greetings:Hello World");
		}
	}
}
