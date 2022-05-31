package com.guysapp.ats.ui.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guysapp.ats.data.Repository
import com.guysapp.ats.data.models.Account
import com.guysapp.ats.data.models.Transactions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionViewModel(repository: Repository?) : ViewModel() {

    var repository : Repository? = null
    var response = MutableLiveData<ArrayList<Transactions>>()
    var isProgress = MutableLiveData<Boolean>()

    init {
        this.repository = repository
    }


    fun getTransactionDetails(accountId : String?): LiveData<ArrayList<Transactions>> {
        isProgress.value = true
        viewModelScope.launch {
            val result = repository?.getTransactions(accountId)
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