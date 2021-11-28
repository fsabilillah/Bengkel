package com.example.bengkel.utils

import android.content.Context
import com.example.bengkel.BuildConfig.BASE_URL

class UrlPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val URL = "url"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUrl(value: String) {
        val editor = preferences.edit()
        editor.putString(URL, value)
        editor.apply()
    }
    fun getUrl(): String {
        return preferences.getString(URL, BASE_URL).toString()
    }
}