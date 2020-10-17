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

        BTN1.text = intent.getStringExtra("name")
        BTN2.text = intent.getStringExtra("status")
        BTN3.text = intent.getStringExtra("species")

        if (intent.getStringExtra("type") == "") {
            BTN4.text = getString(R.string.not_defined)
        } else {
            BTN4.text = intent.getStringExtra("type")

        }

        BTN5.text = intent.getStringExtra("gender")
        BTN6.text = intent.getStringExtra("originName")
        BTN7.text = intent.getStringExtra("locationName")
        BTN8.text = intent.getStringExtra("created")
    }
}


