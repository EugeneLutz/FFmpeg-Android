package com.eugene_lutz.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.eugene_lutz.ffmpeg_android.FFmpegAndroid;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FFmpegAndroid.check();

		final String fileName = "sample_video_720x480_1mb.mp4";
		Util.copyAssetFileToCache(fileName, this);
		final String path = Util.createCacheFilePath(fileName, this);

		// TODO: add ssl support: https://stackoverflow.com/questions/31514949/ffmpeg-over-https-fails
		// https://medium.com/@xiaogegexiao/build-ffmpeg-and-openssl-for-android-a07f511b2c8
		//final String path = "https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8";
		//final String path = "http://184.72.239.149/vod/smil:BigBuckBunny.smil/playlist.m3u8";

		//FFmpegTest.run(path);

		final String outPath = Util.createCacheFilePath("out.3gp", this);
		RemuxingSample.run(path, outPath);
	}
}
