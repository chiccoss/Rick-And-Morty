package com.sohayb.miniprojet_bahisohayb.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohayb.miniprojet_bahisohayb.DataModels.Episode
import com.sohayb.miniprojet_bahisohayb.R
import com.sohayb.miniprojet_bahisohayb.RecyclerAdapters.EpisodeRecycler
import com.sohayb.miniprojet_bahisohayb.ResponseModels.EpisodeResponse
import com.sohayb.miniprojet_bahisohayb.Retrofit.RetroFitSource
import kotlinx.android.synthetic.main.content_main_episodes.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListEpisodesActivity : AppCompatActivity() {

    val webService = RetroFitSource()
    var list : ArrayList<Episode> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_episodes)

        list = intent.getParcelableArrayListExtra<Episode>("listEpis") as ArrayList<Episode>
        initEpisodesRecyclerView(list)


        resfreshLayoutEpisodes.setOnRefreshListener {
            initEpisodesRecyclerView(list)
        }
        /*CoroutineScope(Dispatchers.Main).launch { // or   GlobalScope.launch(Main)
            webService.getInfoFromInterface().getAllEpisodes().await().results.also {
                initEpisodesRecyclerView(it)
            }

        }*/
    }

    /*suspend fun getAllEpisodes(): EpisodeResponse {

        val result = withContext(Dispatchers.IO) {

        }
        return result
    }*/

    private fun initEpisodesRecyclerView(episodes: ArrayList<Episode>) {
        resfreshLayoutEpisodes.isRefreshing=true
        recyclerViewEpisodes.apply {
            layoutManager = LinearLayoutManager(this@ListEpisodesActivity)
            val topSpacingDecorator =
                TopSpacingItemDecoration(16)
            addItemDecoration(topSpacingDecorator)


            adapter = EpisodeRecycler(
                episodes
            )//.also {
            ///   it.notifyDataSetChanged()
            // }
            //itemAnimator = AnimationRecycler()
            

        }
        resfreshLayoutEpisodes.isRefreshing=false
    }
}