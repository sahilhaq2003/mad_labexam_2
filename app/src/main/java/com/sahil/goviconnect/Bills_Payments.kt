package com.sahil.goviconnect

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Bills_Payments : AppCompatActivity() {

    private lateinit var spnType: Spinner
    private lateinit var spnStatus: Spinner
    private lateinit var tvOutstanding: TextView
    private lateinit var tvNextDue: TextView
    private lateinit var tvLastPayment: TextView
    private lateinit var tvToPay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bills_payments)

        spnType = findViewById(R.id.spnType)
        spnStatus = findViewById(R.id.spnStatus)
        tvOutstanding = findViewById(R.id.tvOutstanding)
        tvNextDue = findViewById(R.id.tvNextDue)
        tvLastPayment = findViewById(R.id.tvLastPayment)
        tvToPay = findViewById(R.id.tvToPay)

        ArrayAdapter.createFromResource(
            this, R.array.bill_types, android.R.layout.simple_spinner_item
        ).also { ad ->
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnType.adapter = ad
        }

        ArrayAdapter.createFromResource(
            this, R.array.bill_statuses, android.R.layout.simple_spinner_item
        ).also { ad ->
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnStatus.adapter = ad
        }

        val onFilter = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                Toast.makeText(
                    this@Bills_Payments,
                    getString(R.string.billing_filter_toast, spnType.selectedItem, spnStatus.selectedItem),
                    Toast.LENGTH_SHORT
                ).show()
                // TODO: Filter your list and recompute totals from data source
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        spnType.onItemSelectedListener = onFilter
        spnStatus.onItemSelectedListener = onFilter


        tvOutstanding.text = "Rs. 3,650.00"
        tvNextDue.text = "Sep 10"
        tvLastPayment.text = "Rs. 950.00 (Aug 12)"
        tvToPay.text = getString(R.string.billing_to_pay_fmt, "Rs. 3,650.00")


        findViewById<Button>(R.id.b1Pay).setOnClickListener {
            Toast.makeText(this, getString(R.string.billing_pay_item_toast, "Fertilizer Subsidy Fee"), Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.b2Pay).setOnClickListener {
            Toast.makeText(this, getString(R.string.billing_pay_item_toast, "Crop Insurance Premium"), Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.btnPayAll).setOnClickListener {
            Toast.makeText(this, getString(R.string.billing_pay_all_toast), Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.b1View).setOnClickListener {
            Toast.makeText(this, getString(R.string.billing_view_invoice_toast), Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.b2View).setOnClickListener {
            Toast.makeText(this, getString(R.string.billing_view_invoice_toast), Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.b3View).setOnClickListener {
            Toast.makeText(this, getString(R.string.billing_view_receipt_toast), Toast.LENGTH_SHORT).show()
        }
    }
}
