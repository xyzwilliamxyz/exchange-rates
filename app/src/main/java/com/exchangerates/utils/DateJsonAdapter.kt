package com.exchangerates.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

class DateJsonAdapter {

    @ToJson
    fun toJson(value: Date): String {
        return FORMATTER.format(value)
    }

    @FromJson
    fun fromJson(value: String): Date {
        return FORMATTER.parse(value)
    }

    companion object {
        private val FORMATTER = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    }
}
