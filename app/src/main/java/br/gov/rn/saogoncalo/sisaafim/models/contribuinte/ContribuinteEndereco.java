package br.gov.rn.saogoncalo.sisaafim.models.contribuinte;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

import br.gov.rn.saogoncalo.sisaafim.enums.Bairro;
import br.gov.rn.saogoncalo.sisaafim.enums.TipoLogradouro;
import br.gov.rn.saogoncalo.sisaafim.enums.TipoSubunidade;
import br.gov.rn.saogoncalo.sisaafim.enums.UnidadeFederativa;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class ContribuinteEndereco implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private boolean possuiEnderecoCorrespondecia;

    private String caixaPostal;
    private String codLogradouro;
    private TipoLogradouro tipoLogradouro;
    private String nomeLogradouro;
    private int  numero;
    private String bloco;
    private TipoSubunidade tipoSubunidade;
    private int numeroSubunidade;
    private String cep;
    private Bairro bairro;
    private String cidade;
    private UnidadeFederativa uf;

    @ForeignKey(
            entity = Contribuinte.class,
            parentColumns = "id",
            childColumns = "contribuinteId",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private Long contribuinteId;

    public ContribuinteEndereco() {
    }

    @Ignore
    public ContribuinteEndereco(Long id, boolean possuiEnderecoCorrespondecia,
                                String caixaPostal, String codLogradouro,
                                TipoLogradouro tipoLogradouro, String nomeLogradouro,
                                int numero, String bloco, TipoSubunidade tipoSubunidade,
                                int numeroSubunidade, String cep, Bairro bairro,
                                String cidade, UnidadeFederativa uf, Long contribuinteId) {
        this.id = id;
        this.possuiEnderecoCorrespondecia = possuiEnderecoCorrespondecia;
        this.caixaPostal = caixaPostal;
        this.codLogradouro = codLogradouro;
        this.tipoLogradouro = tipoLogradouro;
        this.nomeLogradouro = nomeLogradouro;
        this.numero = numero;
        this.bloco = bloco;
        this.tipoSubunidade = tipoSubunidade;
        this.numeroSubunidade = numeroSubunidade;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.contribuinteId = contribuinteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContribuinteEndereco that = (ContribuinteEndereco) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ContribuinteEndereco{" +
                "id=" + id +
                ", possuiEnderecoCorrespondecia=" + possuiEnderecoCorrespondecia +
                ", caixaPostal='" + caixaPostal + '\'' +
                ", codLogradouro='" + codLogradouro + '\'' +
                ", tipoLogradouro=" + tipoLogradouro +
                ", nomeLogradouro='" + nomeLogradouro + '\'' +
                ", numero=" + numero +
                ", bloco='" + bloco + '\'' +
                ", tipoSubunidade=" + tipoSubunidade +
                ", numeroSubunidade=" + numeroSubunidade +
                ", cep='" + cep + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf=" + uf +
                ", contribuinteId=" + contribuinteId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPossuiEnderecoCorrespondecia() {
        return possuiEnderecoCorrespondecia;
    }

    public void setPossuiEnderecoCorrespondecia(boolean possuiEnderecoCorrespondecia) {
        this.possuiEnderecoCorrespondecia = possuiEnderecoCorrespondecia;
    }

    public String getCaixaPostal() {
        return caixaPostal;
    }

    public void setCaixaPostal(String caixaPostal) {
        this.caixaPostal = caixaPostal;
    }

    public String getCodLogradouro() {
        return codLogradouro;
    }

    public void setCodLogradouro(String codLogradouro) {
        this.codLogradouro = codLogradouro;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public TipoSubunidade getTipoSubunidade() {
        return tipoSubunidade;
    }

    public void setTipoSubunidade(TipoSubunidade tipoSubunidade) {
        this.tipoSubunidade = tipoSubunidade;
    }

    public int getNumeroSubunidade() {
        return numeroSubunidade;
    }

    public void setNumeroSubunidade(int numeroSubunidade) {
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public UnidadeFederativa getUf() {
        return uf;
    }

    public void setUf(UnidadeFederativa uf) {
        this.uf = uf;
    }

    public Long getContribuinteId() {
        return contribuinteId;
    }

    public void setContribuinteId(Long contribuinteId) {
        this.contribuinteId = contribuinteId;
    }
}
