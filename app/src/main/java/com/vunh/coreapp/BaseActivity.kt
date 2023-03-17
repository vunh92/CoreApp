package com.vunh.coreapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.vunh.coreapp.common.*
import com.vunh.coreapp.ui.CustomToast
import com.vunh.coreapp.ui.home.HomeActivity
import com.vunh.coreapp.ui.login.LoginActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun startBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    fun updateLanguage(locale: String) {
        AppSharePresfs(this).newInstance().setString(CURRENT_LANGUAGE, locale)
        Utils.restartApp(this)
    }

    fun getToken(): String? {
        return AppSharePresfs(this).newInstance().getToken()
    }

    fun gotoLogin(userName: String? = null) {
        val intent = Intent(this, LoginActivity::class.java)
        if (!userName.isNullOrEmpty()) {
            intent.putExtra(INTENT_KEY.EXTRA_EMAIL, userName)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    fun gotoHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    fun showMessage(message: String) {
        when {
            message.contains(HTTP_CODE.AUTHENTICATION_ERROR) -> {
                return
            }
            message.contains(FIREBASE.USER_INVALID) -> {
                showPopup(
                    getString(R.string.common_error),
                    getString(R.string.sign_in_error_user_invalid)
                )
            }
            message.contains(FIREBASE.PASSWORD_INVALID) -> {
                showPopup(
                    getString(R.string.common_error),
                    getString(R.string.sign_in_error_password_invalid)
                )
            }
            message.contains(FIREBASE.COMMON_ERROR) ||
                    message.contains(HTTP_CODE.FORBIDDEN_ERROR) ||
                    message.contains(HTTP_CODE.NOT_FOUND_ERROR) ||
                    message.contains(HTTP_CODE.SERVER_ERROR) -> {
                showPopup(
                    getString(R.string.common_error),
                    getString(R.string.common_error_message_general)
                )
            }
            else -> {
                val customToast = CustomToast()
                    .getInstance(this, message)
                customToast.show()
            }
        }
    }

    fun showPopup(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(getString(R.string.common_ok)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }
}