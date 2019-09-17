package com.eugene_lutz.ffmpeg_android.avutil;

public class Mathematics
{
	public static final int AV_ROUND_ZERO     = 0; ///< Round toward zero.
	public static final int AV_ROUND_INF      = 1; ///< Round away from zero.
	public static final int AV_ROUND_DOWN     = 2; ///< Round toward -infinity.
	public static final int AV_ROUND_UP       = 3; ///< Round toward +infinity.
	public static final int AV_ROUND_NEAR_INF = 5; ///< Round to nearest and halfway cases away from zero.
	/**
	 * Flag telling rescaling functions to pass `INT64_MIN`/`MAX` through
	 * unchanged, avoiding special cases for #AV_NOPTS_VALUE.
	 *
	 * Unlike other values of the enumeration AVRounding, this value is a
	 * bitmask that must be used in conjunction with another value of the
	 * enumeration through a bitwise OR, in order to set behavior for normal
	 * cases.
	 *
	 * Example:
	 * av_rescale_rnd(3, 1, 2, AV_ROUND_UP | AV_ROUND_PASS_MINMAX);
	 * // Rescaling 3:
	 * //     Calculating 3 * 1 / 2
	 * //     3 / 2 is rounded up to 2
	 * //     => 2
	 *
	 * av_rescale_rnd(AV_NOPTS_VALUE, 1, 2, AV_ROUND_UP | AV_ROUND_PASS_MINMAX);
	 * // Rescaling AV_NOPTS_VALUE:
	 * //     AV_NOPTS_VALUE == INT64_MIN
	 * //     AV_NOPTS_VALUE is passed through
	 * //     => AV_NOPTS_VALUE
	 */
	public static final int AV_ROUND_PASS_MINMAX = 8192;

	/**
	 * Rescale a 64-bit integer by 2 rational numbers with specified rounding.
	 *
	 * The operation is mathematically equivalent to `a * bq / cq`.
	 *
	 * @param flags a bitmask of Mathematics.AV_ROUND_* values
	 */
	public static long rescaleQRND(long a, AVRational bq, AVRational cq, int flags)
	{
		return rescaleQRNDNative(a, bq.getPointer(), cq.getPointer(), flags);
	}

	/**
	 * Rescale a 64-bit integer by 2 rational numbers.
	 *
	 * The operation is mathematically equivalent to `a * bq / cq`.
	 *
	 * This function is equivalent to av_rescale_q_rnd() with #AV_ROUND_NEAR_INF.
	 */
	public static long rescaleQ(long a, AVRational bq, AVRational cq)
	{
		return rescaleQNative(a, bq.getPointer(), cq.getPointer());
	}

	/**
	 * Compute the greatest common divisor of two integer operands.
	 *
	 * @param a,b Operands
	 * @return GCD of a and b up to sign; if a >= 0 and b >= 0, return value is >= 0;
	 * if a == 0 and b == 0, returns 0.
	 */
	public long gcd(long a, long b)
	{
		return gcdNative(a, b);
	}

	public long rescale(long a, long b, long c)
	{
		return rescaleNative(a, b, c);
	}


	private static native long rescaleQRNDNative(long a, long bqPointer, long cqPointer, int flags);
	private static native long rescaleQNative(long a, long bqPointer, long cqPointer);
	private static native long gcdNative(long a, long b);
	private static native long rescaleNative(long a, long b, long c);
}
