package br.unisinos.hospital.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Quarto.all", query = "SELECT q FROM Quarto q"),
        @NamedQuery(name = "Quarto.byId", query = "SELECT q FROM Quarto q JOIN q.quartoID qID WHERE qID.andar = :pAndar"),
        @NamedQuery(name = "Quarto.byNumero", query = "SELECT q FROM Quarto q JOIN q.quartoID qID WHERE qID.numero = :pNumero")
})
@Table(name = "Quarto")
public class Quarto {

    @EmbeddedId
    private QuartoID quartoID;

    @OneToMany(mappedBy = "quarto")
    @Size(max = 6)
    private Set<Paciente> pacientes = new HashSet<Paciente>();

    @Deprecated
    public Quarto() {
    }

    public Quarto(QuartoID quartoID) {
        this.quartoID = quartoID;
    }

    public QuartoID getQuartoID() {
        return quartoID;
    }

    public void setQuartoID(QuartoID quartoID) {
        this.quartoID = quartoID;
    }

    public Set<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(Set<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public String toString() {
        return quartoID.toString();
    }
}