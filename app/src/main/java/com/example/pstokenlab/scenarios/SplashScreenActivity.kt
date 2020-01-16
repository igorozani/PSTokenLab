package com.example.pstokenlab.scenarios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.pstokenlab.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Picasso.get().load(R.mipmap.logo).placeholder(R.mipmap.logo).into(imgLogoApp)

        Handler().postDelayed({
            val movieList = Intent(this, MovieListActivity::class.java)
            startActivity(movieList)
            finish()
        },2000)
    }
}
