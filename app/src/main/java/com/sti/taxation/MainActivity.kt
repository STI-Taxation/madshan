package com.sti.taxation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.*
import com.sti.taxation.models.User
import kotlinx.android.synthetic.main.activity_application.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_Customer.setOnClickListener {
            val intent = Intent(this, Customer::class.java)
            startActivity(intent)
        }
        btn_Assessor.setOnClickListener {
            val intent = Intent(this, Assessor::class.java)
            startActivity(intent)
        }
    }
}
