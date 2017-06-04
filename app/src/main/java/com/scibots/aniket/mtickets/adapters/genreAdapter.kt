package com.scibots.aniket.mtickets.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.scibots.aniket.mtickets.Home_view
import com.scibots.aniket.mtickets.R

/**
 * Created by aniket on 4/6/17.
 */

class genreAdapter(internal var context: Context, genreDataset: ArrayList<HashMap<Int, String>>) : RecyclerView.Adapter<genreAdapter.ViewHolder>() {
    internal var genredataset: ArrayList<HashMap<Int, String>>

    init {
        genredataset = genreDataset
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder? {
        val v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.genre_list, parent, false)
        val vh = ViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        for (key in genredataset.get(i).keys) {

            viewHolder.mgenre.setText(genredataset.get(i).get(key))

            viewHolder.mgenre.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View): Unit {
                    var intent: Intent = Intent(context, Home_view::class.java)
                    var b = Bundle()
                    b.putInt("genreId", key)
                    b.putString("genreName", genredataset.get(i).get(key))
                    intent.putExtras(b)
                    context.startActivity(intent)
                    (context as Activity).finish()

                }
            })


        }
    }

    override fun getItemCount(): Int {
        if (genredataset != null) {
            return genredataset?.size!!
        } else {
            return 0
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mgenre: TextView

        init {
            mgenre = itemView.findViewById(R.id.genreName) as TextView
        }
    }
}
