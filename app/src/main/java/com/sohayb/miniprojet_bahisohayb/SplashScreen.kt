package com.sohayb.miniprojet_bahisohayb

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sohayb.miniprojet_bahisohayb.Retrofit.RetroFitSource
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.await

class SplashScreen : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        CoroutineScope(Main).launch {
            StartMainActivityAfterDelay()
        }
    }

    suspend fun StartMainActivityAfterDelay() {
        //delay(2000)


        val webService = RetroFitSource()


        CoroutineScope(Main).launch {
            withContext(Dispatchers.IO) {
                webService.getInfoFromInterface().getAllCharacters().await().results
            }.let { arrayCharacters ->
                CoroutineScope(Main).launch { // or   GlobalScope.launch(Main)
                    webService.getInfoFromInterface().getAllEpisodes()
                        .await().results.also { arrayEpisodes ->

                        CoroutineScope(Main).launch { // or   GlobalScope.launch(Main)
                            webService.getInfoFromInterface().getAllLocations()
                                .await().results.also { arrayLocations ->

                                Intent(this@SplashScreen, MainActivity::class.java).also {
                                    it.putParcelableArrayListExtra("listChars", arrayCharacters)
                                    it.putParcelableArrayListExtra("listEp", arrayEpisodes)
                                    it.putParcelableArrayListExtra("listLoc", arrayLocations)
                                    startActivity(it)
                                }
                            }

                        }

                    }

                }

            }

        }


    }

    override fun onClick(v: View?) {
        Intent(v!!.context, MainActivity::class.java).also {
            startActivity(it)
        }
    }
}