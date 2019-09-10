package br.gov.rn.saogoncalo.sisaafim.asynctask;

import android.os.AsyncTask;

import br.gov.rn.saogoncalo.sisaafim.models.forms.BICadastral;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomBICadastralDAO;
import br.gov.rn.saogoncalo.sisaafim.ui.adapter.ListaPendenciaAdapter;

public class RemoveBoletimInscricaoCadrastral extends AsyncTask<Void, Void, Void> {

    private RoomBICadastralDAO dao;
    private ListaPendenciaAdapter adapter;
    private BICadastral biCadastral;

    public RemoveBoletimInscricaoCadrastral(RoomBICadastralDAO dao,
                                            ListaPendenciaAdapter adapter,
                                            BICadastral biCadastral) {
        this.dao = dao;
        this.adapter = adapter;
        this.biCadastral = biCadastral;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.remove(biCadastral);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.remove(biCadastral);
    }
}
