package com.example.goviconnect

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sahil.goviconnect.R

class RainWeatherActivity : AppCompatActivity() {

    data class ForecastDay(
        val day: String,
        val icon: String,      // using emoji to avoid assets (e.g., "☀️" "🌦️" "⛅")
        val hi: Int,
        val lo: Int,
        val rainPercent: Int
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rain_weather)

        // Bind basic views
        val tvLocation = findViewById<TextView>(R.id.tvLocation)
        val tvTemperature = findViewById<TextView>(R.id.tvTemperature)
        val tvEmoji = findViewById<TextView>(R.id.tvEmoji)
        val tvCondition = findViewById<TextView>(R.id.tvCondition)
        val tvRainPercent = findViewById<TextView>(R.id.tvRainPercent)
        val tvHumidity = findViewById<TextView>(R.id.tvHumidity)
        val tvWind = findViewById<TextView>(R.id.tvWind)
        val btnRefresh = findViewById<Button>(R.id.btnRefresh)

        // Dummy “current” values (replace with real data later if needed)
        tvLocation.text = getString(R.string.home_weather_location) // e.g., "Colombo • Good for farming"
        tvTemperature.text = "28°"
        tvEmoji.text = "☀️"
        tvCondition.text = getString(R.string.home_weather_summary) // "28°C • Sunny"
        tvRainPercent.text = "10%"
        tvHumidity.text = "64%"
        tvWind.text = "12 km/h"

        btnRefresh.setOnClickListener {
            Toast.makeText(this, getString(R.string.refresh), Toast.LENGTH_SHORT).show()
            // You could randomize dummy values here if you want a visible change
        }

        // 3-day forecast list (no RecyclerView)
        val forecast = listOf(
            ForecastDay(day = "Today", icon = "☀️", hi = 31, lo = 25, rainPercent = 10),
            ForecastDay(day = "Tomorrow", icon = "🌦️", hi = 30, lo = 24, rainPercent = 40),
            ForecastDay(day = "Thu", icon = "⛅", hi = 29, lo = 24, rainPercent = 20)
        )

        val container = findViewById<LinearLayout>(R.id.forecastContainer)
        val inflater = LayoutInflater.from(this)
        container.removeAllViews()

        forecast.forEach { f ->
            val item = inflater.inflate(R.layout.item_forecast_day, container, false)
            item.findViewById<TextView>(R.id.tvDay).text = f.day
            item.findViewById<TextView>(R.id.tvIcon).text = f.icon
            item.findViewById<TextView>(R.id.tvHiLo).text = "${f.hi}° / ${f.lo}°"
            item.findViewById<TextView>(R.id.tvRain).text = "${f.rainPercent}% rain"
            container.addView(item)
        }
    }
}
