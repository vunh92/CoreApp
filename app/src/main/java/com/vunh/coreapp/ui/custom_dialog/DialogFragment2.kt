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

class DialogFragment2(val mListener: Listener) : DialogFragment() {
    companion object {
        val TAG = DialogFragment2::class.java.name.toString()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.dialog_fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.fm2_ok_btn).setOnClickListener {
            mListener.onOK(true)
            dismiss()
        }

        view.findViewById<Button>(R.id.fm2_cancel_btn).setOnClickListener {
            mListener.onCancel()
            dismiss()
        }
    }

    interface Listener {
        fun onOK(result: Boolean)
        fun onCancel()
    }
}