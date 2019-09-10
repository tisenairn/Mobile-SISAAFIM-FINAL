package br.gov.rn.saogoncalo.sisaafim.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;

import java.io.IOException;
import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.R;
import br.gov.rn.saogoncalo.sisaafim.asynctask.BaseAsyncTask;
import br.gov.rn.saogoncalo.sisaafim.enums.Bairro;
import br.gov.rn.saogoncalo.sisaafim.enums.Edificio;
import br.gov.rn.saogoncalo.sisaafim.enums.Loteamento;
import br.gov.rn.saogoncalo.sisaafim.enums.TipoNatureza;
import br.gov.rn.saogoncalo.sisaafim.enums.TipoSubunidade;
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
import br.gov.rn.saogoncalo.sisaafim.retrofit.services.ImovelService;
import br.gov.rn.saogoncalo.sisaafim.retrofit.SisaafimRetrofit;
import br.gov.rn.saogoncalo.sisaafim.room.Database;
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
import retrofit2.Response;

import static br.gov.rn.saogoncalo.sisaafim.utils.CarregarSpinner.spinners;
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
import static br.gov.rn.saogoncalo.sisaafim.utils.PegarValorComponentes.getStringForEditText;
import static br.gov.rn.saogoncalo.sisaafim.utils.PegarValorComponentes.getStringForSpinner;
import static br.gov.rn.saogoncalo.sisaafim.utils.PegarValorComponentes.getStringForTextView;
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.formatarCep;
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.removerCaracterEspecial;

public class FormImovelActivity extends AppCompatActivity {

    private TextView mTvInscricao;
    private TextView mTvInscricaoAnterior;
    private TextView mTvSequencial;

    private android.support.v7.widget.AppCompatEditText mEtBloco;
    private android.support.v7.widget.AppCompatEditText mEtLogradouro;
    private android.support.v7.widget.AppCompatEditText mEtLote;
    private android.support.v7.widget.AppCompatEditText mEtComplemento;
    private android.support.v7.widget.AppCompatEditText mEtQuadra;
    private android.support.v7.widget.AppCompatEditText mEtCep;
    private android.support.v7.widget.AppCompatEditText mEtNumero;
    private android.support.v7.widget.AppCompatEditText mEtNumeroSubunidade;

    private android.support.v7.widget.AppCompatSpinner mSpBairro;
    private android.support.v7.widget.AppCompatSpinner mSpLoteamento;
    private android.support.v7.widget.AppCompatSpinner mSpSubunidade;
    private android.support.v7.widget.AppCompatSpinner mSpEdificio;
    private android.support.v7.widget.AppCompatSpinner mSpNatureza;

    private android.support.v7.widget.AppCompatEditText mEtLatitude;
    private android.support.v7.widget.AppCompatEditText mEtLongitude;

    private RoomImovelDAO imovelDAO;
    private RoomImovelEnderecoDAO imovelEnderecoDAO;
    private RoomImovelEnderecoLocalizacaoDAO imovelEnderecoLocalizacaoDAO;
    private RoomBenfeitoriasDAO benfeitoriasDAO;
    private RoomInfoEdificacaoDAO infoEdificacaoDAO;
    private RoomInfoTerrenoDAO infoTerrenoDAO;
    private RoomInfoUnidadeDAO infoUnidadeDAO;
    private RoomContribuinteEnderecoDAO contribuinteEnderecoDAO;
    private RoomContribuinteDAO contribuinteDAO;

    private TextView mTvLocalizacao;

    private Imovel imovel = null;
    private ImovelEndereco imovelEndereco = null;
    private LocalizacaoEndereco localizacao = null;

    private Contribuinte contribuinte = null;
    private ContribuinteEndereco contribuinteEndereco = null;

    private Benfeitorias benfeitorias = null;
    private InfoEdificacao infoEdificacao = null;
    private InfoTerreno infoTerreno = null;
    private InfoUnidade infoUnidade = null;

    private String APPTITLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_imovel);
        inicializaComponentes();
        inicializarDAOS();
        definirEventos();

        spinners(this, mSpNatureza, mSpBairro, mSpLoteamento,
                mSpSubunidade, mSpEdificio);

        receberDados();
        receberDadosDaApi();
        setTitle(APPTITLE);
    }

    private void receberDadosDaApi() {
        ImovelService imovelService = new SisaafimRetrofit().getImovelService();
        Call<List<Imovel>> call = imovelService.buscaTodos();

        new BaseAsyncTask<>(() -> {
            try {
                Response<List<Imovel>> response = call.execute();
                return response.body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }, imovels -> {
            if (imovels != null) {
//                Toast.makeText(this, imovels.toString(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Erro ao buscar imóveis", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info_imovel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.menu_form_info_imovel_item_proximo:
                abrirProximaActivity();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void abreFormularioBiCadastral(BICadastral bicEscolhido) {

        imovel = imovelDAO.buscaPorId(bicEscolhido.getImovelId());
        imovelEndereco = imovelEnderecoDAO.buscaPorId(imovel.getId());
        localizacao = imovelEnderecoLocalizacaoDAO.buscaPorId(imovelEndereco.getId());

        contribuinte = contribuinteDAO.buscaPorId(imovel.getId());
        if (contribuinte != null) {
            contribuinteEndereco = contribuinteEnderecoDAO.buscaPorId(contribuinte.getId());
        }

        benfeitorias = benfeitoriasDAO.buscarPorId(imovel.getId());
        infoEdificacao = infoEdificacaoDAO.buscarPorId(imovel.getId());
        infoTerreno = infoTerrenoDAO.buscarPorId(imovel.getId());
        infoUnidade = infoUnidadeDAO.buscarPorId(imovel.getId());

        carregarDados();
    }

    private void carregarDados() {
        carregarImovel();
        carregarEnderecoImovel();
        carregarLocalizacao();
    }

    private void abrirProximaActivity() {
        Intent intent = abrirInfoEdificioActivity();
        if ( isConnected(this)){
//            Toast.makeText(this, "IsConnected? "+isConnected(this), Toast.LENGTH_SHORT).show();
            intent = abrirMapsActivity();
        }

        intent.putExtra(CHAVE_FORM_IMOVEL, pegarImovel());

        intent.putExtra(CHAVE_FORM_IMOVEL_ENDERECO, pegarEnderecoImovel());

        if (!Objects.equal(localizacao, null))
            intent.putExtra(CHAVE_FORM_IMOVEL_ENDERECO_LOCALIZACAO, localizacao);

        if (contribuinte != null) {
            intent.putExtra(CHAVE_FORM_CONTRIBUINTE, contribuinte);
            if (contribuinteEndereco != null) {
                intent.putExtra(CHAVE_FORM_CONTRIBUINTE_ENDERECO, contribuinteEndereco);
            }
        }

        if (!Objects.equal(benfeitorias, null))
            intent.putExtra(CHAVE_BENFEITORIAS, benfeitorias);

        if (!Objects.equal(infoEdificacao, null))
            intent.putExtra(CHAVE_INFO_EDIFICACAO, infoEdificacao);

        if (!Objects.equal(infoTerreno, null))
            intent.putExtra(CHAVE_INFO_TERRENO, infoTerreno);

        if (!Objects.equal(infoUnidade, null))
            intent.putExtra(CHAVE_INFO_UNIDADE, infoUnidade);

        startActivity(
                intent
        );
    }

    private void receberDados() {
        final Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_BIC)) {
            final BICadastral biCadastral = (BICadastral) dados.getSerializableExtra(CHAVE_BIC);
            abreFormularioBiCadastral(biCadastral);
        } else {
            APPTITLE = "CADASTRAR FORMULÁRIO IMÓVEL";
            this.imovel = new Imovel();
            this.imovelEndereco = new ImovelEndereco();
            this.localizacao = new LocalizacaoEndereco();
        }

//        carregarImovel(dados);
//        carregarEnderecoImovel(dados);
//        carregarLocalizacao(dados);
//
//        if (getBenfeitorias(dados) != null) {
//            benfeitorias = getBenfeitorias(dados);
//            Log.i("BENFEITORIA", "------------ receber Dados: " + benfeitorias.toString());
//            Toast.makeText(this, "Benfeitoria" + benfeitorias.toString(), Toast.LENGTH_LONG).show();
//        }
//
//        if (getInfoEdificacao(dados) != null) {
//            infoEdificacao = getInfoEdificacao(dados);
//            Log.i("infoEdificacao", "------------ receber Dados: " + infoEdificacao.toString());
//            Toast.makeText(this, "Benfeitoria" + infoEdificacao.toString(), Toast.LENGTH_LONG).show();
//        }
//
//        if (getInfoTerreno(dados) != null) {
//            infoTerreno = getInfoTerreno(dados);
//            Log.i("infoTerreno", "------------ receber Dados: " + infoTerreno.toString());
//            Toast.makeText(this, "Benfeitoria" + infoTerreno.toString(), Toast.LENGTH_LONG).show();
//        }
//
//        if (getInfoUnidade(dados) != null) {
//            infoUnidade = getInfoUnidade(dados);
//            Log.i("infoUnidade", "------------ receber Dados: " + infoUnidade.toString());
//            Toast.makeText(this, "Benfeitoria" + infoUnidade.toString(), Toast.LENGTH_LONG).show();
//        }
//
//        carregarContribuinte(dados);
//        carregarEnderecoContribuinte(dados);
    }

    private void carregarEnderecoImovel() {
        if ( imovelEndereco != null) {
            //Bloco
            mEtBloco.setText(imovelEndereco.getBloco());
            //CEP
            mEtCep.setText(imovelEndereco.getCep());
            //Número
            mEtNumero.setText(imovelEndereco.getNumero());
            //Número Subunidade
            mEtNumeroSubunidade.setText(imovelEndereco.getNumeroSubunidade());
            //Logradouro
            mEtLogradouro.setText(imovelEndereco.getLogradouro());
            //Complemento
            mEtComplemento.setText(imovelEndereco.getComplemento());
            //Bairro
            mSpBairro.setSelection(imovelEndereco.getBairro().ordinal());
            //Edificio
            mSpEdificio.setSelection(imovelEndereco.getEdificio().ordinal());
            //Subunidade
            mSpSubunidade.setSelection(imovelEndereco.getSubunidade().ordinal());
        }
    }

    private void carregarImovel() {

        if (imovel != null) {
            APPTITLE = "EDITAR FORMULÁRIO IMÓVEL";
            mTvInscricao.setText(imovel.getInscricao());
            mTvInscricaoAnterior.setText(imovel.getInscricaoAnterior());
            mTvSequencial.setText(imovel.getSequencial() != null
                    ? imovel.getSequencial() : "");
            mEtLote.setText(imovel.getLote());
            mEtQuadra.setText(imovel.getQuadra());
            mSpLoteamento.setSelection(imovel.getLoteamento().ordinal());
            mSpNatureza.setSelection(imovel.getNatureza().ordinal());

        }
    }

    private void carregarLocalizacao() {
        if (localizacao != null) {
            if (localizacao != null)
                Log.i("LOCALIZAÇÃO", "------------ receber Dados: " + localizacao.toString());

            mEtLatitude.setText(localizacao.getLatitude());
            mEtLongitude.setText(localizacao.getLongitude());
        }
    }

    private void definirEventos() {
        formatarCep(mEtCep);
        // ABRIR MAPA PARA PEGAR A LOCALIZAÇÃO
//        mTvLocalizacao.setOnClickListener(view -> startActivity(
//                abrirMapsActivity()
//        ));

    }

    private Imovel pegarImovel() {
        //          INFORMAÇÕES DO IMÓVEL
        imovel.setInscricao(getStringForTextView(mTvInscricao));
        imovel.setInscricao(getStringForTextView(mTvInscricao));
        imovel.setInscricaoAnterior(getStringForTextView(mTvInscricaoAnterior));
        imovel.setSequencial(getStringForTextView(mTvSequencial));
        imovel.setNatureza(
                mSpNatureza.getSelectedItemPosition() == TipoNatureza.PREDIAL.ordinal()
                        ? TipoNatureza.PREDIAL : TipoNatureza.TERRITORIAL
        );
        imovel.setLoteamento(
                Loteamento.valueOf(
                        removerCaracterEspecial(
                                getStringForSpinner(mSpLoteamento)))
        );

        //    Log.i("ERRO Imovel", "pegarImovel: "+ imovel.getTipoSubunidade());
        imovel.setQuadra(getStringForEditText(mEtQuadra));
        imovel.setLote(getStringForEditText(mEtLote));
        // INFORMAÇÕES DA UNIDADE
        // INFORMAÇÕES DA EDIFICAÇÃO
        // INFORMAÇÕES DO TERRENO
        // INFORMAÇÕES DAS BENFEITORIAS
        // SERÃO ADICIONADAS NO INFO_IMOVEL_ACTIVITY
        return imovel;
    }

    private ImovelEndereco pegarEnderecoImovel() {
        //            ENDEREÇO IMÓVEL
        imovelEndereco.setNumero(getStringForEditText(mEtNumero));
        imovelEndereco.setBloco(getStringForEditText(mEtBloco));
        imovelEndereco.setLogradouro(getStringForEditText(mEtLogradouro));
        imovelEndereco.setComplemento(getStringForEditText(mEtComplemento));

        imovelEndereco.setSubunidade(
                TipoSubunidade.valueOf(
                        removerCaracterEspecial(getStringForSpinner(mSpSubunidade)))
        );
        Log.i("IE Subunidade", "pegarEnderecoImovel: " + imovelEndereco.getSubunidade());
        imovelEndereco.setCep(getStringForEditText(mEtCep));
        imovelEndereco.setBairro(
                Bairro.valueOf(
                        removerCaracterEspecial(getStringForSpinner(mSpBairro))));
        imovelEndereco.setEdificio(
                Edificio.valueOf(
                        removerCaracterEspecial(getStringForSpinner(mSpEdificio))));
        imovelEndereco.setNumeroSubunidade(getStringForEditText(mEtNumeroSubunidade));
        //          QUANDO FOR SALVAR, COLOCAR A LOCALIZAÇÃO DO ENDEREÇO
        return imovelEndereco;
    }

    private Intent abrirMapsActivity() {
        final Intent maps = new Intent(
                FormImovelActivity.this, MapsActivity.class
        );
        return maps;
    }


    private Intent abrirInfoEdificioActivity() {
        final Intent infoImovel = new Intent(
                FormImovelActivity.this, InfoImovelActivity.class
        );
        return infoImovel;
    }

    public static boolean isConnected(Context cont){
        ConnectivityManager conmag = (ConnectivityManager)cont.getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conmag != null ) {
            conmag.getActiveNetworkInfo();

            //Verifica internet pela WIFI
            if (conmag.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                return true;
            }

            //Verifica se tem internet móvel
            if (conmag.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
                return true;
            }
        }

        return false;
    }

    private void inicializarDAOS() {
        imovelDAO = Database.getInstance(this).getRoomImovelDAO();
        imovelEnderecoDAO = Database.getInstance(this).getRoomImovelEnderecoDAO();
        imovelEnderecoLocalizacaoDAO = Database.getInstance(this)
                .getRoomImovelEnderecoLocalizacaoDAO();
        benfeitoriasDAO = Database.getInstance(this).getBenfeitoriasDAO();
        infoEdificacaoDAO = Database.getInstance(this).getRoomInfoEdificacaoDAO();
        infoTerrenoDAO = Database.getInstance(this).getRoomInfoTerrenoDAO();
        infoUnidadeDAO = Database.getInstance(this).getRoomInfoUnidadeDAO();

        contribuinteDAO = Database.getInstance(this).getRoomContribuinteDAO();
        contribuinteEnderecoDAO = Database.getInstance(this)
                .getRoomContribuinteEnderecoDAO();
//        biCadastralDAO = Database.getInstance(this).getRoomBiCadastralDAO();
    }

    private void inicializaComponentes() {
        mTvInscricao = findViewById(R.id.ac_form_imovel_tv_inscricao_valor);
        mTvInscricaoAnterior = findViewById(R.id.ac_form_imovel_tv_inscricao_anterior_valor);
        mTvSequencial = findViewById(R.id.ac_form_imovel_tv_sequencial_valor);
        mSpBairro = findViewById(R.id.ac_form_imovel_sp_endereco_bairro);
        mEtBloco = findViewById(R.id.ac_form_imovel_ed_endereco_bloco);
        mEtLogradouro = findViewById(R.id.ac_form_imovel_ed_endereco_logradouro);
        mEtLote = findViewById(R.id.ac_form_imovel_ed_endereco_lote);
        mEtComplemento = findViewById(R.id.ac_form_imovel_ed_endereco_complemento);
        mEtQuadra = findViewById(R.id.ac_form_imovel_ed_endereco_quadra);
        mEtCep = findViewById(R.id.ac_form_imovel_ed_endereco_cep);
        mEtNumero = findViewById(R.id.ac_form_imovel_ed_endereco_numero);
        mSpLoteamento = findViewById(R.id.ac_form_imovel_sp_endereco_loteamento);
        mSpSubunidade = findViewById(R.id.ac_form_imovel_sp_endereco_subunidade);

        mEtNumeroSubunidade = findViewById(R.id.ac_form_imovel_ed_endereco_numero_subunidade);
        mSpEdificio = findViewById(R.id.ac_form_imovel_sp_endereco_edificio);
        mSpNatureza = findViewById(R.id.ac_form_imovel_sp_endereco_natureza);
        mEtLatitude = findViewById(R.id.ac_form_imovel_ed_endereco_latitude);
        mEtLongitude = findViewById(R.id.ac_form_imovel_ed_endereco_longitude);
        mTvLocalizacao = findViewById(R.id.ac_form_imovel_bt_localizacao);
    }
}
