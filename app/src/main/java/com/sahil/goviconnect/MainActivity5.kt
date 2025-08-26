package com.sahil.goviconnect

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity5 : AppCompatActivity() {

    private lateinit var langDropdown: MaterialAutoCompleteTextView
    private lateinit var toggle: MaterialButtonToggleGroup
    private lateinit var usernameLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var etUsername: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnSignIn: MaterialButton
    private lateinit var btnCreateAccount: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)


        langDropdown     = findViewById(R.id.langDropdown)
        toggle           = findViewById(R.id.modeToggle)
        usernameLayout   = findViewById(R.id.usernameLayout)
        passwordLayout   = findViewById(R.id.passwordLayout)
        etUsername       = findViewById(R.id.etUsername)
        etPassword       = findViewById(R.id.etPassword)
        btnSignIn        = findViewById(R.id.btnSignIn)
        btnCreateAccount = findViewById(R.id.btnCreateAccount)


        val languages = resources.getStringArray(R.array.languages)
        langDropdown.setAdapter(ArrayAdapter(this, android.R.layout.simple_list_item_1, languages))
        if (langDropdown.text.isNullOrBlank() && languages.isNotEmpty()) {
            langDropdown.setText(languages.first(), false)
        }


        btnSignIn.setOnClickListener {
            val intent = Intent(this, MainActivity7::class.java)
            startActivity(intent)
            finish()
        }


        btnCreateAccount.setOnClickListener {
            startActivity(Intent(this, MainActivity6::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}
