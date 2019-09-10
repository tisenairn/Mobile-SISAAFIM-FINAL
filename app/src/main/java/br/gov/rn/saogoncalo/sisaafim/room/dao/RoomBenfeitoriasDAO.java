package br.gov.rn.saogoncalo.sisaafim.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.Benfeitorias;

@Dao
public interface RoomBenfeitoriasDAO {
    @Insert
    Long salva(Benfeitorias benfeitorias);

    @Delete
    void remove(Benfeitorias benfeitorias);

    @Query("SELECT * FROM benfeitorias")
    List<Benfeitorias> buscaTodas();

    @Update
    void edita(Benfeitorias benfeitorias);

    @Query("SELECT * FROM benfeitorias WHERE imovelId = :id")
    Benfeitorias buscarPorId(Long id);
}
