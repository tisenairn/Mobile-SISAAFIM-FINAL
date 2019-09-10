package br.gov.rn.saogoncalo.sisaafim.asynctask;

import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.Contribuinte;
import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.ContribuinteEndereco;
import br.gov.rn.saogoncalo.sisaafim.models.forms.BICadastral;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.Benfeitorias;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.ImovelEndereco;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoEdificacao;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoTerreno;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoUnidade;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.LocalizacaoEndereco;
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

import static br.gov.rn.saogoncalo.sisaafim.utils.DateUtils.formatLocalDateTimeToDatabaseStyle;

public class EditaBiCadastralTask extends BaseImovelComInfoGeraisTask{

    private RoomContribuinteEnderecoDAO contribuinteEnderecoDAO;
    private RoomInfoUnidadeDAO infoUnidadeDAO;
    private RoomInfoTerrenoDAO infoTerrenoDAO;
    private RoomInfoEdificacaoDAO infoEdificacaoDAO;
    private RoomBenfeitoriasDAO benfeitoriasDAO;
    private RoomImovelEnderecoLocalizacaoDAO imovelEnderecoLocalizacaoDAO;
    private RoomImovelEnderecoDAO imovelEnderecoDAO;
    private RoomBICadastralDAO biCadastralDAO;
    private RoomImovelDAO imovelDAO;
    private RoomContribuinteDAO contribuinteDAO;
    private BICadastral biCadastral;
    private Imovel imovel;
    private ImovelEndereco imovelEndereco;
    private LocalizacaoEndereco localizacaoEnderecoImovel;
    private Contribuinte contribuinte;
    private ContribuinteEndereco contribuinteEndereco;

    private Benfeitorias benfeitorias;
    private InfoTerreno infoTerreno;
    private InfoEdificacao infoEdificacao;
    private InfoUnidade infoUnidade;

    public EditaBiCadastralTask(RoomImovelDAO imovelDAO,
                                RoomBenfeitoriasDAO benfeitoriasDAO,
                                RoomInfoEdificacaoDAO infoEdificacaoDAO,
                                RoomImovelEnderecoDAO imovelEnderecoDAO,
                                RoomImovelEnderecoLocalizacaoDAO enderecoLocalizacaoDAO,
                                RoomInfoTerrenoDAO infoTerrenoDAO,
                                RoomInfoUnidadeDAO infoUnidadeDAO,
                                RoomContribuinteDAO contribuinteDAO,
                                RoomContribuinteEnderecoDAO contribuinteEnderecoDAO,
                                RoomBICadastralDAO biCadastralDAO,
                                Imovel imovel,
                                LocalizacaoEndereco localizacaoEndereco,
                                Contribuinte contribuinte,
                                ContribuinteEndereco contribuinteEndereco,
                                Benfeitorias benfeitorias,
                                InfoTerreno infoTerreno,
                                ImovelEndereco imovelEndereco,
                                InfoEdificacao infoEdificacao,
                                InfoUnidade infoUnidade,
                                BICadastral biCadastral,
                                FinalizadaListener listener) {
        super(listener);
        this.imovelDAO = imovelDAO;
        this.imovelEnderecoDAO = imovelEnderecoDAO;
        this.imovelEnderecoLocalizacaoDAO = enderecoLocalizacaoDAO;
        this.benfeitoriasDAO = benfeitoriasDAO;
        this.infoEdificacaoDAO = infoEdificacaoDAO;
        this.infoTerrenoDAO = infoTerrenoDAO;
        this.infoUnidadeDAO = infoUnidadeDAO;
        this.contribuinteDAO = contribuinteDAO;
        this.contribuinteEnderecoDAO = contribuinteEnderecoDAO;
        this.biCadastralDAO = biCadastralDAO;
        this.imovel = imovel;
        this.localizacaoEnderecoImovel = localizacaoEndereco;
        this.contribuinte = contribuinte;
        this.contribuinteEndereco = contribuinteEndereco;
        this.benfeitorias = benfeitorias;
        this.imovelEndereco = imovelEndereco;
        this.infoEdificacao = infoEdificacao;
        this.infoTerreno = infoTerreno;
        this.infoUnidade = infoUnidade;
        this.biCadastral = biCadastral;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        final String data = formatLocalDateTimeToDatabaseStyle();
        biCadastral.setDataModificacao(data);
        Long imovelId = imovel.getId();
        Long imovelEnderecoId = imovelEndereco.getId();
        Long contribuinteId = contribuinte.getId();

        biCadastral.setImovelId(imovelId);
//        biCadastral.setFiscalId(imovelId);
        biCadastralDAO.edita(biCadastral);

        imovelDAO.edita(imovel);

        imovelEndereco.setImovelId(imovelId);

        imovelEnderecoDAO.edita(imovelEndereco);

        localizacaoEnderecoImovel.setImovelEnderecoId(imovelEnderecoId);
        imovelEnderecoLocalizacaoDAO.edita(localizacaoEnderecoImovel);

        contribuinte.setImovelId(imovelId);
        contribuinteDAO.edita(contribuinte);

        contribuinteEndereco.setContribuinteId(contribuinteId);
        contribuinteEnderecoDAO.edita(contribuinteEndereco);

        benfeitorias.setImovelId(imovelId);
        benfeitoriasDAO.edita(benfeitorias);

        infoEdificacao.setImovelId(imovelId);
        infoEdificacaoDAO.edita(infoEdificacao);

        infoTerreno.setImovelId(imovelId);
        infoTerrenoDAO.edita(infoTerreno);

        infoUnidade.setImovelId(imovelId);
        infoUnidadeDAO.edita(infoUnidade);
        return null;
    }
}
