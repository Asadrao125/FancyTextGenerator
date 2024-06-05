package com.stylish.fancy.text.generator.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.stylish.fancy.text.generator.R
import com.stylish.fancy.text.generator.databinding.ItemEmoticonsAdapterBinding
import com.stylish.fancy.text.generator.utils.CopyHandler
import com.stylish.fancy.text.generator.utils.MySharedPreferences

class EmoticonsAdapter(var activity: Context) :
    RecyclerView.Adapter<EmoticonsAdapter.MyViewHolder>() {
    private var dataList = ArrayList<String>()
    private var pref = MySharedPreferences(activity)
    private var list: MutableList<String>
    private var copyHandler: CopyHandler

    init {
        list = ArrayList()
        copyHandler = CopyHandler(activity)
    }

    fun setData(list: ArrayList<String>?) {
        dataList.clear()
        dataList.addAll((list)!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemEmoticonsAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            binding.tvEmoticon.text = dataList[position]
            binding.tvCount.text = "" + (position + 1)

            binding.imgCopy.setOnClickListener {
                copyHandler.copyText(
                    dataList[position]
                )
            }

            binding.imgShare.setOnClickListener { copyHandler.shareText(dataList[position]) }

            list = ArrayList(pref.list)
            if (list.contains(dataList[position])) {
                binding.imgFavourite.setImageResource(R.drawable.ic_favourite_filled)
            } else binding.imgFavourite.setImageResource(R.drawable.ic_favourite)

            binding.imgFavourite.setOnClickListener {
                list = ArrayList(pref.list)
                if (list.contains(dataList[position])) {
                    list.remove(dataList[position])
                    pref.saveList(list)
                    Toast.makeText(activity, "Removed from favourites", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    list.add(dataList[position])
                    pref.saveList(list)
                    Toast.makeText(activity, "Added to favourites", Toast.LENGTH_SHORT).show()
                }
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MyViewHolder(val binding: ItemEmoticonsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)
}