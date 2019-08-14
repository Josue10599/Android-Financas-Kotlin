package com.fulltime.financaskotlin.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.fulltime.financaskotlin.R
import com.fulltime.financaskotlin.extension.formataData
import com.fulltime.financaskotlin.extension.formataString
import com.fulltime.financaskotlin.extension.formataValor
import com.fulltime.financaskotlin.model.Tipo
import com.fulltime.financaskotlin.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(private val listaTransacoes: List<Transacao>) : BaseAdapter() {

    private val limite = 14

    override fun getView(position: Int, v: View?, parent: ViewGroup): View {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transacao = listaTransacoes[position]
        configuraCategoria(view, transacao)
        configuraData(view, transacao)
        configuraIcone(transacao, view, context)
        configuraValor(view, transacao, context)
        return view
    }

    private fun configuraValor(view: View, transacao: Transacao, context: Context) {
        with(view.transacao_valor) {
            text = transacao.valor.formataValor()
            setTextColor(corPorTipo(transacao.tipo, context))
        }
    }

    private fun corPorTipo(tipo: Tipo, context: Context): Int {
        if (tipo == Tipo.RECEITA)
            return ContextCompat.getColor(context, R.color.receita)
        return ContextCompat.getColor(context, R.color.despesa)
    }

    private fun iconePorTipo(tipo: Tipo, context: Context): Drawable? {
        if (tipo == Tipo.RECEITA)
            return context.getDrawable(R.drawable.icone_transacao_item_receita)
        return context.getDrawable(R.drawable.icone_transacao_item_despesa)
    }

    private fun configuraIcone(transacao: Transacao, view: View, context: Context) {
        view.transacao_icone.setImageDrawable(iconePorTipo(transacao.tipo, context))
    }

    private fun configuraData(view: View, transacao: Transacao) {
        view.transacao_data.text = transacao.data.formataData()
    }

    private fun configuraCategoria(view: View, transacao: Transacao) {
        view.transacao_categoria.text = transacao.categoria.formataString(limite)
    }

    override fun getItem(position: Int): Any {
        return listaTransacoes[position]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listaTransacoes.size
    }
}