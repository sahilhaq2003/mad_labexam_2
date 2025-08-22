package com.sahil.goviconnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        // "Continue to Login" button
        findViewById<android.view.View>(R.id.btnContinueLogin).setOnClickListener {
            startActivity(Intent(this, MainActivity5::class.java))
            // Optional: don't return to onboarding when back is pressed
            finish()
            // Optional: fade transition
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}
