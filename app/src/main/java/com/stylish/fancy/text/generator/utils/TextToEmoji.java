package com.stylish.fancy.text.generator.utils;

import android.content.Context;

public class TextToEmoji {
    Context context;

    public TextToEmoji(Context context) {
        this.context = context;
    }

    public String detectLetters(String inputString, String emoji) {
        StringBuilder sb = new StringBuilder();
        sb.append(".");
        for (char letter : inputString.toCharArray()) {
            if (Character.isLetterOrDigit(letter)) {

                /* Numbers */

                if (letter == '0') sb.append(number0().replace("⭐", emoji));
                if (letter == '1') sb.append(number1().replace("⭐", emoji));
                if (letter == '2') sb.append(number2().replace("⭐", emoji));
                if (letter == '3') sb.append(number3().replace("⭐", emoji));
                if (letter == '4') sb.append(number4().replace("⭐", emoji));
                if (letter == '5') sb.append(number5().replace("⭐", emoji));
                if (letter == '6') sb.append(number6().replace("⭐", emoji));
                if (letter == '7') sb.append(number7().replace("⭐", emoji));
                if (letter == '8') sb.append(number8().replace("⭐", emoji));
                if (letter == '9') sb.append(number9().replace("⭐", emoji));

                /* Small Letters */

                if (letter == 'a') sb.append(smallA().replace("⭐", emoji));
                if (letter == 'b') sb.append(smallB().replace("⭐", emoji));
                if (letter == 'c') sb.append(smallC().replace("⭐", emoji));
                if (letter == 'd') sb.append(smallD().replace("⭐", emoji));
                if (letter == 'e') sb.append(smallE().replace("⭐", emoji));

                if (letter == 'f') sb.append(smallF().replace("⭐", emoji));
                if (letter == 'g') sb.append(smallG().replace("⭐", emoji));
                if (letter == 'h') sb.append(smallH().replace("⭐", emoji));
                if (letter == 'i') sb.append(smallI().replace("⭐", emoji));
                if (letter == 'j') sb.append(smallJ().replace("⭐", emoji));

                if (letter == 'k') sb.append(smallK().replace("⭐", emoji));
                if (letter == 'l') sb.append(smallL().replace("⭐", emoji));
                if (letter == 'm') sb.append(smallM().replace("⭐", emoji));
                if (letter == 'n') sb.append(smallN().replace("⭐", emoji));
                if (letter == 'o') sb.append(smallO().replace("⭐", emoji));

                if (letter == 'p') sb.append(smallP().replace("⭐", emoji));
                if (letter == 'q') sb.append(smallQ().replace("⭐", emoji));
                if (letter == 'r') sb.append(smallR().replace("⭐", emoji));
                if (letter == 's') sb.append(smallS().replace("⭐", emoji));
                if (letter == 't') sb.append(smallT().replace("⭐", emoji));

                if (letter == 'u') sb.append(smallU().replace("⭐", emoji));
                if (letter == 'v') sb.append(smallV().replace("⭐", emoji));
                if (letter == 'w') sb.append(smallW().replace("⭐", emoji));
                if (letter == 'x') sb.append(smallX().replace("⭐", emoji));
                if (letter == 'y') sb.append(smallY().replace("⭐", emoji));
                if (letter == 'z') sb.append(smallZ().replace("⭐", emoji));

                /* Capital Letters */

                if (letter == 'A') sb.append(capitalA().replace("⭐", emoji));
                if (letter == 'B') sb.append(capitalB().replace("⭐", emoji));
                if (letter == 'C') sb.append(capitalC().replace("⭐", emoji));
                if (letter == 'D') sb.append(capitalD().replace("⭐", emoji));
                if (letter == 'E') sb.append(capitalE().replace("⭐", emoji));

                if (letter == 'F') sb.append(capitalF().replace("⭐", emoji));
                if (letter == 'G') sb.append(capitalG().replace("⭐", emoji));
                if (letter == 'H') sb.append(capitalH().replace("⭐", emoji));
                if (letter == 'I') sb.append(capitalI().replace("⭐", emoji));
                if (letter == 'J') sb.append(capitalJ().replace("⭐", emoji));

                if (letter == 'K') sb.append(capitalK().replace("⭐", emoji));
                if (letter == 'L') sb.append(capitalL().replace("⭐", emoji));
                if (letter == 'M') sb.append(capitalM().replace("⭐", emoji));
                if (letter == 'N') sb.append(capitalN().replace("⭐", emoji));
                if (letter == 'O') sb.append(capitalO().replace("⭐", emoji));

                if (letter == 'P') sb.append(capitalP().replace("⭐", emoji));
                if (letter == 'Q') sb.append(capitalQ().replace("⭐", emoji));
                if (letter == 'R') sb.append(capitalR().replace("⭐", emoji));
                if (letter == 'S') sb.append(capitalS().replace("⭐", emoji));
                if (letter == 'T') sb.append(capitalT().replace("⭐", emoji));

                if (letter == 'U') sb.append(capitalU().replace("⭐", emoji));
                if (letter == 'V') sb.append(capitalV().replace("⭐", emoji));
                if (letter == 'W') sb.append(capitalW().replace("⭐", emoji));
                if (letter == 'X') sb.append(capitalX().replace("⭐", emoji));
                if (letter == 'Y') sb.append(capitalY().replace("⭐", emoji));
                if (letter == 'Z') sb.append(capitalZ().replace("⭐", emoji));

            }
        }
        return sb.toString();
    }

    /* Numbers */

    public String number0() {
        return "⭐⭐⭐⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐⭐⭐⭐\n\n\n";
    }

    public String number1() {
        return "      ⭐ \n" +
                "   ⭐ ⭐\n" +
                " ⭐   ⭐\n" +
                "         ⭐\n" +
                "         ⭐\n" +
                "         ⭐\n" +
                "⭐⭐⭐⭐⭐\n\n\n";
    }

    public String number2() {
        return "     ⭐  ⭐  \n" +
                " ⭐       ⭐\n" +
                "         ⭐ \n" +
                "      ⭐    \n" +
                "   ⭐       \n" +
                " ⭐         \n" +
                "⭐⭐⭐⭐\n\n\n";
    }

    public String number3() {
        return "⭐⭐⭐⭐\n" +
                "               ⭐\n" +
                "               ⭐\n" +
                "⭐⭐⭐⭐\n" +
                "               ⭐\n" +
                "               ⭐\n" +
                "⭐⭐⭐⭐\n\n\n";
    }

    public String number4() {
        return "⭐           \n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐⭐⭐⭐\n" +
                "               ⭐\n" +
                "               ⭐\n" +
                "               ⭐\n\n\n";
    }

    public String number5() {
        return "⭐⭐⭐⭐ \n" +
                "⭐      \n" +
                "⭐      \n" +
                "⭐⭐⭐⭐\n" +
                "               ⭐\n" +
                "               ⭐\n" +
                "               ⭐\n" +
                "⭐⭐⭐⭐\n\n\n";
    }

    public String number6() {
        return "⭐        \n" +
                "⭐        \n" +
                "⭐        \n" +
                "⭐⭐⭐⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐⭐⭐⭐\n\n\n";
    }

    public String number7() {
        return "⭐⭐⭐⭐\n" +
                "               ⭐\n" +
                "               ⭐\n" +
                "     ⭐⭐⭐\n" +
                "               ⭐\n" +
                "               ⭐\n" +
                "               ⭐\n" +
                "               ⭐\n\n\n";
    }

    public String number8() {
        return "⭐⭐⭐⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐⭐⭐⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐⭐⭐⭐\n\n\n";
    }

    public String number9() {
        return "⭐⭐⭐⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐⭐⭐⭐\n" +
                "               ⭐\n" +
                "               ⭐\n" +
                "               ⭐\n\n\n";
    }

    /* Small Letters */

    public String smallA() {
        return "      ⭐\n" +
                "⭐         ⭐\n" +
                "              ⭐    \n" +
                "              ⭐   \n" +
                "⭐⭐    ⭐        \n" +
                "⭐         ⭐ \n" +
                "  ⭐⭐⭐\n\n\n";
    }

    public String smallB() {
        return "⭐        \n" +
                "⭐        \n" +
                "⭐        \n" +
                "⭐ ⭐ ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐ ⭐ ⭐\n\n\n";
    }

    public String smallC() {
        return "   ⭐⭐⭐\n" +
                "⭐    \n" +
                "⭐    \n" +
                "⭐    \n" +
                "⭐    \n" +
                "⭐    \n" +
                "   ⭐⭐⭐\n\n\n";
    }

    public String smallD() {
        return "              ⭐\n" +
                "              ⭐\n" +
                "              ⭐\n" +
                "  ⭐ ⭐ ⭐\n" +
                "⭐         ⭐\n" +
                "⭐         ⭐\n" +
                "   ⭐  ⭐\n\n\n";
    }

    public String smallE() {
        return "     ⭐   ⭐\n" +
                " ⭐          ⭐\n" +
                "⭐            ⭐\n" +
                "⭐⭐ ⭐ ⭐\n" +
                "⭐     \n" +
                "⭐     \n" +
                " ⭐⭐⭐⭐\n\n\n";
    }

    public String smallF() {
        return "     ⭐  ⭐\n" +
                " ⭐         ⭐\n" +
                "⭐       \n" +
                "⭐⭐⭐⭐\n" +
                "⭐     \n" +
                "⭐     \n" +
                "⭐\n\n\n";
    }

    public String smallG() {
        return "    ⭐  ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐⭐⭐⭐\n" +
                "               ⭐\n" +
                "⭐          ⭐ \n" +
                "   ⭐ ⭐\n\n\n";
    }

    public String smallH() {
        return "⭐\n" +
                "⭐       \n" +
                "⭐       \n" +
                "⭐⭐⭐⭐ \n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n\n\n";
    }

    public String smallI() {
        return "⭐\n" +
                "  \n" +
                "⭐\n" +
                "⭐\n" +
                "⭐\n" +
                "⭐\n" +
                "⭐\n\n\n";
    }

    public String smallJ() {
        return "           ⭐\n" +
                "         \n" +
                "           ⭐\n" +
                "           ⭐\n" +
                "⭐      ⭐\n" +
                " ⭐     ⭐\n" +
                "    ⭐ ⭐\n\n\n";
    }

    public String smallK() {
        return "⭐       ⭐\n" +
                "⭐    ⭐       \n" +
                "⭐  ⭐         \n" +
                "⭐⭐\n" +
                "⭐   ⭐        \n" +
                "⭐     ⭐     \n" +
                "⭐       ⭐\n\n\n";
    }

    public String smallL() {
        return "⭐\n" +
                "⭐          \n" +
                "⭐          \n" +
                "⭐\n" +
                "⭐          \n" +
                "⭐          \n" +
                "  ⭐⭐⭐\n\n\n";
    }

    public String smallM() {
        return "⭐                     \n" +
                "⭐     ⭐      ⭐     \n" +
                "⭐⭐     ⭐       ⭐   \n" +
                "⭐          ⭐       ⭐\n" +
                "⭐          ⭐       ⭐\n\n\n";
    }

    public String smallN() {
        return "⭐         \n" +
                "⭐     ⭐      \n" +
                "⭐⭐     ⭐   \n" +
                "⭐          ⭐  \n" +
                "⭐          ⭐  \n\n\n";
    }

    public String smallO() {
        return "⭐⭐⭐⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐⭐⭐⭐\n\n\n";
    }

    public String smallP() {
        return "⭐⭐⭐⭐\n" +
                "⭐           ⭐\n" +
                "⭐           ⭐\n" +
                "⭐⭐⭐⭐  \n" +
                "⭐        \n" +
                "⭐        \n" +
                "⭐\n\n\n";
    }

    public String smallQ() {
        return "    ⭐           ⭐\n" +
                " ⭐  ⭐       ⭐\n" +
                "⭐   ⭐       ⭐\n" +
                "  ⭐  ⭐      ⭐\n" +
                "          ⭐     ⭐\n" +
                "           ⭐    ⭐\n" +
                "               ⭐\n\n\n";
    }

    public String smallR() {
        return "⭐⭐⭐⭐\n" +
                "⭐            ⭐     \n" +
                "⭐            ⭐   \n" +
                "⭐⭐⭐⭐\n" +
                "⭐           ⭐    \n" +
                "⭐            ⭐  \n" +
                "⭐             ⭐\n\n\n";
    }

    public String smallS() {
        return "      ⭐ ⭐   \n" +
                "  ⭐       ⭐ \n" +
                "⭐         \n" +
                "     ⭐      \n" +
                "          ⭐  \n" +
                "⭐        ⭐ \n" +
                "    ⭐ ⭐    \n\n\n";
    }

    public String smallT() {
        return "⭐        \n" +
                "⭐        \n" +
                "⭐        \n" +
                "⭐⭐⭐    \n" +
                "⭐        \n" +
                "⭐      ⭐\n" +
                "  ⭐ ⭐\n\n\n";
    }

    public String smallU() {
        return "⭐             \n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐⭐⭐⭐\n" +
                "                 ⭐\n\n\n";
    }

    public String smallV() {
        return "⭐             ⭐\n" +
                " ⭐           ⭐\n" +
                "  ⭐         ⭐\n" +
                "   ⭐       ⭐\n" +
                "    ⭐     ⭐\n" +
                "     ⭐   ⭐\n" +
                "         ⭐\n\n\n";
    }

    public String smallW() {
        return "⭐      ⭐       ⭐\n" +
                "⭐    ⭐⭐    ⭐\n" +
                "⭐   ⭐  ⭐   ⭐\n" +
                "⭐  ⭐    ⭐  ⭐\n" +
                "⭐ ⭐      ⭐ ⭐\n" +
                "⭐⭐        ⭐⭐\n" +
                "⭐                 ⭐\n\n\n";
    }

    public String smallX() {
        return "⭐            ⭐\n" +
                "   ⭐      ⭐  \n" +
                "     ⭐  ⭐    \n" +
                "        ⭐      \n" +
                "     ⭐  ⭐    \n" +
                "   ⭐       ⭐ \n" +
                " ⭐            ⭐\n\n\n";
    }

    public String smallY() {
        return "⭐       ⭐\n" +
                " ⭐     ⭐ \n" +
                "  ⭐   ⭐  \n" +
                "   ⭐ ⭐   \n" +
                "      ⭐    \n" +
                "      ⭐    \n" +
                "      ⭐\n\n\n";
    }

    public String smallZ() {
        return "⭐⭐⭐⭐⭐    \n" +
                "                 ⭐      \n" +
                "             ⭐       \n" +
                "           ⭐         \n" +
                "       ⭐          \n" +
                "   ⭐           \n" +
                "⭐⭐⭐⭐⭐\n\n\n";
    }

    /* Capital Letters */

    public String capitalA() {
        return "      ⭐⭐\n" +
                "     ⭐   ⭐\n" +
                "    ⭐     ⭐\n" +
                "   ⭐       ⭐\n" +
                "  ⭐⭐⭐⭐\n" +
                " ⭐            ⭐\n" +
                "⭐              ⭐\n\n\n";
    }

    public String capitalB() {
        return "⭐⭐⭐\n" +
                "⭐         ⭐\n" +
                "⭐         ⭐\n" +
                "⭐⭐⭐\n" +
                "⭐         ⭐\n" +
                "⭐         ⭐\n" +
                "⭐⭐⭐\n\n\n";
    }

    public String capitalC() {
        return "   ⭐⭐⭐\n" +
                "⭐    \n" +
                "⭐    \n" +
                "⭐    \n" +
                "⭐    \n" +
                "⭐    \n" +
                "   ⭐⭐⭐\n\n\n";
    }

    public String capitalD() {
        return "⭐⭐⭐\n" +
                "⭐          ⭐\n" +
                "⭐            ⭐\n" +
                "⭐             ⭐\n" +
                "⭐            ⭐\n" +
                "⭐          ⭐\n" +
                "⭐⭐⭐\n\n\n";
    }

    public String capitalE() {
        return "⭐⭐⭐⭐\n" +
                "⭐          \n" +
                "⭐          \n" +
                "⭐⭐⭐⭐\n" +
                "⭐          \n" +
                "⭐          \n" +
                "⭐⭐⭐⭐\n\n\n";
    }

    public String capitalF() {
        return "⭐⭐⭐⭐\n" +
                "⭐          \n" +
                "⭐          \n" +
                "⭐⭐⭐⭐\n" +
                "⭐          \n" +
                "⭐          \n" +
                "⭐\n\n\n";
    }

    public String capitalG() {
        return "⭐⭐⭐⭐\n" +
                "⭐        \n" +
                "⭐        \n" +
                "⭐        \n" +
                "⭐     ⭐⭐\n" +
                "⭐          ⭐\n" +
                "⭐⭐⭐⭐\n\n\n";
    }

    public String capitalH() {
        return "⭐             ⭐\n" +
                "⭐             ⭐\n" +
                "⭐             ⭐\n" +
                "⭐ ⭐ ⭐ ⭐\n" +
                "⭐             ⭐\n" +
                "⭐             ⭐\n" +
                "⭐             ⭐\n\n\n";
    }

    public String capitalI() {
        return " ⭐⭐⭐⭐⭐\n" +
                "           ⭐\n" +
                "           ⭐\n" +
                "           ⭐\n" +
                "           ⭐\n" +
                "           ⭐\n" +
                " ⭐⭐⭐⭐⭐\n\n\n";
    }

    public String capitalJ() {
        return " ⭐⭐⭐⭐⭐\n" +
                "           ⭐\n" +
                "           ⭐\n" +
                "           ⭐\n" +
                "           ⭐\n" +
                "⭐      ⭐\n" +
                "  ⭐⭐\n\n\n";
    }

    public String capitalK() {
        return "⭐       ⭐\n" +
                "⭐    ⭐       \n" +
                "⭐  ⭐         \n" +
                "⭐⭐\n" +
                "⭐   ⭐        \n" +
                "⭐     ⭐     \n" +
                "⭐       ⭐\n\n\n";
    }

    public String capitalL() {
        return "⭐\n" +
                "⭐          \n" +
                "⭐          \n" +
                "⭐\n" +
                "⭐          \n" +
                "⭐          \n" +
                "⭐⭐⭐⭐\n\n\n";
    }

    public String capitalM() {
        return "⭐              ⭐\n" +
                "⭐ ⭐  ⭐ ⭐  \n" +
                "⭐    ⭐     ⭐  \n" +
                "⭐              ⭐ \n" +
                "⭐              ⭐  \n" +
                "⭐              ⭐ \n" +
                "⭐              ⭐\n\n\n";
    }

    public String capitalN() {
        return "⭐                ⭐\n" +
                "⭐ ⭐          ⭐\n" +
                "⭐   ⭐        ⭐\n" +
                "⭐     ⭐      ⭐\n" +
                "⭐       ⭐    ⭐\n" +
                "⭐         ⭐  ⭐\n" +
                "⭐            ⭐\n\n\n";
    }

    public String capitalO() {
        return "⭐⭐⭐⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐⭐⭐⭐\n\n\n";
    }

    public String capitalP() {
        return "⭐⭐⭐⭐\n" +
                "⭐           ⭐\n" +
                "⭐           ⭐\n" +
                "⭐⭐⭐⭐  \n" +
                "⭐        \n" +
                "⭐        \n" +
                "⭐\n\n\n";
    }

    public String capitalQ() {
        return "⭐⭐⭐⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐          ⭐\n" +
                "⭐⭐⭐⭐\n" +
                "                 ⭐\n\n\n";
    }

    public String capitalR() {
        return "⭐⭐⭐⭐\n" +
                "⭐            ⭐     \n" +
                "⭐            ⭐   \n" +
                "⭐⭐⭐⭐\n" +
                "⭐           ⭐    \n" +
                "⭐            ⭐  \n" +
                "⭐             ⭐\n\n\n";
    }

    public String capitalS() {
        return "      ⭐ ⭐   \n" +
                "  ⭐       ⭐ \n" +
                "⭐         \n" +
                "     ⭐      \n" +
                "          ⭐  \n" +
                "⭐        ⭐ \n" +
                "    ⭐ ⭐    \n\n\n";
    }

    public String capitalT() {
        return "⭐⭐⭐⭐⭐\n" +
                "          ⭐\n" +
                "          ⭐\n" +
                "          ⭐\n" +
                "          ⭐\n" +
                "          ⭐\n" +
                "          ⭐\n\n\n";
    }

    public String capitalU() {
        return "⭐          ⭐\n" +
                "⭐          ⭐     \n" +
                "⭐          ⭐     \n" +
                "⭐          ⭐\n" +
                "⭐          ⭐     \n" +
                "⭐          ⭐     \n" +
                "⭐⭐⭐⭐\n\n\n";
    }

    public String capitalV() {
        return "⭐             ⭐\n" +
                " ⭐           ⭐\n" +
                "  ⭐         ⭐\n" +
                "   ⭐       ⭐\n" +
                "    ⭐     ⭐\n" +
                "     ⭐   ⭐\n" +
                "         ⭐\n\n\n";
    }

    public String capitalW() {
        return "⭐      ⭐       ⭐\n" +
                "⭐    ⭐⭐    ⭐\n" +
                "⭐   ⭐  ⭐   ⭐\n" +
                "⭐  ⭐    ⭐  ⭐\n" +
                "⭐ ⭐      ⭐ ⭐\n" +
                "⭐⭐        ⭐⭐\n" +
                "⭐                 ⭐\n\n\n";
    }

    public String capitalX() {
        return "⭐            ⭐\n" +
                "   ⭐      ⭐  \n" +
                "     ⭐  ⭐    \n" +
                "        ⭐      \n" +
                "     ⭐  ⭐    \n" +
                "   ⭐       ⭐ \n" +
                " ⭐            ⭐\n\n\n";
    }

    public String capitalY() {
        return "⭐       ⭐\n" +
                " ⭐     ⭐ \n" +
                "  ⭐   ⭐  \n" +
                "   ⭐ ⭐   \n" +
                "      ⭐    \n" +
                "      ⭐    \n" +
                "      ⭐\n\n\n";
    }

    public String capitalZ() {
        return "⭐⭐⭐⭐⭐    \n" +
                "                 ⭐      \n" +
                "             ⭐       \n" +
                "           ⭐         \n" +
                "       ⭐          \n" +
                "   ⭐           \n" +
                "⭐⭐⭐⭐⭐\n\n\n";
    }
}