package br.gov.rn.saogoncalo.sisaafim.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.forms.BICadastral;

@Dao
public interface RoomBICadastralDAO {
    @Insert
    void salva(BICadastral biCadastral);

    @Delete
    void remove(BICadastral biCadastral);

    @Query("SELECT * FROM bicadastral")
    List<BICadastral> buscaTodos();

    @Update
    void edita(BICadastral biCadastral);
}
