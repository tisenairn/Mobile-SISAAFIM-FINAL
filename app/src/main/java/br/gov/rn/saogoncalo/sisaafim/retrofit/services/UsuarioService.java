package br.gov.rn.saogoncalo.sisaafim.retrofit.services;

import br.gov.rn.saogoncalo.sisaafim.models.administrative.Usuario;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuarioService {

    @GET("imovel/listar/{id}")
    Call<Usuario> buscaPorId(@Path("id") int matricula);
}
