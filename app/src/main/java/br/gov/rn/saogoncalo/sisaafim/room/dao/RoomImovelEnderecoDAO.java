package br.gov.rn.saogoncalo.sisaafim.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.ImovelEndereco;

@Dao
public interface RoomImovelEnderecoDAO {
    @Insert
    Long salva(ImovelEndereco imovelEndereco);

    @Delete
    void remove(ImovelEndereco imovelEndereco);

    @Query("SELECT * FROM imovelendereco")
    List<ImovelEndereco> buscaTodos();

    @Update
    void edita(ImovelEndereco imovelEndereco);

    @Query(("SELECT * FROM imovelendereco WHERE id = :id"))
    ImovelEndereco buscaPorId(Long id);
}
