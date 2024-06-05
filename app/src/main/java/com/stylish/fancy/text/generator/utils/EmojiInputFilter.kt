package com.stylish.fancy.text.generator.utils

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

class EmojiInputFilter : InputFilter {
    private var emojiPattern: Pattern? = null

    init {
        emojiPattern = Pattern.compile("[\\p{So}]")
    }

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        val input = source.subSequence(start, end)
        val newString =
            dest.toString().substring(0, dstart) + input + dest.toString().substring(dend)
        val emojiMatcher = emojiPattern?.matcher(newString)
        var emojiCount = 0
        if (emojiMatcher != null) {
            while (emojiMatcher.find()) {
                emojiCount++
            }
        }
        return if (emojiCount > 1 || !emojiMatcher?.matches()!!) {
            ""
        } else null
    }
}