package com.example.catchthebear

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.catchthebear.databinding.ActivityGameScreenBinding
import kotlin.random.Random

class GameScreen : AppCompatActivity() {

    //sounds
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var tickSound : MediaPlayer

    private lateinit var binding : ActivityGameScreenBinding
    private var runnable: Runnable = Runnable {  }
    private var handler: Handler=Handler()
    private val bearList=ArrayList<ImageView>()
    var difficultTime : Long=0
    var score=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGameScreenBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        //main theme music
        mediaPlayer=MediaPlayer.create(this@GameScreen,R.raw.theme)
        mediaPlayer.setVolume(1f,1f)
        mediaPlayer.start()

        //tick sound effect
        tickSound= MediaPlayer.create(this@GameScreen,R.raw.tick)
        tickSound.setVolume(0.8f,0.8f)

        //get intent
        val intent=intent
        val info=intent.getStringExtra("info")

        //difficulty
        if(info.equals("easy")){
            difficultTime=700
        }
        else if(info.equals("medium")){
            difficultTime=500
        }
        else{
            difficultTime=300
        }

        //ArrayList
        bearList.add(binding.bear1)
        bearList.add(binding.bear2)
        bearList.add(binding.bear3)
        bearList.add(binding.bear4)
        bearList.add(binding.bear5)
        bearList.add(binding.bear6)
        bearList.add(binding.bear7)
        bearList.add(binding.bear8)
        bearList.add(binding.bear9)
        bearList.add(binding.bear10)
        bearList.add(binding.bear11)
        bearList.add(binding.bear12)
        bearList.add(binding.bear13)
        bearList.add(binding.bear14)
        bearList.add(binding.bear15)

        hideImages()

        //CountDownTimer
        object : CountDownTimer(45300,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.timeText.setText("Left Time: "+millisUntilFinished/1000)
            }

            override fun onFinish() {

                mediaPlayer.stop() //main theme music --> stop

                binding.timeText.setText("Time Over!")
                handler.removeCallbacks(runnable)

                for(image in bearList){
                    image.visibility=View.INVISIBLE
                }

                //Alert
                val alert=AlertDialog.Builder(this@GameScreen)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")

                alert.setPositiveButton("Yes"){dialog,which ->
                    //restart
                    val intent=intent
                    finish()
                    startActivity(intent)
                }

                alert.setNegativeButton("No"){dialog,which ->
                    Toast.makeText(this@GameScreen,"Game Over!",Toast.LENGTH_LONG).show()
                }

                alert.show()
            }

        }.start()

    }

    fun tick(view : View){
        score++
        binding.scoreText.setText("Score: $score")

        //sound effect
        tickSound.start()
    }

    fun hideImages() {

        runnable = object : Runnable {
            override fun run() {

                for (image in bearList) {
                    image.visibility = View.INVISIBLE
                }

                var random = Random.nextInt(0, 14)
                bearList[random].visibility = View.VISIBLE

                handler.postDelayed(runnable,difficultTime)
            }
        }
        handler.post(runnable)
    }

    fun backToHome(view : View){
        mediaPlayer.stop() //main theme music --> stop
        val intent= Intent(this@GameScreen,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
