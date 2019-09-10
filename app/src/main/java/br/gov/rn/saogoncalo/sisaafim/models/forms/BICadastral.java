package br.gov.rn.saogoncalo.sisaafim.models.forms;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

import br.gov.rn.saogoncalo.sisaafim.models.administrative.Fiscal;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class BICadastral implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private boolean ativo;

    private String dataAbertura;

    private String dataModificacao;

    private String dataDelecao;

    @ForeignKey(
            entity = Fiscal.class,
            parentColumns = "id",
            childColumns = "fiscalId",
            onDelete = CASCADE,
            onUpdate = CASCADE
    )
    private Long fiscalId;

    @ForeignKey(
            entity = Imovel.class,
            parentColumns = "id",
            childColumns = "imovelId",
            onDelete = CASCADE,
            onUpdate = CASCADE
    )
    private Long imovelId;

    public BICadastral() {
    }

    @Ignore
    public BICadastral(Long id, boolean ativo, String dataAbertura,
                       String dataModificacao, String dataDelecao,
                       Long fiscal, Long imovel) {
        this.id = id;
        this.ativo = ativo;
        this.dataAbertura = dataAbertura;
        this.dataModificacao = dataModificacao;
        this.dataDelecao = dataDelecao;
        this.fiscalId = fiscal;
        this.imovelId = imovel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BICadastral that = (BICadastral) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @NonNull
    @Override
    public String toString() {
        return "BICadastral{" +
                "id=" + id +
                ", ativo=" + ativo +
                ", dataAbertura=" + dataAbertura +
                ", dataModificacao=" + dataModificacao +
                ", dataDelecao=" + dataDelecao +
                ", fiscalId=" + fiscalId +
                ", imovelId=" + imovelId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(String dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    public String getDataDelecao() {
        return dataDelecao;
    }

    public void setDataDelecao(String dataDelecao) {
        this.dataDelecao = dataDelecao;
    }

    public Long getFiscalId() {
        return fiscalId;
    }

    public void setFiscalId(Long fiscalId) {
        this.fiscalId = fiscalId;
    }

    public Long getImovelId() {
        return imovelId;
    }

    public void setImovelId(Long imovelId) {
        this.imovelId = imovelId;
    }
}
