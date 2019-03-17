package com.first.caincreation.atmintis


import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class reset : Activity() {

    private var mAuth: FirebaseAuth? = null
    private var buttonreset: Button? = null
    private var user_name2: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)
        FirebaseApp.initializeApp(this)
        mAuth = FirebaseAuth.getInstance()
        var buttonreset = findViewById<Button>(R.id.bu4)
        user_name2 = findViewById(R.id.edit1)


        buttonreset.setOnClickListener {
            reset2()

        }
    }

    private fun reset2() {
        val email = user_name2?.text.toString().trim()
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Enter your email!", Toast.LENGTH_SHORT).show()
        } else {


            mAuth?.sendPasswordResetEmail(email)?.addOnCompleteListener(object : OnCompleteListener<Void> {


                override fun onComplete(task: Task<Void>) {
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Check Mail", Toast.LENGTH_SHORT).show()

                    } else {
                        var error = task.exception?.message
                        Toast.makeText(applicationContext, "error " + error, Toast.LENGTH_LONG).show()

                    }

                }
            })
        }
    }
}

