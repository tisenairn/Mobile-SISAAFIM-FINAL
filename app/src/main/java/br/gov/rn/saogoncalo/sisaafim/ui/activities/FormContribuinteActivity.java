package br.gov.rn.saogoncalo.sisaafim.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import br.gov.rn.saogoncalo.sisaafim.R;
import br.gov.rn.saogoncalo.sisaafim.asynctask.EditaBiCadastralTask;
import br.gov.rn.saogoncalo.sisaafim.asynctask.SalvaContribuinteTask;
import br.gov.rn.saogoncalo.sisaafim.asynctask.SalvaImovelTask;
import br.gov.rn.saogoncalo.sisaafim.enums.Bairro;
import br.gov.rn.saogoncalo.sisaafim.enums.TipoLogradouro;
import br.gov.rn.saogoncalo.sisaafim.enums.TipoSubunidade;
import br.gov.rn.saogoncalo.sisaafim.enums.UnidadeFederativa;
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

import static br.gov.rn.saogoncalo.sisaafim.utils.CarregarSpinner.spinners;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_BENFEITORIAS;
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
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.formatarCelular;
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.formatarCep;
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.formatarCnh;
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.formatarCnpj;
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.formatarCpf;
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.formatarData;
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.formatarRg;
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.formatarTelefone;
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.removerCaracterEspecial;
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.removerMascara;

public class FormContribuinteActivity extends AppCompatActivity {

    private AppCompatEditText mEdContribuinteNome;
    private AppCompatEditText mEdContribuinteDataNascimento;
    private AppCompatEditText mEdContribuinteTelefone;
    private AppCompatEditText mEdContribuinteCpf;
    private AppCompatEditText mEdContribuinteRg;
    private AppCompatEditText mEdContribuinteOrgaoExpedidor;
    private AppCompatEditText mEdContribuinteCnh;
    private AppCompatEditText mEdContribuinteCnpj;
    private AppCompatEditText mEdContribuinteCelular;
    private AppCompatEditText mEdContribuinteEmail;

    private AppCompatEditText mEdContribuinteBloco;
    private AppCompatEditText mEdContribuinteCidade;
    private AppCompatEditText mEdContribuinteLogradouro;
    private AppCompatEditText mEdContribuinteNumero;
    private AppCompatEditText mEdContribuinteCep;

    private AppCompatSpinner mSpContribuinteUf;
    private AppCompatSpinner mSpContribuinteBairro;
    private AppCompatSpinner mSpContribuinteLogradouro;
    private AppCompatSpinner mSpContribuinteSubunidade;

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

    private Imovel imovel;
    private ImovelEndereco imovelEndereco;
    private LocalizacaoEndereco imovelEnderecoLocalizacao;

    private Contribuinte contribuinte;
    private ContribuinteEndereco contribuinteEndereco;

    private Benfeitorias benfeitorias;
    private InfoEdificacao infoEdificacao;
    private InfoTerreno infoTerreno;
    private InfoUnidade infoUnidade;
    private BICadastral biCadastral;

    private String APPTITLE = "CADASTRAR FORMULÁRIO CONTRIBUINTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_contribuinte);
        setTitle(APPTITLE);

        inicializarDAOS();

        inicializarComponentes();

        spinners(this, mSpContribuinteBairro, mSpContribuinteUf,
                mSpContribuinteLogradouro, mSpContribuinteSubunidade);

        receberDados();
        definirEventos();

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

    private void receberDados() {
        final Intent dados = getIntent();

        carregarImovel(dados);

        carregarImovelEndereco(dados);

        CarregarImovelEnderecoLocalizacao(dados);

        carregarBenfeitorias(dados);

        carregarEdificacao(dados);

        carregarInfoTerreno(dados);

        carregarInfoUnidade(dados);

        carregarContribuinte(dados);
        carregarEnderecoContribuinte(dados);

        biCadastral = new BICadastral();
    }

    private void carregarInfoUnidade(Intent dados) {
        if (dados.hasExtra(CHAVE_INFO_UNIDADE)) {
            infoUnidade = (InfoUnidade) dados.getSerializableExtra(CHAVE_INFO_UNIDADE);
        } else {
            infoUnidade = new InfoUnidade();
        }
    }

    private void carregarInfoTerreno(Intent dados) {
        if (dados.hasExtra(CHAVE_INFO_TERRENO)) {
            infoTerreno = (InfoTerreno) dados.getSerializableExtra(CHAVE_INFO_TERRENO);
        } else {
            infoTerreno = new InfoTerreno();
        }
    }

    private void carregarEdificacao(Intent dados) {
        if (dados.hasExtra(CHAVE_INFO_EDIFICACAO)) {
            infoEdificacao = (InfoEdificacao) dados.getSerializableExtra(CHAVE_INFO_EDIFICACAO);
        } else {
            infoEdificacao = new InfoEdificacao();
        }
    }

    private void carregarBenfeitorias(Intent dados) {
        if (dados.hasExtra(CHAVE_BENFEITORIAS)) {
            benfeitorias = (Benfeitorias) dados.getSerializableExtra(CHAVE_BENFEITORIAS);
        } else {
            benfeitorias = new Benfeitorias();
        }
    }

    private void CarregarImovelEnderecoLocalizacao(Intent dados) {
        if (dados.hasExtra(CHAVE_FORM_IMOVEL_ENDERECO_LOCALIZACAO)) {
            imovelEnderecoLocalizacao = (LocalizacaoEndereco) dados.getSerializableExtra(CHAVE_FORM_IMOVEL_ENDERECO_LOCALIZACAO);
        } else {
            imovelEnderecoLocalizacao = new LocalizacaoEndereco();
        }
    }

    private void carregarImovelEndereco(Intent dados) {
        if (dados.hasExtra(CHAVE_FORM_IMOVEL_ENDERECO)) {
            imovelEndereco = (ImovelEndereco) dados.getSerializableExtra(CHAVE_FORM_IMOVEL_ENDERECO);
            Log.i("IMOVEL_ENDERECO", "receberDados: " + imovelEndereco.toString());
            Log.i("IMOVEL_ENDERECO", "receberDados: " + imovelEndereco.getSubunidade());
        } else {
            imovelEndereco = new ImovelEndereco();
        }
    }

    private void carregarImovel(Intent dados) {
        if (dados.hasExtra(CHAVE_FORM_IMOVEL)) {
            imovel = (Imovel) dados.getSerializableExtra(CHAVE_FORM_IMOVEL);
            Log.i(CHAVE_FORM_IMOVEL, "receberDados: " + imovel.toString());
        } else {
            imovel = new Imovel();
        }
    }

    private void carregarEnderecoContribuinte(Intent dados) {
        if (dados.hasExtra(CHAVE_FORM_CONTRIBUINTE_ENDERECO)) {
            this.contribuinteEndereco = (ContribuinteEndereco)
                    dados.getSerializableExtra(CHAVE_FORM_CONTRIBUINTE_ENDERECO);
            if (!contribuinteEndereco.equals(null)) {

                mSpContribuinteBairro.setSelection(contribuinteEndereco.getBairro().ordinal());
                mEdContribuinteBloco.setText(contribuinteEndereco.getBloco());
                mEdContribuinteLogradouro.setText(contribuinteEndereco.getNomeLogradouro());
                mSpContribuinteSubunidade.setSelection(contribuinteEndereco.getTipoSubunidade().ordinal());
                mSpContribuinteLogradouro.setSelection(contribuinteEndereco.getTipoLogradouro().ordinal());
                mEdContribuinteCidade.setText(contribuinteEndereco.getCidade());
                mSpContribuinteUf.setSelection(contribuinteEndereco.getUf().ordinal());
                mEdContribuinteNumero.setText(String.valueOf(contribuinteEndereco.getNumero()));
                mEdContribuinteCep.setText(contribuinteEndereco.getCep());
            }
        } else {
            contribuinte = new Contribuinte();
        }
    }

    private void carregarContribuinte(Intent dados) {
        if (dados.hasExtra(CHAVE_FORM_CONTRIBUINTE)) {
            APPTITLE = "EDITAR CONTRIBUINTE";
            this.contribuinte = (Contribuinte) dados.getSerializableExtra(CHAVE_FORM_CONTRIBUINTE);

//            if (!contribuinte.equals("")) {
            mEdContribuinteNome.setText(contribuinte.getNome());

            if (contribuinte.getCpfCnpj().length() > 11) {
                mEdContribuinteCnpj.setText(contribuinte.getCpfCnpj());
            } else {
                mEdContribuinteCpf.setText(contribuinte.getCpfCnpj());
            }

            mEdContribuinteRg.setText(contribuinte.getRg());
            mEdContribuinteOrgaoExpedidor.setText(contribuinte.getOrgaoExpedidor());
            mEdContribuinteDataNascimento.setText(contribuinte.getDataNascimento());
            mEdContribuinteCnh.setText(contribuinte.getCnh());
            mEdContribuinteCelular.setText(contribuinte.getCelular());
            mEdContribuinteTelefone.setText(contribuinte.getTelefoneResidencial());
            mEdContribuinteEmail.setText(contribuinte.getEmail());

//            }
        } else {
            contribuinteEndereco = new ContribuinteEndereco();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form_contribuinte, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_form_contribuinte_item_salvar) {

            criarContribuinte();
            criarEnderecoContribuinte();

            if (imovel.getId() == null) {
                salvaImovel();
            } else {
                editaImovel();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void editaImovel() {
        new EditaBiCadastralTask(imovelDAO, benfeitoriasDAO, infoEdificacaoDAO,
                imovelEnderecoDAO, imovelEnderecoLocalizacaoDAO,
                infoTerrenoDAO, infoUnidadeDAO, contribuinteDAO, contribuinteEnderecoDAO,
                biCadastralDAO, imovel, imovelEnderecoLocalizacao, contribuinte,
                contribuinteEndereco, benfeitorias, infoTerreno, imovelEndereco,
                infoEdificacao, infoUnidade, biCadastral,
                this::abrirListaPendenciaActivity).execute();
    }

    private void abrirListaPendenciaActivity() {
        startActivity(
                new Intent(
                        FormContribuinteActivity.this, ListaDePendenciaActivity.class
                )
        );
        finish();
    }

    private void definirEventos() {
        formatarTelefone(mEdContribuinteTelefone);
        formatarCelular(mEdContribuinteCelular);
        formatarCep(mEdContribuinteCep);
        formatarCpf(mEdContribuinteCpf);
        formatarData(mEdContribuinteDataNascimento);
        formatarRg(mEdContribuinteRg);
        formatarCnpj(mEdContribuinteCnpj);
        formatarCnh(mEdContribuinteCnh);
        OnItemSelectedListenerSpinner(mSpContribuinteBairro);
        OnItemSelectedListenerSpinner(mSpContribuinteUf);
    }

    private void salvaImovel() {
        new SalvaImovelTask(this,
                imovel, imovelEnderecoLocalizacao, contribuinte,
                contribuinteEndereco, benfeitorias, infoTerreno, imovelEndereco, infoEdificacao,
                infoUnidade, biCadastral, this::abrirListaPendenciaActivity)
                .execute();
    }

//    private void salvaImovel() {
//        new SalvaImovelTask(imovelDAO, benfeitoriasDAO, infoEdificacaoDAO, imovelEnderecoDAO,
//                imovelEnderecoLocalizacaoDAO, infoTerrenoDAO, infoUnidadeDAO, contribuinteDAO,
//                contribuinteEnderecoDAO, biCadastralDAO,
//                imovel, imovelEnderecoLocalizacao, contribuinte,
//                contribuinteEndereco, benfeitorias, infoTerreno, imovelEndereco, infoEdificacao,
//                infoUnidade, biCadastral, this::abrirListaPendenciaActivity)
//                .execute();
//    }

    private void salvaContribuinte() {
        new SalvaContribuinteTask(contribuinteDAO, contribuinteEnderecoDAO,
                contribuinteEndereco, contribuinte, this::abrirListaPendenciaActivity)
                .execute();
    }

    private void OnItemSelectedListenerSpinner(AppCompatSpinner spinner) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void criarContribuinte() {

        contribuinte.setNome(getStringForEditText(mEdContribuinteNome));
        contribuinte.setRg(getStringForEditText(mEdContribuinteRg));
        contribuinte.setOrgaoExpedidor(getStringForEditText(mEdContribuinteOrgaoExpedidor));
        contribuinte.setDataNascimento(getStringForEditText(mEdContribuinteDataNascimento));
        contribuinte.setCnh(getStringForEditText(mEdContribuinteCnh));
        contribuinte.setCelular(getStringForEditText(mEdContribuinteCelular));
        contribuinte.setTelefoneResidencial(getStringForEditText(mEdContribuinteTelefone));
        contribuinte.setEmail(getStringForEditText(mEdContribuinteEmail));

        if (getStringForEditText(mEdContribuinteCpf).trim().isEmpty()) {
            Log.i("----- CNPJ ", "criarContribuinte: " + getStringForEditText(mEdContribuinteCnpj));
            contribuinte.setCpfCnpj(getStringForEditText(mEdContribuinteCnpj));
        } else {
            Log.i("----- CPF", "criarContribuinte: " + getStringForEditText(mEdContribuinteCpf));
            contribuinte.setCpfCnpj(removerMascara(getStringForEditText(mEdContribuinteCpf)));
        }
    }

    private void criarEnderecoContribuinte() {
        contribuinteEndereco.setBairro(Bairro.valueOf(removerCaracterEspecial(
                getStringForSpinner(mSpContribuinteBairro)))
        );
        contribuinteEndereco.setTipoLogradouro(
                TipoLogradouro.valueOf(
                        removerCaracterEspecial(
                                getStringForSpinner(mSpContribuinteLogradouro)))
        );
        contribuinteEndereco.setCidade(getStringForEditText(mEdContribuinteCidade));
        contribuinteEndereco.setUf(
                UnidadeFederativa.valueOf(
                        removerCaracterEspecial(
                                getStringForSpinner(mSpContribuinteUf)))
        );
        contribuinteEndereco.setCep(getStringForEditText(mEdContribuinteCep));
        contribuinteEndereco.setNomeLogradouro(getStringForEditText(mEdContribuinteLogradouro));
//        contribuinteEndereco.setNumeroSubunidade();
//        contribuinteEndereco.setPossuiEnderecoCorrespondecia();
//        contribuinteEndereco.setCodLogradouro();
//        contribuinteEndereco.setCaixaPostal();
//        Log.i("ERRO Contri", "pegarContri: "+ getStringForSpinner(mSpContribuinteSubunidade));

        contribuinteEndereco.setTipoSubunidade(
                TipoSubunidade.valueOf(
                        removerCaracterEspecial(
                                getStringForSpinner(mSpContribuinteSubunidade)))
        );
//        contribuinteEndereco.setNumero(Integer.valueOf(getStringForEditText(mEdContribuinteNumero)));
        contribuinteEndereco.setNumero(0);
        contribuinteEndereco.setBloco(getStringForEditText(mEdContribuinteBloco));
    }

    private void inicializarComponentes() {
        mEdContribuinteNome = findViewById(R.id.ac_form_contribuinte_ed_nome);
        mEdContribuinteDataNascimento = findViewById(R.id.ac_form_contribuinte_ed_data_nascimento);
        mEdContribuinteTelefone = findViewById(R.id.ac_form_contribuinte_ed_telefone);
        mEdContribuinteCpf = findViewById(R.id.ac_form_contribuinte_ed_cpf);
        mEdContribuinteRg = findViewById(R.id.ac_form_contribuinte_ed_rg);
        mEdContribuinteOrgaoExpedidor = findViewById(R.id.frag_contribuinte_form_ed_expedidor);
        mEdContribuinteCnh = findViewById(R.id.ac_form_contribuinte_ed_cnh);
        mEdContribuinteCnpj = findViewById(R.id.ac_form_contribuinte_ed_cnpj);
        mEdContribuinteCidade = findViewById(R.id.ac_form_contribuinte_ed_cidade);
        mSpContribuinteUf = findViewById(R.id.ac_form_contribuinte_sp_uf);
        mSpContribuinteLogradouro = findViewById(R.id.ac_form_contribuinte_sp_tipo_logradouro);
        mSpContribuinteBairro = findViewById(R.id.ac_form_contribuinte_sp_bairro);
        mSpContribuinteSubunidade = findViewById(R.id.ac_form_contribuinte_sp_tipo_subunidade);
        mEdContribuinteBloco = findViewById(R.id.ac_form_contribuinte_ed_bloco);
        mEdContribuinteLogradouro = findViewById(R.id.ac_form_contribuinte_ed_logradouro);
        mEdContribuinteCep = findViewById(R.id.ac_form_contribuinte_ed_cep);
        mEdContribuinteNumero = findViewById(R.id.ac_form_contribuinte_ed_numero);
        mEdContribuinteCelular = findViewById(R.id.ac_form_contribuinte_ed_celular);
        mEdContribuinteEmail = findViewById(R.id.ac_form_contribuinte_ed_email);
    }
}
