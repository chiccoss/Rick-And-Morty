package com.sohayb.miniprojet_bahisohayb.DataModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class Infos(
    var count: Int,
    var pages: Int,
    var next: String,
    var prev: String
) : Parcelable