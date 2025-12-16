package com.example.toyorljanna.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.toyorljanna.R
import com.example.toyorljanna.data.Letter

/**
 * RecyclerView adapter for displaying letters in a grid
 */
class LetterAdapter(
    private val letters: List<Letter>,
    private val onLetterClick: (Letter) -> Unit
) : RecyclerView.Adapter<LetterAdapter.LetterViewHolder>() {
    
    class LetterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val letterText: TextView = itemView.findViewById(R.id.letterText)
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_letter, parent, false)
        return LetterViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val letter = letters[position]
        holder.letterText.text = letter.character
        
        // Set click listener
        holder.itemView.setOnClickListener {
            onLetterClick(letter)
        }
    }
    
    override fun getItemCount(): Int = letters.size
}