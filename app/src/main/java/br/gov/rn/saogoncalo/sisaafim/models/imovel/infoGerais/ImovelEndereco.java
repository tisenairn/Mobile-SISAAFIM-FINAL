package br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

import br.gov.rn.saogoncalo.sisaafim.enums.Bairro;
import br.gov.rn.saogoncalo.sisaafim.enums.Edificio;
import br.gov.rn.saogoncalo.sisaafim.enums.TipoSubunidade;
import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class ImovelEndereco implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String numero;
    private String bloco;
    private TipoSubunidade subunidade;
    private String numeroSubunidade;
    private String cep;
    private Bairro bairro;
    private Edificio edificio;
    private String logradouro;
    private String complemento;

    @ForeignKey(
            entity = Imovel.class,
            parentColumns = "id",
            childColumns = "imovelId",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private Long imovelId;

    public ImovelEndereco() {
    }

    @Ignore
    public ImovelEndereco(Long id, String logradouro, String complemento
            , String numero, String bloco,
                          TipoSubunidade subunidade, String numeroSubunidade,
                          String cep, Bairro bairro, Edificio edificio,
                          Long localizacaoEndereco, Long imovelEnderecoId) {
        this.id = id;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.numero = numero;
        this.bloco = bloco;
        this.subunidade = subunidade;
        this.numeroSubunidade = numeroSubunidade;
        this.cep = cep;
        this.bairro = bairro;
        this.edificio = edificio;
        this.imovelId = imovelEnderecoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImovelEndereco that = (ImovelEndereco) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @NonNull
    @Override
    public String toString() {
        return "ImovelEndereco{" +
                "id=" + id +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", numero=" + numero +
                ", bloco='" + bloco + '\'' +
                ", subunidade=" + subunidade +
                ", numeroSubunidade='" + numeroSubunidade + '\'' +
                ", cep='" + cep + '\'' +
                ", bairro=" + bairro +
                ", edificio=" + edificio +
                ", imovelId=" + imovelId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public TipoSubunidade getSubunidade() {
        return subunidade;
    }

    public void setSubunidade(TipoSubunidade subunidade) {
        this.subunidade = subunidade;
    }

    public String getNumeroSubunidade() {
        return numeroSubunidade;
    }

    public void setNumeroSubunidade(String numeroSubunidade) {
        this.numeroSubunidade = numeroSubunidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public Long getImovelId() {
        return imovelId;
    }

    public void setImovelId(Long imovelId) {
        this.imovelId = imovelId;
    }
}
