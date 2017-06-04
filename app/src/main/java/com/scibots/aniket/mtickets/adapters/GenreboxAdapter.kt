package com.scibots.aniket.mtickets.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.scibots.aniket.mtickets.dataclass.Movie

/**
 * Created by aniket on 4/6/17.
 */
class GenreboxAdapter(internal var context: Context, Moviesdataset: ArrayList<Movie>) : RecyclerView.Adapter<GenreboxAdapter.ViewHolder>() {

    var Moviesdataset: ArrayList<Movie>? = null

    init {
        this.Moviesdataset = Moviesdataset

    }


    override fun onBindViewHolder(viewHolder: ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



        init {


        }


    }


}