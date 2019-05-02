package com.cigna.mobile.db

object DataCache {
    private val mDataCache: HashMap<String, Any> = HashMap()

    /**
     * Store in the in-memory cache only
     *
     * @param key    primary key for key
     * @param subkey optional secondary key for key
     * @param obj    data to store
     */
    fun addData(key: String, subkey: String = "", obj: Any) {
        mDataCache[mapKeys(key, subkey)] = obj
    }

    /**
     * Store in the in-memory cache only
     *
     * @param key primary key for key
     * @param obj data to store
     */
    fun addData(key: String, obj: Any) {
        addData(key, "", obj)
    }

    /**
     * Remove data from in-memory cache
     *
     * @param key    primary key for key
     * @param subkey optional secondary key for key
     */
    @JvmOverloads
    fun removeData(key: String, subkey: String = "") {
        mDataCache.remove(mapKeys(key, subkey))
    }

    /**
     * Clear all entries from the in-memory cache. Note: Be careful using this
     * method because it will clear the username and encryption key as well.
     */
    fun removeAllData() {
        mDataCache.clear()
    }

    /**
     * Update data in the in-memory cache
     *
     * @param key    primary key for key
     * @param subkey optional secondary key for key
     * @param obj    data to replace
     */
    fun replaceData(key: String, subkey: String = "", obj: Any) {
        mDataCache[mapKeys(key, subkey)] = obj
    }

    /**
     * Update data in the in-memory cache
     *
     * @param key primary key for key
     * @param obj data to replace
     */
    fun replaceData(key: String, obj: Any) {
        replaceData(key, "", obj)
    }

    /**
     * Get data from the in-memory cache
     *
     * @param key    primary key for key
     * @param subkey optional secondary key for key
     * @return stored data
     */
    fun <T> getData(key: String?, subkey: String = ""): T? {
        return mDataCache[mapKeys(key, subkey)] as T?
    }

    fun getData(key: String?): Any? {
        return mDataCache[key]
    }

    /**
     * Returns data from the in-memory cache as a Boolean key.
     *
     * @param key primary key for key
     * @return stored data as true or false
     */
    fun getBoolean(key: String): Boolean? {
        return if (getData(key) as Any? == null) {
            false
        } else getData(key) as Boolean?

    }

    /**
     * Checks whether given data exists in-memory cache.
     *
     * @param key primary key for key
     * @return true or false
     */
    fun exists(key: String): Boolean {
        return mDataCache[key] != null
    }

    /**
     * Checks whether given data exists in-memory cache.
     *
     * @param key    primary key for key
     * @param subKey optional secondary for key
     * @return true or false
     */
    fun exists(key: String, subKey: String = ""): Boolean {
        return mDataCache[mapKeys(key, subKey)] != null
    }

    private fun mapKeys(key: String?, subKey: String): String {
        return key + subKey
    }
}