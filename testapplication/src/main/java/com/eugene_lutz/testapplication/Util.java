package com.eugene_lutz.testapplication;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

class Util
{
	static void copyAssetFileToCache(String fileName, Context context)
	{
		try
		{
			final File cacheDir = context.getExternalCacheDir();
			if (cacheDir == null)
			{
				return;
			}
			final String cachePath = cacheDir.getAbsolutePath();

			final String outputPath = cachePath + "/" + fileName;
			final File f = new File(outputPath);
			if (f.exists())
			{
				final boolean deleted = f.delete();
				if (!deleted)
				{
					return;
				}

				final boolean created = f.createNewFile();
				if (!created)
				{
					return;
				}
			}

			final InputStream source = context.getAssets().open(fileName);
			final FileOutputStream destination = new FileOutputStream(f);
			final byte[] buffer = new byte[4096];
			int receivedBytes = source.read(buffer);
			while(receivedBytes > 0)
			{
				destination.write(buffer, 0, receivedBytes);
				receivedBytes = source.read(buffer);
			}
			source.close();
			destination.close();
		}
		catch (NullPointerException npe)
		{
			Log.e("NullPointerException", npe.getMessage());
		}
		catch (Exception ex)
		{
			Log.e("File", ex.getMessage());
		}
	}

	static String createCacheFilePath(String fileName, Context context)
	{
		final File cacheDir = context.getExternalCacheDir();
		if (cacheDir == null)
		{
			return "";
		}

		final String cachePath = cacheDir.getAbsolutePath();
		return cachePath + "/" + fileName;
	}
}
