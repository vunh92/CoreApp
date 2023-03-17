package com.vunh.coreapp.ui.login

import android.os.Bundle
import androidx.activity.viewModels
import com.vunh.coreapp.BaseActivity
import com.vunh.coreapp.R
import com.vunh.coreapp.common.AppSharePresfs
import com.vunh.coreapp.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private val TAG = LoginActivity::class.java.name
    private lateinit var activityLoginBinding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(activityLoginBinding.root)
        initView()

        viewModel.isLoading.observe(this) {
            activityLoginBinding.loading.isVisible = it
        }

        viewModel.isSuccess.observe(this) {
            AppSharePresfs(this).newInstance().setToken(it)
            gotoHome()
        }

        viewModel.isError.observe(this) {
            showPopup(getString(R.string.common_error), it.toString())
        }
    }

    private fun initView() {
        activityLoginBinding.tvLogin.setOnClickListener {
            val username = activityLoginBinding.edtUserName.text.toString().trim()
            val password = activityLoginBinding.edtPassword.text.toString().trim()
            when {
                username.isEmpty() -> {
                    showMessage(getString(R.string.signup_username_empty))
                    return@setOnClickListener
                }
                password.isEmpty() -> {
                    showMessage(getString(R.string.signup_password_empty))
                    return@setOnClickListener
                }
                else -> {
                    viewModel.login(
                        username = username,
                        password = password,
                    )
                }
            }
        }

    }
}