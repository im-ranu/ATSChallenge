package com.guysapp.ats.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Transactions(
        var id : String,
        var title : String,
        var balance : Double) :Parcelable
