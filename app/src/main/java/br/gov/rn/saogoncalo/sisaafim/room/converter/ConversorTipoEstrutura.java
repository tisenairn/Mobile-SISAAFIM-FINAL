package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.TipoEstrutura;

public class ConversorTipoEstrutura {
    @TypeConverter
    public String paraString(TipoEstrutura value){return value.name();}

    @TypeConverter
    public TipoEstrutura paraTipoEstrutura(String value){
        return TipoEstrutura.valueOf(value);
    }
}
