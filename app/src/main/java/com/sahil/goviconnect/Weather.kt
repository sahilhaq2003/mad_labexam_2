package com.sahil.goviconnect

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Weather : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_weather)


        findViewById<ImageView>(R.id.btnRefresh).setOnClickListener {
            Toast.makeText(this, "Refreshing forecast…", Toast.LENGTH_SHORT).show()

        }
    }
}
