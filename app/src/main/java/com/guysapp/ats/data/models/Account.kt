package com.guysapp.ats.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Account(
        var id : String,
        var name : String,
        var balance : Double) :Parcelable
