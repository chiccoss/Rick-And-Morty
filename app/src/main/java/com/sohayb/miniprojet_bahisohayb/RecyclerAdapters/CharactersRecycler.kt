package com.sohayb.miniprojet_bahisohayb.RecyclerAdapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.request.RequestOptions
import com.sohayb.miniprojet_bahisohayb.DataModels.Character
import com.sohayb.miniprojet_bahisohayb.R
import com.sohayb.miniprojet_bahisohayb.View.ViewCharacter
import com.sohayb.miniprojet_bahisohayb.View.ViewEpisode
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.obj_caracter_view.view.*

class CharactersRecycler(val caracters: ArrayList<Character>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    var context : Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context

        return CharactersViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.obj_caracter_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = caracters.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharactersViewHolder -> {
                caracters.let { holder.Bind(it[position]) }
            }
        }
    }

    inner class CharactersViewHolder
    constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun Bind(caracter: Character) {

            /*    val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(caracter.id)
                    .into()
    */
            Picasso.get().load(caracter.image).into(itemView.idoC)

            itemView.NameC.text = caracter.name
            //itemView.= caracter.image


            itemView.setOnClickListener {

                val destination= Intent(context,
                    ViewCharacter::class.java).apply {

                    putExtra("image",caracter.image)
                    putExtra("name",caracter.name)
                    putExtra("status",caracter.status)
                    putExtra("species",caracter.species)
                    putExtra("type",caracter.type)
                    putExtra("gender",caracter.gender)
                    putExtra("originName",caracter.origin.name)
                    putExtra("locationName",caracter.location.name)
                    putExtra("created",caracter.created)
                }

                (context as Activity).startActivity(destination)
                Toast.makeText(it!!.context,"onClickCalled",Toast.LENGTH_SHORT).show()
            }


            itemView.setOnLongClickListener {
                Toast.makeText(it!!.context,"onLongClickCalled",Toast.LENGTH_SHORT).show()

                true
            }
        }


    }
}