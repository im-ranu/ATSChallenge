package com.guysapp.ats.ui.accounts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.guysapp.ats.data.Repository
import com.guysapp.ats.data.models.Account
import com.guysapp.ats.databinding.FragmentAccountsBinding
import com.guysapp.ats.extensions.Constants
import com.guysapp.ats.extensions.openActivity
import com.guysapp.ats.ui.accounts.adapter.AccountAdapter
import com.guysapp.ats.ui.transactions.TransactionActivity

class AccountsFragment : Fragment() {

    var mBinding : FragmentAccountsBinding? = null
    var repository : Repository? = null
    var adapter : AccountAdapter? = null

    var accountList = ArrayList<Account>()

    val accountsViewModel : AccountsViewModel by viewModels() {AccountsViewModelFactory(repository = repository)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentAccountsBinding.inflate(inflater,container,false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getData()
    }

    private fun init() {
        repository = Repository()

        mBinding?.rvAccountMain?.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        adapter = AccountAdapter(accountList){
            requireContext().openActivity<TransactionActivity>(Constants.INTENT_ACCOUNT_ITEM to it)
        }
        mBinding?.rvAccountMain?.adapter = adapter
    }

    private fun getData() {
        accountsViewModel.getAccounts().observe(requireActivity()) {

            if(accountList.size==0){
                accountList.addAll(it)
            }else{
                accountList.clear()
                accountList.addAll(it)
            }
            adapter?.notifyDataSetChanged()
            mBinding?.pbAccounts?.visibility = View.GONE
            mBinding?.rvAccountMain?.visibility = View.VISIBLE
        }
    }
}