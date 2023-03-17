package com.vunh.coreapp.ui.custom_list.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.vunh.coreapp.R
import com.vunh.coreapp.ui.custom_list.models.CustomListModel


class CustomListViewAdapter(private val context: Context, private val listData: List<CustomListModel>) : BaseAdapter() {
    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return listData.size
    }

    override fun getItem(p0: Int): Any {
        return listData[p0]
    }

    override fun getItemId(p0: Int): Long {
     return p0.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View? {
        val convertView: View?
        val viewHolder: ViewHolder
        if (view == null) {
            convertView = layoutInflater.inflate(R.layout.item_custom_list, parent, false)
            viewHolder = ViewHolder(convertView)
            convertView.tag = viewHolder
        }else {
            convertView = view
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder.titleTv?.text = listData[position].title
        viewHolder.descTv?.text = listData[position].desc
        return convertView
    }

    private class ViewHolder(view: View?) {
        var titleTv = view?.findViewById<TextView>(R.id.item_custom_list_title_tv)
        var descTv = view?.findViewById<TextView>(R.id.item_custom_list_desc_tv)
    }
}