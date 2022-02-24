package com.parulson.quizlycheezycode.utils

import com.parulson.quizlycheezycode.R

object IconPicker {

        val icons = arrayOf(R.drawable.ic_icon_1, R.drawable.ic_icon_2, R.drawable.ic_icon_8)

        var currentIcon = 0

        fun getIcon(): Int {
            currentIcon = (currentIcon + 1) % icons.size
            return icons[currentIcon]
        }
}