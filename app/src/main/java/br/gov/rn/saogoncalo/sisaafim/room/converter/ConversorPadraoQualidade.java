package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.PadraoQualidade;

public class ConversorPadraoQualidade {
    @TypeConverter
    public String paraString(PadraoQualidade value){return value.name();}

    @TypeConverter
    public PadraoQualidade paraPadraoQualidade(String value){
        return PadraoQualidade.valueOf(value);
    }
}
