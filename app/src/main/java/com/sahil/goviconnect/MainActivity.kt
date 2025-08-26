package com.sahil.goviconnect

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.animation.ObjectAnimator
import android.animation.ValueAnimator

class MainActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private val SPLASH_DELAY = 4000L


    private fun Float.dp(): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, resources.displayMetrics)

    private fun wave(v: View, delay: Long) {
        val amp = 8f.dp()
        ObjectAnimator.ofFloat(v, View.TRANSLATION_Y, 0f, -amp, 0f, amp, 0f).apply {
            duration = 900
            startDelay = delay
            repeatCount = ValueAnimator.INFINITE
        }.start()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        wave(findViewById(R.id.dot1), 0L)
        wave(findViewById(R.id.dot2), 150L)
        wave(findViewById(R.id.dot3), 300L)


        handler.postDelayed({
            startActivity(Intent(this, MainActivity2::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, SPLASH_DELAY)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
