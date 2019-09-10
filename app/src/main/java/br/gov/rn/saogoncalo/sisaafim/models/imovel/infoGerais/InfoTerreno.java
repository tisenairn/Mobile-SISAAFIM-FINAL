package br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

import br.gov.rn.saogoncalo.sisaafim.enums.EstadoConservacao;
import br.gov.rn.saogoncalo.sisaafim.enums.Pedologia;
import br.gov.rn.saogoncalo.sisaafim.enums.SituacaoTerreno;
import br.gov.rn.saogoncalo.sisaafim.enums.Topografia;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class InfoTerreno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private Pedologia pedologia;
    private Topografia topografia;
    private EstadoConservacao estadoConservacao;
    private SituacaoTerreno situacaoTerreno;

    @ForeignKey(
            entity = Imovel.class,
            parentColumns = "id",
            childColumns = "imovelId",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private Long imovelId;

    public InfoTerreno() {
    }

    @Ignore
    public InfoTerreno(Long id, Pedologia pedologia, Topografia topografia,
                       EstadoConservacao estadoConservacao, SituacaoTerreno situacaoTerreno,
                       Long imovelId) {
        this.id = id;
        this.pedologia = pedologia;
        this.topografia = topografia;
        this.estadoConservacao = estadoConservacao;
        this.situacaoTerreno = situacaoTerreno;
        this.imovelId = imovelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoTerreno that = (InfoTerreno) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "InfoTerreno{" +
                "id=" + id +
                ", pedologia=" + pedologia +
                ", topografia=" + topografia +
                ", estadoConservacao=" + estadoConservacao +
                ", situacaoTerreno=" + situacaoTerreno +
                ", imovelId=" + imovelId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedologia getPedologia() {
        return pedologia;
    }

    public void setPedologia(Pedologia pedologia) {
        this.pedologia = pedologia;
    }

    public Topografia getTopografia() {
        return topografia;
    }

    public void setTopografia(Topografia topografia) {
        this.topografia = topografia;
    }

    public EstadoConservacao getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(EstadoConservacao estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public SituacaoTerreno getSituacaoTerreno() {
        return situacaoTerreno;
    }

    public void setSituacaoTerreno(SituacaoTerreno situacaoTerreno) {
        this.situacaoTerreno = situacaoTerreno;
    }

    public Long getImovelId() {
        return imovelId;
    }

    public void setImovelId(Long imovelId) {
        this.imovelId = imovelId;
    }
}
