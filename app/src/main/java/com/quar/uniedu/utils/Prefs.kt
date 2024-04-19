package com.quar.uniedu.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    enum class PrefKey {
        USERID, LOGIN, LANG,
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)

    var userId: Int
        get() = preferences.getInt(PrefKey.USERID.name, 0)
        set(value) = preferences.edit().putInt(PrefKey.USERID.name, value).apply()

    var isLogin: Boolean
        get() = preferences.getBoolean(PrefKey.LOGIN.name, false)
        set(value) = preferences.edit().putBoolean(PrefKey.LOGIN.name, value).apply()


    var lang: Int
        get() = preferences.getInt(PrefKey.LANG.name, 0)
        set(value) = preferences.edit().putInt(PrefKey.LANG.name, value).apply()

    fun clear() {
        preferences.edit().clear().apply()
    }
}