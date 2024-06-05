package com.stylish.fancy.text.generator.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stylish.fancy.text.generator.activities.CloneTextActivity
import com.stylish.fancy.text.generator.activities.CountTextActivity
import com.stylish.fancy.text.generator.activities.FavouritesActivity
import com.stylish.fancy.text.generator.activities.MarkTextActivity
import com.stylish.fancy.text.generator.activities.ReplaceTextActivity
import com.stylish.fancy.text.generator.activities.SearchTextActivity
import com.stylish.fancy.text.generator.activities.SelectEmoticonsActivity
import com.stylish.fancy.text.generator.activities.StylishTextActivity
import com.stylish.fancy.text.generator.activities.TextToEmojiActivity
import com.stylish.fancy.text.generator.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        binding.cvReplace.setOnClickListener {
            startActivity(Intent(context, ReplaceTextActivity::class.java))
        }
        binding.cvSearch.setOnClickListener {
            startActivity(Intent(context, SearchTextActivity::class.java))
        }
        binding.cvCount.setOnClickListener {
            startActivity(Intent(context, CountTextActivity::class.java))
        }
        binding.cvClone.setOnClickListener {
            startActivity(Intent(context, CloneTextActivity::class.java))
        }
        binding.cvStylishText.setOnClickListener {
            startActivity(Intent(context, StylishTextActivity::class.java))
        }
        binding.cvMark.setOnClickListener {
            startActivity(Intent(context, MarkTextActivity::class.java))
        }
        binding.cvEmoticons.setOnClickListener {
            startActivity(Intent(context, SelectEmoticonsActivity::class.java))
        }
        binding.cvTextToEmoji.setOnClickListener {
            startActivity(Intent(context, TextToEmojiActivity::class.java))
        }
        binding.cvFavourites.setOnClickListener {
            startActivity(Intent(context, FavouritesActivity::class.java))
        }
        return binding.root
    }
}