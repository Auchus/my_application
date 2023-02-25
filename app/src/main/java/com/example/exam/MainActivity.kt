package com.example.exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exam.databinding.ActivityMainBinding
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

        binding.bottomMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.test ->{
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.temy ->{
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                R.id.home ->{
                    val intent = Intent(this, HomeAccount::class.java)
                    startActivity(intent)
                }

            }
            true
        }

        imageList = arrayOf(
            R.drawable.binarnyj_kod,
/*            R.drawable.informacionnye_modeli,
            R.drawable.logicheskie_vyrazheniya,
            R.drawable.baza_dannyh,
            R.drawable.kodirovanie_informacii,
            R.drawable.algoritmy,
            R.drawable.elektronnye_tablicy,
            R.drawable.poisk_v_dokumente,
            R.drawable.vychislenie_kolichestva,
            R.drawable.vypolnenie_algoritmov,
            R.drawable.pozicionnye_sistemy,
            R.drawable.preobrazovanie_vyrazhenij,
            R.drawable.rekursivnye_algoritmy,
            R.drawable.obrabotka_informacii,
            R.drawable.programmirovanie_v_elektronnye_tablicy,
            R.drawable.vyigryshnaya_strategiya,
            R.drawable.uslovnye_operatory*/
        )

        titleList = arrayOf(
            "Системы счисления",
           /* "Информационные модели",
            "Логические выражения",
            "База данных",
            "Кодирование информации",
            "Алгоритмы",
            "Электронные таблицы",
            "Поиск в документе",
            "Вычисления количества",
            "Выполнение алгоритмов",
            "Позиционные системы",
            "Преобразование логических выражений",
            "Рекурсивные алгоритмы",
            "Обработка целочисленной информации",
            "Программирование в электронных таблицах",
            "Выиграшная стратегия",
            "Условные операторы"*/
        )

        descList = arrayOf(
            getString(R.string.binarnyj_kod),

        )

        detailImageList = arrayOf(
            R.drawable.baseline_laptop_chromebook_24
        )

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.search)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        searchList = arrayListOf<DataClass>()
        getData()



        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    dataList.forEach{
                        if (it.dataTitle.toLowerCase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(dataList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })
        myAdapter = AdapterClass(searchList)
        recyclerView.adapter = myAdapter
        myAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null){
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }


    }

    private fun getData(){
        for (i in imageList.indices){
            val dataClass = DataClass(imageList[i], titleList[i], descList[i], detailImageList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = AdapterClass(searchList)
    }
}