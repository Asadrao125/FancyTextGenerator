package com.stylish.fancy.text.generator.font_utils

import java.util.Random

object Zalgo {
    val UP_CHARS = charArrayOf(
        '\u030d', '\u030e',
        '\u0304', '\u0305',
        '\u033f', '\u0311',
        '\u0306', '\u0310',
        '\u0352', '\u0357',
        '\u0351', '\u0307',
        '\u0308', '\u030a',
        '\u0342', '\u0343',
        '\u0344', '\u034a',
        '\u034b', '\u034c',
        '\u0303', '\u0302',
        '\u030c', '\u0350',
        '\u0300', '\u0301',
        '\u030b', '\u030f',
        '\u0312', '\u0313',
        '\u0314', '\u033d',
        '\u0309', '\u0363',
        '\u0364', '\u0365',
        '\u0366', '\u0367',
        '\u0368', '\u0369',
        '\u036a', '\u036b',
        '\u036c', '\u036d',
        '\u036e', '\u036f',
        '\u033e', '\u035b',
        '\u0346', '\u031a'
    )
    val DOWN_CHARS = charArrayOf(
        '\u0316', '\u0317',
        '\u0318', '\u0319',
        '\u031c', '\u031d',
        '\u031e', '\u031f',
        '\u0320', '\u0324',
        '\u0325', '\u0326',
        '\u0329', '\u032a',
        '\u032b', '\u032c',
        '\u032d', '\u032e',
        '\u032f', '\u0330',
        '\u0331', '\u0332',
        '\u0333', '\u0339',
        '\u033a', '\u033b',
        '\u033c', '\u0345',
        '\u0347', '\u0348',
        '\u0349', '\u034d',
        '\u034e', '\u0353',
        '\u0354', '\u0355',
        '\u0356', '\u0359',
        '\u035a', '\u0323'
    )
    val MID_CHARS = charArrayOf(
        '\u0315', '\u031b',
        '\u0340', '\u0341',
        '\u0358', '\u0321',
        '\u0322', '\u0327',
        '\u0328', '\u0334',
        '\u0335', '\u0336',
        '\u034f', '\u035c',
        '\u035d', '\u035e',
        '\u035f', '\u0360',
        '\u0362', '\u0338',
        '\u0337', '\u0361',
        '\u0489'
    )

    private fun isZalgo(c: Char): Boolean {
        for (upChar in UP_CHARS) {
            if (c == upChar) {
                return true
            }
        }
        for (downChar in DOWN_CHARS) {
            if (c == downChar) {
                return true
            }
        }
        for (midChar in MID_CHARS) {
            if (c == midChar) {
                return true
            }
        }
        return false
    }

    @JvmOverloads
    fun generate(source: String, up: Int = 16, mid: Int = 6, down: Int = 16): String {
        val result = StringBuilder()
        val rand = Random(System.currentTimeMillis())
        for (i in source.indices) {
            if (isZalgo(source[i])) {
                continue
            } else {
                result.append(source[i])
            }
            val upCharCount = if (up > 0) rand.nextInt(up) else 0
            val downCharCount = if (down > 0) rand.nextInt(down) else 0
            val midCharCount = if (mid > 0) rand.nextInt(mid) else 0
            for (j in 0 until upCharCount) {
                result.append(UP_CHARS[rand.nextInt(UP_CHARS.size)])
            }
            for (j in 0 until downCharCount) {
                result.append(DOWN_CHARS[rand.nextInt(DOWN_CHARS.size)])
            }
            for (j in 0 until midCharCount) {
                result.append(MID_CHARS[rand.nextInt(MID_CHARS.size)])
            }
        }
        return result.toString()
    }
}