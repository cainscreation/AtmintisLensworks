package com.first.caincreation.atmintis

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class MainActivity : Activity() {
    private var user_name: EditText? = null
    private var password_name: EditText? = null
    private var firebaseAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        user_name = findViewById(R.id.editText)
        password_name = findViewById(R.id.editText2)
        firebaseAuth = FirebaseAuth.getInstance()


        var btn = findViewById<Button>(R.id.bu2)
        var signin = findViewById<Button>(R.id.bu1)
        var resetbutton = findViewById<Button>(R.id.bu3)

        signin?.setOnClickListener {
            Login()

        }

        resetbutton?.setOnClickListener {


            var intent3 = Intent(this@MainActivity, reset::class.java)
            startActivity(intent3)
        }
        btn?.setOnClickListener {
            var intent = Intent(this@MainActivity, registpage::class.java)
            startActivity(intent)
        }

    }

    private fun Login() {
        var user = user_name?.text.toString().trim()
        var pass = password_name?.text.toString().trim()
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(applicationContext, "This Field Cannot be empty", Toast.LENGTH_LONG).show()
        } else if (TextUtils.isEmpty(pass)) {
            Toast.makeText(applicationContext, "This Field Cannot be empty", Toast.LENGTH_LONG).show()
        } else {
            this.firebaseAuth?.signInWithEmailAndPassword(user, pass)
                ?.addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {


                        if (task.isSuccessful) {
                            Toast.makeText(applicationContext, "Successfully Logged in", Toast.LENGTH_LONG).show()
                            var intentb = Intent(this@MainActivity, front::class.java)
                            startActivity(intentb)
                        } else {

                            var error = task.exception?.message
                            Toast.makeText(applicationContext, "error " + error, Toast.LENGTH_LONG).show()
                        }
                    }
                })
        }

    }

    public fun resetpass(view: View) {
        startActivity(Intent(this@MainActivity, reset::class.java))
    }

}
