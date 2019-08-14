package com.fulltime.financaskotlin.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.fulltime.financaskotlin.R
import com.fulltime.financaskotlin.database.DataBaseTransacao
import com.fulltime.financaskotlin.database.dao.TransacaoDAO
import com.fulltime.financaskotlin.model.Tipo
import com.fulltime.financaskotlin.model.Transacao
import com.fulltime.financaskotlin.ui.ResumoView
import com.fulltime.financaskotlin.ui.adapter.ListaTransacoesAdapter
import com.fulltime.financaskotlin.ui.dialog.AdicionaTransacaoDialog
import com.fulltime.financaskotlin.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val contextMenuItem = 1

    private val transacoes by lazy { return@lazy transacaoDAO.transacoes }
    private val listaTransacoesAdapter by lazy { ListaTransacoesAdapter(transacoes) }
    private val viewDaActivity by lazy { window.decorView }
    private val viewGroupDaActivity by lazy { viewDaActivity as ViewGroup }
    private val transacaoDAO: TransacaoDAO by lazy {
        DataBaseTransacao.getDatabaseTransacao(applicationContext).transacaoDAO()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        configuraResumo()
        configuraAdapter()
        configuraFloatingActionButtonMenu()
    }

    private fun configuraFloatingActionButtonMenu() {
        lista_transacoes_adiciona_despesa.setOnClickListener { openFormAdicionaTransacao(Tipo.DESPESA) }
        lista_transacoes_adiciona_receita.setOnClickListener { openFormAdicionaTransacao(Tipo.RECEITA) }
    }

    private fun openFormAdicionaTransacao(tipo: Tipo) {
        AdicionaTransacaoDialog(viewGroupDaActivity, this, tipo).openDialog { adicionaTransacao(it) }
        lista_transacoes_adiciona_menu.close(true)
    }

    private fun configuraResumo() {
        ResumoView(viewDaActivity, transacoes).atualiza()
    }

    private fun configuraAdapter() {
        with(lista_transacoes_listview) {
            adapter = listaTransacoesAdapter
            setOnItemClickListener { _, _, posicao, _ -> callAlteraTransacaoDialog(posicao) }
            setOnCreateContextMenuListener { contextMenu, _, _ ->
                contextMenu.add(Menu.NONE, contextMenuItem, Menu.NONE, getString(R.string.delete))
            }
        }
    }

    override fun onContextItemSelected(menuAberto: MenuItem): Boolean {
        val idDoMenu = menuAberto.itemId
        if (idDoMenu == contextMenuItem) {
            val adapterContextMenuInfo = menuAberto.menuInfo as AdapterView.AdapterContextMenuInfo
            val posicaoDoItemPressionado = adapterContextMenuInfo.position
            val transacao = transacoes[posicaoDoItemPressionado]
            transacaoDAO.deletaTransacao(transacao)
            transacoes.remove(transacao)
            listaTransacoesAtualizada()
        }
        return super.onContextItemSelected(menuAberto)
    }

    private fun callAlteraTransacaoDialog(posicao: Int) {
        AlteraTransacaoDialog(viewGroupDaActivity, this, transacoes[posicao])
            .openDialog { alteraTransacao(posicao, it) }
    }

    private fun adicionaTransacao(transacao: Transacao) {
        transacaoDAO.adicionaTransacao(transacao)
        transacoes.add(transacao)
        listaTransacoesAtualizada()
    }

    private fun alteraTransacao(posicao: Int, transacao: Transacao) {
        transacaoDAO.atualizaTransacao(transacao)
        transacoes[posicao] = transacao
        listaTransacoesAtualizada()
    }

    private fun listaTransacoesAtualizada() {
        listaTransacoesAdapter.notifyDataSetChanged()
        configuraResumo()
    }
}