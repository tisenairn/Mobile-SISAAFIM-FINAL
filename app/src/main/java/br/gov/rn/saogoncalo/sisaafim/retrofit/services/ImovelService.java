package br.gov.rn.saogoncalo.sisaafim.retrofit.services;

import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.Contribuinte;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ImovelService {

    @GET("imovel/listar")
    Call<List<Imovel>> buscaTodos();

    @POST("imovel/salvar")
    Call<Imovel> salvar(@Body Imovel imovel);

    @GET("imovel/{id}")
    Call<Imovel> buscaPorId(@Path("id") Long imovelId);
}
