package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.UnidadeFederativa;

public class ConversorUnidadeFederativa {
    @TypeConverter
    public String paraString(UnidadeFederativa value){return value.name();}

    @TypeConverter
    public UnidadeFederativa paraLoteamento(String value){
        return UnidadeFederativa.valueOf(value);
    }
}
