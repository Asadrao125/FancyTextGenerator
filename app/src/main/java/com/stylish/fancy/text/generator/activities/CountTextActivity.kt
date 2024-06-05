package com.stylish.fancy.text.generator.activities

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.stylish.fancy.text.generator.R
import com.stylish.fancy.text.generator.databinding.ActivityCountTextBinding

class CountTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountTextBinding

    lateinit var toolbarTitle: TextView
    lateinit var icBackOrMenu: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_count_text)

        icBackOrMenu = findViewById(R.id.icBackOrMenu)
        toolbarTitle = findViewById(R.id.toolbarTitle)
        toolbarTitle.setText(R.string.count_text)
        icBackOrMenu.setOnClickListener { onBackPressed() }

        binding.tvCountText.setOnClickListener {
            hideKeyboard(this@CountTextActivity)
            var input = binding.edtInput.text.toString()
            val temp = input
            if (input.isNotEmpty()) {
                binding.outputLayout.visibility = View.VISIBLE
                input = input.replace("\\s{2,}".toRegex(), " ").trim { it <= ' ' }
                val words =
                    input.trim { it <= ' ' }.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                if (words.isNotEmpty()) {
                    binding.tvTotalWords.text = "Total Words: " + words.size
                }
                if (binding.checkBox.isChecked) {
                    binding.tvTotalLetters.text = "Total Letters: " + temp.length
                } else {
                    val spaces = input.replace("[^ ]".toRegex(), "").length
                    val res = input.length - spaces
                    binding.tvTotalLetters.text = "Total Letters: $res"
                }
                if (binding.cbReverseText.isChecked) {
                    binding.tvReverseText.visibility = View.VISIBLE
                    val buffer = StringBuffer(input)
                    binding.tvReverseText.text = buffer.reverse().toString()
                } else {
                    binding.tvReverseText.visibility = View.GONE
                }
                if (binding.cbCountWithSpaces.isChecked) {
                    binding.tvOutput.visibility = View.VISIBLE
                    binding.tvOutput.text = removeSpace(temp)
                } else {
                    binding.tvOutput.visibility = View.GONE
                    binding.tvOutput.text = input
                }
            } else {
                binding.outputLayout.visibility = View.GONE
                Toast.makeText(this@CountTextActivity, "Please enter input", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun removeSpace(s: String?): String {
            var withoutSpaces = ""
            for (i in 0 until s!!.length) {
                if (s[i] != ' ') withoutSpaces += s[i]
            }
            return withoutSpaces
        }

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