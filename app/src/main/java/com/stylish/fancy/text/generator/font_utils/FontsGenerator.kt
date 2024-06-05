package com.stylish.fancy.text.generator.font_utils

import android.content.Context
import com.stylish.fancy.text.generator.font_utils.FontsBuilder.makeStyle
import com.stylish.fancy.text.generator.interfaces.Style

class FontsGenerator(private var mContext: Context?) {
    private var mEncoders: ArrayList<Style>

    init {
        val time = System.currentTimeMillis()
        mEncoders = makeStyle()
        if (mContext != null) sortEncoders(mContext!!)
        println("time = " + (System.currentTimeMillis() - time))
    }

    private fun sortEncoders(context: Context) {
        val positionMap = HashMap<Style, Int>()
        for (i in mEncoders.indices) {
            positionMap[mEncoders[i]] = i
        }
        //get data from SharedPreferences
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        if (pref.getInt(Integer.toHexString(mEncoders[0].hashCode()), -1) == -1) {
            val editor = pref.edit()
            for (index in mEncoders.indices) {
                val style = mEncoders[index]
                editor.putInt(Integer.toHexString(style.hashCode()), index)
            }
            editor.apply()
        }
        for (index in mEncoders.indices) {
            val style = mEncoders[index]
            val key = Integer.toHexString(style.hashCode())
            val position = pref.getInt(key, index)
            positionMap[style] = position
        }

        /*Collections.sort(mEncoders) { o1: Style, o2: Style ->
            Objects.requireNonNull(
                positionMap[o1]
            ).compareTo(Objects.requireNonNull(positionMap[o2]))
        }*/

        //sort mEncoders
        mEncoders.sortWith { o1: Style, o2: Style ->
            positionMap[o1]?.compareTo(positionMap[o2] ?: 0) ?: 0
        }
    }

    fun generate(input: String?): ArrayList<String?> {
        val result = ArrayList<String?>()
        for (style in mEncoders) {
            val encode = style.generate(input)
            result.add(encode)
        }
        return result
    }

    companion object {
        private const val PREF_NAME = "stylish_position.xml"
    }
}