package com.stylish.fancy.text.generator.utils

import com.stylish.fancy.text.generator.R
import com.stylish.fancy.text.generator.model.SelectEmoticonsModel

object StaticMethods {
    @JvmStatic
    val emoticons: ArrayList<SelectEmoticonsModel>
        get() {
            val emoticonsList = ArrayList<SelectEmoticonsModel>()
            emoticonsList.add(SelectEmoticonsModel("Love", R.drawable.love))
            emoticonsList.add(SelectEmoticonsModel("Happy", R.drawable.happy))
            emoticonsList.add(SelectEmoticonsModel("Music", R.drawable.music))
            emoticonsList.add(SelectEmoticonsModel("Animal", R.drawable.animal))
            emoticonsList.add(SelectEmoticonsModel("Angry", R.drawable.angry))
            emoticonsList.add(SelectEmoticonsModel("Sad", R.drawable.sad))
            emoticonsList.add(SelectEmoticonsModel("Sleeping", R.drawable.sleeping))
            emoticonsList.add(SelectEmoticonsModel("Excited", R.drawable.excited))
            emoticonsList.add(SelectEmoticonsModel("Hungry", R.drawable.hungry))
            emoticonsList.add(SelectEmoticonsModel("Shy", R.drawable.shy))
            emoticonsList.add(SelectEmoticonsModel("Other", R.drawable.other))
            emoticonsList.add(SelectEmoticonsModel("Kiss", R.drawable.kiss))
            emoticonsList.add(SelectEmoticonsModel("Smile", R.drawable.smile))
            emoticonsList.add(SelectEmoticonsModel("Laugh", R.drawable.laugh))
            return emoticonsList
        }
}