package br.gov.rn.saogoncalo.sisaafim.asynctask;

import android.os.AsyncTask;

public class BaseAsyncTask<T> extends AsyncTask<Void, Void, T> {

    private final ExecutaListener<T> executaListener;
    private final FinalizadaListener<T> finalizadaListener;

    public BaseAsyncTask(ExecutaListener<T> executaListener,
                         FinalizadaListener<T> finalizadaListener){
        this.executaListener = executaListener;
        this.finalizadaListener = finalizadaListener;
    }

    @Override
    protected T doInBackground(Void... voids) {
        return executaListener.quandoExecuta();
    }

    @Override
    protected void onPostExecute(T result) {
        super.onPostExecute(result);
        finalizadaListener.quandoFinalizada(result);
    }

    public interface  ExecutaListener<T>{
        T quandoExecuta();
    }

    public interface  FinalizadaListener<T>{
        void quandoFinalizada(T t);
    }
}
