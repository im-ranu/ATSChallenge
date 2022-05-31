package com.guysapp.ats.ui.transactions

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.guysapp.ats.R
import com.guysapp.ats.data.Repository
import com.guysapp.ats.data.models.Account
import com.guysapp.ats.data.models.Transactions
import com.guysapp.ats.databinding.ActivityTransactionsBinding
import com.guysapp.ats.extensions.Constants
import com.guysapp.ats.extensions.openActivity
import com.guysapp.ats.ui.accounts.adapter.AccountAdapter
import com.guysapp.ats.ui.base.BaseActivity
import com.guysapp.ats.ui.transactions.adapter.TransactionAdapter

class TransactionActivity : BaseActivity() {


    var mBinding : ActivityTransactionsBinding? =null
    var accountItem : Account? =null
    var repository : Repository? =null

    var adapter : TransactionAdapter? = null

    var transactionList = ArrayList<Transactions>()

    val transactionViewModel : TransactionViewModel by viewModels(){ TransactionViewModelFactory(repository)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)

        getIntentData()
        init()
        getTransactionsData()

    }

    private fun getIntentData() {
        accountItem = intent?.getParcelableExtra(Constants.INTENT_ACCOUNT_ITEM)
    }

    fun init(){
        mBinding?.transactionTb?.toolbar?.title = "${accountItem?.name}"

        setSupportActionBar(mBinding?.transactionTb?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        repository = Repository()


        mBinding?.rvTransactionsMain?.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        adapter = TransactionAdapter(transactionList){
            Toast.makeText(this,getString(R.string.remaining_balance,it.balance.toString()),Toast.LENGTH_SHORT).show()
        }
        mBinding?.rvTransactionsMain?.adapter = adapter
    }

    private fun getTransactionsData() {
        transactionViewModel.getTransactionDetails(accountItem?.id).observe(this
        ) {
            if(transactionList.size==0){
                transactionList.addAll(it)
            }else{
                transactionList.clear()
                transactionList.addAll(it)
            }
            adapter?.notifyDataSetChanged()
            mBinding?.pbTransactions?.visibility = View.GONE
            mBinding?.rvTransactionsMain?.visibility = View.VISIBLE
        }
    }
}