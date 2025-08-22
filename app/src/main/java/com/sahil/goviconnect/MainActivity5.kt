package com.sahil.goviconnect

import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Toast
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
        setContentView(R.layout.activity_main5) // make sure file name matches

        // Bind views
        langDropdown    = findViewById(R.id.langDropdown)
        toggle          = findViewById(R.id.modeToggle)
        usernameLayout  = findViewById(R.id.usernameLayout)
        passwordLayout  = findViewById(R.id.passwordLayout)
        etUsername      = findViewById(R.id.etUsername)
        etPassword      = findViewById(R.id.etPassword)
        btnSignIn       = findViewById(R.id.btnSignIn)
        btnCreateAccount= findViewById(R.id.btnCreateAccount)

        // Language dropdown
        val languages = resources.getStringArray(R.array.languages)
        langDropdown.setAdapter(ArrayAdapter(this, android.R.layout.simple_list_item_1, languages))
        if (langDropdown.text.isNullOrBlank() && languages.isNotEmpty()) {
            langDropdown.setText(languages.first(), false)
        }

        // Default mode = NIC
        if (toggle.checkedButtonId == -1) toggle.check(R.id.btnNic)
        applyMode(toggle.checkedButtonId)

        // Toggle NIC/Phone
        toggle.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (!isChecked) return@addOnButtonCheckedListener
            clearErrors()
            applyMode(checkedId)
        }

        // Submit on keyboard "Done"
        etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                attemptSignIn()
                true
            } else false
        }

        // Sign In
        btnSignIn.setOnClickListener { attemptSignIn() }

        // Create account (stub)
        btnCreateAccount.setOnClickListener {
            Toast.makeText(this, getString(R.string.login_create_account), Toast.LENGTH_SHORT).show()
            // TODO: start SignUp activity
        }
    }

    private fun applyMode(checkedId: Int) {
        if (checkedId == R.id.btnPhone) {
            usernameLayout.hint = getString(R.string.login_hint_phone)
            etUsername.inputType = InputType.TYPE_CLASS_PHONE
        } else {
            usernameLayout.hint = getString(R.string.login_hint_nic)
            etUsername.inputType = InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
        }
        etUsername.setSelection(etUsername.text?.length ?: 0)
    }

    private fun attemptSignIn() {
        clearErrors()

        val isPhoneMode = toggle.checkedButtonId == R.id.btnPhone
        val user = etUsername.text?.toString()?.trim().orEmpty()
        val pass = etPassword.text?.toString().orEmpty()

        val userOk = if (isPhoneMode) isValidPhone(user) else isValidNic(user)
        if (!userOk) {
            usernameLayout.error = if (isPhoneMode)
                getString(R.string.login_hint_phone)
            else
                getString(R.string.login_hint_nic)
        }

        if (pass.isBlank()) {
            passwordLayout.error = getString(R.string.login_hint_password)
        }

        if (userOk && pass.isNotBlank()) {
            Toast.makeText(this, getString(R.string.login_sign_in), Toast.LENGTH_SHORT).show()
            // TODO: navigate to Home/Dashboard
        }
    }

    private fun clearErrors() {
        usernameLayout.error = null
        passwordLayout.error = null
    }

    // Sri Lanka NIC: 9 digits + [VvXx] OR 12 digits
    private fun isValidNic(nic: String): Boolean {
        val old = Regex("^[0-9]{9}[VvXx]$")
        val newer = Regex("^[0-9]{12}$")
        return old.matches(nic) || newer.matches(nic)
    }

    private fun isValidPhone(phone: String): Boolean {
        return Patterns.PHONE.matcher(phone).matches() && phone.length in 10..15
    }
}
