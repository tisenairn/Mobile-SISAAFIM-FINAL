package br.gov.rn.saogoncalo.sisaafim.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import br.gov.rn.saogoncalo.sisaafim.asynctask.BuscaBICasdastralTask;
import br.gov.rn.saogoncalo.sisaafim.asynctask.RemoveBoletimInscricaoCadrastral;
import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.Contribuinte;
import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.ContribuinteEndereco;
import br.gov.rn.saogoncalo.sisaafim.models.forms.BICadastral;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.Benfeitorias;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.ImovelEndereco;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoEdificacao;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoTerreno;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoUnidade;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.LocalizacaoEndereco;
import br.gov.rn.saogoncalo.sisaafim.room.Database;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomBICadastralDAO;
import br.gov.rn.saogoncalo.sisaafim.ui.activities.FormImovelActivity;
import br.gov.rn.saogoncalo.sisaafim.ui.activities.VisualizarBicActivity;
import br.gov.rn.saogoncalo.sisaafim.ui.adapter.ListaPendenciaAdapter;

import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_BENFEITORIAS;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_BIC;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_CONTRIBUINTE;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_CONTRIBUINTE_ENDERECO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_IMOVEL;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_IMOVEL_ENDERECO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_IMOVEL_ENDERECO_LOCALIZACAO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_INFO_EDIFICACAO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_INFO_TERRENO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_INFO_UNIDADE;

public class ListaPendenciaView {
    private final ListaPendenciaAdapter adapter;
    private final RoomBICadastralDAO dao;
    private final Context context;

    public ListaPendenciaView(Context context) {
        this.context = context;
        this.adapter = new ListaPendenciaAdapter(this.context);
        dao = Database.getInstance(context)
                .getRoomBiCadastralDAO();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo BIC")
                .setMessage("Tem certeza que quer remover o Boletim de Inscrição Cadastral?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    final BICadastral item1 = adapter.getItem(menuInfo.position);
                    remove(item1);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void confirmaEdicao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Editar BIC")
                .setMessage("Tem certeza que quer editar o Boletim de Inscrição Cadastral?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    final BICadastral item1 = adapter.getItem(menuInfo.position);
                    final Intent intent = new Intent(context, FormImovelActivity.class);
                    intent.putExtra(CHAVE_BIC, item1);
                    Log.i("C item1", "confirmaEdicao: "+item1.toString());
                    context.startActivity(intent);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    private void remove(BICadastral biCadastral) {
        new RemoveBoletimInscricaoCadrastral(dao, adapter, biCadastral).execute();
    }

    public void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }

    public void atualizaImovel() {
        new BuscaBICasdastralTask(adapter, dao).execute();
    }

}
