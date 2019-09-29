package com.eugene_lutz.testapplication;

import android.util.Log;

import com.eugene_lutz.ffmpeg_android.ExecuteResult;
import com.eugene_lutz.ffmpeg_android.avformat.AVFormat;
import com.eugene_lutz.ffmpeg_android.avformat.AVFormatContext;
import com.eugene_lutz.ffmpeg_android.avformat.AVIO;
import com.eugene_lutz.ffmpeg_android.avformat.AVInputFormat;
import com.eugene_lutz.ffmpeg_android.avformat.AVOutputFormat;
import com.eugene_lutz.ffmpeg_android.avutil.AVDictionary;
import com.eugene_lutz.ffmpeg_android.avutil.AVDictionaryEntry;

import java.util.Locale;

public class FFmpegTest
{
	/*private static final String LOG_TEST = "LOG_TEST";

	static class FormatFlag
	{
		final int flag;
		final String name;

		FormatFlag(int flag, String name)
		{
			this.flag = flag;
			this.name = name;
		}
	}

	private static final FormatFlag[] avInputFormatFlags = {
			new FormatFlag(AVFormat.AVFMT_NOFILE, "AVFMT_NOFILE"),
			new FormatFlag(AVFormat.AVFMT_NEEDNUMBER, "AVFMT_NEEDNUMBER"),
			new FormatFlag(AVFormat.AVFMT_SHOW_IDS, "AVFMT_SHOW_IDS"),
			new FormatFlag(AVFormat.AVFMT_NOTIMESTAMPS, "AVFMT_NOTIMESTAMPS"),
			new FormatFlag(AVFormat.AVFMT_GENERIC_INDEX, "AVFMT_GENERIC_INDEX"),
			new FormatFlag(AVFormat.AVFMT_TS_DISCONT, "AVFMT_TS_DISCONT"),
			new FormatFlag(AVFormat.AVFMT_NOBINSEARCH, "AVFMT_NOBINSEARCH"),
			new FormatFlag(AVFormat.AVFMT_NOGENSEARCH, "AVFMT_NOGENSEARCH"),
			new FormatFlag(AVFormat.AVFMT_NO_BYTE_SEEK, "AVFMT_NO_BYTE_SEEK"),
			new FormatFlag(AVFormat.AVFMT_SEEK_TO_PTS, "AVFMT_SEEK_TO_PTS")
	};

	private static final FormatFlag[] avOutputFormatFlags = {
			new FormatFlag(AVFormat.AVFMT_NOFILE, "AVFMT_NOFILE"),
			new FormatFlag(AVFormat.AVFMT_NEEDNUMBER, "AVFMT_NEEDNUMBER"),
			new FormatFlag(AVFormat.AVFMT_SHOW_IDS, "AVFMT_SHOW_IDS"),
			new FormatFlag(AVFormat.AVFMT_NOTIMESTAMPS, "AVFMT_NOTIMESTAMPS"),
			new FormatFlag(AVFormat.AVFMT_GENERIC_INDEX, "AVFMT_GENERIC_INDEX"),
			new FormatFlag(AVFormat.AVFMT_TS_DISCONT, "AVFMT_TS_DISCONT"),
			new FormatFlag(AVFormat.AVFMT_NOBINSEARCH, "AVFMT_NOBINSEARCH"),
			new FormatFlag(AVFormat.AVFMT_NOGENSEARCH, "AVFMT_NOGENSEARCH"),
			new FormatFlag(AVFormat.AVFMT_NO_BYTE_SEEK, "AVFMT_NO_BYTE_SEEK"),
			new FormatFlag(AVFormat.AVFMT_SEEK_TO_PTS, "AVFMT_SEEK_TO_PTS")
	};

	public static void run(String path)
	{
		checkAVDictionary();

		checkAVFormatContext(path);

		checkAVInputFormatIterator();
		checkAVInputFormat();

		checkAVOutputFormatIterator();
		checkAVOutputFormat();

		checkAVIOOutputProtocolIterator();
		checkAVIOInputProtocolIterator();
		checkAVIOEnumProtocols();

		Log.d(LOG_TEST, "FFmpeg test finished");
	}*/


	/*private static void checkAVDictionary()
	{
		AVDictionary dictionary = AVDictionary.create();
		dictionary.set("Hello", "World", AVDictionary.AV_DICT_APPEND);
		dictionary.set("Hello", "Darkness my old friend", AVDictionary.AV_DICT_DONT_OVERWRITE);
		dictionary.setInt("IntValue", 1200, AVDictionary.AV_DICT_MULTIKEY);
		dictionary.setInt("IntValue", 1300, AVDictionary.AV_DICT_MULTIKEY);
		dumpAVDictionary(dictionary);

		final AVDictionaryEntry entry = dictionary.get("Hello", null, AVDictionary.AV_DICT_MATCH_CASE);
		final String entryString = entry == null ? null : entry.getValue();
		Log.d(LOG_TEST, entryString);

		AVDictionary imported = AVDictionary.create();
		final ExecuteResult executeResult = new ExecuteResult();
		final String exportString = dictionary.getString(':', ';', executeResult);
		imported.parseString(exportString, ':', ';', AVDictionary.AV_DICT_MULTIKEY);
		dumpAVDictionary(imported);

		AVDictionary copyDictionary = AVDictionary.create();
		copyDictionary.copyFrom(dictionary, AVDictionary.AV_DICT_MULTIKEY);
		dumpAVDictionary(copyDictionary);
	}

	private static void dumpAVDictionary(AVDictionary dictionary)
	{
		final String tag = "AVDictionary dump";
		final StringBuilder dumpInfo = getAVDictionaryDumpInfo(dictionary);
		dumpInfo.append('\n');
		Log.d(tag, dumpInfo.toString());
	}

	private static StringBuilder getAVDictionaryDumpInfo(AVDictionary dictionary)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("\nDumping AVDictionary\n");

		if (dictionary == null)
		{
			builder.append("Given dictionary is null\n");
			return builder;
		}

		final int count = dictionary.count();
		builder.append(String.format(Locale.GERMANY, "Elements in dictionary: %d\n", count));

		final ExecuteResult executeResult = new ExecuteResult();
		final String result = dictionary.getString(':', ';', executeResult);
		final String contents = executeResult.getCode() == 0 ? executeResult.getMessage() : String.format(Locale.GERMANY, "Error code: %d", executeResult.getCode());
		builder.append(String.format(Locale.GERMANY, "Contents of dictionary: %s\n", contents));

		return builder;
	}


	private static void checkAVFormatContext(String path)
	{
		final AVFormatContext.CreateResult openResult = AVFormatContext.openInput(path);
		if (!openResult.isSucceeded())
		{
			Log.d(LOG_TEST, String.format("AVFormatContext.openInput(path) failed with code %d",
					openResult.getErrorCode()));
			return;
		}
		AVFormatContext c = openResult.getFormatContext();

		int findStreamInfoResult = c.findStreamInfo();
		Log.d(LOG_TEST, String.format("AVFormatContext.findStreamInfo() = %d", findStreamInfoResult));

		Log.d(LOG_TEST, "AVFormatContext run finished");
	}


	private static void checkAVInputFormatIterator()
	{
		AVInputFormat.Iterator iterator = AVInputFormat.Iterator.create();
		if (iterator == null)
		{
			Log.d(LOG_TEST, "AVInputFormat.Iterator.create() failed");
			return;
		}

		AVInputFormat format = iterator.iterate();
		if (format == null)
		{
			Log.d(LOG_TEST, "iterator.iterate() failed");
			return;
		}

		Log.d(LOG_TEST, "AVInputFormat info:");
		displayAVInputFormatInfo(format);
	}

	private static void displayAVInputFormatInfo(AVInputFormat format)
	{
		String name = format.getName();
		String longName = format.getLongName();
		String rawExtensions = format.getExtensions();
		String extensions = rawExtensions == null ? "[none]" : rawExtensions;
		String rawMimeTypes = format.getMimeTypes();
		String mimeTypes = rawMimeTypes == null ? "[none]" : rawMimeTypes;

		Log.d(LOG_TEST, String.format("%s: %s. Supported extensions: %s. Mime types: %s",
				name, longName, extensions, mimeTypes));
	}

	private static void checkAVInputFormat()
	{
		Log.d(LOG_TEST, "List of available demuxers:");
		AVInputFormat[] formats = AVInputFormat.listAvailable();
		for (AVInputFormat format : formats)
		{
			displayAVInputFormatInfo(format);
		}

		int flags = formats[0].getFlags();
		Log.d(LOG_TEST, String.format("Flags from format %s = %d", formats[0].getName(), flags));
		for (FormatFlag formatFlag : avInputFormatFlags)
		{
			checkFlag(flags, formatFlag.flag, formatFlag.name);
		}
	}

	private static void checkFlag(int flags, int flag, String flagName)
	{
		if ((flags & flag) == flag)
		{
			Log.d(LOG_TEST, String.format("%s is set", flagName));
		}
	}



	private static void checkAVOutputFormatIterator()
	{
		AVOutputFormat.Iterator iterator = AVOutputFormat.Iterator.create();
		if (iterator == null)
		{
			Log.d(LOG_TEST, "AVOutputFormat.Iterator.create() failed");
			return;
		}

		AVOutputFormat format = iterator.iterate();
		if (format == null)
		{
			Log.d(LOG_TEST, "iterator.iterate() failed");
			return;
		}

		Log.d(LOG_TEST, "AVOutputFormat info:");
		displayAVOutputFormatInfo(format);
	}

	private static void displayAVOutputFormatInfo(AVOutputFormat format)
	{
		String name = format.getName();
		String longName = format.getLongName();
		String rawExtensions = format.getExtensions();
		String extensions = rawExtensions == null ? "[none]" : rawExtensions;
		String rawMimeTypes = format.getMimeTypes();
		String mimeTypes = rawMimeTypes == null ? "[none]" : rawMimeTypes;

		Log.d(LOG_TEST, String.format("%s: %s. Supported extensions: %s. Mime types: %s",
				name, longName, extensions, mimeTypes));
	}

	private static void checkAVOutputFormat()
	{
		Log.d(LOG_TEST, "List of available muxers:");
		AVOutputFormat[] formats = AVOutputFormat.listAvailable();
		for (AVOutputFormat format : formats)
		{
			displayAVOutputFormatInfo(format);
		}

		int flags = formats[0].getFlags();
		Log.d(LOG_TEST, String.format("Flags from format %s = %d", formats[0].getName(), flags));
		for (FormatFlag formatFlag : avOutputFormatFlags)
		{
			checkFlag(flags, formatFlag.flag, formatFlag.name);
		}
	}



	private static void checkAVIOOutputProtocolIterator()
	{
		AVIO.ProtocolIterator outputProtocolIterator = AVIO.ProtocolIterator.create(true);
		if (outputProtocolIterator == null)
		{
			Log.d(LOG_TEST, "Failed to create AVIO.ProtocolIterator (output).");
			return;
		}

		String outputProtocol = outputProtocolIterator.iterate();
		Log.d(LOG_TEST, String.format("Got output protocol %s", outputProtocol));
	}

	private static void checkAVIOInputProtocolIterator()
	{
		AVIO.ProtocolIterator inputProtocolIterator = AVIO.ProtocolIterator.create(false);
		if (inputProtocolIterator == null)
		{
			Log.d(LOG_TEST, "Failed to create AVIO.ProtocolIterator (input).");
			return;
		}

		String inputProtocol = inputProtocolIterator.iterate();
		Log.d(LOG_TEST, String.format("Got input protocol %s", inputProtocol));
	}

	private static void checkAVIOEnumProtocols()
	{
		String[] outputProtocols = AVIO.enumProtocols(true);
		Log.d(LOG_TEST, String.format("Available output protocols (%d):", outputProtocols.length));
		displayStringList(outputProtocols);

		String[] inputProtocols = AVIO.enumProtocols(false);
		Log.d(LOG_TEST, String.format("Available input protocols (%d):", inputProtocols.length));
		displayStringList(inputProtocols);
	}

	private static void displayStringList(String[] list)
	{
		for (String string : list)
		{
			Log.d(LOG_TEST, string);
		}
	}*/
}
