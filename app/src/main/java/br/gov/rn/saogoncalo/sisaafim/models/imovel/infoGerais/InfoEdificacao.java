package br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

import br.gov.rn.saogoncalo.sisaafim.enums.PadraoQualidade;
import br.gov.rn.saogoncalo.sisaafim.enums.TipoEstrutura;
import br.gov.rn.saogoncalo.sisaafim.enums.TipoImovel;
import br.gov.rn.saogoncalo.sisaafim.enums.UtilizacaoImovel;

import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class InfoEdificacao implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private TipoImovel tipoImovel;
    private TipoEstrutura tipoEstrutura;
    private UtilizacaoImovel utilizacaoImovel;
    private PadraoQualidade padraoQualidade;

    @ForeignKey(
            entity = Imovel.class,
            parentColumns = "id",
            childColumns = "imovelId",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private Long imovelId;

    public InfoEdificacao() {
    }

    @Ignore
    public InfoEdificacao(Long id, TipoImovel tipoImovel, TipoEstrutura tipoEstrutura,
                          UtilizacaoImovel utilizacaoImovel, PadraoQualidade padraoQualidade,
                          Long imovelId) {
        this.id = id;
        this.tipoImovel = tipoImovel;
        this.tipoEstrutura = tipoEstrutura;
        this.utilizacaoImovel = utilizacaoImovel;
        this.padraoQualidade = padraoQualidade;
        this.imovelId = imovelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoEdificacao that = (InfoEdificacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "InfoEdificacao{" +
                "id=" + id +
                ", tipoImovel=" + tipoImovel +
                ", tipoEstrutura=" + tipoEstrutura +
                ", utilizacaoImovel=" + utilizacaoImovel +
                ", padraoQualidade=" + padraoQualidade +
                ", imovelId=" + imovelId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoImovel getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public TipoEstrutura getTipoEstrutura() {
        return tipoEstrutura;
    }

    public void setTipoEstrutura(TipoEstrutura tipoEstrutura) {
        this.tipoEstrutura = tipoEstrutura;
    }

    public UtilizacaoImovel getUtilizacaoImovel() {
        return utilizacaoImovel;
    }

    public void setUtilizacaoImovel(UtilizacaoImovel utilizacaoImovel) {
        this.utilizacaoImovel = utilizacaoImovel;
    }

    public PadraoQualidade getPadraoQualidade() {
        return padraoQualidade;
    }

    public void setPadraoQualidade(PadraoQualidade padraoQualidade) {
        this.padraoQualidade = padraoQualidade;
    }

    public Long getImovelId() {
        return imovelId;
    }

    public void setImovelId(Long imovelId) {
        this.imovelId = imovelId;
    }
}
