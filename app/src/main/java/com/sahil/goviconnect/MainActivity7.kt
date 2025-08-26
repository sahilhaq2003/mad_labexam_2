package com.sahil.goviconnect

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.goviconnect.RainWeatherActivity

class MainActivity7 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        val searchInput = findViewById<EditText>(R.id.searchInput)
        val searchClear = findViewById<ImageButton>(R.id.searchClear)

        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchClear.visibility = if (!s.isNullOrEmpty()) View.VISIBLE else View.GONE
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        searchClear.setOnClickListener { searchInput.text?.clear() }

        searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Toast.makeText(this, "Searching: ${searchInput.text}", Toast.LENGTH_SHORT).show()
                true
            } else false
        }

        findViewById<CardView>(R.id.btnSubsidy).setOnClickListener {
            startActivity(Intent(this, Fertilizer::class.java))
        }
        findViewById<CardView>(R.id.btnMarket).setOnClickListener {
            startActivity(Intent(this, Market_Prices::class.java))
        }
        // REPLACE these two click handlers:

        findViewById<CardView>(R.id.btnWeather).setOnClickListener {
            startActivity(Intent(this, Weather::class.java))   // was Weather::class.java
        }

        findViewById<LinearLayout>(R.id.svcWeather).setOnClickListener {
            startActivity(Intent(this, RainWeatherActivity::class.java))   // was toast("Rain & Weather")
        }

        findViewById<LinearLayout>(R.id.svcCalendar).setOnClickListener {
            startActivity(Intent(this, com.sahil.goviconnect.Calendar::class.java))
        }


        findViewById<LinearLayout>(R.id.svcSupport).setOnClickListener {
            startActivity(Intent(this, Support_Request::class.java))
        }


        findViewById<LinearLayout>(R.id.svcHealth).setOnClickListener {
            startActivity(Intent(this, Health_Tips::class.java))
        }


        findViewById<LinearLayout>(R.id.svcAdvisor).setOnClickListener {
            startActivity(Intent(this, Advisor_Chat::class.java))
        }

        findViewById<LinearLayout>(R.id.svcBills).setOnClickListener {
            startActivity(Intent(this, Bills_Payments::class.java))
        }


        findViewById<LinearLayout>(R.id.svcOffers).setOnClickListener { toast("Special Offers") }


        findViewById<CardView>(R.id.updateInsurance).setOnClickListener { toast("Insurance") }
        findViewById<CardView>(R.id.updateTech).setOnClickListener { toast("Tech Support") }
        findViewById<CardView>(R.id.updateRelief).setOnClickListener { toast("Relief Fund") }


        findViewById<CardView>(R.id.helpCenter).setOnClickListener {
            startActivity(Intent(this, Support_Request::class.java))
        }

        findViewById<TextView?>(R.id.viewAll)?.setOnClickListener { toast("View all services") }
    }

    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
