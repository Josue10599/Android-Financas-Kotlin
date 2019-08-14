package com.fulltime.financaskotlin.extension

import java.text.DateFormat
import java.util.*

fun Date.formataData(): String{
    return DateFormat.getDateInstance().format(this)
}