package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.EstadoConservacao;

public class ConversorEstadoConservacao {
    @TypeConverter
    public String paraString(EstadoConservacao value){return value.name();}

    @TypeConverter
    public EstadoConservacao paraEstadoConservacao(String value){
        return EstadoConservacao.valueOf(value);
    }
}
