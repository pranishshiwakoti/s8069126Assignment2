package com.pranish.s8069126assignment2.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pranish.s8069126assignment2.network.dto.EntityDto
import com.pranish.s8069126assignment2.databinding.ItemEntityBinding

class EntityAdapter(private val onClick: (EntityDto) -> Unit) :
    ListAdapter<EntityDto, EntityAdapter.VH>(DIFF) {
    companion object {
        val DIFF = object : DiffUtil.ItemCallback<EntityDto>() {
            override fun areItemsTheSame(oldItem: EntityDto, newItem: EntityDto) = oldItem === newItem
            override fun areContentsTheSame(oldItem: EntityDto, newItem: EntityDto) = oldItem == newItem
        }
    }

    inner class VH(val b: ItemEntityBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val b = ItemEntityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(b)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.b.tvProp1.text = item.title()
        holder.b.tvProp2.text = item.subtitle()
        holder.itemView.setOnClickListener { onClick(item) }
    }
}
