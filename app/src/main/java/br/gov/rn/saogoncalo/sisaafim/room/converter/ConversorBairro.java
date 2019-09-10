package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.Bairro;

public class ConversorBairro {
    @TypeConverter
    public String paraString(Bairro value){return value.name();}

    @TypeConverter
    public Bairro paraBairro(String value){
        return Bairro.valueOf(value);
    }
}
