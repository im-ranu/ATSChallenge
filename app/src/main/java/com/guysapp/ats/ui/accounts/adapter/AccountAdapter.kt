package com.guysapp.ats.ui.accounts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guysapp.ats.data.models.Account
import com.guysapp.ats.databinding.ItemviewRvAccountsBinding

class AccountAdapter(
    var accountList : ArrayList<Account>,
    var itemOnClickLister : (Account)-> Unit
) : RecyclerView.Adapter<AccountAdapter.ViewHolder>() {


    class ViewHolder(var mBinding : ItemviewRvAccountsBinding) : RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemviewRvAccountsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(accountList[position]){
                mBinding.account = this

                mBinding.executePendingBindings()

                mBinding.cvParent.setOnClickListener{
                    itemOnClickLister(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return accountList.size
    }


}