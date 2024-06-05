package com.stylish.fancy.text.generator.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stylish.fancy.text.generator.databinding.ItemStylishAdapterBinding
import com.stylish.fancy.text.generator.utils.CopyHandler

class StylishTextAdapter(var activity: Context) :
    RecyclerView.Adapter<StylishTextAdapter.MyViewHolder>() {
    private var dataList = ArrayList<String?>()

    fun setData(list: ArrayList<String?>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemStylishAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            binding.descriptionTV.text = dataList[position]
            binding.descriptionTV.isSelected = true
            binding.txtNumber.text = (position + 1).toString()
            val copyHandler = CopyHandler(activity)
            val text = binding.descriptionTV.text.toString()

            binding.btnCopy.setOnClickListener { copyHandler.copyText(text) }

            binding.btnShare.setOnClickListener { copyHandler.shareText(text) }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MyViewHolder(val binding: ItemStylishAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)
}