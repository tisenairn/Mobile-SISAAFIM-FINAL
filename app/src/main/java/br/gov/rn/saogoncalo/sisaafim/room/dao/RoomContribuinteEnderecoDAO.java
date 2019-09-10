package br.gov.rn.saogoncalo.sisaafim.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.ContribuinteEndereco;

@Dao
public interface RoomContribuinteEnderecoDAO {
    @Insert
    Long salva(ContribuinteEndereco contribuinteEndereco);

    @Delete
    void remove(ContribuinteEndereco contribuinteEndereco);

    @Query("SELECT * FROM contribuinteendereco")
    List<ContribuinteEndereco> todos();

    @Update
    void edita(ContribuinteEndereco contribuinteEndereco);

    @Query("SELECT * FROM contribuinteendereco WHERE contribuinteId = :id")
    ContribuinteEndereco buscaPorId(Long id);
}
