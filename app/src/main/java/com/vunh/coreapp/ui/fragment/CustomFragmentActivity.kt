package com.vunh.coreapp.ui.fragment

import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.commit
import com.vunh.coreapp.BaseActivity
import com.vunh.coreapp.R
import com.vunh.coreapp.databinding.ActivityCustomFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomFragmentActivity : BaseActivity() , SendTextListener{
    private lateinit var binding: ActivityCustomFragmentBinding
    private lateinit var firstFragment: FirstFragment
    private lateinit var secondFragment: SecondFragment

    interface OnSendDataFirst {
        fun onData(data: String)
    }

    private lateinit var onSendDataFirst: OnSendDataFirst

    fun setListenerSendDataFirst(listener: OnSendDataFirst) {
        this.onSendDataFirst = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        initView()
    }

    private fun initView() {
        /* use viewModel very easy */
        initFragment1()
        initFragment2()
        binding.firstBtn.setOnClickListener {
            val text = binding.edt.text.toString()
            this.onSendDataFirst.onData(text)
        }
        binding.secondBtn.setOnClickListener {
            val text = binding.edt.text.toString()
            secondFragment.setDataTextView(text)
        }
    }

    private fun initFragment1() {
        firstFragment = FirstFragment{
            setDataTextView(it)
        }.apply {
            arguments = Bundle().apply {
                putString("text", "t1")
            }
        }
        firstFragment.setSendTextListener(this)
        supportFragmentManager.commit {
            replace(
                R.id.fragment_1,
                firstFragment
            )
        }
    }

    private fun initFragment2() {
        secondFragment = SecondFragment.newInstance("t2")
        secondFragment.setSendTextListener(this)
        supportFragmentManager.commit {
            replace(
                R.id.fragment_2,
                secondFragment
            )
        }
    }

    fun setDataTextView(data: String) {
        binding.tv.text = data
    }

    override fun sendText(text: String) {
        secondFragment.setDataTextView(text)
    }

    override fun sendText2(text: String) {
        this.onSendDataFirst.onData(text)
    }

}