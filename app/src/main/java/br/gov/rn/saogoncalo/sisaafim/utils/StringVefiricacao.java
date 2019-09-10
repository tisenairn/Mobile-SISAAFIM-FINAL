package br.gov.rn.saogoncalo.sisaafim.utils;

import android.support.v7.widget.AppCompatEditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public abstract class StringVefiricacao {
    public static String removerCaracterEspecial(String string) {
        return string.toUpperCase().replace(" ", "_")
                .replace("Ó", "O")
                .replace("Õ", "O")
                .replace("´", "_")
                .replace("+", "POSITIVO")
                .replace("-", "NEGATIVO")
                .replace("Í", "I")
                .replace("Ç", "C")
                .replace("Ú", "U")
                .replace("Ã", "A");
    }
    public static String formatarStringParaTextView(String string) {
        return string.toUpperCase().replace("_", " ")
                .replace("POSITIVO","+")
                .replace("´","'")
                .replace("NEGATIVO","-");
    }

    public static String removerMascara(String s){
        return s.replace(".", "")
                .replace("_", "")
                .replace("/","")
                .replace("-","");
    }

    public static Double verificarDouble(String s){
        return !s.trim().isEmpty() ? Double.valueOf(s) : 0;
    }

    public static void formatarCep(AppCompatEditText editText) {
        SimpleMaskFormatter smFormatterCep = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher mtWatcherCep = new MaskTextWatcher(editText, smFormatterCep);
        editText.addTextChangedListener(mtWatcherCep);
    }

    public static void formatarRg(AppCompatEditText editText) {
        SimpleMaskFormatter smFormatterCep = new SimpleMaskFormatter("NNN.NNN.NNN");
        MaskTextWatcher mtWatcherCep = new MaskTextWatcher(editText, smFormatterCep);
        editText.addTextChangedListener(mtWatcherCep);
    }

    public static void formatarCnpj(AppCompatEditText editText) {
        SimpleMaskFormatter smFormatterCep = new SimpleMaskFormatter("NN.NNN.NNN/NNNN-NN ");
        MaskTextWatcher mtWatcherCep = new MaskTextWatcher(editText, smFormatterCep);
        editText.addTextChangedListener(mtWatcherCep);
    }
    public static void formatarCnh(AppCompatEditText editText) {
        SimpleMaskFormatter smFormatterCep = new SimpleMaskFormatter("NNNNNNNNNNNNN ");
        MaskTextWatcher mtWatcherCep = new MaskTextWatcher(editText, smFormatterCep);
        editText.addTextChangedListener(mtWatcherCep);
    }

    public static void formatarData(AppCompatEditText editText) {
        SimpleMaskFormatter smFormatterCep = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtWatcherCep = new MaskTextWatcher(editText, smFormatterCep);
        editText.addTextChangedListener(mtWatcherCep);
    }

    public static void formatarTelefone(AppCompatEditText editText) {
        SimpleMaskFormatter smFormatterTelefone = new SimpleMaskFormatter("NNNN-NNNN");
        MaskTextWatcher mtWatcherTelefone = new MaskTextWatcher(editText, smFormatterTelefone);
        editText.addTextChangedListener(mtWatcherTelefone);
    }

    public static void formatarCelular(AppCompatEditText editText) {
        SimpleMaskFormatter smFormatterCelular = new SimpleMaskFormatter("NNNNN-NNNN");
        MaskTextWatcher mtWatcherCelular = new MaskTextWatcher(editText, smFormatterCelular);
        editText.addTextChangedListener(mtWatcherCelular);
    }

    public static void formatarCpf(AppCompatEditText editText) {
        SimpleMaskFormatter smFormatterCelular = new SimpleMaskFormatter("NNN.NNN.NNN_NN");
        MaskTextWatcher mtWatcherCelular = new MaskTextWatcher(editText, smFormatterCelular);
        editText.addTextChangedListener(mtWatcherCelular);
    }
}
