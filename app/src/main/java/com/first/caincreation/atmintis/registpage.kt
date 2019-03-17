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
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class registpage : Activity() {
    private var reg: Button? = null
    private var username: EditText? = null
    private var password: EditText? = null
    private var firebaseAuth: FirebaseAuth? = null
    private var cpassword: EditText? = null
    //private var phone:EditText?.null


    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registpage)
        FirebaseApp.initializeApp(this)


        reg = findViewById(R.id.bu3)
        username = findViewById(R.id.ed1)
        password = findViewById(R.id.ed2)
        cpassword = findViewById(R.id.ed3)
        //phone = findViewById(R.id.ed4)
        firebaseAuth = FirebaseAuth.getInstance()


        reg?.setOnClickListener {
            RegisterNew()
        }


    }

    private fun RegisterNew() {
        var email_text = username?.text.toString().trim()
        var password_text = password?.text.toString().trim()
        var cpassword_text = cpassword?.text.toString().trim()
        // var phon = phone?text.toString().trim()

        if (TextUtils.isEmpty(email_text)) {
            Toast.makeText(applicationContext, "This Field Cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(password_text)) {
            Toast.makeText(applicationContext, "This Field Cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (cpassword_text.equals(password_text)) {
            firebaseAuth?.createUserWithEmailAndPassword(email_text, password_text)
                ?.addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if (task.isSuccessful) {

                            Toast.makeText(applicationContext, "Account Created", Toast.LENGTH_SHORT).show()
                        } else {

                            var error = task.exception?.message
                            Toast.makeText(applicationContext, "error " + error, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
        } else {
            Toast.makeText(applicationContext, "Passwords Not Matched", Toast.LENGTH_SHORT).show()
        }

    }

}





