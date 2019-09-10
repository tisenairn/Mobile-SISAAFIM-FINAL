package br.gov.rn.saogoncalo.sisaafim.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.forms.BICadastral;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomBICadastralDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomImovelDAO;
import br.gov.rn.saogoncalo.sisaafim.ui.adapter.ListaPendenciaAdapter;

public class BuscaBICasdastralTask extends AsyncTask<Void, Void, List<BICadastral>> {
    private final RoomBICadastralDAO biCadastralDAO;
    private final ListaPendenciaAdapter adapter;

    public BuscaBICasdastralTask(ListaPendenciaAdapter adapter,
                                 RoomBICadastralDAO biCadastralDAO) {
        this.biCadastralDAO = biCadastralDAO;
        this.adapter = adapter;
    }


    @Override
    protected List<BICadastral> doInBackground(Void... voids) {
        final List<BICadastral> biCadastrals = biCadastralDAO.buscaTodos();
        return biCadastrals;
    }

    @Override
    protected void onPostExecute(List<BICadastral> biCadastrals) {
        super.onPostExecute(biCadastrals);
        adapter.atualiza(biCadastrals);
    }
}
