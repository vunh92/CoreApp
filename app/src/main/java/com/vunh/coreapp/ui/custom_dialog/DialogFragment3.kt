package com.vunh.coreapp.ui.custom_dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.vunh.coreapp.R

class DialogFragment3(
    private val list: MutableList<String>,
    private val listener: (String) -> Unit
) : DialogFragment() {
    companion object {
        val TAG = DialogFragment3::class.java.toString()
    }

    private var listInDialog: MutableList<String> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listInDialog.addAll(list)
        listInDialog.add("item ${list.size}")
        return inflater.inflate(R.layout.dialog_fragment_3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.fm3_ok_btn).setOnClickListener {
            listener.invoke("OK")
            dismiss()
        }
        view.findViewById<Button>(R.id.fm3_cancel_btn).setOnClickListener {
            listener.invoke("Cancel")
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}