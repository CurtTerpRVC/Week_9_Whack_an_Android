/*
 Name: Curt Terpstra
 Class: CIS-245-OA010 (Spring 2021)
 App: Week 9 Whack an Android
*/
package edu.rockvalleycollege.week9whackanandroid

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    //declare variables
    var y:Float = 0.00F
    var x:Float = 0.00F
    var random = Random()
    var score:Int = 0
    var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //bind objects
        var btnControl = findViewById<Button>(R.id.btnControl)
        var GameBackGround = findViewById<ConstraintLayout>(R.id.GameCanvas)
        var btnImgButton = findViewById<ImageButton>(R.id.imgChuck)
        var txtScore = findViewById<TextView>(R.id.txtScore)

        //set objects offscreen
        btnImgButton.setTranslationX(-300F)
        btnImgButton.setTranslationY(-300F)
        //start invisible
        btnImgButton.visibility = View.INVISIBLE
        btnImgButton.bringToFront()

        //
        btnControl.setOnClickListener{
            if (btnControl.text == "Start"){
                Toast.makeText(this, "Tap Chuck To Score¡", Toast.LENGTH_LONG) .show ()
                btnControl.text = "Stop"
                score = 0
                txtScore.text = "Score: " + score.toString()
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
                timer = Timer()
                timer.schedule(timerTask { changeImage() }, 3000)
                changeImage()
                btnImgButton.visibility = View.VISIBLE
            }else{
                btnImgButton.visibility = View.INVISIBLE
                btnControl.text = "Start"
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
                timer.cancel()
            }

        }

        btnImgButton.setOnClickListener{
            score += 100
            if (score == 1000){
                timer.cancel()
                txtScore.text = "You Have Won!"
                score = 0
                btnControl.text = "Start"
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
                btnImgButton.visibility = View.INVISIBLE
            }else {
                GameBackGround.setBackgroundColor(Color.GREEN)
                val handler = Handler()
                handler.postDelayed(Runnable { GameBackGround.setBackgroundColor(Color.WHITE)}, 250)
                txtScore.text = "Score: " + score.toString()
                }

        }

        GameBackGround.setOnClickListener{
            println("Click")
            score -= 100
            GameBackGround.setBackgroundColor(Color.RED)
            val handler = Handler()
            handler.postDelayed(Runnable { GameBackGround.setBackgroundColor(Color.WHITE)}, 250)
            txtScore.text = "Score: " + score.toString()

            if (score == 0 || score == -100){
                btnImgButton.visibility = View.INVISIBLE
                Toast.makeText(this, "Game Over", Toast.LENGTH_LONG) .show ()
                score = 0
                txtScore.text = "Score: " + score.toString()
                btnImgButton.setTranslationX(-300F)
                btnImgButton.setTranslationY(-300F)
                timer.cancel()
                btnControl.text = "Start"

            }
        }
    }

    fun changeImage(){

        var imgMole = findViewById<ImageButton>(R.id.imgChuck)

        y = ((Math.random () * getScreenHeight()) + 50) .toFloat ()
        x = ((Math.random () * getScreenWidth()) + 50) .toFloat ()
        imgMole.setTranslationX(x)
        imgMole.setTranslationY(y)
        timer.schedule(timerTask { changeImage() }, 1000)
    }
    fun getScreenWidth(): Float {
        return Resources.getSystem().getDisplayMetrics().widthPixels / 1.4F

    }

    fun getScreenHeight(): Float {
        return Resources.getSystem().getDisplayMetrics().heightPixels / 1.4F
    }
}
