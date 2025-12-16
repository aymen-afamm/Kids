package com.example.toyorljanna.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toyorljanna.R
import com.example.toyorljanna.data.AlphabetRepository
import com.example.toyorljanna.data.Letter
import com.example.toyorljanna.utils.TTSHelper

/**
 * Activity to display alphabet letters in a grid
 */
class AlphabetActivity : AppCompatActivity() {
    
    private lateinit var repository: AlphabetRepository
    private lateinit var ttsHelper: TTSHelper
    private lateinit var language: String
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alphabet)
        
        // Get language from intent
        language = intent.getStringExtra("language") ?: "arabic"
        
        // Initialize components
        repository = AlphabetRepository(this)
        ttsHelper = TTSHelper(this) {
            // TTS initialized
        }
        
        setupUI()
        loadLetters()
    }
    
    /**
     * Setup UI components
     */
    private fun setupUI() {
        val titleText = findViewById<TextView>(R.id.titleText)
        titleText.text = if (language == "arabic") {
            getString(R.string.arabic_letters)
        } else {
            getString(R.string.french_letters)
        }
    }
    
    /**
     * Load and display letters
     */
    private fun loadLetters() {
        val letters = repository.getLettersByLanguage(language)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        
        // Setup grid layout with 3 columns
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        
        // Setup adapter with click listener
        val adapter = LetterAdapter(letters) { letter ->
            onLetterClick(letter)
        }
        recyclerView.adapter = adapter
    }
    
    /**
     * Handle letter click - play sound and open tracing
     */
    private fun onLetterClick(letter: Letter) {
        // Play letter sound
        ttsHelper.speak(letter.character, letter.language)
        
        // Open tracing activity
        val intent = Intent(this, TracingActivity::class.java)
        intent.putExtra("language", language)
        intent.putExtra("letterId", letter.id)
        startActivity(intent)
    }
    
    override fun onDestroy() {
        super.onDestroy()
        ttsHelper.shutdown()
    }
}