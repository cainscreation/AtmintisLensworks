package com.first.caincreation.atmintis

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import com.google.firebase.FirebaseApp

class front : Activity() {
    var button1: Button? = null
    var button2: Button? = null
    var button3: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front)
        FirebaseApp.initializeApp(this)

        button1 = findViewById(R.id.button)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)


        button1?.setOnClickListener {
            var intent = Intent(this@front, registpage2::class.java)
            startActivity(intent)
        }


        button3?.setOnClickListener {
            var intent = Intent(this@front, aboutus::class.java)
            startActivity(intent)
        }

        button2?.setOnClickListener {
            var intent = Intent(this@front, imageview::class.java)
            startActivity(intent)
        }


    }
}
