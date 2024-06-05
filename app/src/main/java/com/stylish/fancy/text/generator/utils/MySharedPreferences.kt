package com.stylish.fancy.text.generator.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MySharedPreferences(context: Context) {
    private val preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveList(list: List<String?>?) {
        val editor = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(KEY_LIST, json)
        editor.apply()
    }

    val list: List<String>
        get() {
            val gson = Gson()
            val json = preferences.getString(KEY_LIST, null)
            return if (json != null) {
                val type = object : TypeToken<ArrayList<String>>() {}.type
                gson.fromJson<ArrayList<String>>(json, type) ?: emptyList()
            } else {
                emptyList()
            }
        }

    companion object {
        private const val PREF_NAME = "EmoticonPreference"
        private const val KEY_LIST = "emoticon_list"
    }
}