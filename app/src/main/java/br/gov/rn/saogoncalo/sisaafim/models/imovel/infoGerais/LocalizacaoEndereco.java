package br.gov.rn.saogoncalo.sisaafim.models.imovel.infoGerais;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class LocalizacaoEndereco implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String latitude;
    private String longitude;

    @ForeignKey(
            entity = ImovelEndereco.class,
            parentColumns = "id",
            childColumns = "imovelId",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private Long imovelEnderecoId;

    public LocalizacaoEndereco() {
    }

    @Ignore
    public LocalizacaoEndereco(Long id, String latitude, String longitude,
                               Long imovelEnderecoId) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Long getImovelEnderecoId() {
        return imovelEnderecoId;
    }

    public void setImovelEnderecoId(Long imovelEnderecoId) {
        this.imovelEnderecoId = imovelEnderecoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalizacaoEndereco that = (LocalizacaoEndereco) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "LocalizacaoEndereco{" +
                "id=" + id +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", imovelEnderecoId='" + imovelEnderecoId + '\'' +
                '}';
    }
}
