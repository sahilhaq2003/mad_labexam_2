package com.sahil.goviconnect

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.widget.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView

class Advisor_Chat : AppCompatActivity() {

    private lateinit var messagesContainer: LinearLayout
    private lateinit var scroll: NestedScrollView
    private lateinit var etMessage: EditText
    private lateinit var btnSend: ImageButton
    private lateinit var btnCall: ImageView
    private lateinit var btnAttach: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advisor_chat)

        messagesContainer = findViewById(R.id.messagesContainer)
        scroll = findViewById(R.id.scrollMessages)
        etMessage = findViewById(R.id.etMessage)
        btnSend = findViewById(R.id.btnSend)
        btnCall = findViewById(R.id.btnCall)
        btnAttach = findViewById(R.id.btnAttach)

        btnSend.setOnClickListener { sendMessage() }

        etMessage.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE) {
                sendMessage()
                true
            } else false
        }

        btnAttach.setOnClickListener {
            Toast.makeText(this, getString(R.string.support_attach), Toast.LENGTH_SHORT).show()
        }

        btnCall.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:0112345678")))
        }
    }

    private fun sendMessage() {
        val text = etMessage.text?.toString()?.trim().orEmpty()
        if (text.isEmpty()) return
        addUserMessage(text)
        etMessage.text?.clear()
        Toast.makeText(this, getString(R.string.advisor_send_toast), Toast.LENGTH_SHORT).show()
        scrollToBottom()
    }

    private fun addUserMessage(text: String) {
        val row = LinearLayout(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).also { it.topMargin = dp(8) }
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.END
        }

        val tv = TextView(this).apply {
            setText(text)
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 14f
            setPadding(dp(12), dp(10), dp(12), dp(10))
            background = getDrawable(R.drawable.bg_bubble_user)
        }

        row.addView(tv)
        messagesContainer.addView(row)
    }

    private fun dp(px: Int): Int =
        (px * resources.displayMetrics.density).toInt()

    private fun scrollToBottom() {
        scroll.post { scroll.fullScroll(View.FOCUS_DOWN) }
    }
}
