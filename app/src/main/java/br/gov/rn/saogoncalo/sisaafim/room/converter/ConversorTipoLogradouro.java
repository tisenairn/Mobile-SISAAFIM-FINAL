package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.TipoLogradouro;

public class ConversorTipoLogradouro {
    @TypeConverter
    public String paraString(TipoLogradouro value){return value.name();}

    @TypeConverter
    public TipoLogradouro paraTipoLogradouro(String value){
        return TipoLogradouro.valueOf(value);
    }
}
