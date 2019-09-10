package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.UtilizacaoImovel;

public class ConversorUtilizacaoImovel {
    @TypeConverter
    public String paraString(UtilizacaoImovel value){return value.name();}

    @TypeConverter
    public UtilizacaoImovel paraLoteamento(String value){
        return UtilizacaoImovel.valueOf(value);
    }
}
