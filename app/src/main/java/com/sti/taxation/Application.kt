package com.sti.taxation

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sti.taxation.models.User
import kotlinx.android.synthetic.main.activity_application.*


@Suppress("UNREACHABLE_CODE")
class Application : AppCompatActivity() {

    val TAG = "Application"
    val manager = supportFragmentManager


    // [START declare_database_ref]
    private lateinit var mDatabase: DatabaseReference
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)
        frame_layout.visibility = View.VISIBLE

        mDatabase = FirebaseDatabase.getInstance().getReference("Users")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val data = dataSnapshot.getValue(User::class.java)
                // ...
                txt_assessor.text = data?.firstName
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        mDatabase.addValueEventListener(postListener)

        // [END post_value_event_listener]
        btn_next.setOnClickListener {
            submitForm()
            frame_layout.visibility = View.GONE
        }


    }


    private fun submitForm() {
        if (!validateForm()) {
            return
        }
        val transaction = getSupportFragmentManager().beginTransaction()
        val fragment = ApplicationFragment()
        val bundle = Bundle()
        bundle.putString("core_subTotal", Core_SubTotal.text.toString())
        bundle.putString("core_accDep", Core_AccDep.text.toString())
        bundle.putString("core_depRate", Core_DepRate.text.toString())
        bundle.putString("addi_subTotal", Addi_SubTotal.text.toString())
        bundle.putString("addi_adjustment", Addi_Adjustment.text.toString())
        bundle.putString("addi_total", Addi_Total.text.toString())
        fragment.arguments = bundle
        transaction.replace(R.id.layout_assessor, fragment)
        transaction.commit()
    }

    private fun validateForm(): Boolean {
        var valid = true

        val core_subTotal = Core_SubTotal.text.toString()
        if (TextUtils.isEmpty(core_subTotal)) {
            Core_SubTotal.error = "Required."
            valid = false
        } else {
            Core_SubTotal.error = null
        }

        val core_depRate = Core_DepRate.text.toString()
        if (TextUtils.isEmpty(core_depRate)) {
            Core_DepRate.error = "Required."
            valid = false
        } else {
            Core_DepRate.error = null
        }

        val core_accDep = Core_AccDep.text.toString()
        if (TextUtils.isEmpty(core_accDep)) {
            Core_AccDep.error = "Required."
            valid = false
        } else {
            Core_AccDep.error = null
        }

        val addi_subTotal = Addi_SubTotal.text.toString()
        if (TextUtils.isEmpty(addi_subTotal)) {
            Addi_SubTotal.error = "Required."
            valid = false
        } else {
            Addi_SubTotal.error = null
        }

        val addi_Adjustments = Addi_Adjustment.text.toString()
        if (TextUtils.isEmpty(addi_Adjustments)) {
            Addi_Adjustment.error = "Required."
            valid = false
        } else {
            Addi_Adjustment.error = null
        }

        val addi_Total = Addi_Total.text.toString()
        if (TextUtils.isEmpty(addi_Total)) {
            Addi_Total.error = "Required."
            valid = false
        } else {
            Addi_Total.error = null
        }

        return valid
    }


}

