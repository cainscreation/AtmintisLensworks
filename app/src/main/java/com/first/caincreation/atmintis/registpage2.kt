package com.first.caincreation.atmintis

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class registpage2 : AppCompatActivity() {
    var name: EditText? = null
    var email: EditText? = null
    var contact: EditText? = null
    var cover: EditText? = null
    var total: EditText? = null
    var type: EditText? = null
    var button: Button? = null
    var firebase: FirebaseAuth? = null
    var database: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registpage2)
        FirebaseApp.initializeApp(this)

        name = findViewById<EditText>(R.id.ed1)
        email = findViewById<EditText>(R.id.ed2)
        contact = findViewById<EditText>(R.id.ed3)
        cover = findViewById<EditText>(R.id.ed4)
        total = findViewById<EditText>(R.id.ed5)
        type = findViewById<EditText>(R.id.ed6)
        button = findViewById(R.id.bu1)
        firebase = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference.child("Users").child(firebase?.currentUser!!.uid)
        button?.setOnClickListener {


            save()
        }


    }

    private fun save() {
        var name = name?.text.toString().trim()
        var email = email?.text.toString().trim()
        var contact = contact?.text.toString().trim()
        var cover = cover?.text.toString().trim()
        var total = total?.text.toString().trim()
        var type = type?.text.toString().trim()


        val userinfo = HashMap<String, Any>()
        userinfo.put("Name", name)
        userinfo.put("Email", email)
        userinfo.put("Contact", contact)
        userinfo.put("cover", cover)
        userinfo.put("total", total)
        userinfo.put("type", type)
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(applicationContext, "Field cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Field cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(contact)) {
            Toast.makeText(applicationContext, "Field cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(cover)) {
            Toast.makeText(applicationContext, "Field cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(total)) {
            Toast.makeText(applicationContext, "Field cannot be empty", Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(type)) {
            Toast.makeText(applicationContext, "Field cannot be empty", Toast.LENGTH_SHORT).show()
        } else {
            database?.updateChildren(userinfo)?.addOnCompleteListener(object : OnCompleteListener<Void> {
                override fun onComplete(task: Task<Void>) {
                    if (task.isSuccessful) {

                        Toast.makeText(applicationContext, "Your info is updated", Toast.LENGTH_SHORT).show()
                    } else {

                        var error = task.exception?.message
                        Toast.makeText(applicationContext, "error " + error, Toast.LENGTH_SHORT).show()
                    }

                }


            })
        }
    }


}
