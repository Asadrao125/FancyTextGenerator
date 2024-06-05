package com.stylish.fancy.text.generator.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.stylish.fancy.text.generator.adapter.StylishTextAdapter
import com.stylish.fancy.text.generator.databinding.FragmentStylishBinding
import com.stylish.fancy.text.generator.font_utils.FontsGenerator
import java.util.Locale

class StylishFragment : Fragment(), TextWatcher {
    private lateinit var binding: FragmentStylishBinding

    private lateinit var mAdapter: StylishTextAdapter
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var mGenerator: FontsGenerator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStylishBinding.inflate(inflater, container, false)

        mGenerator = FontsGenerator(activity)
        binding.recycleViewFF.layoutManager = LinearLayoutManager(activity)
        binding.recycleViewFF.setHasFixedSize(true)
        mAdapter = StylishTextAdapter(requireActivity())
        binding.recycleViewFF.adapter = mAdapter
        binding.editTextFF.addTextChangedListener(this)
        restoreText()

        binding.micBtn.setOnClickListener { startForResult() }
        binding.closebtn.setOnClickListener {
            val length = binding.editTextFF.text.length
            if (length > 0) {
                binding.editTextFF.text.delete(length - 1, length)
            }
        }
        binding.closebtn.setOnLongClickListener {
            binding.editTextFF.text.clear()
            false
        }
        return binding.root
    }

    private fun convertText(inp: String) {
        var inp = inp
        if (inp.isEmpty()) inp = "Fancy Text"
        val translate: ArrayList<String?> = mGenerator.generate(inp)
        mAdapter.setData(translate)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        convertText(binding.editTextFF.text.toString())
    }

    override fun afterTextChanged(s: Editable) {}
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
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            if (data != null) {
                val results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                binding.editTextFF.setText(results!![0])
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sharedPreferences = requireActivity().getSharedPreferences("MyPre", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply()
        editor.putString(KEY + 1, binding.editTextFF.text.toString()).apply()
        super.onDestroy()
    }

    private fun restoreText() {
        sharedPreferences = requireActivity().getSharedPreferences("MyPre", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply()
        binding.editTextFF.setText(sharedPreferences.getString(KEY + 1, ""))
    }

    companion object {
        const val KEY = "FontActivity"
    }
}