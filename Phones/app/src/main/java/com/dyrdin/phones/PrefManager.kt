package com.dyrdin.phones

import android.content.Context
import androidx.core.content.edit

object PrefManager {

    private const val PREF_NAME = "user_data"
    private const val KEY_LOGIN = "login"
    private const val KEY_PASSWORD = "password"
    private const val KEY_AUTO_LOGIN = "auto_login"

    private fun getPrefs(context: Context) =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveUser(context: Context, login: String, password: String) {
        getPrefs(context).edit {
            putString(KEY_LOGIN, login)
                .putString(KEY_PASSWORD, password)
        }
    }

    fun setAutoLogin(context: Context, enabled: Boolean) {
        getPrefs(context).edit {
            putBoolean(KEY_AUTO_LOGIN, enabled)
        }
    }

    fun getLogin(context: Context): String? {
        return getPrefs(context).getString(KEY_LOGIN, null)
    }

    fun getPassword(context: Context): String? {
        return getPrefs(context).getString(KEY_PASSWORD, null)
    }

    fun isAutoLoginEnabled(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_AUTO_LOGIN, false)
    }

    fun isUserRegistered(context: Context): Boolean {
        return getLogin(context) != null && getPassword(context) != null
    }

    fun clear(context: Context) {
        getPrefs(context).edit { clear() }
    }
}