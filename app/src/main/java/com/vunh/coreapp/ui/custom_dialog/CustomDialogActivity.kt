package com.vunh.coreapp.ui.custom_dialog

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vunh.coreapp.BaseActivity
import com.vunh.coreapp.R
import com.vunh.coreapp.databinding.ActivityCustomDialogBinding
import com.vunh.coreapp.ui.custom_dialog.bottom_sheet_select_item.Prediction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomDialogActivity : BaseActivity() {
    private lateinit var binding: ActivityCustomDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.baseMaterialAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("title")
                .setMessage("message")
                .setPositiveButton(getString(R.string.common_ok)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setNegativeButton(getString(R.string.common_cancel)) {dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }
        binding.baseAlertDialog.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("title")
                .setMessage("message")
                .setPositiveButton(getString(R.string.common_ok)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }
        binding.loadingDialog.setOnClickListener {
            val dialog = LoadingDialog()
            dialog.show(supportFragmentManager, LoadingDialog.TAG)
        }
        binding.dialogFragment1.setOnClickListener {
            val dialog = DialogFragment1()
            dialog.show(supportFragmentManager, DialogFragment1.TAG)
        }
        binding.dialogFragment2.setOnClickListener {
            val dialog = DialogFragment2(object : DialogFragment2.Listener {
                override fun onOK(result: Boolean) {
                    Toast.makeText(this@CustomDialogActivity, result.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onCancel() {
                    Toast.makeText(this@CustomDialogActivity, "Cancel", Toast.LENGTH_SHORT).show()
                }

            })
            dialog.show(supportFragmentManager, DialogFragment2.TAG)
        }
        binding.dialogFragment3.setOnClickListener {
            val dialog = DialogFragment3(arrayListOf("item 1", "item 2")) {
                Toast.makeText(this@CustomDialogActivity, it, Toast.LENGTH_SHORT).show()
            }
            dialog.show(supportFragmentManager, DialogFragment3.TAG)
        }
        binding.calendar.setOnClickListener {
            val dialog = CalendarDialog()
            dialog.show(supportFragmentManager, CalendarDialog.TAG)
        }
        binding.datePicker.setOnClickListener {
            DatePickerFragment().showDialog(this) { _,year,monthOfYear,dayOfMonth ->
                Toast.makeText(this, "$dayOfMonth/$monthOfYear/$year", Toast.LENGTH_SHORT).show()
            }
        }
        binding.bottomSheetSelectItem.setOnClickListener {
            val dialog = BottomSheetSelectItem(
                isShowClose = true,
                title = BottomSheetSelectItem.TAG,
                predictionSelected = Prediction(id = 2, name = "Name 2"),
                items = arrayListOf(Prediction(id = 1, name = "Name 1"),Prediction(id = 2, name = "Name 2"),Prediction(id = 3, name = "Name 3"))
            ) { tag, position, prediction ->
                Toast.makeText(
                    this@CustomDialogActivity,
                    "$tag - $position - ${prediction.toString()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            dialog.show(supportFragmentManager, BottomSheetSelectItem.TAG)
        }
    }
}