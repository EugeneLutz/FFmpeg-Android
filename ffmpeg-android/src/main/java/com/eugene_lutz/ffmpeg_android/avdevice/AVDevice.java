package com.eugene_lutz.ffmpeg_android.avdevice;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.avformat.AVFormatContext;
import com.eugene_lutz.ffmpeg_android.avformat.AVInputFormat;
import com.eugene_lutz.ffmpeg_android.avformat.AVOutputFormat;

public class AVDevice
{
	//region Static methods

	//region Other

	public static int getVersion()
	{
		return getVersionNative();
	}

	/**
	 * Return the libavdevice build-time configuration.
	 */
	public static String getConfiguration()
	{
		return getConfigurationNative();
	}

	/**
	 * Return the libavdevice license.
	 */
	public static String getLicense()
	{
		return getLicenseNative();
	}

	/**
	 * Initialize libavdevice and register all the input and output devices.
	 */
	public static void registerAll()
	{
		registerAllNative();
	}

	/**
	 * Audio input devices iterator.
	 *
	 * If d is NULL, returns the first registered input audio/video device,
	 * if d is non-NULL, returns the next registered input audio/video device after d
	 * or NULL if d is the last one.
	 */
	public static AVInputFormat nextInputAudioDevice(AVInputFormat format)
	{
		final long next = nextInputAudioDeviceNative(format.getPointer());
		return AVInputFormat.from(next, CStructWrapper.AllocationType.FROM_INSTANCE, 0);
	}

	/**
	 * Video input devices iterator.
	 *
	 * If d is NULL, returns the first registered input audio/video device,
	 * if d is non-NULL, returns the next registered input audio/video device after d
	 * or NULL if d is the last one.
	 */
	public static AVInputFormat nextInputVideoDevice(AVInputFormat format)
	{
		final long next = nextInputVideoDeviceNative(format.getPointer());
		return AVInputFormat.from(next, CStructWrapper.AllocationType.FROM_INSTANCE, 0);
	}

	/**
	 * Audio output devices iterator.
	 *
	 * If d is NULL, returns the first registered output audio/video device,
	 * if d is non-NULL, returns the next registered output audio/video device after d
	 * or NULL if d is the last one.
	 */
	public static AVOutputFormat nextOutputAudioDevice(AVOutputFormat format)
	{
		final long next = nextOutputAudioDeviceNative(format.getPointer());
		return AVOutputFormat.from(next, CStructWrapper.AllocationType.FROM_INSTANCE, 0);
	}

	/**
	 * Video output devices iterator.
	 *
	 * If d is NULL, returns the first registered output audio/video device,
	 * if d is non-NULL, returns the next registered output audio/video device after d
	 * or NULL if d is the last one.
	 */
	public static AVOutputFormat nextOutputVideoDevice(AVOutputFormat format)
	{
		final long next = nextOutputVideoDeviceNative(format.getPointer());
		return AVOutputFormat.from(next, CStructWrapper.AllocationType.FROM_INSTANCE, 0);
	}

	/**
	 * List devices.
	 *
	 * Returns available device names and their parameters.
	 * Some devices may accept system-dependent device names that cannot be
	 * autodetected. The list returned by this function cannot be assumed to
	 * be always completed.
	 *
	 * @param context device context.
	 * @return list of autodetected devices.
	 */
	public static AVDeviceInfoList listDevices(AVFormatContext context)
	{
		final long list = listDevicesNative(context.getPointer());
		return AVDeviceInfoList.from(list, CStructWrapper.AllocationType.CUSTOM, 0);
	}

	//endregion


	//region Application to device messages

	/**
	 * Send control message from application to device:
	 * Dummy message.
	 *
	 * @param context   device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when device doesn't implement handler of the message.
	 */
	public static int appToDevNone(AVFormatContext context)
	{
		return appToDevNoneNative(context.getPointer());
	}

	/**
	 * Send control message from application to device:
	 * Window size change message.
	 *
	 * Message is sent to the device every time the application changes the size
	 * of the window device renders to.
	 * Message should also be sent right after window is created.
	 *
	 * @param context   device context.
	 * @param rect      new window size.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when device doesn't implement handler of the message.
	 */
	public static int appToDevWindowSize(AVFormatContext context, AVDeviceRect rect)
	{
		return appToDevWindowSizeNative(context.getPointer(), rect.getPointer());
	}

	/**
	 * Send control message from application to device:
	 * Repaint request message.
	 *
	 * Message is sent to the device when window has to be repainted.
	 *
	 * data: AVDeviceRect: area required to be repainted.
	 *       NULL: whole area is required to be repainted.
	 *
	 * @param context   device context.
	 * @param rect      area required to be repainted.
	 *                  null if whole area is required to be repainted.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when device doesn't implement handler of the message.
	 */
	public static int appToDevWindowRepaint(AVFormatContext context, AVDeviceRect rect)
	{
		return appToDevWindowRepaintNative(context.getPointer(), rect.getPointer());
	}

	/**
	 * Send control message from application to device:
	 * Request pause.
	 *
	 * Application requests pause/unpause playback.
	 * Mostly usable with devices that have internal buffer.
	 * By default devices are not paused.
	 *
	 * @param context   device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when device doesn't implement handler of the message.
	 */
	public static int appToDevPause(AVFormatContext context)
	{
		return appToDevPauseNative(context.getPointer());
	}

	/**
	 * Send control message from application to device:
	 * Request play.
	 *
	 * Application requests pause/unpause playback.
	 * Mostly usable with devices that have internal buffer.
	 * By default devices are not paused.
	 *
	 * @param context   device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when device doesn't implement handler of the message.
	 */
	public static int appToDevPlay(AVFormatContext context)
	{
		return appToDevPlayNative(context.getPointer());
	}

	/**
	 * Send control message from application to device:
	 * Request toggle pause.
	 *
	 * Application requests pause/unpause playback.
	 * Mostly usable with devices that have internal buffer.
	 * By default devices are not paused.
	 *
	 * @param context   device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when device doesn't implement handler of the message.
	 */
	public static int appToDevTogglePause(AVFormatContext context)
	{
		return appToDevTogglePauseNative(context.getPointer());
	}

	/**
	 * Send control message from application to device:
	 * Volume control message.
	 *
	 * Set volume level. It may be device-dependent if volume
	 * is changed per stream or system wide. Per stream volume
	 * change is expected when possible.
	 *
	 * @param context   device context.
	 * @param volume    new volume with range of 0.0 - 1.0.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when device doesn't implement handler of the message.
	 */
	public static int appToDevSetVolume(AVFormatContext context, double volume)
	{
		return appToDevSetVolumeNative(context.getPointer(), volume);
	}

	/**
	 * Send control message from application to device:
	 * Mute control message.
	 *
	 *  Change mute state. It may be device-dependent if mute status
	 *  is changed per stream or system wide. Per stream mute status
	 *  change is expected when possible.
	 *
	 * @param context   device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when device doesn't implement handler of the message.
	 */
	public static int appToDevMute(AVFormatContext context)
	{
		return appToDevMuteNative(context.getPointer());
	}

	/**
	 * Send control message from application to device:
	 * Unmute control message.
	 *
	 * Change mute state. It may be device-dependent if mute status
	 * is changed per stream or system wide. Per stream mute status
	 * change is expected when possible.
	 *
	 * @param context   device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when device doesn't implement handler of the message.
	 */
	public static int appToDevUnmute(AVFormatContext context)
	{
		return appToDevUnmuteNative(context.getPointer());
	}

	/**
	 * Send control message from application to device:
	 * Toggle mute control messages.
	 *
	 * Change mute state. It may be device-dependent if mute status
	 * is changed per stream or system wide. Per stream mute status
	 * change is expected when possible.
	 *
	 * @param context   device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when device doesn't implement handler of the message.
	 */
	public static int appToDevToggleMute(AVFormatContext context)
	{
		return appToDevToggleMuteNative(context.getPointer());
	}

	/**
	 * Send control message from application to device:
	 * Get volume messages.
	 *
	 * @param context   device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when device doesn't implement handler of the message.
	 */
	public static int appToDevGetVolume(AVFormatContext context)
	{
		return appToDevGetVolumeNative(context.getPointer());
	}

	/**
	 * Send control message from application to device:
	 * Get mute messages.
	 *
	 * @param context   device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when device doesn't implement handler of the message.
	 */
	public static int appToDevGetMute(AVFormatContext context)
	{
		return appToDevGetMuteNative(context.getPointer());
	}

	//endregion


	//region Device to Application messages

	/**
	 * Send control message from device to application.
	 * Dummy message.
	 *
	 * @param context    device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when application doesn't implement handler of the message.
	 */
	public static int devToAppNone(AVFormatContext context)
	{
		return devToAppNoneNative(context.getPointer());
	}

	/**
	 * Send control message from device to application.
	 * Create window buffer message.
	 *
	 * Device requests to create a window buffer. Exact meaning is device-
	 * and application-dependent. Message is sent before rendering first
	 * frame and all one-shot initializations should be done here.
	 * Application is allowed to ignore preferred window buffer size.
	 *
	 * Note: Application is obligated to inform about window buffer size
	 *        with AV_APP_TO_DEV_WINDOW_SIZE message.
	 *
	 * @param context    device context.
	 * @param rect       preferred size of the window buffer.
	 *                   NULL: no preferred size of the window buffer.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when application doesn't implement handler of the message.
	 */
	public static int devToAppCreateWindowBuffer(AVFormatContext context, AVDeviceRect rect)
	{
		return devToAppCreateWindowBufferNative(context.getPointer(), rect.getPointer());
	}

	/**
	 * Send control message from device to application.
	 * Prepare window buffer message.
	 *
	 * Device requests to prepare a window buffer for rendering.
	 * Exact meaning is device- and application-dependent.
	 * Message is sent before rendering of each frame.
	 *
	 * @param context    device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when application doesn't implement handler of the message.
	 */
	public static int devToAppPrepareWindowBuffer(AVFormatContext context)
	{
		return devToAppPrepareWindowBufferNative(context.getPointer());
	}

	/**
	 * Send control message from device to application.
	 * Display window buffer message.
	 *
	 * Device requests to display a window buffer.
	 * Message is sent when new frame is ready to be displayed.
	 * Usually buffers need to be swapped in handler of this message.
	 *
	 * @param context    device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when application doesn't implement handler of the message.
	 */
	public static int devToAppDisplayWindowBuffer(AVFormatContext context)
	{
		return devToAppDisplayWindowBufferNative(context.getPointer());
	}

	/**
	 * Send control message from device to application.
	 * Destroy window buffer message.
	 *
	 * Device requests to destroy a window buffer.
	 * Message is sent when device is about to be destroyed and window
	 * buffer is not required anymore.
	 *
	 * @param context    device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when application doesn't implement handler of the message.
	 */
	public static int devToAppDestroyWindowBuffer(AVFormatContext context)
	{
		return devToAppDestroyWindowBufferNative(context.getPointer());
	}

	/**
	 * Send control message from device to application.
	 * Buffer fullness status messages.
	 *
	 * Device signals buffer overflow.
	 *
	 * @param context    device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when application doesn't implement handler of the message.
	 */
	public static int devToAppBufferOverflow(AVFormatContext context)
	{
		return devToAppBufferOverflowNative(context.getPointer());
	}

	/**
	 * Send control message from device to application.
	 * Buffer fullness status messages.
	 *
	 * Device signals buffer underflow.
	 *
	 * @param context    device context.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when application doesn't implement handler of the message.
	 */
	public static int devToAppBufferUnderflow(AVFormatContext context)
	{
		return devToAppBufferUnderflowNative(context.getPointer());
	}

	/**
	 * Send control message from device to application.
	 * Buffer readable.
	 *
	 * Device informs that buffer is readable.
	 * When possible, device informs how many bytes can be read.
	 *
	 * Warning: Device may not inform when number of bytes than can be read changes.
	 *
	 * @param context          device context.
	 * @param amountAvailable  amount of bytes available to read is known.
	 * @param amount           amount of bytes available to read.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when application doesn't implement handler of the message.
	 */
	public static int devToAppBufferReadable(AVFormatContext context, boolean amountAvailable, long amount)
	{
		return devToAppBufferReadableNative(context.getPointer(), amountAvailable, amount);
	}

	/**
	 * Send control message from device to application.
	 * Buffer writable.
	 *
	 * Device informs that buffer is writable.
	 * When possible, device informs how many bytes can be write.
	 *
	 * Warning: Device may not inform when number of bytes than can be write changes.
	 *
	 * @param context          device context.
	 * @param amountAvailable  amount of bytes available to write is known.
	 * @param amount           amount of bytes available to write.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when application doesn't implement handler of the message.
	 */
	public static int devToAppBufferWritable(AVFormatContext context, boolean amountAvailable, long amount)
	{
		return devToAppBufferWritableNative(context.getPointer(), amountAvailable, amount);
	}

	/**
	 * Send control message from device to application.
	 * Mute state change message.
	 *
	 * Device informs that mute state has changed.
	 *
	 * @param context    device context.
	 * @param state      0 for not muted state, non-zero for muted state.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when application doesn't implement handler of the message.
	 */
	public static int devToAppMuteStateChanged(AVFormatContext context, int state)
	{
		return devToAppMuteStateChangedNative(context.getPointer(), state);
	}

	/**
	 * Send control message from device to application.
	 * Volume level change message.
	 *
	 * Device informs that volume level has changed.
	 *
	 * @param context    device context.
	 * @param volume     new volume with range of 0.0 - 1.0.
	 * @return {@literal >}= 0 on success, negative on error.
	 *         AVERROR(ENOSYS) when application doesn't implement handler of the message.
	 */
	public static int devToAppVolumeLevelChanged(AVFormatContext context, double volume)
	{
		return devToAppVolumeLevelChangedNative(context.getPointer(), volume);
	}

	//endregion


	//endregion



	//region Native methods

	private static native int getVersionNative();
	private static native String getConfigurationNative();
	private static native String getLicenseNative();
	private static native void registerAllNative();
	private static native long nextInputAudioDeviceNative(long format);
	private static native long nextInputVideoDeviceNative(long format);
	private static native long nextOutputAudioDeviceNative(long format);
	private static native long nextOutputVideoDeviceNative(long format);
	private static native long listDevicesNative(long format);

	private static native int appToDevNoneNative(long pointer);
	private static native int appToDevWindowSizeNative(long pointer, long rectPointer);
	private static native int appToDevWindowRepaintNative(long pointer, long rectPointer);
	private static native int appToDevPauseNative(long pointer);
	private static native int appToDevPlayNative(long pointer);
	private static native int appToDevTogglePauseNative(long pointer);
	private static native int appToDevSetVolumeNative(long pointer, double volume);
	private static native int appToDevMuteNative(long pointer);
	private static native int appToDevUnmuteNative(long pointer);
	private static native int appToDevToggleMuteNative(long pointer);
	private static native int appToDevGetVolumeNative(long pointer);
	private static native int appToDevGetMuteNative(long pointer);

	private static native int devToAppNoneNative(long pointer);
	private static native int devToAppCreateWindowBufferNative(long pointer, long rectPointer);
	private static native int devToAppPrepareWindowBufferNative(long pointer);
	private static native int devToAppDisplayWindowBufferNative(long pointer);
	private static native int devToAppDestroyWindowBufferNative(long pointer);
	private static native int devToAppBufferOverflowNative(long pointer);
	private static native int devToAppBufferUnderflowNative(long pointer);
	private static native int devToAppBufferReadableNative(long pointer, boolean amountAvailable, long amount);
	private static native int devToAppBufferWritableNative(long pointer, boolean amountAvailable, long amount);
	private static native int devToAppMuteStateChangedNative(long pointer, int state);
	private static native int devToAppVolumeLevelChangedNative(long pointer, double volume);

	//endregion
}
