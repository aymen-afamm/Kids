package com.example.toyorljanna.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.toyorljanna.R
import com.example.toyorljanna.utils.TTSHelper

/**
 * Activity for children to write words and letters
 */
class WritingActivity : AppCompatActivity() {
    
    private lateinit var ttsHelper: TTSHelper
    private lateinit var writingInput: EditText
    private lateinit var language: String
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing)
        
        // Get language from intent
        language = intent.getStringExtra("language") ?: "arabic"
        
        // Initialize TTS
        ttsHelper = TTSHelper(this) {
            // TTS initialized
        }
        
        setupUI()
    }
    
    /**
     * Setup UI components and listeners
     */
    private fun setupUI() {
        writingInput = findViewById(R.id.writingInput)
        val btnClear = findViewById<Button>(R.id.btnClearText)
        val btnSpeak = findViewById<Button>(R.id.btnSpeakText)
        
        btnClear.setOnClickListener {
            writingInput.text.clear()
        }
        
        btnSpeak.setOnClickListener {
            val text = writingInput.text.toString().trim()
            if (text.isNotEmpty()) {
                ttsHelper.speak(text, language)
            }
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        ttsHelper.shutdown()
    }
}