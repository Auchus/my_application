package com.example.exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam.databinding.ActivityMainBinding
import com.example.exam.temy.temyOne
import com.example.exam.temy.temyTwo
import com.example.exam.temy.teymThree
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView:RecyclerView
    private lateinit var dataList:ArrayList<DataClass>
    lateinit var imageList:Array<Int>
    lateinit var titleList:Array<String>
    private lateinit var searchView: SearchView
    private lateinit var searchList:ArrayList<DataClass>
    lateinit var descList:Array<String>
    lateinit var detailImageList: Array<Int>
    private lateinit var myAdapter: AdapterClass
    lateinit var binding: ActivityMainBinding

    companion object{
        lateinit var auth: FirebaseAuth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomMenu.selectedItemId = R.id.temy
        binding.bottomMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.test ->{
                    val intent = Intent(this, Quest::class.java)
                    startActivity(intent)
                }
                R.id.home ->{
                    val intent2 = Intent(this, HomeAccount::class.java)
                    startActivity(intent2)
                }

            }
            true
        }

        binding.button15.setOnClickListener {
            val intent = Intent(this, temyOne::class.java)
            startActivity(intent)
        }
        binding.button14.setOnClickListener {
            val intent = Intent(this, temyTwo::class.java)
            startActivity(intent)
        }
        binding.button13.setOnClickListener {
            val intent = Intent(this, teymThree::class.java)
            startActivity(intent)
        }




        auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null){
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }


    }



}