package com.example.toyorljanna.data

/**
 * Data class representing a letter in the alphabet
 */
data class Letter(
    val id: Int,
    val character: String,
    val language: String
)

/**
 * Data class for parsing JSON response
 */
data class AlphabetResponse(
    val alphabets: List<Letter>
)