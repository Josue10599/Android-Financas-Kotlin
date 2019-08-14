package com.fulltime.financaskotlin.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class ConverterDecimalFormat {
    @TypeConverter
    fun toBigDecimal(valor: String): BigDecimal = BigDecimal(valor)

    @TypeConverter
    fun toString(valor: BigDecimal): String = valor.toString()
}