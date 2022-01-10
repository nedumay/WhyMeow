package com.example.whymeow

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.AudioAttributes
import android.media.MediaActionSound
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var soundPool: SoundPool
    private lateinit var assetManager: AssetManager

    private var catSound: Int = 0
    private var dogSound: Int = 0
    private var cowSound: Int = 0
    private var chickenSound: Int = 0
    private var duckSound: Int = 0
    private var sheepSound: Int = 0

    private var StreamId = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val catImage: ImageView = findViewById(R.id.imageCat)
        val dogImage: ImageView = findViewById(R.id.imageDog)
        val cowImage: ImageView = findViewById(R.id.imageCow)
        val chickenImage: ImageView = findViewById(R.id.imageChicken)
        val duckImage: ImageView = findViewById(R.id.imageDuck)
        val sheepImage: ImageView = findViewById(R.id.imageSheep)

        val attributs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setAudioAttributes(attributs).build()

        assetManager = assets
        catSound = loadSound("cat.mp3")
        dogSound = loadSound("dog.mp3")
        cowSound = loadSound("cow.mp3")
        chickenSound = loadSound("chek.mp3")
        duckSound = loadSound("duck.mp3")
        sheepSound = loadSound("sheep.mp3")

        catImage.setOnClickListener{ playSound(catSound)}
        dogImage.setOnClickListener{ playSound(dogSound)}
        cowImage.setOnClickListener{ playSound(cowSound)}
        chickenImage.setOnClickListener{ playSound(chickenSound)}
        duckImage.setOnClickListener{ playSound(duckSound)}
        sheepImage.setOnClickListener { playSound(sheepSound)}

    }

    override fun onPause(){
        super.onPause()
        soundPool.release()

    }

    private fun playSound(sound: Int): Int{

        if (sound > 0){
            StreamId = soundPool.play(sound, 1F, 1F, 1,0,1F)
        }
        return StreamId
    }

    private fun loadSound(fileName: String): Int{
        val afd: AssetFileDescriptor = try {
            application.assets.openFd(fileName)
        } catch (e: IOException){
            e.printStackTrace()
            Log.d("Meow", "Не могу загрузить файл $fileName")
            return  -1
        }
        return soundPool.load(afd, 1)
    }



}