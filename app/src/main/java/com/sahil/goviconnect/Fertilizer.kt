package com.sahil.goviconnect

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class Fertilizer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fertilizer)


        findViewById<MaterialButton>(R.id.btnApplyFertilizer).setOnClickListener {
            Toast.makeText(this, "Fertilizer Subsidy application started", Toast.LENGTH_SHORT).show()
        }


        findViewById<MaterialButton>(R.id.btnApplyInsurance).setOnClickListener {
            Toast.makeText(this, "Crop Insurance application started", Toast.LENGTH_SHORT).show()

        }

    }
}
