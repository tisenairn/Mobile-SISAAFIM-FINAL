package br.gov.rn.saogoncalo.sisaafim.models.imovel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

import br.gov.rn.saogoncalo.sisaafim.enums.Loteamento;
import br.gov.rn.saogoncalo.sisaafim.enums.TipoNatureza;
import br.gov.rn.saogoncalo.sisaafim.enums.TipoSubunidade;
//import br.gov.rn.saogoncalo.sisaafim.models.contribuinte.Contribuinte;
//import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.Benfeitorias;
//import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.ImovelEndereco;
//import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoEdificacao;
//import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoTerreno;
//import br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais.InfoUnidade;
//
//import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class Imovel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String inscricao;
    private String inscricaoAnterior;
    private String sequencial;
    private String quadra;
    private String lote;
    private TipoNatureza natureza;
    private Loteamento loteamento;

//    @ForeignKey(
//            entity = ImovelEndereco.class,
//            parentColumns = "id",
//            childColumns = "imovelEnderecoId",
//            onUpdate = CASCADE,
//            onDelete = CASCADE)
//    private Long imovelEnderecoId;
//
//    @ForeignKey(
//            entity = Contribuinte.class,
//            parentColumns = "id",
//            childColumns = "contribuinteId",
//            onUpdate = CASCADE,
//            onDelete = CASCADE)
//    private Long contribuinteId;
//
//    @ForeignKey(
//            entity = InfoUnidade.class,
//            parentColumns = "id",
//            childColumns = "infoUnidadeId",
//            onUpdate = CASCADE,
//            onDelete = CASCADE)
//    private Long infoUnidadeId;
//
//    @ForeignKey(
//            entity = InfoEdificacao.class,
//            parentColumns = "id",
//            childColumns = "infoEdificacaoId",
//            onUpdate = CASCADE,
//            onDelete = CASCADE)
//    private Long infoEdificacaoId;
//
//    @ForeignKey(
//            entity = InfoTerreno.class,
//            parentColumns = "id",
//            childColumns = "infoTerrenoId",
//            onUpdate = CASCADE,
//            onDelete = CASCADE)
//    private Long infoTerrenoId;
//
//    @ForeignKey(
//            entity = Benfeitorias.class,
//            parentColumns = "id",
//            childColumns = "benfeitoriasId",
//            onUpdate = CASCADE,
//            onDelete = CASCADE)
//    private Long benfeitoriasId;

    public Imovel() {
    }
//      Caso seja preciso adicionar novamente no construtor.
//
//    Long imovelEndereco,
//    Long contribuinte, Long infoUnidade,
//    Long infoEdificacao, Long infoTerreno,
//    Long benfeitorias

    @Ignore
    public Imovel(Long id, String inscricao, String inscricaoAnterior,
                  TipoNatureza natureza, Loteamento loteamento,
                  TipoSubunidade tipoSubunidade, String sequencial,
                  String quadra, String lote) {
        this.id = id;
        this.inscricao = inscricao;
        this.inscricaoAnterior = inscricaoAnterior;
        this.natureza = natureza;
        this.loteamento = loteamento;
        this.sequencial = sequencial;
        this.quadra = quadra;
        this.lote = lote;
//        this.imovelEnderecoId = imovelEndereco;
//        this.contribuinteId = contribuinte;
//        this.infoUnidadeId = infoUnidade;
//        this.infoEdificacaoId = infoEdificacao;
//        this.infoTerrenoId = infoTerreno;
//        this.benfeitoriasId = benfeitorias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imovel imovel = (Imovel) o;
        return Objects.equals(id, imovel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "id=" + id +
                ", inscricao='" + inscricao + '\'' +
                ", inscricaoAnterior='" + inscricaoAnterior + '\'' +
                ", natureza=" + natureza +
                ", loteamento=" + loteamento +
                ", sequencial=" + sequencial +
                ", quadra='" + quadra + '\'' +
                ", lote='" + lote + '\'' +
//                ", imovelEndereco=" + imovelEnderecoId +
//                ", contribuinteId=" + contribuinteId +
//                ", infoUnidadeId=" + infoUnidadeId +
//                ", infoEdificacaoId=" + infoEdificacaoId +
//                ", infoTerrenoId=" + infoTerrenoId +
//                ", benfeitoriasId=" + benfeitoriasId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public String getInscricaoAnterior() {
        return inscricaoAnterior;
    }

    public void setInscricaoAnterior(String inscricaoAnterior) {
        this.inscricaoAnterior = inscricaoAnterior;
    }

    public TipoNatureza getNatureza() {
        return natureza;
    }

    public void setNatureza(TipoNatureza natureza) {
        this.natureza = natureza;
    }

    public Loteamento getLoteamento() {
        return loteamento;
    }

    public void setLoteamento(Loteamento loteamento) {
        this.loteamento = loteamento;
    }

    public String getSequencial() {
        return sequencial;
    }

    public void setSequencial(String sequencial) {
        this.sequencial = sequencial;
    }

    public String getQuadra() {
        return quadra;
    }

    public void setQuadra(String quadra) {
        this.quadra = quadra;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

//    public Long getImovelId() {
//        return imovelEnderecoId;
//    }
//
//    public void setImovelId(Long imovelEnderecoId) {
//        this.imovelEnderecoId = imovelEnderecoId;
//    }
//
//    public Long getContribuinteId() {
//        return contribuinteId;
//    }
//
//    public void setContribuinteId(Long contribuinteId) {
//        this.contribuinteId = contribuinteId;
//    }
//
//    public Long getInfoUnidadeId() {
//        return infoUnidadeId;
//    }
//
//    public void setInfoUnidadeId(Long infoUnidadeId) {
//        this.infoUnidadeId = infoUnidadeId;
//    }
//
//    public Long getInfoEdificacaoId() {
//        return infoEdificacaoId;
//    }
//
//    public void setInfoEdificacaoId(Long infoEdificacaoId) {
//        this.infoEdificacaoId = infoEdificacaoId;
//    }
//
//    public Long getInfoTerrenoId() {
//        return infoTerrenoId;
//    }
//
//    public void setInfoTerrenoId(Long infoTerrenoId) {
//        this.infoTerrenoId = infoTerrenoId;
//    }
//
//    public Long getBenfeitoriasId() {
//        return benfeitoriasId;
//    }
//
//    public void setBenfeitoriasId(Long benfeitoriasId) {
//        this.benfeitoriasId = benfeitoriasId;
//    }
}
