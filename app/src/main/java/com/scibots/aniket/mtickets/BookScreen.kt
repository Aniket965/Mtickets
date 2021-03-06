package com.scibots.aniket.mtickets

import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.squareup.picasso.Picasso


class BookScreen : AppCompatActivity() {
    var name: String? = null
    var poster_url: String? = null
    var poster: ImageView? = null
    var username: EditText? = null
    var email: EditText? = null
    var address: EditText? = null
    var quantity: EditText? = null
    var cl: ConstraintLayout? = null


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
        cl = findViewById(R.id.bookLayout) as ConstraintLayout
        Picasso.with(this).load("https://image.tmdb.org/t/p/w342/" + poster_url).into(poster)
        /*
        * Intializing edit texts
        * */
        username = findViewById(R.id.username) as EditText
        email = findViewById(R.id.email) as EditText
        address = findViewById(R.id.homeaddress) as EditText
        quantity = findViewById(R.id.quantity) as EditText


    }

    public fun pay(view: View) {
        if (formValidated()) {
            val dialog = MaterialDialog.Builder(this)
                    .title("Successfully booked")
                    .content("thanks for booking of the movie" + name)
                    .positiveText("ok")
                    .show()
        }
        else{

            Toast.makeText(this,"Please enter valid details",Toast.LENGTH_SHORT).show()
        }
    }

    fun formValidated(): Boolean {
     return (isValidEmail(email?.text)&& isValidAddress(address?.text)&& isValidQunatity(quantity?.text)&& isValidUserName(username?.text))
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        if (target == null) {
            return false
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    fun isValidUserName(target: CharSequence?): Boolean {
        return target != null
    }

    fun isValidQunatity(target: CharSequence?): Boolean {
        if (target == null||target.toString().contains("-")||(Integer.parseInt(target.toString())== 0)) {
//            Log.d("LOL","valdation required")
            return false
        } else {
            return true
        }
    }

    fun isValidAddress(target: CharSequence?): Boolean {
        return target != null
    }

    fun goBack(view: View) {
        super.onBackPressed();
    }


}
