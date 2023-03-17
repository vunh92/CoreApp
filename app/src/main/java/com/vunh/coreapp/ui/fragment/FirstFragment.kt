package com.vunh.coreapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vunh.coreapp.databinding.FragmentFirstBinding

class FirstFragment(
    private val listener: (String) -> Unit
) : Fragment() , CustomFragmentActivity.OnSendDataFirst {
    private lateinit var binding: FragmentFirstBinding

    private lateinit var sendTextListener: SendTextListener

    fun setSendTextListener(sendTextListener: SendTextListener) {
        this.sendTextListener = sendTextListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        (activity as CustomFragmentActivity).setListenerSendDataFirst(this)
        initData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btn.setOnClickListener {
            val text = binding.edt.text.toString()
            listener.invoke(text)
        }
        binding.sendBtn.setOnClickListener {
            val text = binding.edt.text.toString()
            sendTextListener.sendText(text)
        }
    }

    private fun initData() {
        arguments?.let {
            val text = it.getString("text")
            setUpView(text)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpView(text: String?) {
        text?.let {
            binding.tv.text = it
        }
    }

    override fun onData(data: String) {
        binding.tv.text = data
    }

}