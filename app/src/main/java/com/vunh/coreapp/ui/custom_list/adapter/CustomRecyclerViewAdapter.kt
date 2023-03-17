package com.vunh.coreapp.ui.custom_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vunh.coreapp.R
import com.vunh.coreapp.ui.custom_list.models.CustomListModel

class CustomRecyclerViewAdapter(private val listener: (CustomListModel) -> Unit) :
    ListAdapter<CustomListModel, CustomRecyclerViewAdapter.ViewHolder>(MenuCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_custom_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val animation: Animation = AlphaAnimation(0.3f, 1.0f)
            animation.duration = 1000
            it.startAnimation(animation)
            listener.invoke(getItem(position))
        }
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv: TextView = itemView.findViewById(R.id.item_custom_list_title_tv)
        val descTv: TextView = itemView.findViewById(R.id.item_custom_list_desc_tv)

        fun bind(item: CustomListModel) = with(itemView) {
            titleTv.text = item.title
            descTv.text = item.desc
        }
    }

    class MenuCallback : DiffUtil.ItemCallback<CustomListModel>() {
        override fun areItemsTheSame(oldItem: CustomListModel, newItem: CustomListModel): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: CustomListModel, newItem: CustomListModel): Boolean {
            return oldItem == newItem
        }

    }
}