package com.guysapp.ats.ui.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.guysapp.ats.data.Repository

class AccountsViewModelFactory(var repository: Repository?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AccountsViewModel(repository) as T
    }
}