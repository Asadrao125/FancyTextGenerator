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
import com.stylish.fancy.text.generator.databinding.ActivityReverseTextBinding
import com.stylish.fancy.text.generator.utils.GlobalFunction

class ReverseTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReverseTextBinding

    lateinit var icBackOrMenu: ImageView
    lateinit var toolbarTitle: TextView
    private lateinit var globalFunction: GlobalFunction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reverse_text)

        icBackOrMenu = findViewById(R.id.icBackOrMenu)
        toolbarTitle = findViewById(R.id.toolbarTitle)
        toolbarTitle.text = getString(R.string.reverse_text_cb)
        icBackOrMenu.setOnClickListener { onBackPressed() }
        globalFunction = GlobalFunction(this)

        binding.tvReverseText.setOnClickListener {
            hideKeyboard(this@ReverseTextActivity)
            val input = binding.edtInput.text.toString().trim { it <= ' ' }
            if (input.isNotEmpty()) {
                binding.outputLayout.visibility = View.VISIBLE
                val buffer = StringBuffer(input)
                binding.tvOutput.text = buffer.reverse()
            } else {
                binding.outputLayout.visibility = View.GONE
                Toast.makeText(this@ReverseTextActivity, "Please enter input", Toast.LENGTH_SHORT)
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