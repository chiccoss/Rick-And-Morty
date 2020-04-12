package com.sohayb.miniprojet_bahisohayb.RecyclerAdapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sohayb.miniprojet_bahisohayb.DataModels.Location
import com.sohayb.miniprojet_bahisohayb.R
import com.sohayb.miniprojet_bahisohayb.View.ViewLocation
import kotlinx.android.synthetic.main.obj_location_view.view.*

class LocationRecycler(val locations: ArrayList<Location>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context

        return LocationViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.obj_location_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = locations.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LocationViewHolder -> {
                locations.let { holder.Bind(it[position], this@LocationRecycler) }
            }
        }
    }

    inner class LocationViewHolder
    constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun Bind(
            location: Location,
            locationRecycler: LocationRecycler
        ) {

            itemView.NameL.text = location.name
            itemView.idoL.text = location.id.toString()

            itemView.setOnClickListener {

                val destination = Intent(
                    context,
                    ViewLocation::class.java
                ).apply {

                    putExtra("lname", location.name)
                    putExtra("lcrea", location.created)
                    putExtra("ldimen", location.dimension)
                    putExtra("lresidents", location.residents)
                    putExtra("ltype", location.type)
                    putExtra("lurl", location.url)

                }

                (context as Activity).startActivity(destination)

            }


            itemView.setOnLongClickListener {
                locations.remove(location)
                locationRecycler.notifyDataSetChanged()
                true
            }
        }


    }
}