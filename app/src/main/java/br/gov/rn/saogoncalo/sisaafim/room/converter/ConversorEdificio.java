package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.Edificio;

public class ConversorEdificio {
    @TypeConverter
    public String paraString(Edificio value){return value.name();}

    @TypeConverter
    public Edificio paraEdificio(String value){
        return Edificio.valueOf(value);
    }
}
