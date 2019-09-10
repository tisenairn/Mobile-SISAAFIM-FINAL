package br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

import br.gov.rn.saogoncalo.sisaafim.enums.Limitacao;
import br.gov.rn.saogoncalo.sisaafim.enums.Patrimonio;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class InfoUnidade implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private double areaTerreno;
    private double testadaPrincipal;
    private double profundidadePrincipal;
    private double areaContruidaUnidade;
    private double areaTotalConstruida;

    private Limitacao limitacao;
    private Patrimonio patrimonio;


    @ForeignKey(
            entity = Imovel.class,
            parentColumns = "id",
            childColumns = "imovelId",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private Long imovelId;

    public InfoUnidade() {
    }

    @Ignore
    public InfoUnidade(Long id, double areaTerreno, double testadaPrincipal,
                       double profundidadePrincipal, double areaContruidaUnidade,
                       double areaTotalConstruida, Limitacao limitacao,
                       Patrimonio patrimonio, Long imovelId) {
        this.id = id;
        this.areaTerreno = areaTerreno;
        this.testadaPrincipal = testadaPrincipal;
        this.profundidadePrincipal = profundidadePrincipal;
        this.areaContruidaUnidade = areaContruidaUnidade;
        this.areaTotalConstruida = areaTotalConstruida;
        this.limitacao = limitacao;
        this.patrimonio = patrimonio;
        this.imovelId = imovelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoUnidade that = (InfoUnidade) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "InfoUnidade{" +
                "id=" + id +
                ", areaTerreno=" + areaTerreno +
                ", testadaPrincipal=" + testadaPrincipal +
                ", profundidadePrincipal=" + profundidadePrincipal +
                ", areaContruidaUnidade=" + areaContruidaUnidade +
                ", areaTotalConstruida=" + areaTotalConstruida +
                ", limitacao=" + limitacao +
                ", patrimonio=" + patrimonio +
                ", imovelId=" + imovelId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAreaTerreno() {
        return areaTerreno;
    }

    public void setAreaTerreno(double areaTerreno) {
        this.areaTerreno = areaTerreno;
    }

    public double getTestadaPrincipal() {
        return testadaPrincipal;
    }

    public void setTestadaPrincipal(double testadaPrincipal) {
        this.testadaPrincipal = testadaPrincipal;
    }

    public double getProfundidadePrincipal() {
        return profundidadePrincipal;
    }

    public void setProfundidadePrincipal(double profundidadePrincipal) {
        this.profundidadePrincipal = profundidadePrincipal;
    }

    public double getAreaContruidaUnidade() {
        return areaContruidaUnidade;
    }

    public void setAreaContruidaUnidade(double areaContruidaUnidade) {
        this.areaContruidaUnidade = areaContruidaUnidade;
    }

    public double getAreaTotalConstruida() {
        return areaTotalConstruida;
    }

    public void setAreaTotalConstruida(double areaTotalConstruida) {
        this.areaTotalConstruida = areaTotalConstruida;
    }

    public Limitacao getLimitacao() {
        return limitacao;
    }

    public void setLimitacao(Limitacao limitacao) {
        this.limitacao = limitacao;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Long getImovelId() {
        return imovelId;
    }

    public void setImovelId(Long imovelId) {
        this.imovelId = imovelId;
    }
}
