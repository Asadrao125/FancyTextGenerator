package com.stylish.fancy.text.generator.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stylish.fancy.text.generator.activities.EmoticonsActivity
import com.stylish.fancy.text.generator.databinding.ItemSelectEmoticonsAdapterBinding
import com.stylish.fancy.text.generator.model.SelectEmoticonsModel

class SelectEmoticonsAdapter(var activity: Context) :
    RecyclerView.Adapter<SelectEmoticonsAdapter.MyViewHolder>() {
    private var dataList = ArrayList<SelectEmoticonsModel>()

    fun setData(list: ArrayList<SelectEmoticonsModel>?) {
        dataList.clear()
        dataList.addAll(list!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemSelectEmoticonsAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        with(holder) {
            binding.textView.text = dataList[position].name
            binding.imageView.setImageResource(dataList[position].image)
            binding.item.setOnClickListener {
                activity.startActivity(
                    Intent(activity, EmoticonsActivity::class.java)
                        .putExtra("image", dataList[position].image)
                        .putExtra("index", position)
                        .putExtra("name", dataList[position].name)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MyViewHolder(val binding: ItemSelectEmoticonsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)
}