package br.gov.rn.saogoncalo.sisaafim.models.administrative;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class Fiscal implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String observacoes;

    @ForeignKey(entity = Usuario.class, parentColumns = "id", childColumns = "usuarioId",
                onDelete = CASCADE, onUpdate = CASCADE)
    private Long usuarioId;

    public Fiscal() {
    }

    @Ignore
    public Fiscal(Long id, String observacoes, Long usuarioId) {
        this.id = id;
        this.observacoes = observacoes;
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fiscal fiscal = (Fiscal) o;
        return Objects.equals(id, fiscal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Fiscal{" +
                "id=" + id +
                ", observacoes='" + observacoes + '\'' +
                ", usuarioId=" + usuarioId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
