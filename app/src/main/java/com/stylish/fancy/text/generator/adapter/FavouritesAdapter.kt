package com.stylish.fancy.text.generator.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.stylish.fancy.text.generator.databinding.ItemFavouritesAdapterBinding
import com.stylish.fancy.text.generator.utils.CopyHandler
import com.stylish.fancy.text.generator.utils.MySharedPreferences

class FavouritesAdapter(var activity: Context) :
    RecyclerView.Adapter<FavouritesAdapter.MyViewHolder>() {
    private var dataList = ArrayList<String>()
    private var pref = MySharedPreferences(activity)
    private var list: MutableList<String> = ArrayList()

    fun setData(list: ArrayList<String>?) {
        dataList.clear()
        dataList.addAll((list)!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemFavouritesAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        with(holder) {
            binding.tvEmoticon.text = dataList[position]
            binding.tvCount.text = (position + 1).toString()
            val copyHandler = CopyHandler(activity)
            binding.imgCopy.setOnClickListener {
                copyHandler.copyText(
                    dataList[position]
                )
            }

            binding.imgShare.setOnClickListener { copyHandler.shareText(dataList[position]) }

            binding.imgUnFavourite.setOnClickListener {
                list = pref.list.toMutableList()
                list.remove(dataList[position])
                dataList.removeAt(position)
                pref.saveList(list)
                Toast.makeText(activity, "Removed from favourites", Toast.LENGTH_SHORT).show()
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MyViewHolder(val binding: ItemFavouritesAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)
}