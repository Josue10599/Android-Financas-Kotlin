package com.fulltime.financaskotlin.ui.dialog


import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.fulltime.financaskotlin.R
import com.fulltime.financaskotlin.delegate.OnSaveTransacaoDelegate
import com.fulltime.financaskotlin.extension.formataData
import com.fulltime.financaskotlin.model.Tipo
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.text.DateFormat
import java.util.*

abstract class FormularioTransacaoDialog(
    private val context: Context,
    private val viewGroup: ViewGroup?,
    private val tipo: Tipo
) {

    private val formTransacao = createLayout()
    protected val spinnerCategoria = formTransacao.form_transacao_categoria
    protected val textInputEditTextValor = formTransacao.form_transacao_valor
    protected val textInputEditTextData = formTransacao.form_transacao_data
    protected abstract val textoBotaoPositivo: String

    fun openDialog(onSaveTransacao: OnSaveTransacaoDelegate) {
        when (tipo) {
            Tipo.RECEITA -> builderFormTransacoes(onSaveTransacao)
            Tipo.DESPESA -> builderFormTransacoes(onSaveTransacao)
        }
    }

    abstract fun builderFormTransacoes(onSaveTransacao: OnSaveTransacaoDelegate)

    protected fun configuraFormulario(onSaveTransacao: OnSaveTransacaoDelegate) {
        AlertDialog.Builder(context)
            .setTitle(getTitle(tipo))
            .setView(formTransacao)
            .setPositiveButton(textoBotaoPositivo) { _, _ -> salvaTransacao(onSaveTransacao) }
            .setNegativeButton(context.getString(R.string.cancel), null)
            .show()
    }

    private fun salvaTransacao(onSaveTransacao: OnSaveTransacaoDelegate) {
        try {
            retornaTransacao(onSaveTransacao)
        } catch (exception: Exception) {
            Toast.makeText(context, R.string.erro_add_transacao, Toast.LENGTH_SHORT).show()
        }
    }

    protected abstract fun retornaTransacao(onSaveTransacao: OnSaveTransacaoDelegate)

    protected fun configuraEditTextData(dataAtual: Calendar) {
        with(textInputEditTextData) {
            setText(dataAtual.time.formataData())
            setOnClickListener { showCalendarDialog(this, dataAtual) }
        }
    }

    protected abstract fun getTitle(tipo: Tipo): Int

    private fun createLayout() = LayoutInflater.from(context).inflate(R.layout.form_transacao, viewGroup, false)

    protected fun getArray() =
        if (tipo == Tipo.DESPESA) R.array.categorias_de_despesa else R.array.categorias_de_receita

    protected fun getData() = DateFormat.getDateInstance().parse(textInputEditTextData.text.toString())

    protected fun getCategoria() = spinnerCategoria.selectedItem.toString()

    protected fun getValor() = BigDecimal(textInputEditTextValor.text.toString())

    protected fun configuraSpinnerCategoria() {
        spinnerCategoria.adapter =
            ArrayAdapter.createFromResource(context, getArray(), android.R.layout.simple_spinner_dropdown_item)
    }

    private fun showCalendarDialog(setData: EditText, dataAtual: Calendar) {
        DatePickerDialog(
            context,
            { _, ano, mes, dia -> colocaDataSelecionada(ano, mes, dia, setData) },
            dataAtual.get(Calendar.YEAR), dataAtual.get(Calendar.MONTH), dataAtual.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun colocaDataSelecionada(ano: Int, mes: Int, dia: Int, setData: EditText) {
        val dataSelecionada = Calendar.getInstance()
        dataSelecionada.set(ano, mes, dia)
        setData.setText(dataSelecionada.time.formataData())
    }
}