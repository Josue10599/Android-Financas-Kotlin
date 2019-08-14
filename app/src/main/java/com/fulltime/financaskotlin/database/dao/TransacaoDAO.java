package com.fulltime.financaskotlin.database.dao;

import androidx.room.*;
import com.fulltime.financaskotlin.model.Transacao;

import java.util.List;

@Dao
public interface TransacaoDAO {

    @Insert
    void adicionaTransacao(Transacao transacao);

    @Delete
    void deletaTransacao(Transacao transacao);

    @Update
    void atualizaTransacao(Transacao transacao);

    @Query("SELECT * FROM Transacao")
    List<Transacao> getTransacoes();
}
