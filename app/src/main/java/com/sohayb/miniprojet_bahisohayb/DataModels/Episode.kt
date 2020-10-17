package com.sohayb.miniprojet_bahisohayb.DataModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Episode(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: ArrayList<String>,
    val url: String,
    val created: String
) : Parcelable
