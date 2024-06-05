package com.stylish.fancy.text.generator.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.stylish.fancy.text.generator.R
import com.stylish.fancy.text.generator.adapter.FavouritesAdapter
import com.stylish.fancy.text.generator.databinding.ActivityFavouritesBinding
import com.stylish.fancy.text.generator.utils.MySharedPreferences

class FavouritesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavouritesBinding

    lateinit var icBackOrMenu: ImageView
    lateinit var toolbarTitle: TextView
    private var list: ArrayList<String> = ArrayList()
    private var sharedPref: MySharedPreferences? = null
    private lateinit var adapter: FavouritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favourites)

        icBackOrMenu = findViewById(R.id.icBackOrMenu)
        toolbarTitle = findViewById(R.id.toolbarTitle)
        toolbarTitle.text = "Favourites"
        sharedPref = MySharedPreferences(this)
        list = ArrayList()

        binding.recyclerViewEmoticons.layoutManager = LinearLayoutManager(this)
        adapter = FavouritesAdapter(this)
        binding.recyclerViewEmoticons.adapter = adapter
        icBackOrMenu.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        list = ArrayList(sharedPref!!.list)
        adapter.setData(list)
    }
}