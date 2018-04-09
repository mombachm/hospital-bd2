package br.unisinos.hospital.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class QuartoID implements Serializable {

    @NotNull
    @Column(name = "numero")
    private String numero;

    @NotNull
    @Column(name = "andar")
    private String andar;

    @Deprecated
    public QuartoID() {
    }

    public QuartoID(String numero, String andar) {
        this.numero = numero;
        this.andar = andar;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getAndar() {
        return andar;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    @Override
    public String toString() {
        return "Quarto: " +
                "\nAndar: " + numero +
                "\nNumero: " + andar;
    }
}
