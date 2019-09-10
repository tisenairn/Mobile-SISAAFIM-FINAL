package br.gov.rn.saogoncalo.sisaafim.room;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import br.gov.rn.saogoncalo.sisaafim.models.administrative.Fiscal;
import br.gov.rn.saogoncalo.sisaafim.models.administrative.Usuario;
import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.*;
import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.ContribuinteEndereco;
import br.gov.rn.saogoncalo.sisaafim.models.forms.BICadastral;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.Benfeitorias;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.ImovelEndereco;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoEdificacao;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoTerreno;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoUnidade;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.LocalizacaoEndereco;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorBairro;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorEdificio;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorEstadoConservacao;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorLimitacao;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorLoteamento;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorPadraoQualidade;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorPatrimonio;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorPedologia;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorSituacaoTerreno;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorTipoEstrutura;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorTipoImovel;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorTipoLogradouro;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorTipoNatureza;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorTipoSubunidade;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorTipoUsuario;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorTopografia;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorUnidadeFederativa;
import br.gov.rn.saogoncalo.sisaafim.room.converter.ConversorUtilizacaoImovel;
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
import br.gov.rn.saogoncalo.sisaafim.room.dao.RoomUsuarioDAO;

@android.arch.persistence.room.Database(
        entities = {
                Imovel.class,
                Usuario.class,
                Fiscal.class,
                ImovelEndereco.class,
                BICadastral.class,
                Contribuinte.class,
                ContribuinteEndereco.class,
                Benfeitorias.class,
                InfoEdificacao.class,
                InfoTerreno.class,
                InfoUnidade.class,
                LocalizacaoEndereco.class
        }
        , version = 9, exportSchema = false)
@TypeConverters(
        {
                ConversorBairro.class, ConversorEdificio.class,
                ConversorEstadoConservacao.class, ConversorLoteamento.class, ConversorLimitacao.class,
                ConversorPadraoQualidade.class, ConversorPatrimonio.class, ConversorPedologia.class,
                ConversorSituacaoTerreno.class, ConversorTipoEstrutura.class, ConversorTipoImovel.class,
                ConversorTipoLogradouro.class, ConversorTipoNatureza.class, ConversorTipoSubunidade.class,
                ConversorTipoUsuario.class, ConversorTopografia.class, ConversorUnidadeFederativa.class,
                ConversorUtilizacaoImovel.class
        }
)
public abstract class Database extends RoomDatabase {
    private static final String NOME_BANCO_DE_DADOS = "sisaafim.db";

    public abstract RoomImovelDAO getRoomImovelDAO();

    public abstract RoomBICadastralDAO getRoomBiCadastralDAO();

    public abstract RoomBenfeitoriasDAO getBenfeitoriasDAO();

    public abstract RoomImovelEnderecoDAO getRoomImovelEnderecoDAO();

    public abstract RoomImovelEnderecoLocalizacaoDAO getRoomImovelEnderecoLocalizacaoDAO();

    public abstract RoomInfoEdificacaoDAO getRoomInfoEdificacaoDAO();

    public abstract RoomInfoTerrenoDAO getRoomInfoTerrenoDAO();

    public abstract RoomInfoUnidadeDAO getRoomInfoUnidadeDAO();

    public abstract RoomContribuinteDAO getRoomContribuinteDAO();

    public abstract RoomContribuinteEnderecoDAO getRoomContribuinteEnderecoDAO();

    public abstract RoomUsuarioDAO getRoomUsuarioDAO();

    public static Database getInstance(Context context) {
        return Room
                .databaseBuilder(context, Database.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }


}
