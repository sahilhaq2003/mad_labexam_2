package com.sahil.goviconnect

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Market_Prices : AppCompatActivity() {

    private lateinit var spnDistrict: Spinner
    private lateinit var spnCrop: Spinner
    private lateinit var btnRefresh: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_prices)

        spnDistrict = findViewById(R.id.spnDistrict)
        spnCrop = findViewById(R.id.spnCrop)
        btnRefresh = findViewById(R.id.btnRefresh)


        ArrayAdapter.createFromResource(
            this, R.array.districts, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnDistrict.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this, R.array.crops, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnCrop.adapter = adapter
        }

        val onSelect = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                reloadData()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spnDistrict.onItemSelectedListener = onSelect
        spnCrop.onItemSelectedListener = onSelect

        btnRefresh.setOnClickListener {
            Toast.makeText(this, getString(R.string.refresh), Toast.LENGTH_SHORT).show()
            reloadData(force = true)
        }
    }

    private fun reloadData(force: Boolean = false) {
        val district = spnDistrict.selectedItem?.toString() ?: "Colombo"
        val crop = spnCrop.selectedItem?.toString() ?: "All Crops"

    }
}
