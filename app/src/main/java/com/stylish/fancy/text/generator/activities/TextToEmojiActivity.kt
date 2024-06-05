package com.stylish.fancy.text.generator.activities

import android.app.Activity
import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.stylish.fancy.text.generator.R
import com.stylish.fancy.text.generator.databinding.ActivityTextToEmojiBinding
import com.stylish.fancy.text.generator.utils.CopyHandler
import com.stylish.fancy.text.generator.utils.EmojiInputFilter
import com.stylish.fancy.text.generator.utils.TextToEmoji

class TextToEmojiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTextToEmojiBinding

    private lateinit var textToEmoji: TextToEmoji
    private lateinit var copyHandler: CopyHandler
    lateinit var icBackOrMenu: ImageView
    lateinit var toolbarTitle: TextView
    var result = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_text_to_emoji)

        icBackOrMenu = findViewById(R.id.icBackOrMenu)
        toolbarTitle = findViewById(R.id.toolbarTitle)
        toolbarTitle.setText(R.string.text_to_emoji)
        textToEmoji = TextToEmoji(this)
        copyHandler = CopyHandler(this)
        icBackOrMenu.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        val emojiFilter: InputFilter = EmojiInputFilter()

        binding.emoji.filters = arrayOf(emojiFilter)

        binding.tvSubmit.setOnClickListener {
            hideKeyboard(this@TextToEmojiActivity)
            val emojiText = binding.emoji.text.toString()
            val text = binding.text.text.toString()
            result = textToEmoji.detectLetters(text, emojiText)
            if (emojiText.isNotEmpty() && text.isNotEmpty()) {
                binding.tvOutput.text = result
                binding.outputLayout.visibility = View.VISIBLE
            } else {
                binding.outputLayout.visibility = View.GONE
                Toast.makeText(this@TextToEmojiActivity, "Enter Emoji or Text", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.shareLayout.setOnClickListener { copyHandler.shareText(result) }

        binding.copyLayout.setOnClickListener { copyHandler.copyText(result) }
    }

    companion object {
        fun hideKeyboard(activity: Activity) {
            val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            var view = activity.currentFocus
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}