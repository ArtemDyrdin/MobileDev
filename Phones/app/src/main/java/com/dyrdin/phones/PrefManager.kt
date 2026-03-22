package com.dyrdin.phones

import android.content.Context
import androidx.core.content.edit

object PrefManager {

    private const val PREF_NAME = "user_data"
    private const val KEY_AUTO_LOGIN = "auto_login"
    private const val KEY_USER_REGISTERED = "user_registered"

    private fun getPrefs(context: Context) =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setAutoLogin(context: Context, enabled: Boolean) {
        getPrefs(context).edit {
            putBoolean(KEY_AUTO_LOGIN, enabled)
        }
    }

    fun isAutoLoginEnabled(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_AUTO_LOGIN, false)
    }

    fun setUserRegistered(context: Context, registered: Boolean) {
        getPrefs(context).edit {
            putBoolean(KEY_USER_REGISTERED, registered)
        }
    }

    fun isUserRegistered(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_USER_REGISTERED, false)
    }

    fun clear(context: Context) {
        getPrefs(context).edit { clear() }
    }
}