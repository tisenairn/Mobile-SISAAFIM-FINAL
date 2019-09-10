package br.gov.rn.saogoncalo.sisaafim.utils;

import android.support.v7.widget.AppCompatSpinner;
import android.widget.TextView;

import java.util.Objects;

public abstract class PegarValorComponentes {

    public static String getStringForEditText(android.support.v7.widget.AppCompatEditText mEtLatitude) {
        return Objects.requireNonNull(mEtLatitude.getText()).toString();
    }

    public static String getStringForTextView(TextView mTvInscricaoAnterior) {
        return mTvInscricaoAnterior.getText().toString();
    }

    public static String getStringForSpinner(AppCompatSpinner spinner) {
        return spinner.getSelectedItem().toString();
    }
}
