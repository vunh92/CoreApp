package com.vunh.coreapp.common

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class AppSharePresfs() {
    private var mAppSharePresfs: AppSharePresfs? = null
    private lateinit var mContext: Context
    private lateinit var pref: SharedPreferences
    private var gson: Gson? = null

    constructor(context: Context) : this() {
        mContext = context
        gson = Gson()
        pref = mContext.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    }


    @Synchronized
    fun newInstance(): AppSharePresfs {
        if (mAppSharePresfs == null) {
            mAppSharePresfs = AppSharePresfs(mContext)
        }
        return mAppSharePresfs as AppSharePresfs
    }

    fun setFirstBoot(isFirstBoot: Boolean) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putBoolean(KEY_FIRST_BOOT, isFirstBoot)
        editor.apply()
    }

    fun isFirstBoot(): Boolean = pref.getBoolean(KEY_FIRST_BOOT, true)

//    fun setOauthToken(oauthOauthToken: OauthToken) {
//        val editor: SharedPreferences.Editor = pref.edit()
//        editor.putString(KEY_OAUTH_TOKEN, gson!!.toJson(oauthOauthToken))
//        editor.apply()
//    }
//
//    fun getOauthToken(): OauthToken? {
//        val data: String? = pref.getString(KEY_OAUTH_TOKEN, null)
//        return gson!!.fromJson(data, OauthToken::class.java)
//    }

    fun setToken(token: String) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putString(KEY_OAUTH_TOKEN, gson!!.toJson(token))
        editor.apply()
    }

    fun getToken(): String? {
        val data: String? = pref.getString(KEY_OAUTH_TOKEN, null)
        return gson!!.fromJson(data, String::class.java)
    }

    fun setString(key: String, value: String) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String? = pref.getString(key, null)

    fun setInt(key: String, value: Int) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String): Int = pref.getInt(key, 0)

    fun setBoolean(key: String, value: Boolean) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean = pref.getBoolean(key, false)

    fun setFloat(key: String, value: Float) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun getFloat(key: String): Float = pref.getFloat(key, 0F)

    fun setLong(key: String, value: Long) {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun getLong(key: String): Long = pref.getLong(key, 0L)

    fun logout() {
        val editor: SharedPreferences.Editor = pref.edit()
        editor.remove(KEY_OAUTH_TOKEN)
        editor.apply()
    }
}