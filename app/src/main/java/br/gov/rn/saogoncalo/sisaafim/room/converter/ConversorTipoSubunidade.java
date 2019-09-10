package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.TipoSubunidade;

public class ConversorTipoSubunidade {
    @TypeConverter
    public String toString(TipoSubunidade value){return value.name();}

    @TypeConverter
    public TipoSubunidade paraTipoNatureza(String value){
        return value != null ? TipoSubunidade.valueOf(value) : TipoSubunidade.ANDAR;
    }
}
