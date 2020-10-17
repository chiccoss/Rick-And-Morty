package com.sohayb.miniprojet_bahisohayb.RecyclerAdapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sohayb.miniprojet_bahisohayb.DataModels.Episode
import com.sohayb.miniprojet_bahisohayb.R
import com.sohayb.miniprojet_bahisohayb.View.ViewEpisode
import kotlinx.android.synthetic.main.obj_episode_view.view.*


class EpisodeRecycler(val episodes: ArrayList<Episode>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context

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
                episodes.let { holder.Bind(it[position], this@EpisodeRecycler) }
            }
        }
    }

    inner class EpisodeViewHolder
    constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun Bind(
            episode: Episode,
            episodeRecycler: EpisodeRecycler
        ) {

            itemView.NameE.text = episode.name
            itemView.idoE.text = episode.id.toString()

            itemView.setOnClickListener {

                val destination = Intent(
                    context,
                    ViewEpisode::class.java
                ).apply {

                    putExtra("ename", episode.name)
                    putExtra("eairdate", episode.air_date)
                    putExtra("echaracters", episode.characters)
                    putExtra("ecreated", episode.created)
                    putExtra("eepisode", episode.episode)
                    putExtra("eurl", episode.url)

                }

                (context as Activity).startActivity(destination)

            }


            itemView.setOnLongClickListener {

                episodes.remove(episode)
                episodeRecycler.notifyDataSetChanged()
                true
            }
        }


    }
}