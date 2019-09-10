package br.gov.rn.saogoncalo.sisaafim.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

import org.apache.commons.lang3.StringUtils;

import br.gov.rn.saogoncalo.sisaafim.R;
import br.gov.rn.saogoncalo.sisaafim.enums.EstadoConservacao;
import br.gov.rn.saogoncalo.sisaafim.enums.Limitacao;
import br.gov.rn.saogoncalo.sisaafim.enums.PadraoQualidade;
import br.gov.rn.saogoncalo.sisaafim.enums.Patrimonio;
import br.gov.rn.saogoncalo.sisaafim.enums.Pedologia;
import br.gov.rn.saogoncalo.sisaafim.enums.SituacaoTerreno;
import br.gov.rn.saogoncalo.sisaafim.enums.TipoEstrutura;
import br.gov.rn.saogoncalo.sisaafim.enums.TipoImovel;
import br.gov.rn.saogoncalo.sisaafim.enums.Topografia;
import br.gov.rn.saogoncalo.sisaafim.enums.UtilizacaoImovel;
import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.Contribuinte;
import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.ContribuinteEndereco;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.Benfeitorias;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.ImovelEndereco;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoEdificacao;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoTerreno;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoUnidade;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.LocalizacaoEndereco;

import static br.gov.rn.saogoncalo.sisaafim.utils.CarregarSpinner.spinners;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_BENFEITORIAS;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_CONTRIBUINTE;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_CONTRIBUINTE_ENDERECO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_INFO_EDIFICACAO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_INFO_TERRENO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_INFO_UNIDADE;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_IMOVEL;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_IMOVEL_ENDERECO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_IMOVEL_ENDERECO_LOCALIZACAO;
import static br.gov.rn.saogoncalo.sisaafim.utils.PegarValorComponentes.getStringForEditText;
import static br.gov.rn.saogoncalo.sisaafim.utils.PegarValorComponentes.getStringForSpinner;
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.removerCaracterEspecial;
import static br.gov.rn.saogoncalo.sisaafim.utils.StringVefiricacao.verificarDouble;

public class InfoImovelActivity extends AppCompatActivity {

    private android.support.v7.widget.AppCompatEditText mEdAreaDoTerreno;
    private android.support.v7.widget.AppCompatEditText mEdAreaTotal;
    private android.support.v7.widget.AppCompatEditText mEdAreaDaUnidade;
    private android.support.v7.widget.AppCompatEditText mEdTestada;
    private android.support.v7.widget.AppCompatEditText mEdProfundidade;
    private android.support.v7.widget.AppCompatEditText mEdFracaoIdeal;

    private android.support.v7.widget.AppCompatSpinner mSpPedologia;
    private android.support.v7.widget.AppCompatSpinner mSpTopografia;
    private android.support.v7.widget.AppCompatSpinner mSpPatrimonio;
    private android.support.v7.widget.AppCompatSpinner mSpSituacaoDoTerreno;
    private android.support.v7.widget.AppCompatSpinner mSpLimitacao;
    private android.support.v7.widget.AppCompatSpinner mSpPadraoQualidade;
    private android.support.v7.widget.AppCompatSpinner mSpUtilizacaoImovel;
    private android.support.v7.widget.AppCompatSpinner mSpTipoImovel;
    private android.support.v7.widget.AppCompatSpinner mSpTipoEstrutura;
    private android.support.v7.widget.AppCompatSpinner mSpEstadoConservacao;

    private CheckBox mCbAgua;
    private CheckBox mCbSarjetas;
    private CheckBox mCbEsgoto;
    private CheckBox mCbRedeEletrica;
    private CheckBox mCbLimpezaUrbana;
    private CheckBox mCbIlumnacaoPublica;
    private CheckBox mCbPavimentacao;
    private CheckBox mCbTelefone;
    private CheckBox mCbGaleriasPluviais;
    private CheckBox mCbColetaDeLixo;

    private Imovel imovel = null;
    private ImovelEndereco imovelEndereco = null;
    private LocalizacaoEndereco localizacaoEnderecoImovel = null;

    private Contribuinte contribuinte = null;
    private ContribuinteEndereco contribuinteEndereco = null;

    private Benfeitorias benfeitorias;
    private InfoEdificacao infoEdificacao;
    private InfoTerreno infoTerreno;
    private InfoUnidade infoUnidade;

    private final String APPTITLE = "CADASTRAR INFORMAÇÕES DO IMÓVEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_imovel);
        setTitle(APPTITLE);

        inicializarComponentes();

        spinners(this, mSpPedologia, mSpTopografia, mSpPatrimonio,
                mSpSituacaoDoTerreno, mSpLimitacao, mSpPadraoQualidade,
                mSpUtilizacaoImovel, mSpTipoImovel, mSpTipoEstrutura,
                mSpEstadoConservacao);

        definirEventos();

        recebendoDados();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_form_info_imovel_item_proximo) {
            final Intent intent = abrirFormContribuinte();
            intent.putExtra(CHAVE_FORM_IMOVEL, imovel);
            intent.putExtra(CHAVE_FORM_IMOVEL_ENDERECO, imovelEndereco);
            intent.putExtra(CHAVE_FORM_IMOVEL_ENDERECO_LOCALIZACAO, localizacaoEnderecoImovel);

            if (contribuinte != null)
                intent.putExtra(CHAVE_FORM_CONTRIBUINTE, contribuinte);

            if (contribuinteEndereco != null)
                intent.putExtra(CHAVE_FORM_CONTRIBUINTE_ENDERECO, contribuinteEndereco);

            intent.putExtra(CHAVE_BENFEITORIAS, criarBenfeitorias());
            intent.putExtra(CHAVE_INFO_EDIFICACAO, criarInfoEdificao());
            intent.putExtra(CHAVE_INFO_TERRENO, criarInfoTerreno());
            intent.putExtra(CHAVE_INFO_UNIDADE, criarInfoUnidade());

            if (imovel != null && imovelEndereco != null &&
                    localizacaoEnderecoImovel != null) {
                startActivity(
                        intent
                );
            }

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void recebendoDados() {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_FORM_IMOVEL)) {
            imovel = (Imovel) dados.getSerializableExtra(CHAVE_FORM_IMOVEL);


            imovelEndereco = (ImovelEndereco) dados.getSerializableExtra(CHAVE_FORM_IMOVEL_ENDERECO);

            localizacaoEnderecoImovel = (LocalizacaoEndereco) dados.getSerializableExtra(CHAVE_FORM_IMOVEL_ENDERECO_LOCALIZACAO);
        }

        if (dados.hasExtra(CHAVE_FORM_CONTRIBUINTE)) {
            contribuinte = (Contribuinte) dados.getSerializableExtra(CHAVE_FORM_CONTRIBUINTE);
            contribuinteEndereco = (ContribuinteEndereco) dados.getSerializableExtra(CHAVE_FORM_CONTRIBUINTE_ENDERECO);
        }

        carregaBenfeitorias(dados);

        carregarInfoEdificacao(dados);

        carregarInfoTerreno(dados);

        carregarInfoUnidade(dados);

    }

    private void carregarInfoUnidade(@NonNull Intent dados) {
        if (dados.hasExtra(CHAVE_INFO_UNIDADE)) {
            this.infoUnidade = (InfoUnidade)
                    dados.getSerializableExtra(CHAVE_INFO_UNIDADE);
            setTitle("EDITAR INFORMAÇÕES DO IMÓVEL");

            mEdAreaDoTerreno.setText(String.valueOf(infoUnidade.getAreaTerreno()));
            mEdTestada.setText(String.valueOf(infoUnidade.getTestadaPrincipal()));
            mEdAreaTotal.setText(String.valueOf(infoUnidade.getAreaTotalConstruida()));
            mEdProfundidade.setText(String.valueOf(infoUnidade.getProfundidadePrincipal()));
            mEdAreaDaUnidade.setText(String.valueOf(infoUnidade.getAreaContruidaUnidade()));

            Log.i("mSpLimitacao", "carregarInfoUnidade: "+infoUnidade.getLimitacao().ordinal());
            Log.i("mSpPatrimonio", "carregarInfoUnidade: "+infoUnidade.getPatrimonio().ordinal());
            mSpLimitacao.setSelection(infoUnidade.getLimitacao().ordinal());
            mSpPatrimonio.setSelection(infoUnidade.getPatrimonio().ordinal());
        } else {
            this.infoUnidade = new InfoUnidade();
        }
    }

    private void carregarInfoTerreno(@NonNull Intent dados) {
        if (dados.hasExtra(CHAVE_INFO_TERRENO)) {
            this.infoTerreno = (InfoTerreno)
                    dados.getSerializableExtra(CHAVE_INFO_TERRENO);

            if (StringUtils.isNotEmpty(infoTerreno.toString())) {
                mSpTopografia.setSelection(infoTerreno.getTopografia().ordinal());
                mSpPedologia.setSelection(infoTerreno.getPedologia().ordinal());
                mSpEstadoConservacao.setSelection(infoTerreno.getEstadoConservacao().ordinal());
                mSpSituacaoDoTerreno.setSelection(infoTerreno.getSituacaoTerreno().ordinal());
            }

        } else {
            this.infoTerreno = new InfoTerreno();
        }
    }

    private void carregarInfoEdificacao(@NonNull Intent dados) {
        if (dados.hasExtra(CHAVE_INFO_EDIFICACAO)) {
            this.infoEdificacao = (InfoEdificacao)
                    dados.getSerializableExtra(CHAVE_INFO_EDIFICACAO);
            if (StringUtils.isNotEmpty(infoEdificacao.toString())) {
                mSpPadraoQualidade.setSelection(infoEdificacao.getPadraoQualidade().ordinal());
                mSpTipoEstrutura.setSelection(infoEdificacao.getTipoEstrutura().ordinal());
                mSpTipoImovel.setSelection(infoEdificacao.getTipoImovel().ordinal());
                mSpUtilizacaoImovel.setSelection(infoEdificacao.getUtilizacaoImovel().ordinal());
            }
        } else {
            this.infoEdificacao = new InfoEdificacao();
        }
    }

    private void carregaBenfeitorias(@NonNull Intent dados) {
        if (dados.hasExtra(CHAVE_BENFEITORIAS)) {
            benfeitorias = (Benfeitorias)
                    dados.getSerializableExtra(CHAVE_BENFEITORIAS);
            if (StringUtils.isNotEmpty(benfeitorias.toString())) {

                mCbAgua.setChecked(benfeitorias.isAgua());
                mCbColetaDeLixo.setChecked(benfeitorias.isColetaLixo());
                mCbEsgoto.setChecked(benfeitorias.isEsgoto());
                mCbGaleriasPluviais.setChecked(benfeitorias.isGalPluviais());
                mCbIlumnacaoPublica.setChecked(benfeitorias.isIluminacaoPublica());
                mCbLimpezaUrbana.setChecked(benfeitorias.isLimpezaUrbana());
                mCbPavimentacao.setChecked(benfeitorias.isPavimentacao());
                mCbRedeEletrica.setChecked(benfeitorias.isRedeEletrica());
                mCbSarjetas.setChecked(benfeitorias.isSarjetas());
                mCbTelefone.setChecked(benfeitorias.isTelefone());
            }
        } else {
            this.benfeitorias = new Benfeitorias();
        }
    }

    private void definirEventos() {
    }

    private InfoUnidade criarInfoUnidade() {
        infoUnidade.setProfundidadePrincipal(verificarDouble(getStringForEditText(mEdProfundidade)));
        infoUnidade.setAreaContruidaUnidade(verificarDouble(getStringForEditText(mEdAreaDaUnidade)));
        infoUnidade.setAreaTerreno(verificarDouble(getStringForEditText(mEdAreaDoTerreno)));
        infoUnidade.setAreaTotalConstruida(verificarDouble(getStringForEditText(mEdAreaTotal)));

        infoUnidade.setLimitacao(
                Limitacao.valueOf(
                        removerCaracterEspecial(getStringForSpinner(mSpLimitacao))));

        infoUnidade.setPatrimonio(
                Patrimonio.valueOf(
                        removerCaracterEspecial(getStringForSpinner(mSpPatrimonio))));

        infoUnidade.setProfundidadePrincipal(verificarDouble(getStringForEditText(mEdProfundidade)));
        infoUnidade.setTestadaPrincipal(verificarDouble(getStringForEditText(mEdTestada)));
        return infoUnidade;
    }

    private InfoTerreno criarInfoTerreno() {
        infoTerreno.setPedologia(Pedologia.valueOf(
                removerCaracterEspecial(getStringForSpinner(mSpPedologia))));
        infoTerreno.setTopografia(
                Topografia.valueOf(removerCaracterEspecial(getStringForSpinner(mSpTopografia))));
        infoTerreno.setEstadoConservacao(
                EstadoConservacao.valueOf(
                        removerCaracterEspecial(
                                getStringForSpinner(mSpEstadoConservacao))));
        infoTerreno.setSituacaoTerreno(
                SituacaoTerreno.valueOf(
                        removerCaracterEspecial(getStringForSpinner(mSpSituacaoDoTerreno))));
        return infoTerreno;
    }

    private InfoEdificacao criarInfoEdificao() {
        infoEdificacao.setTipoEstrutura(
                TipoEstrutura.valueOf(
                        removerCaracterEspecial(getStringForSpinner(mSpTipoEstrutura))));
        infoEdificacao.setTipoImovel(
                TipoImovel.valueOf(
                        removerCaracterEspecial(getStringForSpinner(mSpTipoImovel))));
        infoEdificacao.setUtilizacaoImovel(
                UtilizacaoImovel.valueOf(
                        removerCaracterEspecial(getStringForSpinner(mSpUtilizacaoImovel))));
        infoEdificacao.setPadraoQualidade(
                PadraoQualidade.valueOf(
                        removerCaracterEspecial(getStringForSpinner(mSpPadraoQualidade))));
        return infoEdificacao;
    }

    private Benfeitorias criarBenfeitorias() {
        benfeitorias.setAgua(mCbAgua.isChecked());
        benfeitorias.setColetaLixo(mCbColetaDeLixo.isChecked());
        benfeitorias.setEsgoto(mCbEsgoto.isChecked());
        benfeitorias.setGalPluviais(mCbGaleriasPluviais.isChecked());
        benfeitorias.setIluminacaoPublica(mCbIlumnacaoPublica.isChecked());
        benfeitorias.setLimpezaUrbana(mCbLimpezaUrbana.isChecked());
        benfeitorias.setPavimentacao(mCbPavimentacao.isChecked());
        benfeitorias.setRedeEletrica(mCbRedeEletrica.isChecked());
        benfeitorias.setSarjetas(mCbSarjetas.isChecked());
        benfeitorias.setTelefone(mCbTelefone.isChecked());
        return benfeitorias;
    }

    private Intent abrirFormContribuinte() {
        return new Intent(
                InfoImovelActivity.this, FormContribuinteActivity.class
        );
    }

    private void inicializarComponentes() {
        mEdAreaDoTerreno = findViewById(R.id.ac_info_imovel_area_do_terreno);
        mEdAreaTotal = findViewById(R.id.ac_info_imovel_area_total);
        mEdAreaDaUnidade = findViewById(R.id.ac_info_imovel_area_da_unidade);
        mEdTestada = findViewById(R.id.ac_info_imovel_testada);
        mEdProfundidade = findViewById(R.id.ac_info_imovel_profundidade);
        mEdFracaoIdeal = findViewById(R.id.ac_info_imovel_et_fracao_ideal);

        mSpTopografia = findViewById(R.id.ac_info_imovel_sp_topografia);
        mSpPatrimonio = findViewById(R.id.ac_info_imovel_sp_patrimonio);
        mSpSituacaoDoTerreno = findViewById(R.id.ac_info_imovel_sp_situacao_terreno);
        mSpPedologia = findViewById(R.id.ac_info_imovel_sp_pedologia);
        mSpLimitacao = findViewById(R.id.ac_info_imovel_sp_limitacao);
        mSpTipoImovel = findViewById(R.id.ac_info_imovel_sp_tipo_imovel);
        mSpTipoEstrutura = findViewById(R.id.ac_info_imovel_sp_tipo_estrutura);
        mSpPadraoQualidade = findViewById(R.id.ac_info_imovel_sp_padrao_qualidade);
        mSpUtilizacaoImovel = findViewById(R.id.ac_info_imovel_sp_utilizacao_imovel);
        mSpEstadoConservacao = findViewById(R.id.ac_info_imovel_sp_estado_conservacao);

        mCbAgua = findViewById(R.id.ac_info_imovel_cb_agua);
        mCbSarjetas = findViewById(R.id.ac_info_imovel_cb_sarjetas);
        mCbEsgoto = findViewById(R.id.ac_info_imovel_cb_esgoto);
        mCbRedeEletrica = findViewById(R.id.ac_info_imovel_cb_rede_eletrica);
        mCbLimpezaUrbana = findViewById(R.id.ac_info_imovel_cb_limpeza_urbana);
        mCbIlumnacaoPublica = findViewById(R.id.ac_info_imovel_cb_iluminacao_publica);
        mCbPavimentacao = findViewById(R.id.ac_info_imovel_cb_pavimentacao);
        mCbTelefone = findViewById(R.id.ac_info_imovel_cb_telefone);
        mCbGaleriasPluviais = findViewById(R.id.ac_info_imovel_cb_galerias_pluviais);
        mCbColetaDeLixo = findViewById(R.id.ac_info_imovel_cb_coleta_lixo);
    }
}
