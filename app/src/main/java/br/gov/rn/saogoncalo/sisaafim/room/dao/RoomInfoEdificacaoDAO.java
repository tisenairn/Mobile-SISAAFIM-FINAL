package br.gov.rn.saogoncalo.sisaafim.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoEdificacao;

@Dao
public interface RoomInfoEdificacaoDAO {
    @Insert
    Long salva(InfoEdificacao infoEdificacao);

    @Delete
    void remove(InfoEdificacao infoEdificacao);

    @Query("SELECT * FROM infoEdificacao")
    List<InfoEdificacao> buscaTodos();

    @Update
    void edita(InfoEdificacao infoEdificacao);

    @Query("SELECT * FROM infoedificacao WHERE imovelId = :id")
    InfoEdificacao buscarPorId(Long id);
}
