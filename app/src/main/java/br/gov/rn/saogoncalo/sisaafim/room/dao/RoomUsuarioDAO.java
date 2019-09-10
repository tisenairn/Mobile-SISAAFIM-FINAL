package br.gov.rn.saogoncalo.sisaafim.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.administrative.Usuario;

@Dao
public interface RoomUsuarioDAO {
    @Insert
    Long salva(Usuario usuario);

    @Delete
    void remove(Usuario usuario);

    @Query("SELECT * FROM usuario")
    List<Usuario> buscaTodas();

    @Update
    void edita(Usuario usuario);

    @Query("SELECT * FROM usuario WHERE matricula = :id")
    Usuario buscarPorId(Long id);
}
