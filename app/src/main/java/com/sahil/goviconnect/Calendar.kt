package com.sahil.goviconnect

import android.os.Bundle
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class Calendar : AppCompatActivity() {

    private lateinit var selectedDate: TextView
    private lateinit var calendarView: CalendarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        selectedDate = findViewById(R.id.selectedDate)
        calendarView = findViewById(R.id.calendarView)


        selectedDate.text = "Selected: Today"


        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val cal = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            val fmt = SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault())
            selectedDate.text = "Selected: ${fmt.format(cal.time)}"
            Toast.makeText(this, "Date picked: ${fmt.format(cal.time)}", Toast.LENGTH_SHORT).show()
        }


        findViewById<ImageView>(R.id.btnAdd).setOnClickListener {
            Toast.makeText(this, "Add new event (coming soon)", Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageView>(R.id.btnRefresh).setOnClickListener {
            Toast.makeText(this, "Refreshing events…", Toast.LENGTH_SHORT).show()

        }
    }
}
