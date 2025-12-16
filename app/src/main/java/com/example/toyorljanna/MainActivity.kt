package com.example.toyorljanna

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.toyorljanna.ui.AlphabetActivity

/**
 * Home screen with two main buttons for Arabic and French alphabets
 */
class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        setupButtons()
    }
    
    /**
     * Setup button click listeners
     */
    private fun setupButtons() {
        val btnArabic = findViewById<Button>(R.id.btnArabicAlphabet)
        val btnFrench = findViewById<Button>(R.id.btnFrenchAlphabet)
        
        btnArabic.setOnClickListener {
            startAlphabetActivity("arabic")
        }
        
        btnFrench.setOnClickListener {
            startAlphabetActivity("french")
        }
    }
    
    /**
     * Start alphabet activity with specified language
     */
    private fun startAlphabetActivity(language: String) {
        val intent = Intent(this, AlphabetActivity::class.java)
        intent.putExtra("language", language)
        startActivity(intent)
    }
}