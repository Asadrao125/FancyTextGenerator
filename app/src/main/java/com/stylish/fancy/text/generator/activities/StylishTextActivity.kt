package com.stylish.fancy.text.generator.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.stylish.fancy.text.generator.R
import com.stylish.fancy.text.generator.adapter.StylishTextAdapter
import com.stylish.fancy.text.generator.databinding.ActivityStylishTextBinding
import com.stylish.fancy.text.generator.font_utils.FontsGenerator
import java.util.Locale

class StylishTextActivity : AppCompatActivity(), TextWatcher {
    private lateinit var binding: ActivityStylishTextBinding

    private lateinit var editText: EditText
    private lateinit var mAdapter: StylishTextAdapter
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var mGenerator: FontsGenerator
    private lateinit var icBackOrMenu: ImageView
    private lateinit var toolbarTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stylish_text)

        icBackOrMenu = findViewById(R.id.icBackOrMenu)
        toolbarTitle = findViewById(R.id.toolbarTitle)
        toolbarTitle.text = getString(R.string.stylish_text)
        icBackOrMenu.setOnClickListener { onBackPressed() }
        mGenerator = FontsGenerator(this)

        binding.micBtn.setOnClickListener { startForResult() }
        binding.recycleViewFF.layoutManager = LinearLayoutManager(this)
        editText = findViewById(R.id.edit_text_FF)

        binding.recycleViewFF.setHasFixedSize(true)
        mAdapter = StylishTextAdapter(this)
        binding.recycleViewFF.adapter = mAdapter
        editText.addTextChangedListener(this)
        restoreText()

        binding.closebtn.setOnClickListener {
            val length = editText.text.length
            if (length > 0) {
                editText.text.delete(length - 1, length)
            }
        }

        binding.closebtn.setOnLongClickListener {
            editText.text.clear()
            false
        }
    }

    private fun startForResult() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi Speak Something")
        activityResultLauncher.launch(intent)
    }

    private val activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            if (data != null) {
                val results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                editText.setText(results!![0])
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null && requestCode == 123) {
            val results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            editText.setText(results!![0])
        }
    }

    private fun convertText(inp: String) {
        var inp = inp
        if (inp.isEmpty()) inp = "Fancy Text"
        val translate: ArrayList<String?> = mGenerator.generate(inp)
        mAdapter.setData(translate)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        convertText(editText.text.toString())
    }

    override fun afterTextChanged(s: Editable) {}
    override fun onDestroy() {
        sharedPreferences = getSharedPreferences("MyPre", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply()
        editor.putString(KEY + 1, editText.text.toString()).apply()
        super.onDestroy()
    }

    private fun restoreText() {
        sharedPreferences = getSharedPreferences("MyPre", MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply()
        editText.setText(sharedPreferences.getString(KEY + 1, ""))
    }

    companion object {
        const val KEY = "FontActivity"
    }
}