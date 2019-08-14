package com.fulltime.financaskotlin.extension

import java.math.BigDecimal
import java.text.DecimalFormat

fun BigDecimal.formataValor(): String {
    return DecimalFormat.getCurrencyInstance().format(this)
}