package com.fulltime.financaskotlin.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.fulltime.financaskotlin.R
import com.fulltime.financaskotlin.delegate.OnSaveTransacaoDelegate
import com.fulltime.financaskotlin.model.Tipo
import com.fulltime.financaskotlin.model.Transacao
import java.util.*

class AdicionaTransacaoDialog(
    viewGroup: ViewGroup,
    context: Context,
    private val tipo: Tipo
) : FormularioTransacaoDialog(context, viewGroup, tipo) {

    override val textoBotaoPositivo: String = context.getString(R.string.add)

    override fun getTitle(tipo: Tipo) =
        if (tipo == Tipo.RECEITA) R.string.adiciona_receita else R.string.adiciona_despesa

    override fun retornaTransacao(onSaveTransacao: OnSaveTransacaoDelegate) {
        onSaveTransacao(Transacao(valor = getValor(), categoria = getCategoria(), data = getData(), tipo = tipo))
    }

    override fun builderFormTransacoes(onSaveTransacao: OnSaveTransacaoDelegate) {
        super.configuraEditTextData(Calendar.getInstance())
        super.configuraSpinnerCategoria()
        super.configuraFormulario(onSaveTransacao)
    }
}
