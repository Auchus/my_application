package com.example.exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exam.databinding.ActivityMainBinding

class HomeAccount : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomMenu.selectedItemId = R.id.home
        binding.bottomMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.test ->{
                    val intent = Intent(this, Quest::class.java)
                    startActivity(intent)
                }
                R.id.temy ->{
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

            }
            true
        }
    }
}