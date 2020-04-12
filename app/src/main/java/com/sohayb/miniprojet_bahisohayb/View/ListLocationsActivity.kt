package com.sohayb.miniprojet_bahisohayb.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohayb.miniprojet_bahisohayb.DataModels.Location
import com.sohayb.miniprojet_bahisohayb.R
import com.sohayb.miniprojet_bahisohayb.RecyclerAdapters.LocationRecycler
import com.sohayb.miniprojet_bahisohayb.Retrofit.RetroFitSource
import kotlinx.android.synthetic.main.content_main_locations.*

class ListLocationsActivity : AppCompatActivity() {

    val webService = RetroFitSource()
    var list : ArrayList<Location> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_locations)

        list = intent.getParcelableArrayListExtra<Location>("listLoc") as ArrayList<Location>

        initLocationsRecyclerView(list)
        resfreshLayoutLocations.setOnRefreshListener {

            initLocationsRecyclerView(list)
        }

    }


    private fun initLocationsRecyclerView(locations: ArrayList<Location>) {
        resfreshLayoutLocations.isRefreshing=true
        recyclerViewLocation.apply {
            layoutManager = LinearLayoutManager(this@ListLocationsActivity)
            val topSpacingDecorator =
                TopSpacingItemDecoration(16)
            addItemDecoration(topSpacingDecorator)
            adapter = LocationRecycler(
                locations
            )
            
            
            


        }
        resfreshLayoutLocations.isRefreshing=false
    }
}