package com.sohayb.miniprojet_bahisohayb

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sohayb.miniprojet_bahisohayb.DataModels.Character
import com.sohayb.miniprojet_bahisohayb.DataModels.Episode
import com.sohayb.miniprojet_bahisohayb.DataModels.Location
import com.sohayb.miniprojet_bahisohayb.View.ListCaractersActivity
import com.sohayb.miniprojet_bahisohayb.View.ListEpisodesActivity
import com.sohayb.miniprojet_bahisohayb.View.ListLocationsActivity
import kotlinx.android.synthetic.main.main_page.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        if(Random(3).nextInt(0,1)==0){
            LayoutConstraint.setBackgroundResource(R.drawable.rickybg)
        }else{
            LayoutConstraint.setBackgroundResource(R.drawable.rickincar)
        }

        val listEpis = intent.getParcelableArrayListExtra<Episode>("listEp")
        val listChar = intent.getParcelableArrayListExtra<Character>("listChars")
        val listLoc = intent.getParcelableArrayListExtra<Location>("listLoc")

        buttonCharactes.setOnClickListener {
            Intent(this@MainActivity, ListCaractersActivity::class.java).let {
                it.putParcelableArrayListExtra("listChars",listChar)
                startActivity(it)
            }
        }

        buttonLocation.setOnClickListener {
            Intent(this@MainActivity, ListLocationsActivity::class.java).let {
                it.putParcelableArrayListExtra("listLoc",listLoc)
                startActivity(it)
            }
        }

        buttonEpisodes.setOnClickListener {
            Intent(this@MainActivity, ListEpisodesActivity::class.java).also {
                it.putParcelableArrayListExtra("listEpis",listEpis)
                startActivity(it)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onResume() {
        super.onResume()
        if(Random(3).nextInt(0,1)==0){
            LayoutConstraint.setBackgroundResource(R.drawable.rickybg)
        }else{
            LayoutConstraint.setBackgroundResource(R.drawable.rickincar)
        }
    }
}
