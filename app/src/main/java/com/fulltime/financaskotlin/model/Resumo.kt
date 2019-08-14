package com.fulltime.financaskotlin.model

import java.math.BigDecimal

class Resumo(val transacoes: List<Transacao>) {

    val despesas get() = soma(Tipo.DESPESA)

    val receitas get() = soma(Tipo.RECEITA)

    val total get() = receitas.subtract(despesas)

    private fun soma(tipo: Tipo): BigDecimal {
        val somaDeTransacoesPeloTipo = transacoes
            .filter { it.tipo == tipo }
            .sumByDouble { it.valor.toDouble() }
        return BigDecimal(somaDeTransacoesPeloTipo)
    }
}
