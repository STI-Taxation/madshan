package com.sti.taxation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_assessor.*

class Assessor : AppCompatActivity() {

    val TAG = "Assessor"

    //Connection to Firebase
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assessor)

        //variables that connects to firebase
        database = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        //If Login button is selected..
        //It will authenticate to Firebase either user exist or not.
        btn_login.setOnClickListener({

            val email = input_email.text.toString()
            val pass = input_pass.text.toString()
            signIn(email,pass)
        })

        //if user click register button
        /*txt_create.setOnClickListener({
            showRegister()
        })*/

    }
    private fun validateForm(): Boolean {
        var valid = true

        val email = input_email.text.toString()
        if (TextUtils.isEmpty(email)) {
            input_email.error = "Required."
            valid = false
        } else {
            input_email.error = null
        }

        val password = input_pass.text.toString()
        if (TextUtils.isEmpty(password)) {
            input_pass.error = "Required."
            valid = false
        } else {
            input_pass.error = null
        }

        return valid
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        if (!validateForm()) {
            return
        }

        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // [END_EXCLUDE]
            }

        // [END sign_in_with_email]
    }


    //function to show register layout
    /*fun showRegister(){
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        val fragment = RegisterFragment()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }*/

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {

            val intent = Intent(this, Application::class.java)
            startActivity(intent)

        } else {

        }
    }

}
