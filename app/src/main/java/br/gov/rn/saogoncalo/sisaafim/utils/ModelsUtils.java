package br.gov.rn.saogoncalo.sisaafim.utils;

import android.content.Intent;

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

public abstract class ModelsUtils {

    public static Imovel getImovel(Intent imovel) {
        return imovel.hasExtra(CHAVE_FORM_IMOVEL)
                ? (Imovel) imovel.getSerializableExtra(CHAVE_FORM_IMOVEL)
                : null;
    }

    public static ImovelEndereco getImovelEndereco(Intent imovel) {
        return imovel.hasExtra(CHAVE_FORM_IMOVEL_ENDERECO)
                ? (ImovelEndereco) imovel.getSerializableExtra(CHAVE_FORM_IMOVEL_ENDERECO)
                : null;
    }

    public static LocalizacaoEndereco getImovelLocalizacao(Intent imovel) {
        return imovel.hasExtra(CHAVE_FORM_IMOVEL_ENDERECO_LOCALIZACAO)
                ? (LocalizacaoEndereco) imovel.getSerializableExtra(CHAVE_FORM_IMOVEL_ENDERECO_LOCALIZACAO)
                : null;
    }

    public static Contribuinte getContribuinte(Intent imovel) {
        return imovel.hasExtra(CHAVE_FORM_CONTRIBUINTE)
                ? (Contribuinte) imovel.getSerializableExtra(CHAVE_FORM_CONTRIBUINTE)
                : null;
    }

    public static ContribuinteEndereco getContribuinteEndereco(Intent imovel) {
        return imovel.hasExtra(CHAVE_FORM_CONTRIBUINTE_ENDERECO)
                ? (ContribuinteEndereco) imovel.getSerializableExtra(CHAVE_FORM_CONTRIBUINTE_ENDERECO)
                : null;
    }

    public static Benfeitorias getBenfeitorias(Intent imovel) {
        return imovel.hasExtra(CHAVE_BENFEITORIAS)
                ? (Benfeitorias) imovel.getSerializableExtra(CHAVE_BENFEITORIAS)
                : null;
    }

    public static InfoEdificacao getInfoEdificacao(Intent imovel) {
        return imovel.hasExtra(CHAVE_INFO_EDIFICACAO)
                ? (InfoEdificacao) imovel.getSerializableExtra(CHAVE_INFO_EDIFICACAO)
                : null;
    }

    public static InfoTerreno getInfoTerreno(Intent imovel) {
        return imovel.hasExtra(CHAVE_INFO_TERRENO)
                ? (InfoTerreno) imovel.getSerializableExtra(CHAVE_INFO_TERRENO)
                : null;
    }

    public static InfoUnidade getInfoUnidade(Intent imovel) {
        return imovel.hasExtra(CHAVE_INFO_UNIDADE)
                ? (InfoUnidade) imovel.getSerializableExtra(CHAVE_INFO_UNIDADE)
                : null;
    }
}
