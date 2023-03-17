package com.vunh.coreapp.ui.login

import androidx.lifecycle.MutableLiveData
import com.vunh.coreapp.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {
    val isLoading = MutableLiveData(false)
    val isSuccess = MutableLiveData("")
    val isError = MutableLiveData(false)

    fun login(username: String?, password: String?) {
        isLoading.value = true
        if(username?.isNotBlank() == true && username == "vunh"
            && password?.isNotBlank() == true && password == "vunh") {
            isSuccess.value = username + password
            isLoading.value = false
        }
    }
}