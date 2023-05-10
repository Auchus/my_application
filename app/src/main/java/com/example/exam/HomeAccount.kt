package com.example.exam

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import org.checkerframework.common.subtyping.qual.Bottom

class HomeAccount : AppCompatActivity() {

    /*    companion object{
            lateinit var auth: FirebaseAuth
        }*/
    lateinit var  pieChart: PieChart
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_account)
        auth = Firebase.auth
        val email = auth.currentUser?.email
        val displayName = auth.currentUser?.displayName
        findViewById<TextView>(R.id.txt2).text = "Ваши данные " + "\n" + email + "\n" + displayName

        val logaut = findViewById<Button>(R.id.exit)
        auth = FirebaseAuth.getInstance()

        logaut.setOnClickListener{
            auth.signOut()
            Toast.makeText(this,"Выход из аккаунта", Toast.LENGTH_SHORT).show()
            finish()
        }

        // создать график




/*        pieChart = findViewById(R.id.pie_chart)

        val list: ArrayList<PieEntry> = ArrayList()

        list.add(PieEntry(100f,"100"))
        list.add(PieEntry(103f,"102"))
        list.add(PieEntry(104f,"103"))
        list.add(PieEntry(105f,"104"))

        val pieDateSet = PieDataSet(list,"List")
        pieDateSet.setColors(ColorTemplate.COLORFUL_COLORS,255)
        pieDateSet.valueTextSize = 15f
        pieDateSet.valueTextColor = Color.BLACK
        val pieData = PieData(pieDateSet)

        pieChart.data = pieData

        pieChart.description.text = "Pie Chart"
        pieChart.centerText = "List"
        pieChart.animateY(2000)*/


        val score = intent.getIntExtra("RIGHT_ANSWER_COUNT",0)
        val result = getString(R.string.result_score,score)
        val r = findViewById<TextView>(R.id.txtrez)
        r.text = result


    }





}