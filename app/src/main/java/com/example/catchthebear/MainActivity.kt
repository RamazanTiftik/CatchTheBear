package com.example.catchthebear

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.catchthebear.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
    }

    fun easy(view : View){
        val intent= Intent(applicationContext,GameScreen::class.java)
        intent.putExtra("info","easy")
        startActivity(intent)
    }

    fun medium(view : View){
        val intent= Intent(applicationContext,GameScreen::class.java)
        intent.putExtra("info","medium")
        startActivity(intent)
    }

    fun hard(view : View){
        val intent= Intent(applicationContext,GameScreen::class.java)
        intent.putExtra("info","hard")
        startActivity(intent)
    }

}