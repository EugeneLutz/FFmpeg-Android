package com.eugene_lutz.testapplication;

import android.util.Log;

import com.eugene_lutz.ffmpeg_android.FFmpegAndroid;
import com.eugene_lutz.ffmpeg_android.avcodec.AVCodecParameters;
import com.eugene_lutz.ffmpeg_android.avcodec.AVPacket;
import com.eugene_lutz.ffmpeg_android.avformat.AVFormat;
import com.eugene_lutz.ffmpeg_android.avformat.AVFormatContext;
import com.eugene_lutz.ffmpeg_android.avformat.AVIO;
import com.eugene_lutz.ffmpeg_android.avformat.AVIOContext;
import com.eugene_lutz.ffmpeg_android.avformat.AVOutputFormat;
import com.eugene_lutz.ffmpeg_android.avutil.AVRational;
import com.eugene_lutz.ffmpeg_android.avformat.AVStream;
import com.eugene_lutz.ffmpeg_android.avutil.AVMediaType;
import com.eugene_lutz.ffmpeg_android.avutil.Mathematics;


class RemuxingSample
{
	private static final String LOG_TAG = "LOG_TEST";


	static void run(String inputPath, String outputPath)
	{
		// Раскомментируйте, чтобы выполнить аналог этой функции на C++

		/*
		if (inputPath != null || outputPath != null)
		{
			int r = FFmpegAndroid.runTestNative(inputPath, outputPath);
			return;
		}/**/

		final AVFormatContext.CreateResult openInputResult = AVFormatContext.openInput(inputPath);
		if (!openInputResult.isSucceeded())
		{
			Log.d(LOG_TAG, String.format("Could not open input file '%s'", inputPath));
			return;
		}
		AVFormatContext inputContext = openInputResult.getFormatContext();

		final int findResult = inputContext.findStreamInfo();
		if (findResult < 0)
		{
			Log.d(LOG_TAG, "Failed to retrieve input stream information");
			return;
		}

		inputContext.dumpFormat(0, inputPath, false);

		final AVFormatContext.CreateResult createOutputResult = AVFormatContext.createOutputByFileName(outputPath);
		if (!createOutputResult.isSucceeded())
		{
			Log.d(LOG_TAG, "Could not create output context");
			return;
		}
		final AVFormatContext outputContext = createOutputResult.getFormatContext();

		final int numStreams = inputContext.getNumberOfStreams();
		final int[] streamMapping = new int[numStreams];
		int streamIndex = 0;

		final AVOutputFormat outputFormat = outputContext.getOutputFormat();
		for (int i = 0; i < numStreams; i++)
		{
			final AVStream inStream = inputContext.getStream(i);
			final AVCodecParameters inStreamCodecParameters = inStream.getCodecParameters();
			final AVMediaType codecType = inStreamCodecParameters.getCodecType();
			if (codecType != AVMediaType.AVMEDIA_TYPE_AUDIO &&
					codecType != AVMediaType.AVMEDIA_TYPE_VIDEO &&
					codecType != AVMediaType.AVMEDIA_TYPE_SUBTITLE) {
				streamMapping[i] = -1;
				continue;
			}

			streamMapping[i] = streamIndex++;
			final AVStream outStream = outputContext.newStream(null);
			if (outStream == null)
			{
				Log.d(LOG_TAG, "Failed allocating output stream");
				return;
			}

			final AVCodecParameters outStreamCodecParameters = outStream.getCodecParameters();
			final int copyResult = inStreamCodecParameters.copyTo(outStreamCodecParameters);
			if (copyResult < 0)
			{
				Log.d(LOG_TAG, "Failed to copy codec parameters");
				return;
			}

			outStreamCodecParameters.setCodecTag(0);
		}

		//outputContext.dumpFormat(0, outputPath, true);
		final int outputFormatFlags = outputFormat.getFlags();
		if ((outputFormatFlags & AVFormat.AVFMT_NOFILE) != AVFormat.AVFMT_NOFILE)
		{
			final AVIOContext.CreateResult openResult = AVIOContext.open(outputPath, AVIO.AVIO_FLAG_WRITE);
			if (!openResult.isSucceeded())
			{
				Log.d(LOG_TAG, String.format("Could not open output file '%s'", outputPath));
				return;
			}

			final AVIOContext ioContext = openResult.getIOContext();
			outputContext.setIOContext(ioContext);
		}

		final int writeHeaderResult = outputContext.writeHeader();
		if (writeHeaderResult < 0)
		{
			Log.d(LOG_TAG, "Error occurred when opening output file");
			return;
		}

		final AVPacket packet = AVPacket.create();
		if (packet == null)
		{
			Log.d(LOG_TAG, "Cannot create packet");
			return;
		}

		for (;;)
		{
			final int readResult = inputContext.readFrame(packet);
			if (readResult < 0)
			{
				break;
			}

			final int sourceStreamIndex = packet.getStreamIndex();
			final AVStream inStream = inputContext.getStream(sourceStreamIndex);
			if (sourceStreamIndex >= numStreams ||
					streamMapping[sourceStreamIndex] < 0)
			{
				packet.unref();
				continue;
			}

			final int destinationStreamIndex = streamMapping[sourceStreamIndex];
			packet.setStreamIndex(destinationStreamIndex);
			final AVStream outStream = outputContext.getStream(destinationStreamIndex);


			final AVRational inTimeBase = inStream.getTimeBase();
			final AVRational outTimeBase = outStream.getTimeBase();
			final int rescaleFlags = Mathematics.AV_ROUND_NEAR_INF | Mathematics.AV_ROUND_PASS_MINMAX;

			final long oldPts = packet.getPresentationTimestamp();
			final long pts = Mathematics.rescaleQRND(oldPts, inTimeBase, outTimeBase, rescaleFlags);
			packet.setPresentationTimestamp(pts);

			final long oldDts = packet.getDecompressionTimestamp();
			final long dts = Mathematics.rescaleQRND(oldDts, inTimeBase, outTimeBase, rescaleFlags);
			packet.setDecompressionTimestamp(dts);

			final long oldDuration = packet.getDuration();
			final long duration = Mathematics.rescaleQ(oldDuration, inTimeBase, outTimeBase);
			packet.setDuration(duration);

			packet.setPosition(-1);

			final int writePacketResult = outputContext.interleavedWriteFrameFromPacket(packet);
			if (writePacketResult < 0)
			{
				Log.d(LOG_TAG, "Error muxing packet");
				break;
			}

			packet.unref();
		}

		final int writeTrailerResult = outputContext.writeTrailer();
		if (writeTrailerResult != 0)
		{
			Log.d(LOG_TAG, "Error writing trailer");
		}
	}

	void testPacket()
	{
		try
		{
			try (AVPacket packet = AVPacket.create())
			{
				// ...
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	//private static native int runNative(String input, String output);
}
