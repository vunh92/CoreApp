package com.vunh.coreapp.ui.custom_dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.vunh.coreapp.R
import com.vunh.coreapp.common.Utils.Companion.toCalendar
import com.vunh.coreapp.common.Utils.Companion.toDataUI
import com.vunh.coreapp.ui.custom_dialog.calendar.VSACalendarPicker
import java.util.*

class CalendarDialog: DialogFragment(){
    companion object {
        val TAG = CalendarDialog::class.java.name.toString()

        const val NOT_SELECT = -1
        const val FROM_SELECT = 1
        const val TO_SELECT = 2

        const val ARG_FROM_DATE = "ArgFromDate"
        const val ARG_TO_DATE = "ArgToDate"

        fun newInstance() = CalendarDialog()

        fun newInstance(fromDate: String) = CalendarDialog().apply {
            arguments = Bundle().apply {
                putString(ARG_FROM_DATE, fromDate)
            }
        }
    }

    var stateSelect: Int = NOT_SELECT

    var fromDate: String = ""
    var toDate: String = ""
    lateinit var datePicker: VSACalendarPicker

    var onDateSelectListener: ((Date) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            when {
                it.containsKey(ARG_FROM_DATE) -> {
                    fromDate = it.getString(ARG_FROM_DATE) ?: ""
                }
            }
        }
        arguments?.clear()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.dialog_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    override fun onResume() {
        super.onResume()
        setData()
    }

    private fun setData() {
        val from = (fromDate.toDataUI() ?: Calendar.getInstance().time).toCalendar()
        datePicker.init(
            from.get(Calendar.YEAR),
            from.get(Calendar.MONTH) + 1,
            from.get(Calendar.DAY_OF_MONTH),
            DatePicker.OnDateChangedListener { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month - 1, dayOfMonth)
            }
        )
    }

    private fun initView(view: View) {
        datePicker = view.findViewById(R.id.datePicker)
        view.findViewById<TextView>(R.id.tvConfirm).setOnClickListener {
            val date = Calendar.getInstance()
            date.set(datePicker.year, datePicker.month - 1, datePicker.dayOfMonth)

            onDateSelectListener?.invoke(date.time)
        }

    }

}