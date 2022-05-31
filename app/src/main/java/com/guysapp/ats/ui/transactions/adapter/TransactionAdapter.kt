package com.guysapp.ats.ui.transactions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guysapp.ats.data.models.Account
import com.guysapp.ats.data.models.Transactions
import com.guysapp.ats.databinding.ItemviewRvAccountsBinding
import com.guysapp.ats.databinding.ItemviewRvTransactionsBinding
import com.guysapp.ats.ui.accounts.adapter.AccountAdapter

class TransactionAdapter(
    var transactionList : ArrayList<Transactions>,
    var itemOnClickLister : (Transactions)-> Unit
) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {


    class ViewHolder(var mBinding : ItemviewRvTransactionsBinding) : RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemviewRvTransactionsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(transactionList[position]){
                mBinding.transaction = this

                mBinding.executePendingBindings()

                mBinding.cvParent.setOnClickListener{
                    itemOnClickLister(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }


}