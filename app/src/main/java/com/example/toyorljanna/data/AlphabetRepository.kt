package com.example.toyorljanna.data

import android.content.Context
import com.google.gson.Gson
import java.io.IOException

/**
 * Repository class to load alphabet data from JSON file
 */
class AlphabetRepository(private val context: Context) {
    
    /**
     * Load all letters from JSON file
     */
    fun getAllLetters(): List<Letter> {
        return try {
            val jsonString = context.assets.open("alphabets.json").bufferedReader().use { it.readText() }
            val response = Gson().fromJson(jsonString, AlphabetResponse::class.java)
            response.alphabets
        } catch (e: IOException) {
            emptyList()
        }
    }
    
    /**
     * Get letters filtered by language
     */
    fun getLettersByLanguage(language: String): List<Letter> {
        return getAllLetters().filter { it.language == language }
    }
}