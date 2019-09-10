package br.gov.rn.saogoncalo.sisaafim.retrofit;

import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.Contribuinte;
import br.gov.rn.saogoncalo.sisaafim.models.forms.BICadastral;
import br.gov.rn.saogoncalo.sisaafim.retrofit.services.BiCadastralService;
import br.gov.rn.saogoncalo.sisaafim.retrofit.services.ContribuinteService;
import br.gov.rn.saogoncalo.sisaafim.retrofit.services.ImovelService;
import br.gov.rn.saogoncalo.sisaafim.retrofit.services.UsuarioService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SisaafimRetrofit {

    private final String url;
    private final ImovelService imovelService;
    private final ContribuinteService contribuinteService;

    private final UsuarioService usuarioService;
    private final BiCadastralService biCadastralService;

    public SisaafimRetrofit() {

        url = "https://sisaafim-v1.herokuapp.com/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        imovelService = retrofit.create(ImovelService.class);
        contribuinteService = retrofit.create(ContribuinteService.class);
        usuarioService = retrofit.create(UsuarioService.class);
        biCadastralService = retrofit.create(BiCadastralService.class);
    }

    public UsuarioService getUsuarioService() { return usuarioService; }

    public ImovelService getImovelService() { return imovelService; }
    public ContribuinteService getContribuinteService() { return contribuinteService; }
    public BiCadastralService getBiCadastralService() { return biCadastralService; }
}
