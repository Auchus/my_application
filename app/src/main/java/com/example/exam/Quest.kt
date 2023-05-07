package com.example.exam

import android.app.DownloadManager
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView.ScaleType
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.io.File
import java.io.FileOutputStream

class Quest : AppCompatActivity() {
    private lateinit var storage: FirebaseStorage
    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, pervyjTest::class.java)
            startActivity(intent)
        }

        val imageslider = findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = ArrayList<SlideModel>()


        /*тут будет фотки как решать задания с екселем НУЖНО СДЕЛАТЬ*/
        imageList.add(
            SlideModel(
                "https://theslide.ru/img/tmb/7/632743/43093a0328ae81739cd6316d131bc7d1-800x.jpg",
                "Кратчайшее расстояние"
            )
        )
        imageList.add(
            SlideModel(
                "https://images.wallpaperscraft.ru/image/single/melburn_avstraliia_shtat_viktoriia_97986_1920x1080.jpg",
                "eagver"
            )
        )
        imageList.add(
            SlideModel(
                "https://sportishka.com/uploads/posts/2022-04/thumbs/1650691837_43-sportishka-com-p-melburn-avstraliya-krasivo-foto-53.jpg",
                "aerg"
            )
        )
        imageList.add(
            SlideModel(
                "https://puzzleit.ru/files/puzzles/215/215148/_original.jpg",
                "erg"
            )
        )

        imageslider.setImageList(imageList, ScaleTypes.FIT)

        val storage = Firebase.storage
        val storageRef = storage.reference

        val dowloding = findViewById<Button>(R.id.dowloding)
        // Добавляем слушатель на клик по кнопке

        val fileRef = storageRef.child("/theory/teoria.pdf")
        dowloding.setOnClickListener {
            fileRef.downloadUrl
                .addOnSuccessListener { uri ->
                    val downloadManager =
                        getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    val request = DownloadManager.Request(uri)
/*                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)*/
                    request.setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_DOWNLOADS,
                        "teoria.pdf"
                    )
                    downloadManager.enqueue(request)
                }
                .addOnFailureListener {
                    // обработка ошибки
                }
        }


        val dowlodingImage = findViewById<Button>(R.id.dowlodingImage)
        // Добавляем слушатель на клик по кнопке

        val fileRef2 = storageRef.child("/theory/МетодическоеПособие.pdf")
        dowlodingImage.setOnClickListener {
            fileRef2.downloadUrl
                .addOnSuccessListener { uri ->
                    val downloadManager =
                        getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                    val request = DownloadManager.Request(uri)
/*                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)*/
                    request.setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_DOWNLOADS,
                        "МетодическоеПособие.pdf"
                    )
                    downloadManager.enqueue(request)
                }
                .addOnFailureListener {
                    // обработка ошибки
                }
        }




    }
}