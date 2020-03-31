package com.sohayb.miniprojet_bahisohayb.RecyclerAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sohayb.miniprojet_bahisohayb.DataModels.Episode
import com.sohayb.miniprojet_bahisohayb.R
import kotlinx.android.synthetic.main.obj_episode_view.view.*


class EpisodeRecycler(val episodes: ArrayList<Episode>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //context = parent.context

        return EpisodeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.obj_episode_view,
                parent,
                false
            )
        )


    }

    override fun getItemCount(): Int = episodes.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EpisodeViewHolder -> {
                episodes.let { holder.Bind(it[position]) }
            }
        }
    }

    inner class EpisodeViewHolder
    constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun Bind(episode: Episode) {

            /*    val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

                Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(caracter.id)
                    .into()
    */
            //Picasso.get().load(caracter.image).into(itemView.ImageListObj);

            itemView.NameE.text = episode.name
            itemView.idoE.text = episode.id.toString()


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