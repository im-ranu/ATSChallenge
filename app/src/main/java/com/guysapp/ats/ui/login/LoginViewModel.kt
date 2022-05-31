package com.guysapp.ats.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guysapp.ats.data.Repository
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.HttpException
import kotlin.math.log

class LoginViewModel(repository: Repository?) : ViewModel() {

    var repository : Repository? = null
    var response = MutableLiveData<Boolean>()
    var IsProgressBarVisible = MutableLiveData<Boolean>()

    init {
        this.repository = repository
    }

    var exception = CoroutineExceptionHandler { coroutineContext, throwable ->
        if (throwable is HttpException){
            response.value = false
        }
    }

    fun doLogin(username : String,password : String) {
        IsProgressBarVisible.value = true
        viewModelScope.launch(exception) {
            val result = repository?.doLogin(username, password)
            delay(2000)
            withContext(Dispatchers.Main){
                Log.e("TAG", "doLogin: ${result?.value}", )
                IsProgressBarVisible.value = false
                response.value = result?.value == true
            }
        }
    }

}