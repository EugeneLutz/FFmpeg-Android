package com.eugene_lutz.ffmpeg_android.swscale;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

public class SwsVector extends CStructWrapper
{
	private SwsVector(long pointer, AllocationType allocType, int flag)
	{
		super(pointer, allocType, flag);
	}

	@Override
	protected void finalize() throws Throwable
	{
		switch (allocationType)
		{
			case FROM_INSTANCE: break;
			case ALLOC: freeNative(pointer); break;
			default: break;
		}
	}



	public static SwsVector from(long pointer, AllocationType allocType)
	{
		return pointer == 0 ? null : new SwsVector(pointer, allocType, 0);
	}

	public static SwsVector from(long pointer)
	{
		return new SwsVector(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static SwsVector create(int length)
	{
		final long vector = createNative(length);
		return from(vector, AllocationType.ALLOC);
	}

	/**
	 * Return a normalized Gaussian curve used to filter stuff
	 * quality = 3 is high quality, lower is lower quality.
	 */
	public static SwsVector getGaussianVec(double variance, double quality)
	{
		final long vec = getGaussianVecNative(variance, quality);
		return from(vec, AllocationType.ALLOC);
	}



	public double[] getCoefficients()
	{
		return getCoefficientsNative(pointer);
	}

	public int getLength()
	{
		return getLengthNative(pointer);
	}

	public void setCoefficient(double coefficient, int index)
	{
		setCoefficientNative(pointer, coefficient, index);
	}

	/**
	 * Scale all the coefficients of a by the scalar value.
	 */
	public void scale(double scalar)
	{
		scaleNative(pointer, scalar);
	}

	/**
	 * Scale all the coefficients of a so that their sum equals height.
	 */
	public void normalize(double height)
	{
		normalizeNative(pointer, height);
	}



	private static native long createNative(int length);
	private static native void freeNative(long pointer);
	private static native double[] getCoefficientsNative(long pointer);
	private static native int getLengthNative(long pointer);
	private static native void setCoefficientNative(long pointer, double coefficient, int index);
	private static native long getGaussianVecNative(double variance, double quality);
	private static native void scaleNative(long pointer, double scalar);
	private static native void normalizeNative(long pointer, double height);
}
