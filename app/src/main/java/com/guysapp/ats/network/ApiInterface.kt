package com.guysapp.ats.network

import com.guysapp.ats.data.models.Account
import com.guysapp.ats.data.models.Transactions
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    @FormUrlEncoded
    @POST("/login")
     fun doLogin(
        @FieldMap requestMap : Map<String,String>
    ): Call<ResponseBody>

    @GET("/accounts")
    fun getAccounts() : Call<ArrayList<Account>>

    @GET("/transactions")
    fun getTransactions(@Query("accountId") accountId : String) : Call<ArrayList<Transactions>>




}