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
import com.stylish.fancy.text.generator.databinding.ActivityReplaceTextBinding
import com.stylish.fancy.text.generator.utils.GlobalFunction

class ReplaceTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReplaceTextBinding

    lateinit var icBackOrMenu: ImageView
    lateinit var toolbarTitle: TextView
    private lateinit var globalFunction: GlobalFunction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_replace_text)

        icBackOrMenu = findViewById(R.id.icBackOrMenu)
        toolbarTitle = findViewById(R.id.toolbarTitle)
        toolbarTitle.text = getString(R.string.replace)
        icBackOrMenu.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        globalFunction = GlobalFunction(this)

        binding.tvReplaceText.setOnClickListener {
            hideKeyboard(this@ReplaceTextActivity)
            val originalText = binding.edtInput.text.toString().trim { it <= ' ' }
            val findText = binding.edtFindText.text.toString().trim { it <= ' ' }
            val replaceWith = binding.edtReplaceWith.text.toString().trim { it <= ' ' }
            if (originalText.isNotEmpty() && findText.isNotEmpty() && replaceWith.isNotEmpty()) {
                if (originalText.contains(findText)) {
                    binding.tvOutput.text = originalText.replace(findText.toRegex(), replaceWith)
                    binding.outputLayout.visibility = View.VISIBLE
                    binding.noOutput.visibility = View.GONE
                } else {
                    binding.outputLayout.visibility = View.GONE
                    binding.noOutput.visibility = View.VISIBLE
                    Toast.makeText(this@ReplaceTextActivity, "No text found", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                binding.outputLayout.visibility = View.GONE
                binding.noOutput.visibility = View.VISIBLE
                Toast.makeText(applicationContext, "Please enter all inputs", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.shareLayout.setOnClickListener {
            if (binding.tvOutput.text.toString() != "Output") {
                globalFunction.shareMsg(binding.tvOutput.text.toString())
            } else {
                Toast.makeText(applicationContext, "No output to share", Toast.LENGTH_SHORT).show()
            }
        }

        binding.copyLayout.setOnClickListener {
            if (binding.tvOutput.text.toString() != "Output") {
                globalFunction.copyOutput(binding.tvOutput.text.toString())
            } else {
                Toast.makeText(applicationContext, "No output to share", Toast.LENGTH_SHORT).show()
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