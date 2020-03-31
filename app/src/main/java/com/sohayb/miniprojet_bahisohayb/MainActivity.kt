package com.sohayb.miniprojet_bahisohayb

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.sohayb.miniprojet_bahisohayb.DataModels.Character
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

        var listEpis = intent.getParcelableArrayListExtra<Character>("listEp")
        var listChar = intent.getParcelableArrayListExtra<Character>("listChars")
        var listLoc = intent.getParcelableArrayListExtra<Character>("listLoc")


        buttonCharactes.setOnClickListener {
            //it.putExtra("listChars",intent.extras!!.get("listChars") as ArrayList<Character>)


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
        /*
        var carac : ArrayList<Character>? = items!!.results
                       for (it in carac!!){
                           print(it.name)
                       }
                   print("i m here")

        // var u=0
        button.setOnClickListener {
            // IO : NETWORK AND DB
            // MAIN FOR THINGS IN MAIN THREAD
            // DEFAULT FOR HEAVY OPERATIONS
            u++
            CoroutineScope(IO).launch { // or use GlobalScope
                ApiRequest ()
            }
        }

    }

    suspend fun ApiRequest() {
        //textView.text=getResult1FromAPI()
        //textView.text=getRes2()
        println("debug :  ")

    }


    private suspend fun getResult1FromAPI(): String {
        logT("getresult1FromAPI")
        //delay(1000)
        //return RESULT_ONE
        //Thread.sleep(1000)
    }

    fun getRes2(): String {
        logT("getRes2")
        //delay(1000)
        //return RESULT_TWO
    }

    fun logT(methodName: String) {
        println("debug : The method calling is $methodName")
    }*/
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
