package com.example.exam.temy

import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.*
import com.example.exam.R
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class temyTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temy_two)
        val context = applicationContext
        FirebaseApp.initializeApp(context)
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("temy").document("yxrvOIstQ6mwYaH1Z2uI")

        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                val yourText = document.getString("temyTwo")
                // здесь вы можете использовать ваш текст
                val txt = findViewById<TextView>(R.id.textTextView)
                txt.text = yourText

            } else {
                Log.d(ContentValues.TAG, "No such document")
            }
        }.addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }


        // Получаем ссылку на видео из Firebase
        val storageReference = FirebaseStorage.getInstance().getReference("/video/Разбор ОГЭ по информатике на 5 за 20 минут _ Информатика Умскул ОГЭ.mp4")
        storageReference.downloadUrl.addOnSuccessListener { videoUri ->

            // Устанавливаем URI видео в VideoView
            val videoView = findViewById<VideoView>(R.id.video_view)
            videoView.setVideoURI(videoUri)

            // Создаем и устанавливаем MediaController
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)
            videoView.setMediaController(mediaController)

            // Устанавливаем обработчик событий VideoView
            videoView.setOnPreparedListener { mediaPlayer ->
                // Устанавливаем размеры VideoView в соответствии с размерами видео
                val videoWidth = mediaPlayer.videoWidth
                val videoHeight = mediaPlayer.videoHeight
                videoView.layoutParams.width = videoWidth
                videoView.layoutParams.height = videoHeight
                videoView.requestLayout()

                // Запускаем воспроизведение видео
                videoView.start()
            }

            // Обработка ошибок VideoView
            videoView.setOnErrorListener { mediaPlayer, _, _ ->
                mediaPlayer.release() // Освобождаем ресурсы mediaPlayer
                false
            }
        }.addOnFailureListener {
            // Обработка ошибок загрузки ссылки на видео
            Toast.makeText(this, "Не удалось загрузить ссылку на видео", Toast.LENGTH_SHORT).show()
        }

        /*кнопка для скачивания*/
        val storage = Firebase.storage
        val storageRef = storage.reference
        val dowloding = findViewById<Button>(R.id.downloadButton)

        // Добавляем слушатель на клик по кнопке

        val fileRef = storageRef.child("/theory/Encoding and decoding.pdf")
        dowloding.setOnClickListener {
            fileRef.downloadUrl
                .addOnSuccessListener { uri ->
                    val downloadManager =
                        getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    val request = DownloadManager.Request(uri)
/*                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)*/
                    request.setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_DOWNLOADS,
                        "Encoding and decoding.pdf"
                    )
                    downloadManager.enqueue(request)
                }
                .addOnFailureListener {
                    // обработка ошибки
                }
        }
    }
}