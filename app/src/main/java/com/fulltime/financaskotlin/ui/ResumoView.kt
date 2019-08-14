package com.fulltime.financaskotlin.ui

import android.view.View
import androidx.core.content.ContextCompat
import com.fulltime.financaskotlin.R
import com.fulltime.financaskotlin.extension.formataValor
import com.fulltime.financaskotlin.model.Resumo
import com.fulltime.financaskotlin.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(
    private val view: View,
    transacoes: List<Transacao>
) {
    private val resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(view.context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(view.context, R.color.despesa)

    fun atualiza() {
        printReceita()
        printDespesa()
        printTotal()
    }

    private fun printDespesa() {
        with(view.resumo_card_despesa) {
            text = resumo.despesas.formataValor()
            setTextColor(corDespesa)
        }
    }

    private fun printReceita() {
        with(view.resumo_card_receita) {
            text = resumo.receitas.formataValor()
            setTextColor(corReceita)
        }
    }

    private fun printTotal() {
        val total = resumo.total
        with(view.resumo_card_total) {
            text = total.formataValor()
            setTextColor(corReceita)
            if (total < BigDecimal.ZERO)
                setTextColor(corDespesa)
        }
    }
}