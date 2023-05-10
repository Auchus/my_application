package com.example.exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.exam.MainActivity.Companion.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class pervyjTest : AppCompatActivity() {
    private var firebaseDatabase: FirebaseDatabase? = null
    private var database: DatabaseReference? = null
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private var QUIZ_COUNT = 13
    private var uri = "https://stackoverflow.com/questions/45535272/how-to-link-button-with-website-in-android-studio-using-kotlin"
    private  var quizData = mutableListOf(
        mutableListOf("Статья, набранная на компьютере, содержит 20 страниц, на каждой странице 40 строк, в каждой строке 48 символов. В одном из представлений Unicode каждый символ кодируется двумя байтами. Определите информационный объём статьи в Кбайтах в этом варианте представления Unicode.", "74 Кбайт","75 Кбайт", "76 Кбайт" ),
        mutableListOf("На киностудии снимали фильм про шпионов и закодировали сообщение придуманным шифром. В сообщении присутствуют только буквы приведённого фрагмента кодовой таблицы:", "74 Кбайт","75 Кбайт", "76 Кбайт" ),
        mutableListOf("Напишите наименьшее целое число x, для которого истинно высказывание: НЕ(X<=7)И(X<20),", "8","15", "11" ),
        mutableListOf("Определите длину кратчайшего пути между пунктами А и E. Передвигаться можно только по дорогам, протяжённость которых указана в таблице", "8","9", "7" ),
        mutableListOf("У исполнителя Омега две команды, которым присвоены номера:\n" +
                "1. прибавь 3;\n" +
                "2. раздели на b\n" +
                "(b — неизвестное натуральное число; b≥2).\n" +
                "Выполняя первую из них, Омега увеличивает число на экране на 3, а выполняя вторую, делит это число на b. Программа для исполнителя Омега— это последовательность номеров команд. Известно, что программа 11211 переводит число 30 в число 12. Определите значение b.", "6","5", "7" ),
        mutableListOf("Ниже приведена программа, записанная на пяти языках программирования.", "5","7", "3" ),
        mutableListOf("Доступ к файлу table.xls, находящемуся на сервере ofis.com, осуществляется по протоколу ftp. Запишите последовательность кодирующую адрес указанного файла в сети Интернет.", "ftp://ofis.com/table.xls.","ftp://table.xlsofis.com.", "table.xls.com" ),
        mutableListOf("В языке запросов поискового сервера для обозначения логической операции «ИЛИ» используется символ «|», а для логической операции «И» – символ «&».\n" +
                "\n" +
                "В таблице приведены запросы и количество найденных по ним страниц некоторого сегмента сети Интернет.\n\n" +
                "|Запрос | Найдено страниц|\n\n" +
                "Линкор | Корвет = 3320\n" +
                "Линкор & Корвет = 1300\n" +
                "Линкор = 2100", "2520","3400", "2020" ),
        mutableListOf("На рисунке — схема дорог, связывающих города А, Б, В, Г, Д, Е, Ж, И, К. По каждой дороге можно двигаться только в одном направлении, указанном стрелкой. Сколько существует различных путей из города А в город К, проходящих через город Д?", "9","7", "12" ),
        mutableListOf("Среди приведённых ниже трёх чисел, записанных в различных системах счисления, найдите максимальное и запишите его в ответе в десятичной системе счисления. В ответе запишите только число, основание системы счисления указывать не нужно.\n" +
                "50 в 16 системе счисления\n" +
                "106 в 8 системе счисления\n" +
                "1001010 в 2 системе счисления", "80","70", "74" ),
        mutableListOf("В одном из произведений Ф.М.Достоевского, текст которого приведён в подкаталоге Достоевский каталога Проза, присутствует персонаж Мармеладов. С помощью поисковых средств операционной системы и текстового редактора выясните имя этого персонажа.\n" +
                "Выполните задание, распаковав архив на своём устройстве.$uri", "Семен","Иван", "петр" ),
        mutableListOf("Сколько файлов с расширением .doc содержится в подкаталогах каталога Task12? В ответе укажите только число.\n" +
                "Выполните задание, распаковав архив на своём устройстве.$uri", "3","5", "2" ),
        mutableListOf("Откройте файл с данной электронной таблицей (расположение файла Вам сообщат организаторы экзамена). На основании данных, содержащихся в этой таблице, ответьте на два вопроса.\n" +
                "1.Сколько учеников в Северо-Восточном округе (СВ) выбрали в качестве любимого предмета математику? Ответ на этот вопрос запишите в ячейку Н2 таблицы.\n" +
                "2.Каков средний тестовый балл у учеников Южного округа (Ю)? Ответ на этот вопрос запишите в ячейку Н3 таблицы с точностью два знака после запятой.\n" +
                "3.Постройте круговую диаграмму, отображающую соотношение числа участников, сдающих информатику, немецкий язык и обществознание. Левый верхний угол диаграммы разместите вблизи ячейки G6.$uri", "17","525","70")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pervyj_test)
        quizData.shuffle()
        showNextQuiz()


    }


    fun showNextQuiz(){

        val count = findViewById<TextView>(R.id.countLabel)
        count.text = getString(R.string.count_label, quizCount)

        val quiz = quizData[0]
        val txt = findViewById<TextView>(R.id.nazvanie_voprosa)
        txt.text = quiz[0]
        rightAnswer = quiz[1]
        quiz.removeAt(0)
        quiz.shuffle()
        val btn1 = findViewById<Button>(R.id.btn_answer1)
        btn1.text = quiz[0]
        val btn2 = findViewById<Button>(R.id.btn_answer2)
        btn2.text = quiz[1]
        val btn3 = findViewById<Button>(R.id.btn_answer3)
        btn3.text = quiz[2]
        quizData.removeAt(0)

    }

    fun checkAnswer(view: View){
        val answerBtn: Button = findViewById(view.id)
        val btnText = answerBtn.text.toString()

        val alertTitle: String
        if (btnText == rightAnswer){
            alertTitle = "Правильно"
            rightAnswerCount++
        }else{
            alertTitle = "В следующий раз думай лучше"
        }

        AlertDialog.Builder(this).setTitle(alertTitle)
            .setMessage("Ответ: $rightAnswer")
            .setPositiveButton("OK"){dialogInterface, i -> checkQuizCount()}
            .setCancelable(false).show()
    }

    fun checkQuizCount(){
        if (quizCount == QUIZ_COUNT){
            val intent = Intent(this@pervyjTest,QuizResult::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount)
            startActivity(intent)
            val rezult = Intent(this@pervyjTest,HomeAccount::class.java)
            rezult.putExtra("rezultCount", rightAnswerCount)


        }else{
            quizCount++
            showNextQuiz()
        }
    }
}