package com.sohayb.miniprojet_bahisohayb.DataModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class LocationFromApi(
    val name: String,
    val url: String
) : Parcelable