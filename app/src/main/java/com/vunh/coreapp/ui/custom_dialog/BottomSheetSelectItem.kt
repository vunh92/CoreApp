package com.vunh.coreapp.ui.custom_dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vunh.coreapp.R
import com.vunh.coreapp.ui.custom_dialog.bottom_sheet_select_item.Prediction
import com.vunh.coreapp.ui.custom_dialog.bottom_sheet_select_item.PredictionAdapter


@SuppressLint("ValidFragment")
class BottomSheetSelectItem(
    private var isShowClose: Boolean = false,
    var title: String,
    private var predictionSelected: Prediction? = null,
    var items: List<Prediction>,
    private val clickListener: (String?, Int?, Prediction?) -> Unit
) : BottomSheetDialogFragment(), PredictionAdapter.Listener {
    companion object {
        val TAG = BottomSheetSelectItem::class.java.name.toString()
    }
    var position: Int? = null
    lateinit var predictionAdapter: PredictionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View =
            inflater.inflate(R.layout.layout_bottom_layout_selection, container, false)

        rootView.findViewById<TextView>(R.id.tvTitle).text = title
        rootView.findViewById<TextView>(R.id.tvClose).visibility = if (isShowClose == true) View.VISIBLE else View.GONE
        rootView.findViewById<TextView>(R.id.tvClose).setOnClickListener {
            dialog?.let { it1 -> onDismiss(it1) }
        }

        predictionAdapter = PredictionAdapter(this, predictionSelected)
        rootView.findViewById<RecyclerView>(R.id.rcvItem).layoutManager = LinearLayoutManager(context)
        rootView.findViewById<RecyclerView>(R.id.rcvItem).adapter = predictionAdapter
        predictionAdapter.addAll(items)

        return rootView
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener { dialog: DialogInterface ->
            val dialogc = dialog as BottomSheetDialog
            val bottomSheet: FrameLayout =
                dialogc.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            val bottomSheetBehavior: BottomSheetBehavior<*> =
                BottomSheetBehavior.from<View?>(bottomSheet)
            bottomSheetBehavior.peekHeight = Resources.getSystem().displayMetrics.heightPixels
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
        }
        return bottomSheetDialog
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        clickListener.invoke(tag, position, predictionSelected )
    }

    override fun onItemPredictionClick(position: Int, prediction: Prediction) {
        predictionSelected = prediction
        this.position = position
        this.dismiss()
    }
}
