package com.sohayb.miniprojet_bahisohayb.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sohayb.miniprojet_bahisohayb.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_character.*

class ViewCharacter : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.view_character)


        Picasso.get().load(intent.getStringExtra("image")).into(ViewChracterImage)

        ViewCaracteName.text = intent.getStringExtra("name")
        ViewCaracterStatus.text = intent.getStringExtra("status")
        ViewCaracterSpecies.text = intent.getStringExtra("species")

        if (intent.getStringExtra("type") == "") {
            ViewCaracterType.text = getString(R.string.not_defined)
        } else {
            ViewCaracterType.text = intent.getStringExtra("type")

        }

        ViewCaracterGender.text = intent.getStringExtra("gender")
        ViewCaracterOrigin.text = intent.getStringExtra("originName")
        ViewCaracterLocation.text = intent.getStringExtra("locationName")
        ViewCaracterCreated.text = intent.getStringExtra("created")
    }
}


