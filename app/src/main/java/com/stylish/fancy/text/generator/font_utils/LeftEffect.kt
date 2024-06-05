package com.stylish.fancy.text.generator.font_utils

import com.stylish.fancy.text.generator.interfaces.Style

class LeftEffect(private var left: String) : Style {

    override fun generate(input: String?): String {
        val result = StringBuilder()
        if (input != null) {
            for (i in input.indices) {
                if (input[i] == ' ') {
                    result.append(left).append(" ")
                } else {
                    result.append(left).append(input[i])
                }
            }
        }
        result.append(left)
        return result.toString()
    }

    override fun hashCode(): Int {
        return left.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LeftEffect

        if (left != other.left) return false

        return true
    }
}