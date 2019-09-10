package br.gov.rn.saogoncalo.sisaafim.ui.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import br.gov.rn.saogoncalo.sisaafim.R;
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
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomBenfeitoriasDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomContribuinteDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomContribuinteEnderecoDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomImovelDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomImovelEnderecoDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomImovelEnderecoLocalizacaoDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomInfoEdificacaoDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomInfoTerrenoDAO;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomInfoUnidadeDAO;

import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_BIC;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_IMOVEL_ENDERECO_LOCALIZACAO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_VISUALIZAR;
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.formatarStringParaTextView;

public class VisualizarBicActivity extends AppCompatActivity {

    private TextView mImovelTvInscricao;
    private TextView mImovelTvInscricaoAnterior;
    private TextView mImovelTvSequencial;
    private TextView mImovelTvBairro;
    private TextView mImovelTvLogradouro;
    private TextView mImovelTvLote;
    private TextView mImovelTvComplemento;
    private TextView mImovelTvQuadra;
    private TextView mImovelTvCep;
    private TextView mImovelTvNumero;
    private TextView mImovelTvLoteamento;
    private TextView mImovelTvSubunidade;
    private TextView mImovelTvEdificio;
    private TextView mImovelTvNatureza;
    private TextView mImovelTvNumeroSubunidade;
    private TextView mImovelTvBloco;
    private TextView mImovelTvLatitude;
    private TextView mImovelTvLongitude;
    private Button mImovelBtLocalizacao;

    private TextView mContribuinteTvNome;
    private TextView mContribuinteTvDataNasc;

    private RoomImovelDAO imovelDAO;
    private RoomImovelEnderecoDAO imovelEnderecoDAO;
    private RoomImovelEnderecoLocalizacaoDAO imovelEnderecoLocalizacaoDAO;
    private RoomBenfeitoriasDAO benfeitoriasDAO;
    private RoomInfoEdificacaoDAO infoEdificacaoDAO;
    private RoomInfoTerrenoDAO infoTerrenoDAO;
    private RoomInfoUnidadeDAO infoUnidadeDAO;
    private RoomContribuinteEnderecoDAO contribuinteEnderecoDAO;
    private RoomContribuinteDAO contribuinteDAO;
    private RoomBICadastralDAO biCadastralDAO;

    private BICadastral biCadastral = null;

    private Imovel imovelEncontrado = null;
    private ImovelEndereco imovelEnderecoEncontrado = null;
    private LocalizacaoEndereco localizacaoEndereco = null;

    private Contribuinte contribuinte = null;
    private ContribuinteEndereco contribuinteEndereco = null;

    private Benfeitorias benfeitorias = null;
    private InfoEdificacao infoEdificacao = null;
    private InfoTerreno infoTerreno = null;
    private InfoUnidade infoUnidade = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_bic);
        inicializarComponentes();
        inicializarDAOS();
        receberDados();
        definirEventos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_visualizar_bic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_visualizar_bic_item_voltar:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void definirEventos() {
        mImovelBtLocalizacao.setOnClickListener(v -> {
            if (StringUtils.isNotEmpty(localizacaoEndereco.getLatitude()) && StringUtils.isNotEmpty(localizacaoEndereco.getLongitude())) {
                final Intent intent = new Intent(VisualizarBicActivity.this, MapsActivity.class);
                intent.putExtra(CHAVE_FORM_IMOVEL_ENDERECO_LOCALIZACAO, localizacaoEndereco);
                intent.putExtra(CHAVE_VISUALIZAR, true);
                startActivity(intent);
            } else {
                Snackbar.make(v, "Não tem localização salva!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void receberDados() {
        final Intent intent = getIntent();
        if (intent.hasExtra(CHAVE_BIC)) {
            final BICadastral biCadastral = (BICadastral) intent.getSerializableExtra(CHAVE_BIC);
            abreFormularioBiCadastral(biCadastral);
        }
    }

    private void abreFormularioBiCadastral(@NonNull BICadastral bicEscolhido) {

        imovelEncontrado = imovelDAO.buscaPorId(bicEscolhido.getImovelId());
        imovelEnderecoEncontrado = imovelEnderecoDAO.buscaPorId(imovelEncontrado.getId());
        localizacaoEndereco = imovelEnderecoLocalizacaoDAO.buscaPorId(imovelEnderecoEncontrado.getId());

        contribuinte = contribuinteDAO.buscaPorId(imovelEncontrado.getId());
        if (contribuinte != null) {
            contribuinteEndereco = contribuinteEnderecoDAO.buscaPorId(contribuinte.getId());
        }

        benfeitorias = benfeitoriasDAO.buscarPorId(imovelEncontrado.getId());
        infoEdificacao = infoEdificacaoDAO.buscarPorId(imovelEncontrado.getId());
        infoTerreno = infoTerrenoDAO.buscarPorId(imovelEncontrado.getId());
        infoUnidade = infoUnidadeDAO.buscarPorId(imovelEncontrado.getId());

        carregarDados();
    }

    private void carregarDados() {
        pegarDadosImovel();

        pegarImovelEndereco();

        pegarLocalizacaoImovel();

        pegarContribuinte();
    }

    private void pegarContribuinte() {
        mContribuinteTvNome.setText(contribuinte.getNome());
        mContribuinteTvDataNasc.setText(contribuinte.getDataNascimento());
    }

    private void pegarLocalizacaoImovel() {
        mImovelTvLatitude.setText(localizacaoEndereco.getLatitude());
        mImovelTvLongitude.setText(localizacaoEndereco.getLongitude());
    }

    private void pegarImovelEndereco() {
        mImovelTvSubunidade.setText(
                formatarStringParaTextView(String.valueOf(imovelEnderecoEncontrado.getSubunidade()))
        );
        mImovelTvBairro.setText(
                formatarStringParaTextView(String.valueOf(imovelEnderecoEncontrado.getBairro()))
        );
        mImovelTvBloco.setText(String.valueOf(imovelEnderecoEncontrado.getBloco()));
        mImovelTvCep.setText(String.valueOf(imovelEnderecoEncontrado.getCep()));
        mImovelTvComplemento.setText(String.valueOf(imovelEnderecoEncontrado.getComplemento()));
        mImovelTvEdificio.setText(
                formatarStringParaTextView(String.valueOf(imovelEnderecoEncontrado.getEdificio()))
        );
        mImovelTvLogradouro.setText(String.valueOf(imovelEnderecoEncontrado.getLogradouro()));
        mImovelTvNumero.setText(String.valueOf(imovelEnderecoEncontrado.getNumero()));
        mImovelTvNumeroSubunidade.setText(String.valueOf(imovelEnderecoEncontrado.getNumeroSubunidade()));
    }

    private void pegarDadosImovel() {
        mImovelTvInscricao.setText(imovelEncontrado.getInscricao());
        mImovelTvInscricaoAnterior.setText(imovelEncontrado.getInscricaoAnterior());
        mImovelTvSequencial.setText(imovelEncontrado.getSequencial());

        mImovelTvNatureza.setText(
                formatarStringParaTextView(String.valueOf(imovelEncontrado.getNatureza()))
        );
        mImovelTvLoteamento.setText(
                formatarStringParaTextView(String.valueOf(imovelEncontrado.getLoteamento()))
        );
        mImovelTvLote.setText(String.valueOf(imovelEncontrado.getLote()));
        mImovelTvQuadra.setText(String.valueOf(imovelEncontrado.getQuadra()));
    }

    private void inicializarComponentes() {
        mImovelTvInscricao = findViewById(R.id.ac_visualizar_bic_tv_inscricao_valor);
        mImovelTvInscricaoAnterior = findViewById(R.id.ac_visualizar_bic_tv_inscricao_anterior_valor);
        mImovelTvSequencial = findViewById(R.id.ac_visualizar_bic_tv_sequencial_valor);

        mImovelTvBairro = findViewById(R.id.ac_visualizar_bic_tv_bairro_valor);
        mImovelTvLogradouro = findViewById(R.id.ac_visualizar_bic_tv_logradouro_valor);
        mImovelTvLote = findViewById(R.id.ac_visualizar_bic_tv_lote_valor);
        mImovelTvComplemento = findViewById(R.id.ac_visualizar_bic_tv_complemento_valor);
        mImovelTvQuadra = findViewById(R.id.ac_visualizar_bic_tv_quadra_valor);
        mImovelTvCep = findViewById(R.id.ac_visualizar_bic_tv_cep_valor);
        mImovelTvNumero = findViewById(R.id.ac_visualizar_bic_tv_numero_valor);
        mImovelTvLoteamento = findViewById(R.id.ac_visualizar_bic_tv_loteamento_valor);
        mImovelTvSubunidade = findViewById(R.id.ac_visualizar_bic_tv_subunidade_valor);
        mImovelTvEdificio = findViewById(R.id.ac_visualizar_bic_tv_edificio_valor);
        mImovelTvNatureza = findViewById(R.id.ac_visualizar_bic_tv_natureza_valor);
        mImovelTvNumeroSubunidade = findViewById(R.id.ac_visualizar_bic_tv_numero_subunidade_valor);
        mImovelTvBloco = findViewById(R.id.ac_visualizar_bic_tv_bloco_valor);
        mImovelBtLocalizacao = findViewById(R.id.ac_visualizar_bic_bt_localizacao);
        mImovelTvLatitude = findViewById(R.id.ac_visualizar_bic_tv_latitude_valor);
        mImovelTvLongitude = findViewById(R.id.ac_visualizar_bic_tv_longitude_valor);

        mContribuinteTvNome = findViewById(R.id.ac_visualizar_bic_tv_contribuinte_nome_valor);
        mContribuinteTvDataNasc = findViewById(R.id.ac_visualizar_bic_tv_contribuinte_datanasc_valor);
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
        biCadastralDAO = Database.getInstance(this).getRoomBiCadastralDAO();
    }

}
