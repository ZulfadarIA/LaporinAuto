package com.zulfadar.laporinauto.helper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun dateFormater(input: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date: Date = inputFormat.parse(input) ?: return ""

    val outputFormat = SimpleDateFormat("EEE, dd MMM - HH:mm", Locale("id", "ID"))
    return outputFormat.format(date)
}