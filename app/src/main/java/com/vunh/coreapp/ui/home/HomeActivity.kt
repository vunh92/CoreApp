package com.vunh.coreapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import com.vunh.coreapp.BaseActivity
import com.vunh.coreapp.R
import com.vunh.coreapp.databinding.ActivityHomeBinding
import com.vunh.coreapp.ui.custom_dialog.CustomDialogActivity
import com.vunh.coreapp.ui.custom_list.CustomListActivity
import com.vunh.coreapp.ui.fragment.CustomFragmentActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        initView()
    }

    private fun initView() {
        binding.homeCustomDialogBtn.setOnClickListener {
            val intent = Intent(this, CustomDialogActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        binding.homeCustomListBtn.setOnClickListener {
            val intent = Intent(this, CustomListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        binding.homeFragmentBtn.setOnClickListener {
            val intent = Intent(this, CustomFragmentActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun initViewModel() {}
}