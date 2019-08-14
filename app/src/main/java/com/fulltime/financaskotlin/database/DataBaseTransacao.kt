package com.fulltime.financaskotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fulltime.financaskotlin.database.converter.ConverterDate
import com.fulltime.financaskotlin.database.converter.ConverterDecimalFormat
import com.fulltime.financaskotlin.database.converter.ConverterTipo
import com.fulltime.financaskotlin.database.dao.TransacaoDAO
import com.fulltime.financaskotlin.model.Transacao

@Database(entities = [Transacao::class], version = 5)
@TypeConverters(ConverterDate::class, ConverterDecimalFormat::class, ConverterTipo::class)
abstract class DataBaseTransacao : RoomDatabase() {
    abstract fun transacaoDAO(): TransacaoDAO

    companion object {

        private var db: DataBaseTransacao? = null

        fun getDatabaseTransacao(applicatioContext: Context): DataBaseTransacao {
            if (db == null) db = Room.databaseBuilder(applicatioContext, DataBaseTransacao::class.java, "transacao.db").allowMainThreadQueries().build()
            return db!!
        }
    }
}
