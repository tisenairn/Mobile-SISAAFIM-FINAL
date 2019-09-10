package br.gov.rn.saogoncalo.sisaafim.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.LocalizacaoEndereco;

@Dao
public interface RoomImovelEnderecoLocalizacaoDAO {
    @Insert
    Long salva(LocalizacaoEndereco localizacaoEndereco);

    @Delete
    void remove(LocalizacaoEndereco localizacaoEndereco);

    @Query("SELECT * FROM localizacaoendereco")
    List<LocalizacaoEndereco> buscaTodos();

    @Update
    void edita(LocalizacaoEndereco localizacaoEnderecoImovel);

    @Query("SELECT * FROM localizacaoendereco WHERE imovelEnderecoId = :id")
    LocalizacaoEndereco buscaPorId(Long id);
}
