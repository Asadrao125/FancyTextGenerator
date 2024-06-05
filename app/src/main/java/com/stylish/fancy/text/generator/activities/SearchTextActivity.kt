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
import com.stylish.fancy.text.generator.databinding.ActivitySearchTextBinding
import com.stylish.fancy.text.generator.utils.GlobalFunction

class SearchTextActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchTextBinding

    var result: String? = null
    lateinit var icBackOrMenu: ImageView
    lateinit var toolbarTitle: TextView
    private var globalFunction: GlobalFunction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_text)

        icBackOrMenu = findViewById(R.id.icBackOrMenu)
        toolbarTitle = findViewById(R.id.toolbarTitle)
        toolbarTitle.text = getString(R.string.search)
        icBackOrMenu.setOnClickListener { onBackPressed() }
        globalFunction = GlobalFunction(this)

        binding.tvSearchText.setOnClickListener {
            hideKeyboard(this@SearchTextActivity)
            var input = binding.edtInput.text.toString().trim { it <= ' ' }
            val searchFor = binding.edtSearchFor.text.toString().trim { it <= ' ' }
            if (input.isNotEmpty() && searchFor.isNotEmpty()) {
                if (input.contains(searchFor)) {
                    binding.outputLayout.visibility = View.VISIBLE
                    binding.noOutput.visibility = View.GONE
                    input = input.replace(
                        searchFor.toRegex(),
                        "<font color='#EE0000'>$searchFor</font>"
                    )
                    binding.tvOutput.text = Html.fromHtml(input)
                    result = "" + Html.fromHtml(input)
                } else {
                    binding.outputLayout.visibility = View.GONE
                    binding.noOutput.visibility = View.VISIBLE
                    Toast.makeText(
                        this@SearchTextActivity,
                        "No Text Found: $input",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                binding.outputLayout.visibility = View.GONE
                binding.noOutput.visibility = View.VISIBLE
                Toast.makeText(this@SearchTextActivity, "Please enter input", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.shareLayout.setOnClickListener {
            if (binding.tvOutput.text.toString() != "Output") {
                globalFunction!!.shareMsg(binding.tvOutput.text.toString())
            } else {
                Toast.makeText(applicationContext, "No Output To Share", Toast.LENGTH_SHORT).show()
            }
        }

        binding.copyLayout.setOnClickListener {
            if (binding.tvOutput.text.toString() != "Output") {
                globalFunction!!.copyOutput(binding.tvOutput.text.toString())
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

    fun removeSpace(s: String): String {
        var withoutspaces = ""
        for (i in s.indices) {
            if (s[i] != ' ') withoutspaces += s[i]
        }
        return withoutspaces
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