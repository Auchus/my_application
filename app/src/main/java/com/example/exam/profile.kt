package com.example.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.exam.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class profile : AppCompatActivity() {
    lateinit var binding2: ActivityMainBinding
    companion object{
        lateinit var auth: FirebaseAuth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding2.root)

    }
    fun onClickLogaut(view: View){
        auth.signOut();
        finish()
    }
}