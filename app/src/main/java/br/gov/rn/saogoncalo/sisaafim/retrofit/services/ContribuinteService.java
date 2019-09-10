package br.gov.rn.saogoncalo.sisaafim.retrofit.services;

import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.Contribuinte;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ContribuinteService {

    @POST("contribuinte/salvar")
    Call<Contribuinte> salvar(@Body Contribuinte contribuinte);

}
