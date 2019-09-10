package br.gov.rn.saogoncalo.sisaafim.room.converter;

import android.arch.persistence.room.TypeConverter;

import br.gov.rn.saogoncalo.sisaafim.enums.Limitacao;

public class ConversorLimitacao {
    @TypeConverter
    public String paraString(Limitacao value){return value.name();}

    @TypeConverter
    public Limitacao paraLimitacao(String value){
        return Limitacao.valueOf(value);
    }
}
