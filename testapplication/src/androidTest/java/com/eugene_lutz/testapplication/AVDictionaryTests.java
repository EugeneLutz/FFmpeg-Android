package com.eugene_lutz.testapplication;

import android.support.test.runner.AndroidJUnit4;

import com.eugene_lutz.ffmpeg_android.ExecuteResult;
import com.eugene_lutz.ffmpeg_android.avutil.AVDictionary;
import com.eugene_lutz.ffmpeg_android.avutil.AVDictionaryEntry;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AVDictionaryTests
{
	//private static final String TAG = "LuxuryTest";

	private AVDictionary createDefaultDictionary()
	{
		final AVDictionary dictionary = new AVDictionary();
		dictionary.set("greetings", "Hello ");
		dictionary.set("greetings", "World", AVDictionary.AV_DICT_APPEND);

		return dictionary;
	}

	@Test
	public void testDictionary() throws Exception
	{
		try (final AVDictionary dictionary = AVDictionary.create())
		{
			dictionary.set("greetings", "Hello ", AVDictionary.AV_DICT_APPEND);
			dictionary.set("greetings", "World", AVDictionary.AV_DICT_APPEND);

			try (final AVDictionaryEntry entry = dictionary.get("greetings", null, AVDictionary.AV_DICT_MATCH_CASE))
			{
				assertNotNull(entry);
				assertEquals(entry.getValue(), "Hello World");
			}

			final String result = dictionary.getString(':', ';', null);
			assertEquals(result, "greetings:Hello World");
		}
	}

	@Test
	public void entryIsNotNull() throws Exception
	{
		try (final AVDictionary dictionary = createDefaultDictionary())
		{
			final AVDictionaryEntry entry = dictionary.get("greetings");
			assertNotNull(entry);
		}
	}

	@Test
	public void testKeys() throws Exception
	{
		try (final AVDictionary dictionary = AVDictionary.create())
		{
			dictionary.set("greetings", "Hello World", AVDictionary.AV_DICT_MULTIKEY);
			dictionary.set("greetings", "Hello Germany", AVDictionary.AV_DICT_MULTIKEY);
			dictionary.set("greetings", "Hello England", AVDictionary.AV_DICT_MULTIKEY);
			dictionary.set("greetings", "Hello USA", AVDictionary.AV_DICT_MULTIKEY);
			dictionary.set("greetings", "Hello Austria", AVDictionary.AV_DICT_MULTIKEY);

			final String key = "greetings";
			final int flags = AVDictionary.AV_DICT_MATCH_CASE;
			AVDictionaryEntry entry = dictionary.get(key, null, flags);
			int numEntries = 0;
			while (entry != null)
			{
				numEntries++;
				entry = dictionary.get(key, entry, flags);
				assertTrue(numEntries <= 5);
			}

			assertEquals(5, numEntries);
		}
	}

	@Test
	public void testSuffix() throws Exception
	{
		try (final AVDictionary dictionary = createDefaultDictionary())
		{
			final AVDictionaryEntry entry = dictionary.get("greet", null, AVDictionary.AV_DICT_IGNORE_SUFFIX);
			assertNotNull(entry);
		}
	}

	@Test
	public void testSetInt() throws Exception
	{
		try (final AVDictionary dictionary = AVDictionary.create())
		{
			final String key = "some_number";

			final int value = 42;
			final int setResult = dictionary.setInt(key, value);
			assertTrue(setResult >= 0);
			final AVDictionaryEntry entry = dictionary.get(key, null, AVDictionary.AV_DICT_IGNORE_SUFFIX);
			assertNotNull(entry);
			assertEquals(String.valueOf(value), entry.getValue());

			final int value2 = 24;
			final int setResult2 = dictionary.setInt(key, value2, AVDictionary.AV_DICT_DONT_OVERWRITE);
			assertTrue(setResult2 >= 0);
			final AVDictionaryEntry entry2 = dictionary.get(key, null, AVDictionary.AV_DICT_IGNORE_SUFFIX);
			assertNotNull(entry2);
			assertEquals(String.valueOf(value), entry2.getValue());
		}
	}

	@Test
	public void testParseString() throws Exception
	{
		try (final AVDictionary dictionary = createDefaultDictionary())
		{
			final ExecuteResult getStringResult = new ExecuteResult();
			final char keyValueSeparator = ':';
			final char pairSeparator = ';';
			final String importString = dictionary.getString(keyValueSeparator, pairSeparator, getStringResult);
			assertTrue(getStringResult.isSucceeded());
			assertNotNull(importString);

			try (final AVDictionary imported = AVDictionary.create())
			{
				final int parseResult = imported.parseString(importString, keyValueSeparator, pairSeparator, 0);
				assertEquals(parseResult, 0);

				final String importString2 = dictionary.getString(keyValueSeparator, pairSeparator, getStringResult);
				assertTrue(getStringResult.isSucceeded());
				assertEquals(importString, importString2);
			}
		}
	}

	@Test
	public void testCopy() throws Exception
	{
		try (final AVDictionary dictionary = createDefaultDictionary())
		{
			final ExecuteResult getStringResult = new ExecuteResult();
			final char keyValueSeparator = ':';
			final char pairSeparator = ';';
			final String importString = dictionary.getString(keyValueSeparator, pairSeparator, getStringResult);
			assertTrue(getStringResult.isSucceeded());
			assertNotNull(importString);

			try (final AVDictionary imported = AVDictionary.create())
			{
				final int copyResult = imported.copyFrom(dictionary, 0);
				assertEquals(copyResult, 0);

				final String importString2 = dictionary.getString(keyValueSeparator, pairSeparator, getStringResult);
				assertTrue(getStringResult.isSucceeded());
				assertEquals(importString, importString2);
			}
		}
	}
}
