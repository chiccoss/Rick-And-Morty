package com.sohayb.miniprojet_bahisohayb.View

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohayb.miniprojet_bahisohayb.DataModels.Character
import com.sohayb.miniprojet_bahisohayb.R
import com.sohayb.miniprojet_bahisohayb.RecyclerAdapters.CharactersRecycler
import com.sohayb.miniprojet_bahisohayb.Retrofit.RetroFitSource
import kotlinx.android.synthetic.main.content_main_caracters.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await
import java.util.*
import kotlin.collections.ArrayList


@Suppress("NAME_SHADOWING")
class ListCaractersActivity : AppCompatActivity() {
    val webService = RetroFitSource()
    var list : ArrayList<Character> = ArrayList()
    // Make a CoroutineContext variable
    //val main: CoroutineContext by lazy { Dispatchers.Main }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_caracters)
        //getAllCaractersAndFillRecycler()
        //val bunldde = intent.extras
        list=intent.getParcelableArrayListExtra<Character>("listChars") as ArrayList<Character>

            initCharactersRecyclerViewWithAll(list)

        resfreshLayoutCharacters.setOnRefreshListener {
            initCharactersRecyclerViewWithAll(list)
        }




    }

    fun getAllCaractersAndFillRecycler() { // normally called from main at first
        CoroutineScope(Dispatchers.Main).launch { // or   GlobalScope.launch(Main)
            withContext(Dispatchers.IO) {
                webService.getInfoFromInterface().getAllCharacters().await()
            }.also { /*it -> // TODO THIS CODE IS IS FOR MVVM ,PAGING , MUABLELIVEDATA
                var count = 2
                //var i = 2

                var AllCaractersOfApiArray: ArrayList<Character> = ArrayList()
                for (character in it.results) {
                    AllCaractersOfApiArray.add(character)

                }
                val pageCount =it.info.pages + 1
                while (count < pageCount) {
                    Log.i("number of pages --", "num $count")
                    //delay(1000)
                    webService.getInfoFromInterface().getNextpageCaracters(count).await()
                        .also {
                            for (character in it.results) {
                                Log.i("erer","ordianto")
                                AllCaractersOfApiArray.add(character)
                            }
                        }
                    count++

                }
                /*for (characters in tabDetab) {
                    for (character in characters.results) {
                        AllCaractersOfApiArray.add(character)
                    }
                }*/
*/

                //initCharactersRecyclerViewWithAll(AllCaractersOfApiArray) // tes cencé utiliser ce la
                initCharactersRecyclerViewWithAll(it.results)
            }


            /*.also {

        }*/
        }
    }


    object getAllCaractersFromOutside {
        fun getAllCaracters(): ArrayList<Character> {
            val webService = RetroFitSource()
            var list: ArrayList<Character> = ArrayList()
            //var caractersArray : CaractersResponse? = null
            CoroutineScope(Dispatchers.Main).launch { // or   GlobalScope.launch(Main)
                withContext(Dispatchers.IO) {
                     list = webService.getInfoFromInterface().getAllCharacters().await().results
                }

            }
            return list
        }
    }

    fun getCharacters(ids: String) {
        //val requests = ArrayList<Observable<>>()
        CoroutineScope(Dispatchers.Main).launch { // or   GlobalScope.launch(Main)
            withContext(Dispatchers.IO) {
                webService.getInfoFromInterface().getAllCharactersWith(ids).await()
            }.also {
                // var chars : ArrayList<Character> = ArrayList()
                //chars.add(it)
                initCharactersRecyclerViewWithAll(it)
            }
            //progressBar.setVisibility(View.GONE)
            //g().results.let { initCharactersRecyclerView(it) }
        }
    }

    /*fun getCharacter(id: String) {
        CoroutineScope(Dispatchers.Main).launch { // or   GlobalScope.launch(Main)
            withContext(Dispatchers.IO) {
                webService.getInfoFromInterface().getCaracterById(id).await()
            }.also {
                var chars: ArrayList<Character> = ArrayList()
                chars.add(it)
                initCharactersRecyclerViewWithAll(chars)
            }
            //progressBar.setVisibility(View.GONE)
            //g().results.let { initCharactersRecyclerView(it) }
        }

    }*/

    private fun initCharactersRecyclerViewWithAll(character: ArrayList<Character>) {

        resfreshLayoutCharacters.isRefreshing=true
        val topSpacingDecorator = TopSpacingItemDecoration(16)
        recyclerViewCaracters.apply {
            //if(itemDecorationCount ==1){
            //removeItemDecoration(topSpacingDecorator)
            //}
            layoutManager = LinearLayoutManager(this@ListCaractersActivity)

            if (itemDecorationCount == 0) {
                Log.i("", "inside count 0")
                addItemDecoration(topSpacingDecorator)
            }

            adapter = CharactersRecycler(
                character
            ).also {
                it.notifyDataSetChanged()
            }
        }

        resfreshLayoutCharacters.isRefreshing=false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu!!.findItem(R.id.search).actionView as SearchView

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            isIconifiedByDefault = false
        }

        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(charSeq: String): Boolean {
                Log.i("t", "onQueryTextSubmit")
                val regex = Regex("[a-zA-Z]+")
                if (charSeq.isEmpty()) {
                    Log.i(" epty", "empty")
                    getAllCaractersAndFillRecycler()
                    Toast.makeText(
                        this@ListCaractersActivity,
                        "Enter des caracteres valides",
                        Toast.LENGTH_SHORT
                    ).show()
                    return false
                } else if (charSeq.contains(regex)) {
                    Log.i("ee", "hfgugy")
                    CoroutineScope(Dispatchers.Main).launch { // or   GlobalScope.launch(Main)
                        withContext(Dispatchers.IO) {
                            webService.getInfoFromInterface().getAllCharacters().await()
                        }.also {
                            val newArray: ArrayList<Character> = ArrayList()
                            for (character in it.results) {
                                Log.i("ee", "inside let $charSeq")
                                if ((character.name).toLowerCase(Locale.ROOT).contains(charSeq)) {
                                    Log.i("Log", "inside if")
                                    newArray.add(character)
                                }
                            }
                            initCharactersRecyclerViewWithAll(newArray)
                        }
                    }
                    //TODO add le meme algorithm pour la function getAllCharactersAndFillRecycler()
                    return true
                    // var caracters : CaractersResponse  = getAllCaracters()
                } else {
                    Toast.makeText(
                        this@ListCaractersActivity,
                        "GOT SELECTED CARACTERS",
                        Toast.LENGTH_SHORT
                    ).show()
                    getAllCaractersAndFillRecycler()
                    //getCharacters(ids)
                    return true
                }
                //getCharacters(ids)
                //return true
            }

            override fun onQueryTextChange(charSeq: String): Boolean {
                Log.i("t", "onQueryTextSubmit")
                val regex = Regex("[a-zA-Z]+")
                if (charSeq.isEmpty()) {
                    Log.i(" epty", "empty")
                    getAllCaractersAndFillRecycler()
                    Toast.makeText(
                        this@ListCaractersActivity,
                        "Enter des caracteres valides",
                        Toast.LENGTH_SHORT
                    ).show()
                    return false
                } else if (charSeq.contains(regex)) {
                    Log.i("ee", "hfgugy")
                    CoroutineScope(Dispatchers.Main).launch { // or   GlobalScope.launch(Main)
                        withContext(Dispatchers.IO) {
                            webService.getInfoFromInterface().getAllCharacters().await()
                        }.also {
                            val newArray: ArrayList<Character> = ArrayList()
                            for (character in it.results) {
                                Log.i("ee", "inside let $charSeq")
                                //Log.i("ee", "inside let ${character.name}")
                                if ((character.name).toLowerCase(Locale.ROOT).contains(charSeq)) {
                                    Log.i("Log", "inside if")
                                    newArray.add(character)
                                }
                            }
                            initCharactersRecyclerViewWithAll(newArray)
                        }
                    }
                    return true
                    // var caracters : CaractersResponse  = getAllCaracters()
                } else {
                    Toast.makeText(
                        this@ListCaractersActivity,
                        "GOT SELECTED CARACTERS",
                        Toast.LENGTH_SHORT
                    ).show()
                    getAllCaractersAndFillRecycler()
                    //getCharacters(ids)
                    return true
                }
                //getCharacters(ids)
                //return true
            }
        })
        return true
    }
}