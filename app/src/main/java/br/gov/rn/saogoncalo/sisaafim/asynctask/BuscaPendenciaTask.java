package br.gov.rn.saogoncalo.sisaafim.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.forms.BICadastral;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomBICadastralDAO;
import br.gov.rn.saogoncalo.sisaafim.ui.adapter.ListaPendenciaAdapter;

public class BuscaPendenciaTask extends AsyncTask<Void, Void, List<BICadastral>> {

    private final RoomBICadastralDAO dao;
    private final ListaPendenciaAdapter adapter;

    public BuscaPendenciaTask(RoomBICadastralDAO dao,
                              ListaPendenciaAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<BICadastral> doInBackground(Void... voids) {
        final List<BICadastral> todasPendencias = dao.buscaTodos();
        return todasPendencias;
    }

    @Override
    protected void onPostExecute(List<BICadastral> todasPendencias) {
        super.onPostExecute(todasPendencias);
        adapter.atualiza(todasPendencias);
    }
}
