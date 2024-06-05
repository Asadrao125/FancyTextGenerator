package com.stylish.fancy.text.generator.activities

import android.app.Activity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.stylish.fancy.text.generator.R
import com.stylish.fancy.text.generator.databinding.ActivityMarkTextBinding
import com.stylish.fancy.text.generator.utils.GlobalFunction

class MarkTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMarkTextBinding

    var result: String? = null
    lateinit var icBackOrMenu: ImageView
    lateinit var toolbarTitle: TextView
    private lateinit var globalFunction: GlobalFunction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mark_text)

        icBackOrMenu = findViewById(R.id.icBackOrMenu)
        toolbarTitle = findViewById(R.id.toolbarTitle)
        toolbarTitle.text = getString(R.string.mark)
        globalFunction = GlobalFunction(this)
        icBackOrMenu.setOnClickListener { onBackPressed() }

        binding.tvnMarkText.setOnClickListener {
            hideKeyboard(this@MarkTextActivity)
            val input = binding.edtInput.text.toString()
            val num = binding.edtTotalWord.text.toString().trim { it <= ' ' }
            if (input.isNotEmpty() && num.isNotEmpty()) {
                val totalWord = num.toInt()
                if (totalWord <= input.length) {
                    binding.outputLayout.visibility = View.VISIBLE
                    binding.noOutput.visibility = View.GONE
                    val `val` = input.substring(0, totalWord)
                    val newS = input.replace(`val`, "")
                    val next = "<font color='#EE0000'>$`val`</font>"
                    binding.tvOutput.text = Html.fromHtml(next + newS)
                    result = Html.fromHtml(next + newS).toString()
                } else {
                    binding.noOutput.visibility = View.VISIBLE
                    binding.outputLayout.visibility = View.GONE
                    Toast.makeText(
                        this@MarkTextActivity,
                        "Please enter valid limit",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                binding.outputLayout.visibility = View.GONE
                binding.noOutput.visibility = View.VISIBLE
                Toast.makeText(this@MarkTextActivity, "Please enter input", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.shareLayout.setOnClickListener {
            if (binding.tvOutput.text.toString() != "Output") {
                globalFunction.shareMsg(binding.tvOutput.text.toString())
            } else {
                Toast.makeText(applicationContext, "No Output To Share", Toast.LENGTH_SHORT).show()
            }
        }

        binding.copyLayout.setOnClickListener {
            if (binding.tvOutput.text.toString() != "Output") {
                globalFunction.copyOutput(binding.tvOutput.text.toString())
            } else {
                Toast.makeText(applicationContext, "No Output To Share", Toast.LENGTH_SHORT).show()
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