package br.gov.rn.saogoncalo.sisaafim.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public abstract class DateUtils {

    public static String formatLocalDateTimeToDatabaseStyle(){
        SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final long timeMillis = System.currentTimeMillis();
        return data.format(timeMillis);
    }
}
