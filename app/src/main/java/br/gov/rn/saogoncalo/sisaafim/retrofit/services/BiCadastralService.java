package br.gov.rn.saogoncalo.sisaafim.retrofit.services;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.forms.BICadastral;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BiCadastralService {

    @GET("bic/listar")
    Call<List<BICadastral>> buscaTodos();

    @GET("bic/listar/{id}")
    Call<BICadastral> buscaPorId(@Path("id") int id);

    @POST("bic/salvar")
    Call<BICadastral> salvar(@Body BICadastral biCadastral);
}
