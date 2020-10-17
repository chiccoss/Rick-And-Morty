package com.sohayb.miniprojet_bahisohayb.View

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
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


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_caracters)


        list=intent.getParcelableArrayListExtra<Character>("listChars") as ArrayList<Character>

            initCharactersRecyclerViewWithAll(list)

        resfreshLayoutCharacters.setOnRefreshListener {
            initCharactersRecyclerViewWithAll(list)
        }




    }

    fun getAllCaractersAndFillRecycler() {
        CoroutineScope(Dispatchers.Main).launch { 
            withContext(Dispatchers.IO) {
                webService.getInfoFromInterface().getAllCharacters().await()
            }.also {
                initCharactersRecyclerViewWithAll(it.results)
            }


        }
    }


    object getAllCaractersFromOutside {
        fun getAllCaracters(): ArrayList<Character> {
            val webService = RetroFitSource()
            var list: ArrayList<Character> = ArrayList()

            CoroutineScope(Dispatchers.Main).launch { 
                withContext(Dispatchers.IO) {
                     list = webService.getInfoFromInterface().getAllCharacters().await().results
                }

            }
            return list
        }
    }

    fun getCharacters(ids: String) {
        CoroutineScope(Dispatchers.Main).launch { 
            withContext(Dispatchers.IO) {
                webService.getInfoFromInterface().getAllCharactersWith(ids).await()
            }.also {
                initCharactersRecyclerViewWithAll(it)
            }

        }
    }



    private fun initCharactersRecyclerViewWithAll(character: ArrayList<Character>) {

        resfreshLayoutCharacters.isRefreshing=true
        val topSpacingDecorator = TopSpacingItemDecoration(16)
        recyclerViewCaracters.apply {

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

                val regex = Regex("[a-zA-Z]+")
                if (charSeq.isEmpty()) {

                    getAllCaractersAndFillRecycler()
                    Toast.makeText(
                        this@ListCaractersActivity,
                        "Enter des caracteres valides",
                        Toast.LENGTH_SHORT
                    ).show()
                    return false
                } else if (charSeq.contains(regex)) {

                    CoroutineScope(Dispatchers.Main).launch { 
                        withContext(Dispatchers.IO) {
                            webService.getInfoFromInterface().getAllCharacters().await()
                        }.also {
                            val newArray: ArrayList<Character> = ArrayList()
                            for (character in it.results) {

                                if ((character.name).toLowerCase(Locale.ROOT).contains(charSeq)) {
                                    newArray.add(character)
                                }
                            }
                            initCharactersRecyclerViewWithAll(newArray)
                        }
                    }

                    return true

                } else {

                    getAllCaractersAndFillRecycler()

                    return true
                }

            }

            override fun onQueryTextChange(charSeq: String): Boolean {

                val regex = Regex("[a-zA-Z]+")
                if (charSeq.isEmpty()) {

                    getAllCaractersAndFillRecycler()
                    Toast.makeText(
                        this@ListCaractersActivity,
                        "Entrer des caracteres valides",
                        Toast.LENGTH_SHORT
                    ).show()
                    return false
                } else if (charSeq.contains(regex)) {

                    CoroutineScope(Dispatchers.Main).launch { 
                        withContext(Dispatchers.IO) {
                            webService.getInfoFromInterface().getAllCharacters().await()
                        }.also {
                            val newArray: ArrayList<Character> = ArrayList()
                            for (character in it.results) {

                                if ((character.name).toLowerCase(Locale.ROOT).contains(charSeq)) {
                                    newArray.add(character)
                                }
                            }
                            initCharactersRecyclerViewWithAll(newArray)
                        }
                    }
                    return true

                } else {

                    getAllCaractersAndFillRecycler()

                    return true
                }

            }
        })
        return true
    }
}