package com.eugene_lutz.ffmpeg_android.avutil;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;

public class AVRational extends CStructWrapper
{
	//region Constructor, Destructor, etc...

	private AVRational(long pointer, AllocationType allocType)
	{
		super(pointer, allocType, 0);
	}

	@Override
	protected void finalizeDefault()
	{
		freeNative(pointer);
	}

	public static AVRational from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE);
	}

	public static AVRational from(long pointer, AllocationType allocType)
	{
		return pointer == 0 ? null :new AVRational(pointer, allocType);
	}

	//endregion



	//region Static methods

	/**
	 * Creates a new instance of AVRAtional.
	 * @param numerator Numerator.
	 * @param denominator Denominator.
	 * @return A new instance of AVRational.
	 */
	public static AVRational create(int numerator, int denominator)
	{
		final long rationalPointer = createNative(numerator, denominator);
		return from(rationalPointer, AllocationType.ALLOC);
	}

	/**
	 * Compare two rationals.
	 *
	 * @param a First rational
	 * @param b Second rational
	 *
	 * @return One of the following values:
	 *         - 0 if `a == b`
	 *         - 1 if `a > b`
	 *         - -1 if `a < b`
	 *         - `INT_MIN` if one of the values is of the form `0 / 0`
	 */
	public static int compare(AVRational a, AVRational b)
	{
		return compareNative(a.pointer, b.pointer);
	}

	//endregion



	//region Instance methods

	/**
	 * Compares to given rational.
	 *
	 * See also {@link #compare(AVRational, AVRational)} getComponentAt}
	 *
	 * @return Result of comparison.
	 */
	public int compareTo(AVRational rational)
	{
		return compare(this, rational);
	}

	/**
	 * Converts rational to a `double`.
	 * @return `a` in floating-point form
	 */
	public double toDouble()
	{
		return toDoubleNative(pointer);
	}

	/**
	 * Multiply to given rational.
	 * @param rational rational.
	 * @return result of multiplication.
	 */
	public AVRational multiplyTo(AVRational rational)
	{
		final long multiplyResult = multiplyNative(pointer, rational.pointer);
		return from(multiplyResult, AllocationType.ALLOC);
	}

	/**
	 * Divide by given rational.
	 * @param rational rational.
	 * @return result of division.
	 */
	public AVRational divideBy(AVRational rational)
	{
		final long divideResult = divideNative(pointer, rational.pointer);
		return from(divideResult, AllocationType.ALLOC);
	}

	/**
	 * Add given rational.
	 * @param rational rational.
	 * @return result of addition.
	 */
	public AVRational add(AVRational rational)
	{
		final long addResult = addNative(pointer, rational.pointer);
		return from(addResult, AllocationType.ALLOC);
	}

	/**
	 * Subtract given rational.
	 * @param rational rational.
	 * @return result of subtraction.
	 */
	public AVRational subtract(AVRational rational)
	{
		final long subtractResult = subtractNative(pointer, rational.pointer);
		return from(subtractResult, AllocationType.ALLOC);
	}

	//endregion



	//region Getters/Setters

	/**
	 * Returns numerator.
	 * @return Numerator.
	 */
	public int getNumerator()
	{
		return getNumeratorNative(pointer);
	}

	/**
	 * Sets numerator.
	 * @param value Value.
	 */
	public void setNumerator(int value)
	{
		setNumeratorNative(pointer, value);
	}

	/**
	 * Returns denominator.
	 * @return Denominator.
	 */
	public int getDenominator()
	{
		return getDenominatorNative(pointer);
	}

	/**
	 * Sets denominator.
	 * @param value Value.
	 */
	public void setDenominator(int value)
	{
		setDenominatorNative(pointer, value);
	}

	//endregion



	//region Native methods

	public static native long createNative(int numerator, int denominator);
	public static native void freeNative(long pointer);
	public static native int compareNative(long a, long b);
	public static native double toDoubleNative(long pointer);
	public static native long multiplyNative(long a, long b);
	public static native long divideNative(long a, long b);
	public static native long addNative(long a, long b);
	public static native long subtractNative(long a, long b);
	public static native int getNumeratorNative(long pointer);
	public static native void setNumeratorNative(long pointer, int value);
	public static native int getDenominatorNative(long pointer);
	public static native void setDenominatorNative(long pointer, int value);

	//endregion
}
