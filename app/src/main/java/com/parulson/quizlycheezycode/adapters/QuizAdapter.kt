package com.parulson.quizlycheezycode.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.parulson.quizlycheezycode.activities.QuestionActivity
import com.parulson.quizlycheezycode.databinding.QuizItemBinding
import com.parulson.quizlycheezycode.models.Quiz
import com.parulson.quizlycheezycode.utils.ColorPicker
import com.parulson.quizlycheezycode.utils.IconPicker

class QuizAdapter(val context: Context, val quizzes: List<Quiz>):
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {
    inner class QuizViewHolder (val binding: QuizItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        return QuizViewHolder(QuizItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val currentQuiz = quizzes[position]
        holder.binding.tvQuizTitle.text = currentQuiz.title
        holder.binding.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.binding.ivQuizIcon.setImageResource(IconPicker.getIcon())
        holder.itemView.setOnClickListener{
            Toast.makeText(context, currentQuiz.title, Toast.LENGTH_SHORT).show()

            val intent = Intent(context, QuestionActivity::class.java)
            intent.putExtra("DATE", quizzes[position].title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }
}