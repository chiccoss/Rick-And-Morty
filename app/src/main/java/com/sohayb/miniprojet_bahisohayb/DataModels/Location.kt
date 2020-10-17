package com.sohayb.miniprojet_bahisohayb.DataModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: ArrayList<String>,
    val url: String,
    val created: String
) : Parcelable

