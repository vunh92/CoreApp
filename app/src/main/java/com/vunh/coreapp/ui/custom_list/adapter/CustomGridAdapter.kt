package com.vunh.coreapp.ui.custom_list.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.vunh.coreapp.R
import com.vunh.coreapp.ui.custom_list.models.CustomGridViewModel

class CustomGridAdapter(private val context: Context, private val listData: MutableList<CustomGridViewModel>) : BaseAdapter() {
    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return listData.size
    }

    override fun getItem(p0: Int): Any {
       return p0
    }

    override fun getItemId(p0: Int): Long {
       return p0.toLong()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val convertView: View
        val viewHolder: ViewHolder
        if (view == null) {
            convertView = layoutInflater.inflate(R.layout.item_custom_gridview, parent, false)
            viewHolder = ViewHolder(convertView)
            convertView.tag = viewHolder
        }else {
            convertView = view
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder.titleImg?.background = when(position) {
            0 -> context.resources.getDrawable(android.R.drawable.ic_delete)
            1 -> context.resources.getDrawable(android.R.drawable.ic_btn_speak_now)
            2 -> context.resources.getDrawable(android.R.drawable.ic_dialog_email)
            else -> context.resources.getDrawable(android.R.drawable.ic_lock_idle_alarm)
        }
        viewHolder.titleTv?.text = listData[position].title
        viewHolder.descTv?.text = listData[position].desc
        return convertView
    }

    private class ViewHolder(view: View?) {
        var titleImg = view?.findViewById<ImageView>(R.id.item_custom_list_title_img)
        var titleTv = view?.findViewById<TextView>(R.id.item_custom_list_title_tv)
        var descTv = view?.findViewById<TextView>(R.id.item_custom_list_desc_tv)
    }
}