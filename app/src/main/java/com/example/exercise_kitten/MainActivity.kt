package com.example.exercise_kitten

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exercise_kitten.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.itemIconTintList = null
        binding.bottomNavigationView.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.page3 -> {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.fragmentContainerView, MenuFrameFragment()).commit()
                }
            }
        }
    }

}