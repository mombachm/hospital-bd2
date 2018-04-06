package br.unisinos.hospital.models;

import javax.persistence.*;

@Entity
@Table(name = "Quarto")
public class Quarto {

    @Id
    @Column(name = "Numero", unique = true)
    private String numero;

    @Column(name = "Nome")
    private String andar;

    public Quarto(String numero, String andar) {
        this.numero = numero;
        this.andar = andar;
    }

    public Quarto() {
    }

    public String getNumero() {
        return this.numero;
    }

    public void setAndar(String andar) {
        this.andar = andar;
    }

    public String getAndar() {
        return this.andar;
    }

}