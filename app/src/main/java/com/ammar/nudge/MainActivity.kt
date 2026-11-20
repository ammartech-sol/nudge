package com.ammar.nudge

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)

        // First launch: no saved profile yet, so go to the profile screen
        val name = getSharedPreferences("profile", MODE_PRIVATE).getString("name", null)
        if (name == null) {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.greetingText).text = "Hi, $name"
    }
}