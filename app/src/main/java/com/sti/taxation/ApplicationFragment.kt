package com.sti.taxation

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_application.*


class ApplicationFragment : Fragment() {

    private val TAG = "ApplicationFragment"

    //Connection to Firebase
    private lateinit var mDatabase: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_application, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_submitFinal.setOnClickListener {
            submitForm()
        }

    }

    private fun submitForm() {
        if (!validateForm()) {
            return
        }
        mDatabase = FirebaseDatabase.getInstance().getReference("Applications")
        val currentUserDb = mDatabase.push()
        //Data from previous form
        val bundle = arguments
        val core_subTotal= bundle?.getString("core_subTotal", "0")
        val core_depRate= bundle?.getString("core_depRate", "0")
        val core_accDep= bundle?.getString("core_accDep", "0")
        val addi_subTotal= bundle?.getString("addi_subTotal", "0")
        val addi_Adjustments= bundle?.getString("addi_adjustment", "0")
        val addi_Total= bundle?.getString("addi_total", "0")
        class_one.setText(core_accDep)
        //end of data
         currentUserDb.child("Value Computation").child("Building Core").child("Sub Total")
             .setValue(core_subTotal)
         currentUserDb.child("Value Computation").child("Building Core").child("Accumulated Depreciation")
             .setValue(core_accDep)
         currentUserDb.child("Value Computation").child("Building Core").child("Depreciation Rate")
             .setValue(core_depRate)
         currentUserDb.child("Value Computation").child("Building Core").child("Additional Items")
             .child("Sub Total").setValue(addi_subTotal)
         currentUserDb.child("Value Computation").child("Building Core").child("Additional Items")
             .child("Adjustments").setValue(addi_Adjustments)
         currentUserDb.child("Value Computation").child("Building Core").child("Additional Items")
             .child("Total").setValue(addi_Total)
        //End of adding Building info
        currentUserDb.child("Property Assessment").child("Classification").child("class_one")
            .setValue(class_one.text.toString())
        currentUserDb.child("Property Assessment").child("Classification").child("class_two")
            .setValue(class_two.text.toString())
        currentUserDb.child("Property Assessment").child("Classification").child("class_total")
            .setValue(class_total.text.toString())
        currentUserDb.child("Property Assessment").child("Market Value").child("market_one")
            .setValue(market_one.text.toString())
        currentUserDb.child("Property Assessment").child("Market Value").child("market_two")
            .setValue(market_two.text.toString())
        currentUserDb.child("Property Assessment").child("Market Value").child("market_total")
            .setValue(market_total.text.toString())
        currentUserDb.child("Property Assessment").child("Assessment Level").child("assess_one")
            .setValue(assess_one.text.toString())
        currentUserDb.child("Property Assessment").child("Assessment Level").child("assess_two")
            .setValue(assess_two.text.toString())
        currentUserDb.child("Property Assessment").child("Assessment Level").child("assess_total")
            .setValue(assess_total.text.toString())
        currentUserDb.child("Property Assessment").child("Assessed Value").child("value_one")
            .setValue(value_one.text.toString())
        currentUserDb.child("Property Assessment").child("Assessed Value").child("value_two")
            .setValue(value_two.text.toString())
        currentUserDb.child("Property Assessment").child("Assessed Value").child("value_total")
            .setValue(value_total.text.toString())

        val intent = Intent(this.context, TransactionId::class.java)
        intent.putExtra("transID", currentUserDb.key.toString())
        startActivity(intent)
    }

    private fun validateForm(): Boolean {
        var valid = true

        val classOne = class_one.text.toString()
        if (TextUtils.isEmpty(classOne)) {
            class_one.error = "Required."
            valid = false
        } else {
            class_one.error = null
        }

        val classTwo = class_two.text.toString()
        if (TextUtils.isEmpty(classTwo)) {
            class_two.error = "Required."
            valid = false
        } else {
            class_two.error = null
        }

        val classTotal = class_total.text.toString()
        if (TextUtils.isEmpty(classTotal)) {
            class_total.error = "Required."
            valid = false
        } else {
            class_total.error = null
        }

        val marketOne = market_one.text.toString()
        if (TextUtils.isEmpty(marketOne)) {
            market_one.error = "Required."
            valid = false
        } else {
            market_one.error = null
        }

        val marketTwo = market_two.text.toString()
        if (TextUtils.isEmpty(marketTwo)) {
            market_two.error = "Required."
            valid = false
        } else {
            market_two.error = null
        }

        val marketTotal = market_total.text.toString()
        if (TextUtils.isEmpty(marketTotal)) {
            market_total.error = "Required."
            valid = false
        } else {
            market_total.error = null
        }
        val assessOne = assess_one.text.toString()
        if (TextUtils.isEmpty(assessOne)) {
            assess_one.error = "Required."
            valid = false
        } else {
            assess_one.error = null
        }
        val assessTwo = assess_two.text.toString()
        if (TextUtils.isEmpty(assessTwo)) {
            assess_two.error = "Required."
            valid = false
        } else {
            assess_two.error = null
        }
        val assessTotal = assess_total.text.toString()
        if (TextUtils.isEmpty(assessTotal)) {
            assess_total.error = "Required."
            valid = false
        } else {
            assess_total.error = null
        }
        val valueOne = value_one.text.toString()
        if (TextUtils.isEmpty(valueOne)) {
            value_one.error = "Required."
            valid = false
        } else {
            value_one.error = null
        }
        val valueTwo = value_two.text.toString()
        if (TextUtils.isEmpty(valueTwo)) {
            value_two.error = "Required."
            valid = false
        } else {
            value_two.error = null
        }
        val valueTotal = value_total.text.toString()
        if (TextUtils.isEmpty(valueTotal)) {
            value_total.error = "Required."
            valid = false
        } else {
            value_total.error = null
        }

        return valid
    }


}