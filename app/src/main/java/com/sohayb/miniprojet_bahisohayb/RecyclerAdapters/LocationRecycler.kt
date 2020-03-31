package com.sohayb.miniprojet_bahisohayb.RecyclerAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sohayb.miniprojet_bahisohayb.DataModels.Location
import com.sohayb.miniprojet_bahisohayb.R

import kotlinx.android.synthetic.main.obj_location_view.view.*

class LocationRecycler(val locations: ArrayList<Location>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //context = parent.context

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
                locations.let { holder.Bind(it[position]) }
            }
        }
    }

    inner class LocationViewHolder
    constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun Bind(location: Location) {

            /*    val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(caracter.id)
                    .into()
    */
            //Picasso.get().load(caracter.image).into(itemView.ImageListObj);

            //itemView.CaracterName.text=caracter.name

            itemView.NameL.text = location.name
            itemView.idoL.text = location.id.toString()

            itemView.setOnClickListener {

                /*val destination= Intent(context,
                    ViewObject::class.java).apply {

                    putExtra("name","fr")

                }

                (context as Activity).startActivity(destination)
                Toast.makeText(it!!.context,"on click ",Toast.LENGTH_SHORT).show()*/
            }


            itemView.setOnLongClickListener {
                true
            }
        }


    }
}