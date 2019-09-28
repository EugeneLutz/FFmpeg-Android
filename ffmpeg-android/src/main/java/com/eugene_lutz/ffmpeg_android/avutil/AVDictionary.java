package com.eugene_lutz.ffmpeg_android.avutil;

import com.eugene_lutz.ffmpeg_android.CStructWrapper;
import com.eugene_lutz.ffmpeg_android.ExecuteResult;

/**
 *  AVDictionary is provided for compatibility with libav. It is both in
 *  implementation as well as API inefficient. It does not scale and is
 *  extremely slow with large dictionaries.
 *  It is recommended that new code uses our tree container from tree.c/h
 *  where applicable, which uses AVL trees to achieve O(log n) performance.
 */
public class AVDictionary extends CStructWrapper
{
	//region Class related stuff

	/** Only get an entry with exact-case key match. Only relevant in AVDictionary.get(). */
	public static final int AV_DICT_MATCH_CASE = 1;

	/** Return first entry in a dictionary whose first part corresponds to the search key,
	 ignoring the suffix of the found key string. Only relevant in AVDictionary.get(). */
	public static final int AV_DICT_IGNORE_SUFFIX = 2;

	/** Don't overwrite existing entries. */
	public static final int AV_DICT_DONT_OVERWRITE = 16;

	/** If the entry already exists, append to it.  Note that no
	 delimiter is added, the strings are simply concatenated. */
	public static final int AV_DICT_APPEND = 32;

	/** Allow to store several equal keys in the dictionary */
	public static final int AV_DICT_MULTIKEY = 64;

	//endregion



	//region Constructor, Destructor, etc...

	public AVDictionary(long pointer, AllocationType allocationType, int allocationFlag)
	{
		super(pointer, allocationType, allocationFlag);
	}

	@Override
	protected void finalizeDefault()
	{
		freeNative(pointer);
	}

	public static AVDictionary from(long pointer)
	{
		return from(pointer, AllocationType.FROM_INSTANCE, 0);
	}

	public static AVDictionary from(long pointer, AllocationType allocationType, int allocationFlag)
	{
		return pointer == 0 ? null : new AVDictionary(pointer, allocationType, allocationFlag);
	}

	//endregion



	//region Static methods

	public static AVDictionary create()
	{
		return new AVDictionary(0, AllocationType.ALLOC, 0);
	}

	//endregion



	//region Instance methods

	/**
	 * Get a dictionary entry with matching key.
	 *
	 * The returned entry key or value must not be changed, or it will
	 * cause undefined behavior.
	 *
	 * To iterate through all the dictionary entries, you can set the matching key
	 * to the null string "" and set the AV_DICT_IGNORE_SUFFIX flag.
	 *
	 * @param prev Set to the previous matching element to find the next.
	 *             If set to null the first matching element is returned.
	 * @param key matching key
	 * @param flags a collection of AV_DICT_* flags controlling how the entry is retrieved
	 * @return found entry or null in case no matching entry was found in the dictionary
	 */
	public AVDictionaryEntry get(String key, AVDictionaryEntry prev, int flags)
	{
		final long prevPointer = prev == null ? 0 : prev.getPointer();
		final long entry = getNative(pointer, key, prevPointer, flags);
		return AVDictionaryEntry.from(entry);
	}

	/**
	 * Get number of entries in dictionary.
	 *
	 * @return number of entries in dictionary
	 */
	public int count()
	{
		return countNative(pointer);
	}

	/**
	 * Set the given entry, overwriting an existing entry.
	 *
	 * Warning: Adding a new entry to a dictionary invalidates all existing entries
	 * previously returned with AVDictionary.get.
	 *
	 * @param key entry key to add.
	 * @param value entry value to add.
	 *        Passing a null value will cause an existing entry to be deleted.
	 * @return {@literal >}= 0 on success otherwise an error code {@literal <}0
	 */
	public int set(String key, String value, int flags)
	{
		return setNative(key, value, flags);
	}

	/**
	 * Convenience wrapper for av_dict_set that converts the value to a string
	 * and stores it.
	 *
	 * Note: If AV_DICT_DONT_STRDUP_KEY is set, key will be freed on error.
	 */
	public int setInt(String key, long value, int flags)
	{
		return setIntNative(key, value, flags);
	}

	/**
	 * Parse the key/value pairs list and add the parsed entries to a dictionary.
	 *
	 * In case of failure, all the successfully set entries are stored in
	 * *pm. You may need to manually free the created dictionary.
	 *
	 * @param keyValSep  a string of characters used to separate
	 *                     key from value
	 * @param pairsSep    a string of characters used to separate
	 *                     two pairs from each other
	 * @param flags        flags to use when adding to dictionary.
	 * @return             0 on success, negative AVERROR code on failure
	 */
	public int parseString(String str, String keyValSep, String pairsSep, int flags)
	{
		return parseStringNative(str, keyValSep, pairsSep, flags);
	}

	/**
	 * Copy entries from source AVDictionary struct into this dictionary.
	 * @param source pointer to source AVDictionary struct
	 * @param flags flags to use when setting entries in this object
	 * note: metadata is read using the AV_DICT_IGNORE_SUFFIX flag
	 * @return 0 on success, negative AVERROR code on failure. If dst was allocated
	 *           by this function, callers should free the associated memory.
	 */
	public int copyFrom(AVDictionary source, int flags)
	{
		return copyNative(source.pointer, flags);
	}

	/**
	 * Get dictionary entries as a string.
	 *
	 * Create a string containing dictionary's entries.
	 * Such string may be passed back to av_dict_parse_string().
	 * Note: String is escaped with backslashes ('\').
	 * Warning: Separators cannot be neither '\\' nor '\0'. They also cannot be the same.
	 *
	 * @param  keyValSep   character used to separate key from value
	 * @param  pairsSep     character used to separate two pairs from each other
	 * @return                   {@literal >}= 0 on success, negative on error
	 */
	public String getString(char keyValSep, char pairsSep, ExecuteResult result)
	{
		return getStringNative(pointer, keyValSep, pairsSep, result);
	}

	//endregion



	//region Getters/Setters

	//endregion



	//region Native methods

	private static native long getNative(long pointer, String key, long dictEntryPrev, int flags);
	private static native int countNative(long pointer);
	private native int setNative(String key, String value, int flags);
	private native int setIntNative(String key, long value, int flags);
	private native int parseStringNative(String str, String keyValSep, String pairsSep, int flags);
	private native int copyNative(long src, int flags);
	private static native void freeNative(long pointer);
	private static native String getStringNative(long pointer, char keyValSep, char pairsSep, ExecuteResult result);

	//endregion
}
