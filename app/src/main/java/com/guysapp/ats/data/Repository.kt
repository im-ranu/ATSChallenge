package com.guysapp.ats.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.guysapp.ats.data.models.Account
import com.guysapp.ats.data.models.Transactions
import com.guysapp.ats.network.ApiInterface
import com.guysapp.ats.network.RestClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    var isResultSuccess = MutableLiveData<Boolean>()
    private var restClient : ApiInterface? = null
    init {
        restClient = RestClient.getRetrofitClient().create(ApiInterface::class.java)
    }

      fun doLogin(username: String, password: String) : LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        val requestMap : MutableMap<String,String> = HashMap()
        requestMap["username"] = username
        requestMap["password"] = password
        val call = restClient?.doLogin(requestMap)

        call?.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                result.value = response.isSuccessful
                return
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                result.value = false
                return
            }

        })

        return result
    }

     fun getAccounts():LiveData<ArrayList<Account>>{
        val result = MutableLiveData<ArrayList<Account>>()

        val call = restClient?.getAccounts()
         call?.enqueue(object : Callback<ArrayList<Account>>{
             override fun onResponse(
                 call: Call<ArrayList<Account>>,
                 response: Response<ArrayList<Account>>
             ) {
                 if (response.isSuccessful){
                     isResultSuccess.value = true
                     result.value = response.body()
                 }else{
                     isResultSuccess.value = false
                 }
             }

             override fun onFailure(call: Call<ArrayList<Account>>, t: Throwable) {
                 isResultSuccess.value = false
             }

         })
        return result
    }

    fun getTransactions(accountId : String?):LiveData<ArrayList<Transactions>>{
        val result = MutableLiveData<ArrayList<Transactions>>()
        val call = restClient?.getTransactions(accountId.toString())
        call?.enqueue(object :Callback<ArrayList<Transactions>>{
            override fun onResponse(
                call: Call<ArrayList<Transactions>>,
                response: Response<ArrayList<Transactions>>
            ) {
                if (response.isSuccessful){
                    isResultSuccess.value = true
                    result.value = response.body()
                }else{
                    isResultSuccess.value = false
                }
            }

            override fun onFailure(call: Call<ArrayList<Transactions>>, t: Throwable) {
                isResultSuccess.value = false
            }

        })
        return result
    }
}