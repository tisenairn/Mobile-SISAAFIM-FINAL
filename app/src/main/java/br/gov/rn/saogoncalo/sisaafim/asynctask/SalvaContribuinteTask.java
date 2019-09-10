package br.gov.rn.saogoncalo.sisaafim.asynctask;

import android.os.AsyncTask;

import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.Contribuinte;
import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.ContribuinteEndereco;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomContribuinteDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomContribuinteEnderecoDAO;

public class SalvaContribuinteTask extends AsyncTask<Void, Void, Void> {
    private final FinalizadaListener listener;
    private final RoomContribuinteDAO dao;
    private final Contribuinte contribuinte;
    private final ContribuinteEndereco contribuinteEdereco;
    private final RoomContribuinteEnderecoDAO contribuinteEnderecoDAO;

    public SalvaContribuinteTask(RoomContribuinteDAO dao,
                                 RoomContribuinteEnderecoDAO contribuinteEnderecoDAO,
                                 ContribuinteEndereco contribuinteEdereco,
                                 Contribuinte contribuinte,
                                 FinalizadaListener listener) {
        this.dao = dao;
        this.contribuinteEnderecoDAO = contribuinteEnderecoDAO;
        this.contribuinte = contribuinte;
        this.contribuinteEdereco = contribuinteEdereco;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Long contribuinteId = dao.salva(contribuinte);
        contribuinteEdereco.setContribuinteId(contribuinteId);
        contribuinteEnderecoDAO.salva(contribuinteEdereco);
        this.listener.quandoFinalizada();
        return null;
    }

    public interface FinalizadaListener {
        void quandoFinalizada();
    }
}
