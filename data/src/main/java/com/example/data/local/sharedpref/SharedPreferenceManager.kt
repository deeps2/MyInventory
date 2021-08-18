package com.example.data.local.sharedpref

import android.content.SharedPreferences
import javax.inject.Inject


class SharedPreferenceManager @Inject constructor(private val preference: SharedPreferences) {

    private val KEY_USER_ID = "USER_ID"
    private val KEY_IS_LOG_IN = "KEY_IS_LOG_IN"

    fun getUserId() = getPreference(KEY_USER_ID, "")
    fun setUserId(userID: String) = setPreference(KEY_USER_ID, userID)

    fun isUserLoggedIn() = getPreference(KEY_IS_LOG_IN, false)
    fun setUserLoggedIn(isLoggedIn: Boolean) = setPreference(KEY_IS_LOG_IN, isLoggedIn)

    private fun <T> setPreference(key: String, value: T) {
        try {
            val editor = preference.edit()
            when (value) {
                is String -> editor.putString(key, value as String)
                is Int -> editor.putInt(key, value as Int)
                is Boolean -> editor.putBoolean(key, value as Boolean)
                is Float -> editor.putFloat(key, value as Float)
                is Long -> editor.putLong(key, value as Long)
            }
            editor.apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun <T> getPreference(key: String, defaultValue: T): T? {
        try {
            when (defaultValue) {
                is String -> preference.getString(key, defaultValue as String)
                is Int -> preference.getInt(key, defaultValue as Int)
                is Boolean -> preference.getBoolean(key, defaultValue as Boolean)
                is Float -> preference.getFloat(key, defaultValue as Float)
                is Long -> preference.getLong(key, defaultValue as Long)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    private fun removeKey(key: String) {
        preference.edit().remove(key).apply()
    }

    private fun clearAllPreference(key: String) {
        preference.edit().clear().apply()
    }

}