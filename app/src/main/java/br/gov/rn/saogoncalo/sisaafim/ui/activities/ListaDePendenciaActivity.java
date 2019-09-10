package br.gov.rn.saogoncalo.sisaafim.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.R;
import br.gov.rn.saogoncalo.sisaafim.asynctask.BaseAsyncTask;
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
import br.gov.rn.saogoncalo.sisaafim.ui.ListaPendenciaView;
import br.gov.rn.saogoncalo.sisaafim.ui.adapter.ListaPendenciaAdapter;
import retrofit2.Call;
import retrofit2.Response;

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

public class ListaDePendenciaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView mLvListaDePendencias;

    private static final int REQUEST_CODE = 1;
    private FloatingActionButton mFab;
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

    private ListaPendenciaView listaPendenciaView;
    private ListaPendenciaAdapter adapter;
    private RoomImovelDAO roomImovelDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_de_pendencia);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String APPTITLE = "LISTA DE BIC";
        setTitle(APPTITLE);
        listaPendenciaView = new ListaPendenciaView(this);

        inicializarComponentes();
        definirEventos();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        configurarLista();
        verificarPermissoes();
//        receberDadosDaApi();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaPendenciaView.atualizaImovel();
    }

    private void configurarLista() {
        mLvListaDePendencias = findViewById(R.id.ac_lista_de_pendencia_lv_pendencias);
        listaPendenciaView.configuraAdapter(mLvListaDePendencias);
        configuraListenerDeCliquePorItem(mLvListaDePendencias);
        registerForContextMenu(mLvListaDePendencias);
    }

    private void configuraListenerDeCliquePorItem(ListView mLvListaDePendencias) {
        mLvListaDePendencias.setOnItemClickListener((adapterView, view, posicao, id) -> new AlertDialog
                .Builder(this)
                .setTitle("Visualizar BIC")
                .setMessage("Tem certeza que quer Visualizar o Boletim de Inscrição Cadastral?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    Intent intent = new Intent(ListaDePendenciaActivity.this,
                            VisualizarBicActivity.class);
                    BICadastral bicEscolhido = (BICadastral) adapterView.getItemAtPosition(posicao);
                    abreformularioModoVisualizarBicadastral(bicEscolhido, intent);
                })
                .setNegativeButton("Não", null)
                .show());
    }

    private void receberDadosDaApi() {
        BiCadastralService biCadastralService = new SisaafimRetrofit().getBiCadastralService();
        Call<List<BICadastral>> call = biCadastralService.buscaTodos();

        new BaseAsyncTask<>(() -> {
            try {
                Response<List<BICadastral>> response = call.execute();
                List<BICadastral> biCadastrals = response.body();

                Imovel imovel;
                LocalizacaoEndereco imovelEnderecoLocalizacao;
                Contribuinte contribuinte;
                ContribuinteEndereco contribuinteEndereco;
                Benfeitorias benfeitorias;
                InfoTerreno infoTerreno;
                ImovelEndereco imovelEndereco;
                InfoEdificacao infoEdificacao;
                InfoUnidade infoUnidade;
                BICadastral biCadastral;
                ImovelService imovelService = new SisaafimRetrofit().getImovelService();

                for (BICadastral biCadastralCall : biCadastrals
                ) {
                    Call<Imovel> imovelCall = imovelService.buscaPorId(biCadastralCall.getImovelId());
                    if (imovelCall.execute().isSuccessful()) {
                        imovel = imovelCall.execute().body();
                    }
                }

                return biCadastrals;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }, biCadastrals -> {
            if (biCadastrals != null) {
//                adapter.atualiza(biCadastrals);
                Toast.makeText(this, biCadastrals.toString(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Erro ao buscar Bic", Toast.LENGTH_LONG).show();
            }
        }).execute();
    }

    private void abreformularioModoVisualizarBicadastral(BICadastral bicEscolhido, Intent intent) {
        intent.putExtra(CHAVE_BIC, bicEscolhido);
        startActivity(intent);
    }

    private void inicializarComponentes() {
        mFab = findViewById(R.id.fab);
        inicializarDAOS();
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

    private void definirEventos() {
        mFab.setOnClickListener(view -> Snackbar.make(view, "Trocar por ação!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_lista_pendencias_menu_remover:
                listaPendenciaView.confirmaRemocao(item);
                break;
            case R.id.activity_lista_pendencias_menu_editar:
                listaPendenciaView.confirmaEdicao(item);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.activity_lista_pendencias_menu_remover) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.lista_de_pendencia, menu);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pendencias) {
            // TODO: Lista de pendências
        } else if (id == R.id.nav_consultar_bic) {
            startActivity(
                    new Intent(ListaDePendenciaActivity.this, FormImovelActivity.class)
            );
            finish();
        } else if (id == R.id.nav_sair_app) {
            startActivity(
                    new Intent(ListaDePendenciaActivity.this, LoginActivity.class)
            );
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void verificarPermissoes() {
        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[0]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[1]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[2]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[3]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[4]) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this.getApplicationContext(),
                permissions[5]) == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(ListaDePendenciaActivity.this,
                    permissions, REQUEST_CODE);
        }

    }
}
