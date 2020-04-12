package com.sohayb.miniprojet_bahisohayb

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sohayb.miniprojet_bahisohayb.Retrofit.RetroFitSource
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.await

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)


        CoroutineScope(Main).launch {
            StartMainActivityAfterDelay()
        }
    }

    suspend fun StartMainActivityAfterDelay() {
        delay(1000)
        val webService = RetroFitSource()


        CoroutineScope(Main).launch {
            withContext(Dispatchers.IO) {
                webService.getInfoFromInterface().getAllCharacters().await().results

            }.let { arrayCharacters -> //    Liste Caracters

                CoroutineScope(Main).launch {
                    webService.getInfoFromInterface().getAllEpisodes()

                        .await().results.also { arrayEpisodes -> //    Liste Episodes
                            CoroutineScope(Main).launch {
                                webService.getInfoFromInterface().getAllLocations()

                                    .await().results.also { arrayLocations ->  //    Liste Location

                                        Intent(this@SplashScreen, MainActivity::class.java).also {
                                            it.putParcelableArrayListExtra(
                                                "listChars",
                                                arrayCharacters
                                            )
                                            it.putParcelableArrayListExtra(
                                                "listEp",
                                                arrayEpisodes
                                            )
                                            it.putParcelableArrayListExtra(
                                                "listLoc",
                                                arrayLocations
                                            )
                                            startActivity(it)
                                        }
                                    }
                            }
                        }
                }
            }
        }
    }

    fun Context.toast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }




}