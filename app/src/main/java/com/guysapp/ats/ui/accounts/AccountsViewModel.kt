package com.guysapp.ats.ui.accounts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guysapp.ats.data.Repository
import com.guysapp.ats.data.models.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AccountsViewModel(repository: Repository?) : ViewModel() {

    var response = MutableLiveData<ArrayList<Account>>()
    var isProgress = MutableLiveData<Boolean>()
    var repository : Repository? = null
    init {
        this.repository = repository
    }

    fun getAccounts() : LiveData<ArrayList<Account>>{
        isProgress.value = true
        viewModelScope.launch {
            var result = repository?.getAccounts()
            delay(2000)
            withContext(Dispatchers.Main){
                isProgress.value = false
                if (repository?.isResultSuccess?.value!!){
                    if (result?.value?.size!!>0){
                        response.value = result.value
                    }
                }
            }
        }
        return response
    }
}