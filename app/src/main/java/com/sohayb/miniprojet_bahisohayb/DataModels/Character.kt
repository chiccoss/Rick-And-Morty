package com.sohayb.miniprojet_bahisohayb.DataModels

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    val id: Int,    //The id of the character.
    val name: String,    //The name of the character.
    val status: String,    //The status of the character ('Alive', 'Dead' or 'unknown').
    val species: String,    //The species of the character.
    val type: String,    //The type or subspecies of the character.
    val gender: String,    //The gender of the character ('Female', 'Male', 'Genderless' or 'unknown').
    val origin: Origin,// String,    //Name and link to the character's origin location.
    val location: LocationFromApi, // String,  //Name and link to the character's last known location endpoint.
    val image: String, //(url),//	//Link to the character's image. All images are 300x300px and most are medium shots or portraits since they are intended to be used as avatars.
    val episode: ArrayList<String>, //(urls)	//List of episodes in which this character appeared
    val url: String, //(url)	//Link to the character's own URL endpoint.
    val created: String    //Time at which the character was created in the database.
) : Parcelable


