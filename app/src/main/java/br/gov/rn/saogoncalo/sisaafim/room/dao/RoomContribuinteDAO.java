package br.gov.rn.saogoncalo.sisaafim.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.Contribuinte;

@Dao
public interface RoomContribuinteDAO {
    @Insert
    Long salva(Contribuinte contribuinte);

    @Delete
    void remove(Contribuinte contribuinte);

    @Query("SELECT * FROM contribuinte")
    List<Contribuinte> todos();

    @Update
    void edita(Contribuinte contribuinte);

    @Query("SELECT * FROM contribuinte WHERE imovelId = :id")
    Contribuinte buscaPorId(Long id);
}
