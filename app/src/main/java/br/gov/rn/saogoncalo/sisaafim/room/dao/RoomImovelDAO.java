package br.gov.rn.saogoncalo.sisaafim.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;

@Dao
public interface RoomImovelDAO {
    @Insert
    Long salva(Imovel imovel);

    @Delete
    void remove(Imovel imovel);

    @Query("SELECT * FROM imovel")
    List<Imovel> buscaTodos();

    @Query("SELECT * FROM imovel WHERE id = :id")
    Imovel buscaPorId(Long id);

    @Update
    void edita(Imovel imovel);
}
