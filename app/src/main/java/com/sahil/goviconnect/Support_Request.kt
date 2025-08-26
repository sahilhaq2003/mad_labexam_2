package com.sahil.goviconnect

import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class Support_Request : AppCompatActivity() {

    private lateinit var spnCategory: Spinner
    private lateinit var spnPriority: Spinner
    private lateinit var etSubject: EditText
    private lateinit var etDescription: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnAttach: ImageButton
    private lateinit var tvAttachmentName: TextView
    private lateinit var btnSubmit: Button
    private lateinit var btnCancel: Button

    private var attachmentUri: Uri? = null

    private val pickImage = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        attachmentUri = uri
        tvAttachmentName.text = if (uri != null) uri.lastPathSegment ?: uri.toString()
        else getString(R.string.support_no_attachment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support_request)
        bindViews()
        setupSpinners()
        setupActions()
    }

    private fun bindViews() {
        spnCategory = findViewById(R.id.spnCategory)
        spnPriority = findViewById(R.id.spnPriority)
        etSubject = findViewById(R.id.etSubject)
        etDescription = findViewById(R.id.etDescription)
        etPhone = findViewById(R.id.etPhone)
        btnAttach = findViewById(R.id.btnAttach)
        tvAttachmentName = findViewById(R.id.tvAttachmentName)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnCancel = findViewById(R.id.btnCancel)
    }

    private fun setupSpinners() {
        ArrayAdapter.createFromResource(
            this, R.array.support_categories, android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnCategory.adapter = it
        }
        ArrayAdapter.createFromResource(
            this, R.array.support_priorities, android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnPriority.adapter = it
        }
    }

    private fun setupActions() {
        btnAttach.setOnClickListener { pickImage.launch("image/*") }
        btnCancel.setOnClickListener {
            clearForm()
            Toast.makeText(this, getString(R.string.support_cleared), Toast.LENGTH_SHORT).show()
        }
        btnSubmit.setOnClickListener {
            if (validate()) {
                Toast.makeText(this, getString(R.string.support_submitted), Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun validate(): Boolean {
        val subject = etSubject.text.toString().trim()
        val desc = etDescription.text.toString().trim()
        if (subject.length < 4) {
            etSubject.error = getString(R.string.support_error_subject)
            etSubject.requestFocus()
            return false
        }
        if (desc.length < 10) {
            etDescription.error = getString(R.string.support_error_desc)
            etDescription.requestFocus()
            return false
        }
        val phone = etPhone.text.toString().trim()
        if (phone.isNotEmpty() && phone.length < 9) {
            etPhone.error = getString(R.string.support_error_phone)
            etPhone.requestFocus()
            return false
        }
        return true
    }

    private fun clearForm() {
        spnCategory.setSelection(0)
        spnPriority.setSelection(1)
        etSubject.text?.clear()
        etDescription.text?.clear()
        etPhone.text?.clear()
        attachmentUri = null
        tvAttachmentName.text = getString(R.string.support_no_attachment)
    }
}
