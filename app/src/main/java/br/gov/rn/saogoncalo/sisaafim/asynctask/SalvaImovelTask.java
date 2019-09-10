package br.gov.rn.saogoncalo.sisaafim.asynctask;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;

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
import br.gov.rn.saogoncalo.sisaafim.retrofit.SisaafimRetrofit;
import br.gov.rn.saogoncalo.sisaafim.retrofit.services.BiCadastralService;
import br.gov.rn.saogoncalo.sisaafim.retrofit.services.ContribuinteService;
import br.gov.rn.saogoncalo.sisaafim.retrofit.services.ImovelService;
import br.gov.rn.saogoncalo.sisaafim.room.Database;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomBICadastralDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomBenfeitoriasDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomContribuinteDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomContribuinteEnderecoDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomImovelDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomImovelEnderecoDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomImovelEnderecoLocalizacaoDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomInfoEdificacaoDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomInfoTerrenoDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomInfoUnidadeDAO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalvaImovelTask extends BaseImovelComInfoGeraisTask {

    private final RoomImovelDAO imovelDAO;
    private final RoomImovelEnderecoDAO imovelEnderecoDAO;
    private final RoomImovelEnderecoLocalizacaoDAO imovelEnderecoLocalizacaoDAO;
    private final RoomBenfeitoriasDAO benfeitoriasDAO;
    private final RoomInfoEdificacaoDAO infoEdificacaoDAO;
    private final RoomInfoTerrenoDAO infoTerrenoDAO;
    private final RoomInfoUnidadeDAO infoUnidadeDAO;
    private final RoomContribuinteDAO contribuinteDAO;
    private final RoomContribuinteEnderecoDAO contribuinteEnderecoDAO;
    private final RoomBICadastralDAO biCadastralDAO;

    private Context context;
    private final Imovel imovel;
    private final ImovelEndereco imovelEndereco;
    private final LocalizacaoEndereco imovelEnderecoLocalizacao;

    private final Contribuinte contribuinte;
    private final ContribuinteEndereco contribuinteEdereco;
    private final Benfeitorias benfeitorias;
    private final InfoUnidade infoUnidade;
    private final InfoEdificacao infoEdificacao;
    private final InfoTerreno infoTerreno;

    private final BICadastral biCadastral;

    private final BiCadastralService biCadastralService;
    private final ImovelService imovelService;
    private final ContribuinteService contribuinteService;


    public SalvaImovelTask(Context context, Imovel imovel, LocalizacaoEndereco imovelEnderecoLocalizacao,
                           Contribuinte contribuinte,
                           ContribuinteEndereco contribuinteEdereco,
                           Benfeitorias benfeitorias,
                           InfoTerreno infoTerreno, ImovelEndereco imovelEndereco,
                           InfoEdificacao infoEdificacao,
                           InfoUnidade infoUnidade,
                           BICadastral biCadastral, FinalizadaListener listener
    ) {
        super(listener);
        this.context = context;
        this.imovel = imovel;
        this.imovelEnderecoLocalizacao = imovelEnderecoLocalizacao;
        this.contribuinte = contribuinte;
        this.contribuinteEdereco = contribuinteEdereco;
        this.benfeitorias = benfeitorias;
        this.imovelEndereco = imovelEndereco;
        this.infoEdificacao = infoEdificacao;
        this.infoTerreno = infoTerreno;
        this.infoUnidade = infoUnidade;
        this.biCadastral = biCadastral;

        this.imovelDAO = Database.getInstance(context).getRoomImovelDAO();
        this.imovelEnderecoDAO = Database.getInstance(context).getRoomImovelEnderecoDAO();
        this.imovelEnderecoLocalizacaoDAO = Database.getInstance(context)
                .getRoomImovelEnderecoLocalizacaoDAO();
        this.benfeitoriasDAO = Database.getInstance(context).getBenfeitoriasDAO();
        this.infoEdificacaoDAO = Database.getInstance(context).getRoomInfoEdificacaoDAO();
        this.infoTerrenoDAO = Database.getInstance(context).getRoomInfoTerrenoDAO();
        this.infoUnidadeDAO = Database.getInstance(context).getRoomInfoUnidadeDAO();
        this.contribuinteDAO = Database.getInstance(context).getRoomContribuinteDAO();
        this.contribuinteEnderecoDAO = Database.getInstance(context).getRoomContribuinteEnderecoDAO();
        this.biCadastralDAO = Database.getInstance(context).getRoomBiCadastralDAO();

        biCadastralService = new SisaafimRetrofit().getBiCadastralService();
        imovelService = new SisaafimRetrofit().getImovelService();
        contribuinteService = new SisaafimRetrofit().getContribuinteService();
    }
//    public SalvaImovelTask(RoomImovelDAO imovelDAO, RoomBenfeitoriasDAO benfeitoriasDAO,
//                           RoomInfoEdificacaoDAO infoEnderecoDAO,
//                           RoomImovelEnderecoDAO imovelEnderecoDAO,
//                           RoomImovelEnderecoLocalizacaoDAO enderecoLocalizacaoDAO, RoomInfoTerrenoDAO infoTerrenoDAO,
//                           RoomInfoUnidadeDAO infoUnidadeDAO,
//                           RoomContribuinteDAO contribuinteDAO,
//                           RoomContribuinteEnderecoDAO contribuinteEnderecoDAO,
//                           RoomBICadastralDAO biCadastralDAO, Imovel imovel, LocalizacaoEndereco imovelEnderecoLocalizacao,
//                           Contribuinte contribuinte,
//                           ContribuinteEndereco contribuinteEdereco,
//                           Benfeitorias benfeitorias,
//                           InfoTerreno infoTerreno, ImovelEndereco imovelEndereco,
//                           InfoEdificacao infoEdificacao,
//                           InfoUnidade infoUnidade,
//                           BICadastral biCadastral, FinalizadaListener listener
//    ) {
//        super(listener);
//        this.imovelDAO = imovelDAO;
//        this.imovelEnderecoDAO = imovelEnderecoDAO;
//        this.imovelEnderecoLocalizacaoDAO = enderecoLocalizacaoDAO;
//        this.benfeitoriasDAO = benfeitoriasDAO;
//        this.infoEdificacaoDAO = infoEnderecoDAO;
//        this.infoTerrenoDAO = infoTerrenoDAO;
//        this.infoUnidadeDAO = infoUnidadeDAO;
//        this.contribuinteDAO = contribuinteDAO;
//        this.contribuinteEnderecoDAO = contribuinteEnderecoDAO;
//        this.biCadastralDAO = biCadastralDAO;
//        this.imovel = imovel;
//        this.imovelEnderecoLocalizacao = imovelEnderecoLocalizacao;
//        this.contribuinte = contribuinte;
//        this.contribuinteEdereco = contribuinteEdereco;
//        this.benfeitorias = benfeitorias;
//        this.imovelEndereco = imovelEndereco;
//        this.infoEdificacao = infoEdificacao;
//        this.infoTerreno = infoTerreno;
//        this.infoUnidade = infoUnidade;
//        this.biCadastral = biCadastral;
//
//        biCadastralService = new SisaafimRetrofit().getBiCadastralService();
//        imovelService = new SisaafimRetrofit().getImovelService();
//        contribuinteService = new SisaafimRetrofit().getContribuinteService();
//    }


    @Override
    protected Void doInBackground(Void... voids) {
        // Imovel
        final Long imovelId = imovelDAO.salva(imovel);
        final Long imovelEnderecoId = imovelEnderecoDAO.salva(imovelEndereco);

        imovelEnderecoLocalizacao.setImovelEnderecoId(imovelEnderecoId);
        imovelEnderecoLocalizacaoDAO.salva(imovelEnderecoLocalizacao);

        // Contribuinte
        contribuinte.setImovelId(imovelId);
        Long contribuinteId = contribuinteDAO.salva(contribuinte);
        contribuinteEdereco.setContribuinteId(contribuinteId);
        contribuinteEnderecoDAO.salva(contribuinteEdereco);

        // Benfeitorias
        benfeitorias.setImovelId(imovelId);
        benfeitoriasDAO.salva(benfeitorias);

        // Info do Imovel
        infoEdificacao.setImovelId(imovelId);
        infoEdificacaoDAO.salva(infoEdificacao);

        // Info do Terreno
        infoTerreno.setImovelId(imovelId);
        infoTerrenoDAO.salva(infoTerreno);

        // Info da Unidade
        infoUnidade.setImovelId(imovelId);
        infoUnidadeDAO.salva(infoUnidade);

        // BIC
        biCadastral.setAtivo(true);
        SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy");
        final long timeMillis = System.currentTimeMillis();
        biCadastral.setDataAbertura(data.format(timeMillis));
        biCadastral.setImovelId(imovelId);

        Call<Imovel> imovelCall = imovelService.salvar(imovel);
        imovelCall.enqueue(new Callback<Imovel>() {
            @Override
            public void onResponse(Call<Imovel> call, Response<Imovel> response) {
                if (response.isSuccessful()) {
                    Imovel imovel = response.body();
                    Log.i("++ImovelCall", "onResponse: "+imovel.toString());
                }
            }

            @Override
            public void onFailure(Call<Imovel> call, Throwable t) {
                Log.i("++ImovelCall_Error", "onResponse: "+t.toString());
            }
        });

        contribuinteService.salvar(contribuinte);
        biCadastralService.salvar(biCadastral);
        biCadastralDAO.salva(biCadastral);

        return null;
    }

}
