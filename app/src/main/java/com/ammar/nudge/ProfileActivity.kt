package com.ammar.nudge

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val nameLayout = findViewById<TextInputLayout>(R.id.nameLayout)
        val nameInput = findViewById<TextInputEditText>(R.id.nameInput)
        val emailLayout = findViewById<TextInputLayout>(R.id.emailLayout)
        val emailInput = findViewById<TextInputEditText>(R.id.emailInput)
        val continueButton = findViewById<MaterialButton>(R.id.continueButton)
        val cancelButton = findViewById<MaterialButton>(R.id.cancelButton)

        continueButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            var valid = true

            // Name: required, 2 to 30 characters
            if (name.isEmpty()) {
                nameLayout.error = "Please enter your name"
                valid = false
            } else if (name.length < 2) {
                nameLayout.error = "Name is too short"
                valid = false
            } else if (name.length > 30) {
                nameLayout.error = "Name must be 30 characters or less"
                valid = false
            } else {
                nameLayout.error = null
            }

            // Email: optional, but must look right if filled in
            if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailLayout.error = "That email doesn't look right"
                valid = false
            } else {
                emailLayout.error = null
            }

            if (valid) {
                getSharedPreferences("profile", MODE_PRIVATE).edit()
                    .putString("name", name)
                    .putString("email", email)
                    .apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        cancelButton.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Leave setup?")
                .setMessage("Your details won't be saved.")
                .setNegativeButton("Stay", null)
                .setPositiveButton("Leave") { _, _ -> finishAffinity() }
                .show()
        }
    }
}