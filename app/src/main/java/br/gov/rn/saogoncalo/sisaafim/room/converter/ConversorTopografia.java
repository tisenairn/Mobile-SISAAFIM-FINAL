package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.Topografia;

public class ConversorTopografia {
    @TypeConverter
    public String paraString(Topografia value){return value.name();}

    @TypeConverter
    public Topografia paraLoteamento(String value){
        return Topografia.valueOf(value);
    }
}
