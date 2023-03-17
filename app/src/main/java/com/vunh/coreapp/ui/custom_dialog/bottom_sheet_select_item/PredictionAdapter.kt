package com.vunh.coreapp.ui.custom_dialog.bottom_sheet_select_item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.vunh.coreapp.R
import com.vunh.coreapp.common.Utils


class PredictionAdapter (listener: Listener, predictionSelected: Prediction? = null) : RecyclerView.Adapter<PredictionAdapter.ViewHolder>() {

    var predictionList: MutableList<Prediction> = arrayListOf()
    var mListener = listener
    var predictionSelected = predictionSelected

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PredictionAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_prediction, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return predictionList.size
    }

    override fun onBindViewHolder(holder: PredictionAdapter.ViewHolder, position: Int) {
      holder.tvText.text = predictionList.get(position).name
        if(predictionSelected?.id == predictionList.get(position).id){
            holder.ivSeclected.setColorFilter(ContextCompat.getColor(holder.ivSeclected.context, R.color.colorBlue18), android.graphics.PorterDuff.Mode.SRC_IN)
        }else{
            holder.ivSeclected.setColorFilter(ContextCompat.getColor(holder.ivSeclected.context, R.color.colorGrayEC), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        holder.tvText.setOnClickListener {
            Utils.setAnimationClickItem(it)
            predictionSelected = predictionList.get(position)
            notifyDataSetChanged()
            mListener.onItemPredictionClick(position, predictionList.get(position))
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvText: TextView = itemView.findViewById(R.id.tvText)
        val ivSeclected: ImageView = itemView.findViewById(R.id.ivSelected)
    }

    fun getItem(position: Int): Prediction {
        return predictionList.get(position)
    }

    fun clear() {
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

    fun remove(r: Prediction) {
        val position: Int = predictionList.indexOf(r)
        if (position > -1) {
            predictionList.removeAt(position)
            notifyItemRemoved(position)
        }
    }


    fun add(r: Prediction) {
        predictionList.add(r)
        notifyItemInserted(predictionList.size - 1)
    }

    fun addAll(moveResults: List<Prediction>) {
        for (result in moveResults) {
            add(result)
        }
    }

    interface Listener {
        fun onItemPredictionClick(position: Int, prediction: Prediction)
    }
}