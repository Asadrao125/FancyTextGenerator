package com.stylish.fancy.text.generator.font_utils

import com.stylish.fancy.text.generator.interfaces.Style

class RightEffect(private var character: String) : Style {

    override fun generate(input: String?): String? {
        return try {
            val result = StringBuilder()
            if (input != null) {
                for (i in input.indices) {
                    if (input[i] == ' ') {
                        result.append(" ")
                    } else {
                        result.append(input[i]).append(character)
                    }
                }
            }
            result.toString()
        } catch (e: OutOfMemoryError) {
            ""
        }
    }

    override fun hashCode(): Int {
        return character.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RightEffect

        if (character != other.character) return false

        return true
    }
}