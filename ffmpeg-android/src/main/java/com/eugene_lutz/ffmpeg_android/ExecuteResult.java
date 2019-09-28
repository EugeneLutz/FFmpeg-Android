package com.eugene_lutz.ffmpeg_android;

public final class ExecuteResult
{
	private boolean succeeded;
	private int code;
	private String message;

	public ExecuteResult()
	{
		succeeded = true;
		code = 0;
		message = null;
	}

	public boolean isSucceeded()
	{
		return succeeded;
	}

	public int getCode()
	{
		return code;
	}

	public String getMessage()
	{
		return message;
	}
}
