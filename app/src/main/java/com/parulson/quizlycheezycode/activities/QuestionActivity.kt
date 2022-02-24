package com.parulson.quizlycheezycode.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.parulson.quizlycheezycode.R
import com.parulson.quizlycheezycode.adapters.OptionAdapter
import com.parulson.quizlycheezycode.databinding.ActivityQuestionBinding
import com.parulson.quizlycheezycode.models.Question
import com.parulson.quizlycheezycode.models.Quiz

class QuestionActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuestionBinding

    var quizzes: MutableList<Quiz>? = null
    var questions: MutableMap<String, Question>? = null
    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpFirestore()
        setUpEventListener()
    }

    private fun setUpEventListener() {
        binding.btnPrevious.setOnClickListener {
            index --
            bindViews()
        }
        binding.btnNext.setOnClickListener {
            index ++
            bindViews()
        }
        binding.btnSubmit.setOnClickListener {
            Log.d("FinalQuiz", questions.toString())

            val intent = Intent(this, ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)
        }
    }

    private fun setUpFirestore() {
        val firestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")
        if (date != null){
            firestore.collection("quizly").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty){
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindViews()
                    }

                }
        }

    }

    private fun bindViews() {

        binding.btnPrevious.visibility = View.GONE
        binding.btnNext.visibility = View.GONE
        binding.btnSubmit.visibility = View.GONE

        if(index == 1) {
            binding.btnNext.visibility = View.VISIBLE
        }else if (index == questions!!.size){
            binding.btnSubmit.visibility = View.VISIBLE
            binding.btnPrevious.visibility = View.VISIBLE
        }else{
            binding.btnPrevious.visibility = View.VISIBLE
            binding.btnNext.visibility = View.VISIBLE
        }

        val question = questions!!["question$index"]
        question?.let {
            binding.tvDescription.text = it.description
            val optionAdapter = OptionAdapter(this, it)
            binding.rvOptions.layoutManager = LinearLayoutManager(this)
            binding.rvOptions.adapter = optionAdapter
            binding.rvOptions.setHasFixedSize(true)
        }
    }

}