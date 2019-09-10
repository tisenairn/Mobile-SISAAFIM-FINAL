package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.TipoNatureza;

public class ConversorTipoNatureza {
    @TypeConverter
    public String toString(TipoNatureza value){return value.name();}

    @TypeConverter
    public TipoNatureza paraTipoNatureza(String value){
            return value != null ? TipoNatureza.valueOf(value) : TipoNatureza.PREDIAL;
    }
}
