package br.gov.rn.saogoncalo.sisaafim.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.ArrayAdapter;

import br.gov.rn.saogoncalo.sisaafim.R;

public abstract class CarregarSpinner {

    private static Context context;

    private static void carregarSpinner(@NonNull AppCompatSpinner spinner) {
        int tipos_array = 0;
        switch (spinner.getId()) {
            case R.id.ac_form_imovel_sp_endereco_natureza:
                tipos_array = R.array.tipos_natureza_array;
                break;
            case R.id.ac_form_imovel_sp_endereco_bairro:
            case R.id.ac_form_contribuinte_sp_bairro:
                tipos_array = R.array.bairros_array;
                break;
            case R.id.ac_form_imovel_sp_endereco_loteamento:
                tipos_array = R.array.loteamentos_array;
                break;
            case R.id.ac_form_imovel_sp_endereco_subunidade:
            case R.id.ac_form_contribuinte_sp_tipo_subunidade:
                tipos_array = R.array.tipos_subunidades_array;
                break;
            case R.id.ac_form_imovel_sp_endereco_edificio:
                tipos_array = R.array.edificios_array;
                break;
            case R.id.ac_form_contribuinte_sp_uf:
                tipos_array = R.array.unidades_federativas_array;
                break;
            case R.id.ac_info_imovel_sp_pedologia:
                tipos_array = R.array.pedologias_array;
                break;
            case R.id.ac_info_imovel_sp_patrimonio:
                tipos_array = R.array.patrimonios_array;
                break;
            case R.id.ac_info_imovel_sp_padrao_qualidade:
                tipos_array = R.array.padroes_qualidade_array;
                break;
            case R.id.ac_info_imovel_sp_estado_conservacao:
                tipos_array = R.array.estados_conservacao_array;
                break;
            case R.id.ac_info_imovel_sp_limitacao:
                tipos_array = R.array.limitacoes_array;
                break;
            case R.id.ac_info_imovel_sp_situacao_terreno:
                tipos_array = R.array.situacoes_terreno_array;
                break;
            case R.id.ac_info_imovel_sp_tipo_estrutura:
                tipos_array = R.array.tipos_estruturas_array;
                break;
            case R.id.ac_info_imovel_sp_tipo_imovel:
                tipos_array = R.array.tipos_imoveis_array;
                break;
            case R.id.ac_info_imovel_sp_topografia:
                tipos_array = R.array.tipos_topografias_array;
                break;
            case R.id.ac_info_imovel_sp_utilizacao_imovel:
                tipos_array = R.array.ultilizacoes_imovel_array;
                break;
            case R.id.ac_form_contribuinte_sp_tipo_logradouro:
                tipos_array = R.array.tipos_logradouros_array;
                break;
            default:
                break;
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                tipos_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_list);
        spinner.setAdapter(adapter);
    }

    public static void spinners( Context context, @NonNull AppCompatSpinner... spinners) {
        CarregarSpinner.context = context;
        for (AppCompatSpinner spinner: spinners
        ) {
            carregarSpinner(spinner);
        }
    }
}
