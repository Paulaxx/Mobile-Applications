package com.example.pong

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity

class GameView(context: Context, attributeSet: AttributeSet) :
    SurfaceView(context, attributeSet), SurfaceHolder.Callback
{
    private val thread : GameThread
    private var ballX = 0f
    private var ballY = 0f
    var dx = 5f
    var dy = 5f
    private val SIZE = 100f
    private val PLAYER_LENGTH = 250f
    private val PLAYER_WIDTH = 50f
    private var playerX1: Float = 0f
    private var playerY1: Float = 0f
    private var playerX2: Float = 0f
    private var playerY2: Float = 0f
    private var first: Int = 1
    private var h: Int = 0
    private var points: Int = 0

    init {
        holder.addCallback(this)
        thread = GameThread(holder, this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread.running = true
        thread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        thread.running = false
        thread.join()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        if(first == 1){
            playerX1 = width/2-PLAYER_LENGTH/2
            playerY1 = height-PLAYER_WIDTH-20f
            playerX2 = width/2+PLAYER_LENGTH/2
            playerY2 = height.toFloat()-20f
            first = 0
            h = height
        }

        if (canvas == null) return

        val ballColor = Paint().apply {
            color = ContextCompat.getColor(context, R.color.dark_pink);
        }
        val playerColor = Paint().apply {
            color = ContextCompat.getColor(context, R.color.bright_yellow);
        }

        canvas.drawOval(ballX, ballY, ballX+SIZE, ballY+SIZE, ballColor)
        canvas.drawRect(playerX1, playerY1, playerX2, playerY2, playerColor)
    }

    fun update() {
        ballX += dx
        ballY += dy
        var middleX = ballX + SIZE
        var downY = ballY + SIZE

        if (ballX <=0 || ballX+SIZE>=width)
            dx = -dx
        if (ballY <=0 || ballY+SIZE>=(height-20-PLAYER_WIDTH))
            dy = -dy

        if(downY >= (height-20-PLAYER_WIDTH)){
            if(middleX !in playerX1..playerX2){
                //surfaceDestroyed(holder)
                val myIntent = Intent(context, ScoreActivity::class.java)
                myIntent.putExtra("points", points)
                myIntent.putExtra("level", dx.toInt())
                context.startActivity(myIntent)
            }
            else{
                points ++
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        playerX1 = event!!.x.toFloat()
        playerX2 = playerX1 + PLAYER_LENGTH
        return true
    }
}