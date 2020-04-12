package com.sohayb.miniprojet_bahisohayb.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sohayb.miniprojet_bahisohayb.R
import kotlinx.android.synthetic.main.view_location.*


class ViewLocation : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.view_location)
        BTN1.text = intent.getStringExtra("lname")
        BTN2.text = intent.getStringExtra("lcrea")
        BTN3.text = intent.getStringExtra("ldimen")
        val charsInside = intent.getStringArrayExtra("lresidents")
        BTN5.text = intent.getStringExtra("ltype")
        BTN6.text = intent.getStringExtra("lurl")

        BTN4.setOnClickListener {

        }
    }
}
