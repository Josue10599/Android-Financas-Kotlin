package com.fulltime.financaskotlin.delegate

import com.fulltime.financaskotlin.model.Transacao

// Utilizando Interface
//interface OnSaveTransacaoDelegate {
//    fun saveTransacao(transacao: Transacao)
//}

// Utilizando Expressão Lambda
typealias OnSaveTransacaoDelegate = (Transacao) -> Unit