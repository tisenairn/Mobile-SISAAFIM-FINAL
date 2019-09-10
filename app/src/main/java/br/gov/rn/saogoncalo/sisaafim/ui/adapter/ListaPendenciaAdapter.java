package br.gov.rn.saogoncalo.sisaafim.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.gov.rn.saogoncalo.sisaafim.R;
import br.gov.rn.saogoncalo.sisaafim.models.forms.BICadastral;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;
import br.gov.rn.saogoncalo.sisaafim.room.Database;
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomBICadastralDAO;

public class ListaPendenciaAdapter extends BaseAdapter {

    private final List<BICadastral> biCadastrals = new ArrayList<>();
    private final Context context;
    private RoomBICadastralDAO biCadastralDAO;

    public ListaPendenciaAdapter(Context context) {
        this.context = context;
        biCadastralDAO = Database.getInstance(context).getRoomBiCadastralDAO();
    }

    @Override
    public int getCount() {
        return biCadastrals.size();
    }

    @Override
    public BICadastral getItem(int position) {
        return biCadastrals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return biCadastrals.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View viewCriada = criarView(viewGroup);
        BICadastral biCadastral = biCadastrals.get(position);
        vincula(viewCriada, biCadastral);
        return viewCriada;
    }

    private void vincula(View viewCriada, BICadastral biCadastral) {
        TextView dataCriacao = viewCriada.findViewById(R.id.ac_list_pendencia_item_data_criacao);
        TextView fiscalId = viewCriada.findViewById(R.id.ac_list_pendencia_item_fiscal);
        TextView contribuinte = viewCriada.findViewById(R.id.ac_list_pendencia_item_imovel);

        dataCriacao.setText(biCadastral.getDataAbertura());
        fiscalId.setText(String.valueOf(biCadastral.getFiscalId()));
        contribuinte.setText(biCadastral.getImovelId().toString());
    }

    private View criarView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_pendencia, viewGroup, false);
    }

    public void atualiza(List<BICadastral> biCadastrals){
        this.biCadastrals.clear();
        this.biCadastrals.addAll(biCadastrals);
        notifyDataSetChanged();
    }

    public void remove(BICadastral biCadastral) {
        this.biCadastrals.remove(biCadastral);
        notifyDataSetChanged();
    }
}
