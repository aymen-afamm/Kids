package com.example.toyorljanna.ui

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toyorljanna.R
import com.example.toyorljanna.data.AlphabetRepository
import com.example.toyorljanna.data.Letter
import com.example.toyorljanna.utils.TTSHelper

/**
 * Activity for letter tracing with finger
 */
class TracingActivity : AppCompatActivity() {
    
    private lateinit var repository: AlphabetRepository
    private lateinit var ttsHelper: TTSHelper
    private lateinit var tracingView: TracingView
    private lateinit var currentLetterText: TextView
    
    private lateinit var language: String
    private lateinit var letters: List<Letter>
    private var currentIndex = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracing)
        
        // Get data from intent
        language = intent.getStringExtra("language") ?: "arabic"
        val letterId = intent.getIntExtra("letterId", 1)
        
        // Initialize components
        repository = AlphabetRepository(this)
        ttsHelper = TTSHelper(this) {
            // Play sound when TTS is ready
            playCurrentLetterSound()
        }
        
        // Get UI components
        tracingView = findViewById(R.id.tracingView)
        currentLetterText = findViewById(R.id.currentLetter)
        
        // Load letters and find current index
        letters = repository.getLettersByLanguage(language)
        currentIndex = letters.indexOfFirst { it.id == letterId }.takeIf { it >= 0 } ?: 0
        
        setupButtons()
        displayCurrentLetter()
    }
    
    /**
     * Setup button click listeners
     */
    private fun setupButtons() {
        val btnClear = findViewById<Button>(R.id.btnClear)
        val btnRepeatSound = findViewById<Button>(R.id.btnRepeatSound)
        val btnNext = findViewById<Button>(R.id.btnNext)
        
        btnClear.setOnClickListener {
            tracingView.clearDrawing()
        }
        
        btnRepeatSound.setOnClickListener {
            playCurrentLetterSound()
        }
        
        btnNext.setOnClickListener {
            moveToNextLetter()
        }
    }
    
    /**
     * Display current letter
     */
    private fun displayCurrentLetter() {
        if (currentIndex < letters.size) {
            val currentLetter = letters[currentIndex]
            currentLetterText.text = currentLetter.character
        }
    }
    
    /**
     * Play sound for current letter
     */
    private fun playCurrentLetterSound() {
        if (currentIndex < letters.size) {
            val currentLetter = letters[currentIndex]
            ttsHelper.speak(currentLetter.character, currentLetter.language)
        }
    }
    
    /**
     * Move to next letter or finish
     */
    private fun moveToNextLetter() {
        tracingView.clearDrawing()
        currentIndex++
        
        if (currentIndex < letters.size) {
            displayCurrentLetter()
            playCurrentLetterSound()
        } else {
            // All letters completed
            Toast.makeText(this, getString(R.string.great_job), Toast.LENGTH_LONG).show()
            finish()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        ttsHelper.shutdown()
    }
}