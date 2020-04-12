package com.sohayb.miniprojet_bahisohayb.View

import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.sohayb.miniprojet_bahisohayb.R
import kotlinx.android.synthetic.main.view_episode.*


class ViewEpisode : YouTubeBaseActivity() {


    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_episode)

        BTN1.text = intent.getStringExtra("ename")
        BTN2.text = intent.getStringExtra("eairdate")

        val charsInside = intent.getStringArrayExtra("echaracters")
        BTN4.text = intent.getStringExtra("ecreated")
        BTN5.text = intent.getStringExtra("eepisode")
        BTN6.text = intent.getStringExtra("eurl")
        BTN3.setOnClickListener {

        }
    }

    fun ShowCharacter(s: String) {

    }
}



