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
import com.stylish.fancy.text.generator.databinding.ActivityCloneTextBinding
import com.stylish.fancy.text.generator.utils.GlobalFunction

class CloneTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCloneTextBinding
    lateinit var toolbarTitle: TextView
    lateinit var icBackOrMenu: ImageView
    private lateinit var globalFunction: GlobalFunction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_clone_text)

        icBackOrMenu = findViewById(R.id.icBackOrMenu)
        toolbarTitle = findViewById(R.id.toolbarTitle)

        toolbarTitle.text = "Clone Text"
        icBackOrMenu.setOnClickListener { onBackPressed() }
        globalFunction = GlobalFunction(this)

        binding.tvCloneText.setOnClickListener {
            hideKeyboard(this@CloneTextActivity)
            val input = binding.edtInput.text.toString().trim { it <= ' ' }
            val num = binding.edtCloneLimit.text.toString().trim { it <= ' ' }
            if (num.isNotEmpty()) {
                val limit = num.toInt()
                if (limit > 0) {
                    if (input.isNotEmpty()) {
                        binding.tvCloneText.isEnabled = false
                        cloneText(input, limit)
                        binding.noOutput.visibility = View.GONE
                        binding.outputLayout.visibility = View.VISIBLE
                    } else {
                        binding.noOutput.visibility = View.VISIBLE
                        binding.outputLayout.visibility = View.GONE
                        Toast.makeText(
                            this@CloneTextActivity,
                            "Please enter input and limit",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    binding.noOutput.visibility = View.VISIBLE
                    binding.outputLayout.visibility = View.GONE
                    Toast.makeText(
                        this@CloneTextActivity,
                        "Please enter valid limit",
                        Toast.LENGTH_SHORT
                    ).show()
                }
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

    private fun cloneText(input: String, limit: Int) {
        binding.tvOutput.post {
            val stringBuilder = StringBuilder()
            for (i in 0 until limit) {
                stringBuilder.append(input).append("\n")
            }
            binding.tvOutput.text = stringBuilder.toString()
            binding.outputLayout.visibility = View.VISIBLE
            binding.tvCloneText.isEnabled = true
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

    private fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}