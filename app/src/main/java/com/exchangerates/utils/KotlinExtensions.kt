package com.exchangerates.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatToTime(): String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(this)
    //Last update: 2019-05-21 21:30:40
}
