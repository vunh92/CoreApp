package com.vunh.coreapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vunh.coreapp.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding

    companion object {
        fun newInstance(text: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString("text", text)
                }
            }
    }

    private lateinit var sendTextListener: SendTextListener

    fun setSendTextListener(sendTextListener: SendTextListener) {
        this.sendTextListener = sendTextListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
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
            (activity as CustomFragmentActivity).setDataTextView(text)
        }
        binding.sendBtn.setOnClickListener {
            val text = binding.edt.text.toString()
            sendTextListener.sendText2(text)
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

    fun setDataTextView(data: String) {
        binding.tv.text = data
    }
}