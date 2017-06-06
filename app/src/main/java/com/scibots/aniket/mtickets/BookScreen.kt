package com.scibots.aniket.mtickets

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.afollestad.materialdialogs.MaterialDialog
import com.basgeekball.awesomevalidation.helper.SpanHelper.setColor
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate


class BookScreen : AppCompatActivity() {
    var name: String? = null
    var poster_url: String? = null
    var poster: ImageView? = null
    var username:EditText?=null
    var email:EditText?=null
    var address:EditText?=null
    var quantity:EditText?=null
    val mAwesomeValidation = AwesomeValidation(ValidationStyle.UNDERLABEL)  


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_screen)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        var intent = intent
        var b = intent.extras
        if (b != null) {

            name = b.getString("name")
            poster_url = b.getString("url")

        }
        poster = findViewById(R.id.poster) as ImageView
        Picasso.with(this).load("https://image.tmdb.org/t/p/w342/" +poster_url).into(poster)
        /*
        * Intializing edit texts
        * */
        username = findViewById(R.id.username) as EditText
        email = findViewById(R.id.email) as EditText
        address = findViewById(R.id.homeaddress) as EditText
        quantity = findViewById(R.id.quantity) as EditText

        mAwesomeValidation.setContext(this)
        mAwesomeValidation.addValidation(this,R.id.username, "[a-zA-Z\\s]+",R.string.errusername );
        mAwesomeValidation.addValidation(this, R.id.email, android.util.Patterns.EMAIL_ADDRESS, R.string.erremail);



    }

    public fun pay(view:View){
        if(formValidated()){
            val dialog = MaterialDialog.Builder(this)
                .title("Successfully booked")
                .content("thanks for booking of the movie"+name)
                .positiveText("ok")
                .show()
        }
    }

     fun  formValidated(): Boolean{
         mAwesomeValidation.validate();

         return false
     }


}
