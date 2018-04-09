package br.unisinos.hospital.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
        @NamedQuery(name = "Especializacao.all", query = "SELECT e FROM Especializacao e"),
        @NamedQuery(name = "Especializacao.byId", query = "SELECT e FROM Especializacao e WHERE e.id = :pId"),
        @NamedQuery(name = "Especializacao.byDescricao", query = "SELECT e FROM Especializacao e WHERE e.descricao like :pDescricao")
})
@Table(name = "Especializacao", uniqueConstraints = @UniqueConstraint(columnNames = {"descricao"}))
public class Especializacao {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "descricao")
    private String descricao;

    public Especializacao() {
    }

    public Especializacao(String descricao) {
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
