package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.Loteamento;

public class ConversorLoteamento {
    @TypeConverter
    public String paraString(Loteamento value){return value.name();}

    @TypeConverter
    public Loteamento paraLoteamento(String value){
        return Loteamento.valueOf(value);
    }
}
