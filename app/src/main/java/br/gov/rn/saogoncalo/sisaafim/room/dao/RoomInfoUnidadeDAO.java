package br.gov.rn.saogoncalo.sisaafim.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoUnidade;

@Dao
public interface RoomInfoUnidadeDAO {
    @Insert
    Long salva(InfoUnidade infoUnidade);

    @Delete
    void remove(InfoUnidade infoUnidade);

    @Query("SELECT * FROM infounidade")
    List<InfoUnidade> buscaTodos();

    @Update
    void edita(InfoUnidade infoUnidade);

    @Query("SELECT * FROM infounidade WHERE imovelId = :id")
    InfoUnidade buscarPorId(Long id);

}
