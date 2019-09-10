package br.gov.rn.saogoncalo.sisaafim.asynctask;

import android.os.AsyncTask;

import br.gov.rn.saogoncalo.sisaafim.models.forms.BICadastral;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomBICadastralDAO;

public class SalvaBiCasdastralTask extends AsyncTask<Void, Void, Void> {

    private final FinalizadaListener listener;
    private final RoomBICadastralDAO dao;
    private final BICadastral inscricaoCadastral;


    public SalvaBiCasdastralTask(RoomBICadastralDAO dao,
                                 BICadastral inscricaoCadastral,
                                 FinalizadaListener listener
    ) {
        this.listener = listener;
        this.dao = dao;
        this.inscricaoCadastral = inscricaoCadastral;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.salva(inscricaoCadastral);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoFinalizada();
    }

    public interface FinalizadaListener {
        void quandoFinalizada();
    }
}
