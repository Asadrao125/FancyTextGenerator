package com.stylish.fancy.text.generator.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.stylish.fancy.text.generator.R
import com.stylish.fancy.text.generator.adapter.SelectEmoticonsAdapter
import com.stylish.fancy.text.generator.databinding.ActivitySelectEmoticonsBinding
import com.stylish.fancy.text.generator.utils.StaticMethods.emoticons

class SelectEmoticonsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectEmoticonsBinding

    private lateinit var icBackOrMenu: ImageView
    private lateinit var emoticonsAdapter: SelectEmoticonsAdapter
    private lateinit var toolbarTitle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_emoticons)

        emoticonsAdapter = SelectEmoticonsAdapter(this)

        binding.rvEmoticons.layoutManager = GridLayoutManager(this, 2)
        binding.rvEmoticons.setHasFixedSize(true)
        binding.rvEmoticons.adapter = emoticonsAdapter
        emoticonsAdapter.setData(emoticons)

        icBackOrMenu = findViewById(R.id.icBackOrMenu)
        toolbarTitle = findViewById(R.id.toolbarTitle)
        toolbarTitle.text = "Emoticons"

        icBackOrMenu.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }
}