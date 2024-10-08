package com.stylish.fancy.text.generator.font_utils

import com.stylish.fancy.text.generator.interfaces.Style

class ReplaceEffect private constructor(replacement: String) : Style {
    private var replacement = ""

    init {
        this.replacement = replacement
    }

    override fun hashCode(): Int {
        return replacement.hashCode()
    }

    override fun generate(input: String?): String {
        val result = StringBuilder()
        var letter: Char
        for (i in 0 until input!!.length) {
            letter = input[i]
            val index = NORMAL!!.indexOf(letter)
            result.append(if (index != -1) replacement[index] else letter)
        }
        return result.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReplaceEffect

        if (replacement != other.replacement) return false

        return true
    }

    companion object {
        var NORMAL: String? = null
        private var MAX_LENGTH = 0
        private var STYLES: ArrayList<String> = ArrayList()
        private const val CIRCLE = "\u24d0\u24d1\u24d2\u24d3\u24d4\u24d5\u24d6\u24d7\u24d8\u24d9" +
                "\u24da\u24db\u24dc\u24dd\u24de\u24df\u24e0\u24e1\u24e2\u24e3\u24e4\u24e5\u24e6\u24e7\u24e8" +
                "\u24e9_,;\u2a00?!\u2298\u29b8'\u24b6\u24b7\u24b8\u24b9" +
                "\u24ba\u24bb\u24bc\u24bd\u24be\u24bf\u24c0\u24c1\u24c2\u24c3\u24c4\u24c5\u24c6\u24c7\u24c8" +
                "\u24c9\u24ca\u24cb\u24cc\u24cd\u24ce\u24cf0\u2460\u2461\u2462\u2463\u2464\u2465\u2466\u2467\u2468"
        private const val FULL_WIDTH = "\uff41\uff42\uff43\uff44\uff45\uff46\uff47\uff48" +
                "\uff49\uff4a\uff4b\uff4c\uff4d\uff4e\uff4f\uff50\uff51\uff52\uff53\uff54\uff55" +
                "\uff56\uff57\uff58\uff59\uff5a_\uff0c\uff1b\uff0e\uff1f\uff01\uff0f\\\uff07\uff21" +
                "\uff22\uff23\uff24\uff25\uff26\uff27\uff28\uff29\uff2a\uff2b\uff2c\uff2d\uff2e\uff2f" +
                "\uff30\uff31\uff32\uff33\uff34\uff35\uff36\uff37\uff38\uff39\uff3a\uff10\uff11\uff12" +
                "\uff13\uff14\uff15\uff16\uff17\uff18\uff19"
        private const val A_CUTE = "\u00e1b\u0107d\u00e9f\u01f5h\u00edj\u1e31\u013a\u1e3f\u0144" +
                "\u0151\u1e55q\u0155\u015bt\u00fav\u1e83x\u04f3\u017a_,;.?!/\\'\u00c1B\u0106D\u00c9F\u01f4" +
                "H\u00edJ\u1e30\u0139\u1e3e\u0143\u0150\u1e54Q\u0154\u015bT\u0170V\u1e82X\u04f2\u01790123456789"
        private const val CURVY_1 = "\u0e04\u0e52\u0188\u0257\ufec9\u093f\ufeed\u0266" +
                "\u0671\ufedd\u16d5\u026d\u0e53\u0e01\u047b\u03c1\u06f9\u027c\u0e23\u0547\u0aaa" +
                "\u06f7\u0e1d\u0e0b\u05e5\u0579_,;\u0701?!/\\'\u0e04\u0e52\u0188\u0257\ufec9\u093f" +
                "\ufeed\u0266\u0671\ufedd\u16d5\u026d\u0e53\u0e01\u047b\u03c1\u06f9\u027c\u0e23" +
                "\u0547\u0aaa\u06f7\u0e1d\u0e0b\u05e5\u05790123456789"
        private const val CURVY_2 = "\u03b1\u0432\u00a2\u2202\u0454\u0192\ufeed\u043d\u03b9\u05e0" +
                "\u043a\u2113\u043c\u03b7\u03c3\u03c1\u06f9\u044f\u0455\u0442\u03c5\u03bd\u03c9\u03c7\u0443" +
                "\u0579_,;.?!/\\'\u03b1\u0432\u00a2\u2202\u0454\u0192\ufeed\u043d\u03b9\u05e0\u043a\u2113" +
                "\u043c\u03b7\u03c3\u03c1\u06f9\u044f\u0455\u0442\u03c5\u03bd\u03c9\u03c7\u0443\u05790123456789"
        private const val CURVY_3 =
            "ค๒ς๔єŦﻮђเןкɭ๓ภ๏קợгรՇยשฬאץչ_,;.?!/\\'ค๒ς๔єŦﻮђเןкɭ๓ภ๏קợгรՇยשฬאץչ0123456789"
        private const val ROCK_DOT =
            "äḅċḋëḟġḧïjḳḷṁṅöṗqṛṡẗüṿẅẍÿż_,;.?!/\\'ÄḄĊḊЁḞĠḦЇJḲḶṀṄÖṖQṚṠṪÜṾẄẌŸŻ012ӟ456789"
        private const val STROKE =
            "Ⱥƀȼđɇfǥħɨɉꝁłmnøᵽꝗɍsŧᵾvwxɏƶ_,;.?!/\\'ȺɃȻĐɆFǤĦƗɈꝀŁMNØⱣꝖɌSŦᵾVWXɎƵ01ƻ3456789"
        private const val SUPPER_SCRIPT =
            "ᵃᵇᶜᵈᵉᶠᵍʰⁱʲᵏˡᵐⁿᵒᵖqʳˢᵗᵘᵛʷˣʸᶻ_,;.?!/\\'ᴬᴮᶜᴰᴱᶠᴳᴴᴵᴶᴷᴸᴹᴺᴼᴾQᴿˢᵀᵁⱽᵂˣʸᶻ⁰¹²³⁴⁵⁶⁷⁸⁹"
        private const val SUB_SCRIPT =
            "ₐbcdₑfgₕᵢⱼₖₗₘₙₒₚqᵣₛₜᵤᵥwₓyz_,;.?!/\\'ₐBCDₑFGₕᵢⱼₖₗₘₙₒₚQᵣₛₜᵤᵥWₓYZ₀₁₂₃₄₅₆₇₈₉"
        private const val FAUX_CYRILLIC =
            "аъсↁэfБЂіјкlмиорqѓѕтцvшхЎz_,;.?!/\\'ДБҀↁЄFБНІЈЌLМИФРQЯЅГЦVЩЖЧZ0123456789"
        private const val SMALL_CAP =
            "ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴩqʀꜱᴛᴜᴠᴡxyᴢ_,;.?!/\\'ᴀʙᴄᴅᴇꜰɢʜɪᴊᴋʟᴍɴᴏᴩQʀꜱᴛᴜᴠᴡxYᴢ0123456789"
        private const val ANTROPHOBIA = "αв¢∂єfgнιנкℓмиσρqяѕтυνωχуz" +
                "_,;.?!/\\'αв¢∂єfgнιנкℓмиσρqяѕтυνωχуz0123456789"
        private const val CURRENCY = "₳฿₵ĐɆ₣₲ⱧłJ₭Ⱡ₥₦Ø₱QⱤ₴₮ɄV₩ӾɎⱫ_,;.?!/\\'₳฿₵ĐɆ₣₲ⱧłJ₭Ⱡ₥" +
                "₦Ø₱QⱤ₴₮ɄV₩ӾɎⱫ0123456789"
        private const val PARANORMAL =
            "αвcdєfghíjklmnσpqrstuvwхчz_,;.?!/\\'αвcdєfghíjklmnσpqrstuvwхчz0123456789"
        private const val SORCERER =
            "ǟɮƈɖɛʄɢɦɨʝӄʟʍռօքզʀֆȶʊʋաӼʏʐ_,;.?!/\\'ǟɮƈɖɛʄɢɦɨʝӄʟʍռօքզʀֆȶʊʋաӼʏʐ0123456789"
        private val EFFECTS: Array<String>

        init {
            NORMAL = "abcdefghijklmnopqrstuvwxyz_,;.?!/\\'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
            MAX_LENGTH = NORMAL!!.length
            EFFECTS = arrayOf<String>(
                "ᎯᏰᏨᎠᎬᎰᎶᎻᎨᏠᏦᏝᎷᏁᎾᏢᏅᏒᏕᎿᏬᏉᏯᎲᎽᏃ_,;.?!/\\'ᎯᏰᏨᎠᎬᎰᎶᎻᎨᏠᏦᏝᎷᏁᎾᏢᏅᏒᏕᎿᏬᏉᏯᎲᎽᏃ0123456789",
                "ᴬᴮᶜᴰᴱᶠᴳᴴᴵᴶᴷᴸᴹᴺᴼᴾᵟᴿˢᵀᵁᵛᵂˣᵞᶻ_,;.?!/\\'ᴬᴮᶜᴰᴱᶠᴳᴴᴵᴶᴷᴸᴹᴺᴼᴾᵟᴿˢᵀᵁᵛᵂˣᵞᶻ0123456789",
                "ꍏꌃꉓꀸꍟꎇꁅꃅꀤꀭꀘ꒒ꎭꈤꂦᖘꆰꋪꌗ꓄ꀎᐯꅏꊼꌩꁴ_,;.?!/\\'ꍏꌃꉓꀸꍟꎇꁅꃅꀤꀭꀘ꒒ꎭꈤꂦᖘꆰꋪꌗ꓄ꀎᐯꅏꊼꌩꁴ0123456789",
                "卂乃匚ⅅ乇千Ꮆ卄丨丿Ҡㄥ爪几ㄖ卩Ɋ尺丂ㄒ凵ᐯ山乂ㄚ乙_,;.?!/\\'卂乃匚ⅅ乇千Ꮆ卄丨丿Ҡㄥ爪几ㄖ卩Ɋ尺丂ㄒ凵ᐯ山乂ㄚ乙0123456789",
                "ᾰ♭ḉᖱḙḟ❡ℏ!♩кℓՊℵ✺℘ǭԻṧтṳṽω✘⑂ℨ_,;.?!/\\'ᾰ♭ḉᖱḙḟ❡ℏ!♩кℓՊℵ✺℘ǭԻṧтṳṽω✘⑂ℨ0123456789",
                "ᎯℬℂⅅℰℱᎶℋℐᎫᏦℒℳℕᎾℙℚℛЅTUᏉᏇXᎽℤ_,;.?!/\\'ᎯℬℂⅅℰℱᎶℋℐᎫᏦℒℳℕᎾℙℚℛЅTUᏉᏇXᎽℤ0123456789",
                "ąβȼď€ƒǥhɨjЌℓʍɲ๏ρǭяşţµ˅ώж¥ƶ_,;.?!/\\'ąβȼď€ƒǥhɨjЌℓʍɲ๏ρǭяşţµ˅ώж¥ƶ0123456789",
                "åβçď£ƒğȟȋjķȽɱñ¤קǭȑ§țɥ√Ψ×ÿž_,;.?!/\\'åβçď£ƒğȟȋjķȽɱñ¤קǭȑ§țɥ√Ψ×ÿž0123456789",
                "ąþȼȡƹƒǥɦɨǰƙŁʍɲǿρǭřȿƮµ˅ώж¥ƶ_,;.?!/\\'ąþȼȡƹƒǥɦɨǰƙŁʍɲǿρǭřȿƮµ˅ώж¥ƶ0123456789",
                "άвςȡέғģħίјķĻмήόρqŕşţùνώxчž_,;.?!/\\'άвςȡέғģħίјķĻмήόρqŕşţùνώxчž0123456789",
                "ÃβČĎẸƑĞĤĮĴЌĹϻŇỖƤǪŘŜŤǗϋŴЖЎŻ_,;.?!/\\'ÃβČĎẸƑĞĤĮĴЌĹϻŇỖƤǪŘŜŤǗϋŴЖЎŻ0123456789",
                "αв¢∂єfgнιנкℓмиσρqяѕтυνωχуz_,;.?!/\\'αв¢∂єfgнιנкℓмиσρqяѕтυνωχуz0123456789",
                "ค๒ς๔єŦﻮђเןкl๓ภ๏קợгรtยשฬץאz_,;.?!/\\'ค๒ς๔єŦﻮђเןкl๓ภ๏קợгรtยשฬץאz0123456789",
                "ĂβČĎĔŦĞĤĨĴĶĹМŃŐРQŔŚŤÚVŴЖŶŹ_,;.?!/\\'ĂβČĎĔŦĞĤĨĴĶĹМŃŐРQŔŚŤÚVŴЖŶŹ0123456789",
                "aвcdeғgнιjĸlмnopqrѕтυvwхyz_,;.?!/\\'aвcdeғgнιjĸlмnopqrѕтυvwхyz0123456789",
                "მჩეძპfცhἶქκlოῆõρგΓჰནυὗwჯყɀ_,;.?!/\\'მჩეძპfცhἶქκlოῆõρგΓჰནυὗwჯყɀ0123456789",
                "ÄBĊĐË₣ĠȞÏĴĶĻMŅÖPǬŖŚȚŮVŴXŸŹ_,;.?!/\\'ÄBĊĐË₣ĠȞÏĴĶĻMŅÖPǬŖŚȚŮVŴXŸŹ0123456789",
                "αвc∂εғgнιנкℓмησρqяsтυvωxүz_,;.?!/\\'αвc∂εғgнιנкℓмησρqяsтυvωxүz0123456789",
                "äḅċďệḟġḧïjḳŀṃńöṗqŕṩẗüṿẅẍÿẓ_,;.?!/\\'äḅċďệḟġḧïjḳŀṃńöṗqŕṩẗüṿẅẍÿẓ0123456789",
                "ḀḃḉḊḕḟḠḧḭjḲḶṁṆṏṖqṙṠṮṳṼẇẌẏẒ_,;.?!/\\'ḀḃḉḊḕḟḠḧḭjḲḶṁṆṏṖqṙṠṮṳṼẇẌẏẒ0123456789",
                "⒜⒝⒞⒟⒠⒡⒢⒣⒤⒥⒦⒧⒨⒩⒪⒫⒬⒭⒮⒯⒰⒱⒲⒳⒴⒵_,;.?!/\\'⒜⒝⒞⒟⒠⒡⒢⒣⒤⒥⒦⒧⒨⒩⒪⒫⒬⒭⒮⒯⒰⒱⒲⒳⒴⒵0123456789",
                "αвς∂єfgнιנкℓмиσρףяѕтυνωאָуz_,;.?!/\\'αвς∂єfgнιנкℓмиσρףяѕтυνωאָуz0123456789",
                "ḀḃḉḊḕḟḠḧḭjḲḶṁṆṏṖqṙṠṮṳṼẇẌẏẒ_,;.?!/\\'ḀḃḉḊḕḟḠḧḭjḲḶṁṆṏṖqṙṠṮṳṼẇẌẏẒ0123456789",
                "ÁßČĎĔŦĞĤĨĴĶĹМŃŐРQŔŚŤÚVŴЖŶŹ_,;.?!/\\'ÁßČĎĔŦĞĤĨĴĶĹМŃŐРQŔŚŤÚVŴЖŶŹ0123456789",
                "ĂбĈĎÊҒĜĤĨĴҚĹMŇÕPØŘŜŤŨVŴҲŶŽ_,;.?!/\\'ĂбĈĎÊҒĜĤĨĴҚĹMŇÕPØŘŜŤŨVŴҲŶŽ0123456789",
                "ąɓçdęƒɠђįʝķɭɱŋǫƥʠŗşţųvwҳƴʐ_,;.?!/\\'ąɓçdęƒɠђįʝķɭɱŋǫƥʠŗşţųvwҳƴʐ0123456789",
                "ꍏ♭☾◗€Ϝ❡♄♗♪ϰ↳♔♫⊙ρ☭☈ⓢ☂☋✓ω⌘☿☡_,;.?!/\\'ꍏ♭☾◗€Ϝ❡♄♗♪ϰ↳♔♫⊙ρ☭☈ⓢ☂☋✓ω⌘☿☡⓪➊➋➌➍➎➏➐➑➒",
                "ﾑ乃ᄃり乇ｷムんﾉﾌズﾚﾶ刀のｱゐ尺丂ｲひ√Wﾒﾘ乙_,;.?!/\\'ﾑ乃ᄃり乇ｷムんﾉﾌズﾚﾶ刀のｱゐ尺丂ｲひ√Wﾒﾘ乙０１２３４５６７８９",
                "ልጌርዕቿቻኗዘጎጋጕረጠክዐየዒዪነፕሁሀሠሸሃጊ_,;.?!/\\'ልጌርዕቿቻኗዘጎጋጕረጠክዐየዒዪነፕሁሀሠሸሃጊ0123456789",
                "ƛƁƇƊЄƑƓӇƖʆƘԼMƝƠƤƢƦƧƬƲƔƜҲƳȤ_,;.?!/\\'ƛƁƇƊЄƑƓӇƖʆƘԼMƝƠƤƢƦƧƬƲƔƜҲƳȤ０１２３４５６７８９",
                "მჩეძპfცhἶქκlოῆõρგΓჰནυὗwჯყɀ_,;.?!/\\'მჩეძპfცhἶქκlოῆõρგΓჰནυὗwჯყɀ0123456789"
            )
            STYLES = ArrayList()
            STYLES.addAll(listOf(*EFFECTS))
            STYLES.add(CIRCLE)
            STYLES.add(FULL_WIDTH)
            STYLES.add(A_CUTE)
            STYLES.add(CURVY_1)
            STYLES.add(CURVY_2)
            STYLES.add(CURVY_3)
            STYLES.add(ROCK_DOT)
            STYLES.add(STROKE)
            STYLES.add(SUPPER_SCRIPT)
            STYLES.add(SUB_SCRIPT)
            STYLES.add(FAUX_CYRILLIC)
            STYLES.add(SMALL_CAP)
            STYLES.add(ANTROPHOBIA)
            STYLES.add(CURRENCY)
            STYLES.add(PARANORMAL)
            STYLES.add(SORCERER)
        }

        fun createStyle(): ArrayList<Style> {
            val styles = ArrayList<Style>()
            for (style in STYLES) {
                styles.add(ReplaceEffect(style))
            }
            return styles
        }
    }
}