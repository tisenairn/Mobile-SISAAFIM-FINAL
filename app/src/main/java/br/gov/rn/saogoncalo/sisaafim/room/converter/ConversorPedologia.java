package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.Pedologia;

public class ConversorPedologia {
    @TypeConverter
    public String paraString(Pedologia value){return value.name();}

    @TypeConverter
    public Pedologia paraPedologia(String value){
        return Pedologia.valueOf(value);
    }
}
