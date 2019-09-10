package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.TipoImovel;

public class ConversorTipoImovel {
    @TypeConverter
    public String paraString(TipoImovel value){return value.name();}

    @TypeConverter
    public TipoImovel paraTipoImovel(String value){
        return TipoImovel.valueOf(value);
    }
}
