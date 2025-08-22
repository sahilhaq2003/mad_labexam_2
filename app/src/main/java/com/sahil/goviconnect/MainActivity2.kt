package com.sahil.goviconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // NEXT ➜ go to Activity 3
        findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.nextFab)
            .setOnClickListener {
                startActivity(Intent(this, MainActivity3::class.java))
                // optional:
                // overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                // finish() // remove this screen from back stack
            }

        // (optional) Skip goes to Activity 3 too
        findViewById<android.view.View>(R.id.skip)?.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }
    }
}
