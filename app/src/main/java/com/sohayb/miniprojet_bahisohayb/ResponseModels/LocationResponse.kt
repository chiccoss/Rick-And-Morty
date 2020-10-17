package com.sohayb.miniprojet_bahisohayb.ResponseModels

import com.sohayb.miniprojet_bahisohayb.DataModels.Infos
import com.sohayb.miniprojet_bahisohayb.DataModels.Location

class LocationResponse {

    lateinit var info: Infos
    lateinit var results: ArrayList<Location>

}