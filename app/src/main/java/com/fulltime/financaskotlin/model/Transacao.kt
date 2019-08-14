package com.fulltime.financaskotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.*

@Entity
class Transacao(
    @PrimaryKey (autoGenerate = true) var id: Long? = null,
    var valor: BigDecimal,
    var categoria: String = "Indefinido",
    var data: Date = Calendar.getInstance().time,
    val tipo: Tipo
)