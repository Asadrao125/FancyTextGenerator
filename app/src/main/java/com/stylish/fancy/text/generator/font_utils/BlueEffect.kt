package com.stylish.fancy.text.generator.font_utils

import com.stylish.fancy.text.generator.interfaces.Style

class BlueEffect : Style {
    override fun generate(input: String?): String {
        val result = StringBuilder()
        var letter: Char
        if (input != null) {
            for (element in input) {
                letter = element
                val index = NORMAL.indexOf(letter)
                try {
                    result.append(if (index != -1) EFFECTS[index] else letter)
                } catch (e: Exception) {
                    result.append(letter)
                }
            }
        }
        return result.toString()
    }

    override fun hashCode(): Int {
        return EFFECTS.contentHashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    companion object {
        private const val NORMAL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        private val EFFECTS: Array<String?>

        init {
            val blue =
                "\uD83C\uDDE6 \uD83C\uDDE7 \uD83C\uDDE8 \uD83C\uDDE9 \uD83C\uDDEA \uD83C\uDDEB " +
                        "\uD83C\uDDEC \uD83C\uDDED \uD83C\uDDEE \uD83C\uDDEF \uD83C\uDDF0 \uD83C\uDDF1 " +
                        "\uD83C\uDDF2 \uD83C\uDDF3 \uD83C\uDDF4 \uD83C\uDDF5 \uD83C\uDDF6 \uD83C\uDDF7 " +
                        "\uD83C\uDDF8 \uD83C\uDDF9 \uD83C\uDDFA \uD83C\uDDFB \uD83C\uDDFC \uD83C\uDDFD " +
                        "\uD83C\uDDFE \uD83C\uDDFF \uD83C\uDDE6 \uD83C\uDDE7 \uD83C\uDDE8 \uD83C\uDDE9 " +
                        "\uD83C\uDDEA \uD83C\uDDEB \uD83C\uDDEC \uD83C\uDDED \uD83C\uDDEE \uD83C\uDDEF " +
                        "\uD83C\uDDF0 \uD83C\uDDF1 \uD83C\uDDF2 \uD83C\uDDF3 \uD83C\uDDF4 \uD83C\uDDF5 " +
                        "\uD83C\uDDF6 \uD83C\uDDF7 \uD83C\uDDF8 \uD83C\uDDF9 \uD83C\uDDFA \uD83C\uDDFB " +
                        "\uD83C\uDDFC \uD83C\uDDFD \uD83C\uDDFE \uD83C\uDDFF "
            EFFECTS = arrayOfNulls(NORMAL.length)
            val length = NORMAL.length
            val step = blue.length / length
            for (j in 0 until length) {
                EFFECTS[j] = blue.substring(j * step, (j + 1) * step)
            }
        }
    }
}