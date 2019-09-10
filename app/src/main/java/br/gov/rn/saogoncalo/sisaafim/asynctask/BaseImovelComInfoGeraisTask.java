package br.gov.rn.saogoncalo.sisaafim.asynctask;

import android.os.AsyncTask;

import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.Contribuinte;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.Benfeitorias;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.ImovelEndereco;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoEdificacao;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoTerreno;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoUnidade;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.LocalizacaoEndereco;

public class BaseImovelComInfoGeraisTask extends AsyncTask<Void, Void, Void> {

    private FinalizadaListener listener;

    public BaseImovelComInfoGeraisTask(FinalizadaListener listener) {
        this.listener = listener;
    }

    void vinculaComInfoGerais(Long imovelId, Contribuinte contribuinte,
                              Benfeitorias benfeitorias,
                              ImovelEndereco endereco,
                              InfoEdificacao infoEdificacao,
                              InfoTerreno infoTerreno, InfoUnidade infoUnidade){

        contribuinte.setImovelId(imovelId);
        benfeitorias.setImovelId(imovelId);
        endereco.setImovelId(imovelId);
        infoEdificacao.setImovelId(imovelId);
        infoTerreno.setImovelId(imovelId);
        infoUnidade.setImovelId(imovelId);

    }

    @Override
    protected Void doInBackground(Void... voids) {
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
