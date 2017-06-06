package com.scibots.aniket.mtickets

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.scibots.aniket.mtickets.databaseMangment.starhandler
import com.squareup.picasso.Picasso


class Detail_screen : AppCompatActivity() {
    var id: Int? = null
    var name: String? = null
    var url_back: String? = null
    var overview: String? = null
    var average_votes: String? = null
    var release_date: String? = null
    var language:String? =null
    var poster_url:String? =null
    val TAG = "Detailed View"
    var dtitle:TextView? = null
    var disp:TextView? = null
    var ddate:TextView? = null
    var dstarrate:TextView? = null
    var dlanguage:TextView? = null
    var starred: ImageView? = null
    var book:Button? = null
    var dataBaseHandler = starhandler(this, null, null, 2)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_screen)
        var intent = intent
        var b = intent.extras
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }

        if (b != null) {
            id = b.getInt("movieId")
            name = b.getString("moviename")
            url_back = b.getString("moviebackurl")
            overview = b.getString("movieOverview")
            release_date = b.getString("moviedate")
            average_votes = b.getString("movievotes")
            language = b.getString("lang")
            poster_url = b.getString("poster_url")
        }

        /*
        * if stared it will change into pink
        * */
        starred = findViewById(R.id.stared) as ImageView
        if (dataBaseHandler.isMovieLiked(id.toString())) {
            starred?.setImageResource(R.drawable.ic_favorite_pink)
        }
        Log.d(TAG, dataBaseHandler.isMovieLiked(id.toString()).toString())




        /**
         * loading background Image
         * */
        var Background = findViewById(R.id.backImageTheme)as ImageView
            Picasso.with(this).load("https://image.tmdb.org/t/p/w780/" + url_back).into(Background)

        /**
         * Filling data
         * */
        dtitle = findViewById(R.id.dtitle) as TextView
        disp = findViewById(R.id.ddisp) as TextView
        ddate = findViewById(R.id.ddate) as TextView
        dstarrate = findViewById(R.id.dratestar) as TextView
        dlanguage = findViewById(R.id.language) as TextView

        dtitle?.setText(name)
        disp?.setText(overview)
        ddate?.setText(release_date)
        dstarrate?.setText(average_votes)
        dlanguage?.setText(language)

        book = findViewById(R.id.dbookbut) as Button


    }

    public fun open_book_screen(view:View){
        var intent: Intent = Intent(this, BookScreen::class.java)
        var b = Bundle()
        b.putString("url", poster_url)
        b.putString("name",name)
        intent.putExtras(b)
        this.startActivity(intent)
    }

    fun goBack(view: View) {
        super.onBackPressed();
    }

    fun toggelLike(view: View) {
        if (dataBaseHandler.isMovieLiked(id.toString())) {
            dataBaseHandler.removeLike(id.toString())
            starred?.setImageResource(R.drawable.ic_favorite_border_pink_24dp)
        } else {
            dataBaseHandler.addLikedMovie(id.toString())
            starred?.setImageResource(R.drawable.ic_favorite_pink)
        }
    }
}
