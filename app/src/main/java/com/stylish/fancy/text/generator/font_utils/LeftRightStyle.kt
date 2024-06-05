package com.stylish.fancy.text.generator.font_utils

import com.stylish.fancy.text.generator.interfaces.Style

class LeftRightStyle(private var left: String, private var right: String) : Style {

    override fun generate(input: String?): String? {
        val result = StringBuilder()
        if (input != null) {
            for (i in input.indices) {
                if (input[i] == ' ') {
                    result.append(" ")
                } else {
                    result.append(left).append(input[i]).append(right)
                }
            }
        }
        return result.toString()
    }

    override fun hashCode(): Int {
        var result = left.hashCode()
        result = 31 * result + right.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LeftRightStyle

        if (left != other.left) return false
        if (right != other.right) return false

        return true
    }
}