package br.gov.rn.saogoncalo.sisaafim.models.contribuinte;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

import br.gov.rn.saogoncalo.sisaafim.models.imovel.Imovel;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class Contribuinte implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String cpfCnpj;
    private String rg;
    private String nome;
    private String email;
    private String telefoneResidencial;
    private String telefoneComercial;
    private String fax;
    private String celular;
    private String dataNascimento;
    private String cnh;
    private String orgaoExpedidor;

    @ForeignKey(
            entity = Imovel.class,
            parentColumns = "id",
            childColumns = "imovelId",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private Long imovelId;

//    @ForeignKey(
//            entity = ContribuinteEndereco.class,
//            parentColumns = "id",
//            childColumns = "contribuinteEnderecoId",
//            onDelete = CASCADE,
//            onUpdate = CASCADE
//    }
//    private Long contribuinteEnderecoId;

    public Contribuinte() {
    }

    @Ignore
    public Contribuinte(Long id, String cpfCnpj, String nome, String email, String rg,
                        String telefoneResidencial, String telefoneComercial,
                        String fax, String celular, String cnh, String orgaoExpedidor,
                        String dataNascimento, Long imovelId) {
        this.id = id;
        this.cpfCnpj = cpfCnpj;
        this.rg = rg;
        this.nome = nome;
        this.email = email;
        this.orgaoExpedidor = orgaoExpedidor;
        this.telefoneResidencial = telefoneResidencial;
        this.telefoneComercial = telefoneComercial;
        this.cnh = cnh;
        this.fax = fax;
        this.celular = celular;
        this.dataNascimento = dataNascimento;
        this.imovelId = imovelId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contribuinte that = (Contribuinte) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Contribuinte{" +
                "id=" + id +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", rg='" + rg + '\'' +
                ", orgaoExpedidor='" + orgaoExpedidor + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cnh='" + cnh + '\'' +
                ", telefoneResidencial='" + telefoneResidencial + '\'' +
                ", telefoneComercial='" + telefoneComercial + '\'' +
                ", fax='" + fax + '\'' +
                ", celular='" + celular + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", imovelId=" + imovelId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Long getImovelId() {
        return imovelId;
    }

    public void setImovelId(Long imovelId) {
        this.imovelId = imovelId;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getOrgaoExpedidor() {
        return orgaoExpedidor;
    }

    public void setOrgaoExpedidor(String orgaoExpedidor) {
        this.orgaoExpedidor = orgaoExpedidor;
    }
}
