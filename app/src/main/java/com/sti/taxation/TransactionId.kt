package com.sti.taxation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_transaction_id.*

class TransactionId : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction_id)
        val intent = intent
        txt_transID.setText(intent.getStringExtra("transID"))
    }
}
