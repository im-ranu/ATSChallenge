package com.guysapp.ats.ui.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guysapp.ats.data.Repository
import com.guysapp.ats.ui.accounts.AccountsViewModel

class TransactionViewModelFactory(var repository: Repository?) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransactionViewModel(repository) as T
    }
}