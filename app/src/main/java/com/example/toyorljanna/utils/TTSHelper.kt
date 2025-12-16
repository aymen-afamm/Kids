package com.example.toyorljanna.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

/**
 * Helper class for Text-to-Speech functionality
 */
class TTSHelper(context: Context, private val onInitListener: () -> Unit) {
    
    private var tts: TextToSpeech? = null
    private var isInitialized = false
    
    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                isInitialized = true
                onInitListener()
            }
        }
    }
    
    /**
     * Speak the given text in the specified language
     */
    fun speak(text: String, language: String) {
        if (!isInitialized) return
        
        val locale = when (language) {
            "arabic" -> Locale("ar")
            "french" -> Locale.FRENCH
            else -> Locale.ENGLISH
        }
        
        tts?.language = locale
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
    
    /**
     * Stop speaking and release resources
     */
    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}