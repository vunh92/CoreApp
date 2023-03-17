package com.vunh.coreapp.ui.custom_dialog

import android.app.DatePickerDialog
import android.content.Context
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment() {

    companion object {
        val TAG = DatePickerFragment::class.java.toString()
    }

    fun showDialog(context: Context, listener: DatePickerDialog.OnDateSetListener) {
        val current = Calendar.getInstance()
        val year: Int = current.get(Calendar.YEAR)
        val month: Int = current.get(Calendar.MONTH)
        val day: Int = current.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(context, listener, year, month, day)
        datePickerDialog.show()
    }
}