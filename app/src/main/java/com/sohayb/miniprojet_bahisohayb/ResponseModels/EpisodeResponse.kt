package com.sohayb.miniprojet_bahisohayb.ResponseModels

import com.sohayb.miniprojet_bahisohayb.DataModels.Episode
import com.sohayb.miniprojet_bahisohayb.DataModels.Infos

class EpisodeResponse {
    lateinit var info: Infos
    lateinit var results: ArrayList<Episode>
}