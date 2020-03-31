package com.sohayb.miniprojet_bahisohayb.Retrofit

import com.sohayb.miniprojet_bahisohayb.DataModels.Character
import com.sohayb.miniprojet_bahisohayb.DataModels.Episode
import com.sohayb.miniprojet_bahisohayb.DataModels.Location
import com.sohayb.miniprojet_bahisohayb.ResponseModels.CaractersResponse
import com.sohayb.miniprojet_bahisohayb.ResponseModels.CharacterResponse
import com.sohayb.miniprojet_bahisohayb.ResponseModels.EpisodeResponse
import com.sohayb.miniprojet_bahisohayb.ResponseModels.LocationResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("character/{id}")
    fun getAllCharactersWith(
        @Path("id") ids: String
    ): Call<ArrayList<Character>>

    @GET("character")
    fun getAllCharacters(): Call<CaractersResponse>

    @GET("character")
    fun getNextpageCaracters(@Query("page") page: Int): Call<CaractersResponse>

    @GET("episode")
    fun getAllEpisodes(): Deferred<EpisodeResponse>

    @GET("location")
    fun getAllLocations(): Deferred<LocationResponse>

    @GET("character/{id}")
    fun getCaracterById(@Path("id") id: String): Call<Character>

    @GET("location/{id}")
    fun getLocationById(@Path("id") id: Int): Call<Location>

    @GET("episode/{id}")
    fun getEpisodeById(@Path("id") id: Int): Call<Episode>

    /*
     @GET("episode")
     fun getAllEpisodes(): Call<CaractersResponse>

     @GET("location")
     fun getAllLocations(): Call<CaractersResponse>
     @GET("/character")
     fun getAllEpisodes(): Deferred<EpisodeResponse>

     @GET("/character/{id}")
     fun getCaracterById(): Call<Character?>
     @GET("users/{id}")*/
}
