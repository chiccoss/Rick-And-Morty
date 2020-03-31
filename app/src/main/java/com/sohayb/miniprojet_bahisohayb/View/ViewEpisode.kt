package com.sohayb.miniprojet_bahisohayb.View

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.sohayb.miniprojet_bahisohayb.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_episode.*


class ViewEpisode : YouTubeBaseActivity()  {


    // var monInitializeListener : OnInitializedListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_episode)

        Picasso.get().load("https://rickandmortyapi.com/api/character/avatar/361.jpeg").into(ViewChracterImage)




        //intent.extras


        /*button.setOnClickListener {

            try {
                Log.i("Yt player "," Player config begin")
                youTubePlayerView.initialize(ApiKey, object : OnInitializedListener{
                    override fun onInitializationSuccess(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubePlayer?,
                        p2: Boolean
                    ) {

                        if (!p2) {
                            p1?.cueVideo("https://www.youtube.com/watch?v=fA5Z2e86Hns")
                            Log.i("YoutubeActivity","Inside video playing")
                        }

                        //p1.loadVideo("https://www.youtube.com/watch?v=fA5Z2e86Hns")
                        //p1!!.cueVideo("https://www.youtube.com/watch?v=fA5Z2e86Hns")
                        p1?.play()
                    }

                    override fun onInitializationFailure(
                        p0: YouTubePlayer.Provider?,
                        p1: YouTubeInitializationResult?
                    ) {

                    }

                })
                Log.i("Yt player "," Player config end")
            }catch (e : Exception){
                Log.i("Yt player ","catched error ${e.message}")
            }


        }

        //youTubePlayerView.
        // videoView.setVideoPath("https://www.youtube.com/watch?v=lm-5C3MfRQs")

*/
    }
}