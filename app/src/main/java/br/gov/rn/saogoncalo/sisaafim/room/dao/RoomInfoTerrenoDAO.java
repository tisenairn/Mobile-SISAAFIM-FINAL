package br.gov.rn.saogoncalo.sisaafim.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoEdificacao;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoTerreno;

@Dao
public interface RoomInfoTerrenoDAO {
    @Insert
    Long salva(InfoTerreno infoTerreno);

    @Delete
    void remove(InfoTerreno infoTerreno);

    @Query("SELECT * FROM infoterreno")
    List<InfoTerreno> buscaTodos();

    @Update
    void edita(InfoTerreno infoTerreno);

    @Query("SELECT * FROM infoterreno WHERE imovelId = :id")
    InfoTerreno buscarPorId(Long id);
}
