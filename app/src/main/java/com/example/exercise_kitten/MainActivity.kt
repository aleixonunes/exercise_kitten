package com.example.exercise_kitten

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.example.exercise_kitten.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.itemIconTintList = null
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu -> {
                    binding.bottomNavigationView.animate()
                        .translationY(binding.bottomNavigationView.height.toFloat())

                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.animation, R.anim.animation)
                        .add(R.id.fragmentContainerView, MenuFrameFragment())
                        .commit()
                }
            }
            false
        }
    }

}