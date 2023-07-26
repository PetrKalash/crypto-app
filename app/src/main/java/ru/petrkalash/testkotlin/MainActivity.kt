package ru.petrkalash.testkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.petrkalash.testkotlin.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

        }
    }
}