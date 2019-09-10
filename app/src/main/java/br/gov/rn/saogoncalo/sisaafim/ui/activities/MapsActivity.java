package br.gov.rn.saogoncalo.sisaafim.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.commons.lang3.StringUtils;

import br.gov.rn.saogoncalo.sisaafim.R;
import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.Contribuinte;
import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.ContribuinteEndereco;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.Benfeitorias;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.ImovelEndereco;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoEdificacao;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoTerreno;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoUnidade;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.LocalizacaoEndereco;

import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_BENFEITORIAS;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_CONTRIBUINTE;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_CONTRIBUINTE_ENDERECO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_IMOVEL;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_IMOVEL_ENDERECO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_FORM_IMOVEL_ENDERECO_LOCALIZACAO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_INFO_EDIFICACAO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_INFO_TERRENO;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_INFO_UNIDADE;
import static br.gov.rn.saogoncalo.sisaafim.utils.ChavesPutExtras.CHAVE_VISUALIZAR;
import static br.gov.rn.saogoncalo.sisaafim.utils.ModelsUtils.getBenfeitorias;
import static br.gov.rn.saogoncalo.sisaafim.utils.ModelsUtils.getContribuinte;
import static br.gov.rn.saogoncalo.sisaafim.utils.ModelsUtils.getContribuinteEndereco;
import static br.gov.rn.saogoncalo.sisaafim.utils.ModelsUtils.getImovel;
import static br.gov.rn.saogoncalo.sisaafim.utils.ModelsUtils.getImovelEndereco;
import static br.gov.rn.saogoncalo.sisaafim.utils.ModelsUtils.getImovelLocalizacao;
import static br.gov.rn.saogoncalo.sisaafim.utils.ModelsUtils.getInfoEdificacao;
import static br.gov.rn.saogoncalo.sisaafim.utils.ModelsUtils.getInfoTerreno;
import static br.gov.rn.saogoncalo.sisaafim.utils.ModelsUtils.getInfoUnidade;

public class MapsActivity extends FragmentActivity implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback {

    final LatLng CENTER = new LatLng(-5.7892443, -35.3360807);
    private GoogleMap mMap;
    private FloatingActionButton mFabSalvarLocalizacao;
    private LatLng mLatLng = null;

    private Imovel imovel = null;
    private ImovelEndereco imovelEndereco;
    private LocalizacaoEndereco mLocalizacaoEndereco = null;

    private Contribuinte contribuinte;
    private ContribuinteEndereco contribuinteEndereco;

    private Benfeitorias benfeitorias = null;
    private InfoEdificacao infoEdificacao = null;
    private InfoTerreno infoTerreno = null;
    private InfoUnidade infoUnidade = null;
    private boolean visualizar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        java.util.Objects.requireNonNull(mapFragment).getMapAsync(this);
        inicializarComponetes();
        receberDados();
    }

    private void receberDados() {
        final Intent dados = getIntent();
        if (getImovel(dados) != null) {
            imovel = getImovel(dados);
        }
        if (dados.hasExtra(CHAVE_VISUALIZAR)) {
            visualizar = dados.getBooleanExtra(CHAVE_VISUALIZAR, false);
            mFabSalvarLocalizacao.setImageResource(R.drawable.ic_action_voltar);
        }

        if (getImovelEndereco(dados) != null) {
            imovelEndereco = getImovelEndereco(dados);
        }

        if (getImovelLocalizacao(dados) != null) {
            mLocalizacaoEndereco = getImovelLocalizacao(dados);
            if (mLocalizacaoEndereco != null) {
                if (!Objects.equal(mLocalizacaoEndereco.getLatitude(), null) &&
                        StringUtils.isNotEmpty(mLocalizacaoEndereco.getLatitude()) ||
                        !Objects.equal(mLocalizacaoEndereco.getLongitude(), null) &&
                                StringUtils.isNotEmpty(mLocalizacaoEndereco.getLongitude())) {

                    mLatLng = new LatLng(Double.valueOf(mLocalizacaoEndereco.getLatitude()),
                            Double.valueOf(mLocalizacaoEndereco.getLongitude()));
                }
            }
        } else {
            mLocalizacaoEndereco = new LocalizacaoEndereco();
        }

        if (getContribuinte(dados) != null) {
            contribuinte = getContribuinte(dados);
        }

        if (getContribuinteEndereco(dados) != null) {
            contribuinteEndereco = getContribuinteEndereco(dados);
        }

        if (getBenfeitorias(dados) != null) {
            benfeitorias = getBenfeitorias(dados);
        }

        if (getInfoEdificacao(dados) != null) {
            infoEdificacao = getInfoEdificacao(dados);
        }

        if (getInfoTerreno(dados) != null) {
            infoTerreno = getInfoTerreno(dados);
        }

        if (getInfoUnidade(dados) != null) {
            infoUnidade = getInfoUnidade(dados);
        }

    }

    private void inicializarComponetes() {

        mFabSalvarLocalizacao = findViewById(R.id.ac_map_fab_salvar);
    }

    private void definirEventos() {

        mFabSalvarLocalizacao.setOnClickListener(v -> {
            if (visualizar) {
                finish();
            } else {
                abrirFormImovel();
            }
        });
        if (visualizar) {
            Toast.makeText(this, "Modo visualizar!", Toast.LENGTH_SHORT).show();
        } else {
            mMap.setOnMapClickListener(latLng -> {
                if (mLatLng == null) {
                    mLatLng = new LatLng(latLng.latitude, latLng.longitude);
                    mLocalizacaoEndereco.setLatitude(String.valueOf(mLatLng.latitude));
                    mLocalizacaoEndereco.setLongitude(String.valueOf(mLatLng.longitude));
                    mMap.addMarker(new MarkerOptions().position(latLng)
                            .title("Imóvel: " + latLng.toString()));
                } else {
                    mMap.clear();
                    mLatLng = new LatLng(latLng.latitude, latLng.longitude);
                    mLocalizacaoEndereco.setLatitude(String.valueOf(mLatLng.latitude));
                    mLocalizacaoEndereco.setLongitude(String.valueOf(mLatLng.longitude));
                    mMap.addMarker(new MarkerOptions().position(mLatLng)
                            .title("Imóvel: " + mLatLng.toString()));
                }
            });
        }
    }

    private void abrirFormImovel() {

        if (mLatLng != null) {
            //TODO: Passar os dados da latLng para o formulário
            final Intent intent = new Intent(MapsActivity.this, InfoImovelActivity.class);

            intent.putExtra(CHAVE_FORM_IMOVEL, imovel);
            intent.putExtra(CHAVE_FORM_IMOVEL_ENDERECO, imovelEndereco);
            intent.putExtra(CHAVE_FORM_IMOVEL_ENDERECO_LOCALIZACAO, mLocalizacaoEndereco);

            Log.i("Localização", "abrirFormImovel: " + mLocalizacaoEndereco);

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
            finish();
        } else {
            Toast.makeText(
                    MapsActivity.this,
                    "Adicione a localização",
                    Toast.LENGTH_LONG)
                    .show();
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in CENTER and move the camera

        if (mLatLng != null)
            mMap.addMarker(new MarkerOptions().position(mLatLng).title(mLatLng.toString()));

        mMap.setMapType(8);
        mMap.moveCamera(CameraUpdateFactory.zoomTo(12));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(CENTER), 1750, null);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);

        }
        definirEventos();

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return true;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        final LatLng mMyLocation = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mMyLocation));
    }

}
