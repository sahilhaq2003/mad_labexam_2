package com.sahil.goviconnect

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Health_Tips : AppCompatActivity() {

    private lateinit var spnTopic: Spinner
    private lateinit var spnStage: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_tips)

        spnTopic = findViewById(R.id.spnTopic)
        spnStage = findViewById(R.id.spnStage)

        ArrayAdapter.createFromResource(
            this, R.array.health_topics, android.R.layout.simple_spinner_item
        ).also { ad ->
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnTopic.adapter = ad
        }

        ArrayAdapter.createFromResource(
            this, R.array.growth_stages, android.R.layout.simple_spinner_item
        ).also { ad ->
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnStage.adapter = ad
        }

        val onSelect = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                Toast.makeText(
                    this@Health_Tips,
                    getString(R.string.health_filter_toast, spnTopic.selectedItem, spnStage.selectedItem),
                    Toast.LENGTH_SHORT
                ).show()

            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        spnTopic.onItemSelectedListener = onSelect
        spnStage.onItemSelectedListener = onSelect
    }
}
