package com.example.toyorljanna.ui

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Custom view for finger tracing
 */
class TracingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    
    private val paint = Paint().apply {
        color = Color.parseColor("#FF6B6B") // Bright coral color
        strokeWidth = 15f // Thicker for easier finger tracing
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }
    
    private val path = Path()
    
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw a subtle background pattern
        canvas.drawColor(Color.parseColor("#FAFAFA"))
        canvas.drawPath(path, paint)
    }
    
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                // Do nothing, just finish the stroke
            }
        }
        return true
    }
    
    /**
     * Clear the drawing
     */
    fun clearDrawing() {
        path.reset()
        invalidate()
    }
}