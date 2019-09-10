package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.Patrimonio;

public class ConversorPatrimonio {
    @TypeConverter
    public String paraString(Patrimonio value){return value.name();}

    @TypeConverter
    public Patrimonio paraPatrimonio(String value){
        return Patrimonio.valueOf(value);
    }
}
