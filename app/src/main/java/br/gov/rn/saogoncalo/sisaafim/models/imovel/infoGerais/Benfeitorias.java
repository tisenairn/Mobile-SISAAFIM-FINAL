package br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class Benfeitorias implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private boolean agua;

    private boolean sarjetas;

    private boolean esgoto;

    private boolean redeEletrica;

    private boolean limpezaUrbana;

    private boolean iluminacaoPublica;

    private boolean pavimentacao;

    private boolean telefone;

    private boolean galPluviais;

    private boolean coletaLixo;

    @ForeignKey(
            entity = Imovel.class,
            parentColumns = "id",
            childColumns = "imovelId",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private Long imovelId;

    public Benfeitorias() {
    }

    @Ignore
    public Benfeitorias(Long id, boolean agua, boolean sarjetas, boolean esgoto,
                        boolean redeEletrica, boolean limpezaUrbana,
                        boolean iluminacaoPublica, boolean pavimentacao,
                        boolean telefone, boolean galPluviais, boolean coletaLixo,
                        Long imovelEnderecoId) {
        this.id = id;
        this.agua = agua;
        this.sarjetas = sarjetas;
        this.esgoto = esgoto;
        this.redeEletrica = redeEletrica;
        this.limpezaUrbana = limpezaUrbana;
        this.iluminacaoPublica = iluminacaoPublica;
        this.pavimentacao = pavimentacao;
        this.telefone = telefone;
        this.galPluviais = galPluviais;
        this.coletaLixo = coletaLixo;
        this.imovelId = imovelEnderecoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Benfeitorias that = (Benfeitorias) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Benfeitorias{" +
                "id=" + id +
                ", agua=" + agua +
                ", sarjetas=" + sarjetas +
                ", esgoto=" + esgoto +
                ", redeEletrica=" + redeEletrica +
                ", limpezaUrbana=" + limpezaUrbana +
                ", iluminacaoPublica=" + iluminacaoPublica +
                ", pavimentacao=" + pavimentacao +
                ", telefone=" + telefone +
                ", galPluviais=" + galPluviais +
                ", coletaLixo=" + coletaLixo +
                ", imovelId=" + imovelId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAgua() {
        return agua;
    }

    public void setAgua(boolean agua) {
        this.agua = agua;
    }

    public boolean isSarjetas() {
        return sarjetas;
    }

    public void setSarjetas(boolean sarjetas) {
        this.sarjetas = sarjetas;
    }

    public boolean isEsgoto() {
        return esgoto;
    }

    public void setEsgoto(boolean esgoto) {
        this.esgoto = esgoto;
    }

    public boolean isRedeEletrica() {
        return redeEletrica;
    }

    public void setRedeEletrica(boolean redeEletrica) {
        this.redeEletrica = redeEletrica;
    }

    public boolean isLimpezaUrbana() {
        return limpezaUrbana;
    }

    public void setLimpezaUrbana(boolean limpezaUrbana) {
        this.limpezaUrbana = limpezaUrbana;
    }

    public boolean isIluminacaoPublica() {
        return iluminacaoPublica;
    }

    public void setIluminacaoPublica(boolean iluminacaoPublica) {
        this.iluminacaoPublica = iluminacaoPublica;
    }

    public boolean isPavimentacao() {
        return pavimentacao;
    }

    public void setPavimentacao(boolean pavimentacao) {
        this.pavimentacao = pavimentacao;
    }

    public boolean isTelefone() {
        return telefone;
    }

    public void setTelefone(boolean telefone) {
        this.telefone = telefone;
    }

    public boolean isGalPluviais() {
        return galPluviais;
    }

    public void setGalPluviais(boolean galPluviais) {
        this.galPluviais = galPluviais;
    }

    public boolean isColetaLixo() {
        return coletaLixo;
    }

    public void setColetaLixo(boolean coletaLixo) {
        this.coletaLixo = coletaLixo;
    }

    public Long getImovelId() {
        return imovelId;
    }

    public void setImovelId(Long imovelId) {
        this.imovelId = imovelId;
    }
}
