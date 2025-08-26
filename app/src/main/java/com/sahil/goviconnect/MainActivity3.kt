package com.sahil.goviconnect

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


        window.statusBarColor = ContextCompat.getColor(this, R.color.bg_cream)
        WindowInsetsControllerCompat(window, window.decorView)
            .isAppearanceLightStatusBars = true


        findViewById<View?>(R.id.nextFab3)?.setOnClickListener {
            startActivity(Intent(this, MainActivity4::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }


        findViewById<View?>(R.id.prev)?.setOnClickListener { finish() }
    }
}
