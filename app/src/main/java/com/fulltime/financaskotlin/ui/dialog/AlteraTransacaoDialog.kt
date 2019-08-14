package com.fulltime.financaskotlin.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.fulltime.financaskotlin.R
import com.fulltime.financaskotlin.delegate.OnSaveTransacaoDelegate
import com.fulltime.financaskotlin.extension.formataData
import com.fulltime.financaskotlin.model.Tipo
import com.fulltime.financaskotlin.model.Transacao
import java.util.*

class AlteraTransacaoDialog(
    viewGroup: ViewGroup,
    private val context: Context,
    private val transacao: Transacao
) : FormularioTransacaoDialog(context, viewGroup, transacao.tipo) {

    override val textoBotaoPositivo: String = context.getString(R.string.change)

    override fun getTitle(tipo: Tipo) = if (tipo == Tipo.RECEITA) R.string.altera_receita else R.string.altera_despesa

    override fun retornaTransacao(onSaveTransacao: OnSaveTransacaoDelegate) {
        transacao.valor = getValor()
        transacao.data = getData()
        transacao.categoria = getCategoria()
        onSaveTransacao(transacao)
    }

    override fun builderFormTransacoes(onSaveTransacao: OnSaveTransacaoDelegate) {
        val data = Calendar.getInstance()
        data.time = transacao.data
        inicializaCampoValor()
        inicializaCampoData()
        inicializaCategoriaSelecionada()
        super.configuraEditTextData(data)
        super.configuraSpinnerCategoria()
        super.configuraFormulario(onSaveTransacao)
    }

    private fun inicializaCampoValor() {
        textInputEditTextValor.setText(String.format(transacao.valor.toString()))
    }

    private fun inicializaCampoData() {
        textInputEditTextData.setText(transacao.data.formataData())
    }

    private fun inicializaCategoriaSelecionada() {
        val itemSelecionado = context.resources.getStringArray(getArray()).indexOf(transacao.categoria)
        spinnerCategoria.setSelection(itemSelecionado)
    }
}
