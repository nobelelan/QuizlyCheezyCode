package com.parulson.quizlycheezycode.utils


object ColorPicker {

    val colors = arrayOf("#f689b9", "#d85567", "#7e0c79", "#eb6639", "#d08f28", "#15f1ca")

    var currentColorIndex = 0

    fun getColor(): String {
        currentColorIndex = (currentColorIndex + 1) % colors.size
        return colors[currentColorIndex]
    }
}