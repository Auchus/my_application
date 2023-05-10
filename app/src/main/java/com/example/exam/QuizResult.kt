package com.example.exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.exam.classModel.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class QuizResult : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)

        val score = intent.getIntExtra("RIGHT_ANSWER_COUNT",0)
        val result = findViewById<TextView>(R.id.resultLabel)
        result.text = getString(R.string.result_score,score)
        val btn = findViewById<Button>(R.id.tryAgainBtn)
        btn.setOnClickListener{
            startActivity(Intent(this@QuizResult, pervyjTest::class.java))
        }
// Get the authenticated user's ID

        val user = FirebaseAuth.getInstance().currentUser
        val userId = user?.uid
// Create a Firebase database reference to the user's results

        val database = Firebase.database
        val myRef = database.getReference("User/$userId/results")

// Create a HashMap with the user's test result

        val userResult = hashMapOf(
            "score" to score
        )
// Store the user's test result in Firebase
        val newResultRef = myRef.push()
        newResultRef.setValue(userResult)


    }
    fun onClickExit(view: View){
        val exitBtn = R.id.exitBtn
        val intent = Intent(this, Quest::class.java)
        startActivity(intent)
    }




}