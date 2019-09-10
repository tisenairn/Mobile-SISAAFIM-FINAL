package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.TipoUsuario;

public class ConversorTipoUsuario {
    @TypeConverter
    public String paraString(TipoUsuario value){return value.name();}

    @TypeConverter
    public TipoUsuario paraTipoUsuario(String value){
        return TipoUsuario.valueOf(value);
    }
}
