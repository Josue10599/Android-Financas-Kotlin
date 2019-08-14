package com.fulltime.financaskotlin.database.converter

import androidx.room.TypeConverter
import com.fulltime.financaskotlin.model.Tipo

class ConverterTipo {
    @TypeConverter
    fun toTipo(tipo: Int): Tipo = if (tipo == 0) Tipo.DESPESA else Tipo.RECEITA

    @TypeConverter
    fun toInt(tipo: Tipo): Int = if (tipo == Tipo.DESPESA) 0 else 1
}