package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.SituacaoTerreno;

public class ConversorSituacaoTerreno {
    @TypeConverter
    public String paraString(SituacaoTerreno value){return value.name();}

    @TypeConverter
    public SituacaoTerreno paraSituacaoTerreno(String value){
        return SituacaoTerreno.valueOf(value);
    }
}
