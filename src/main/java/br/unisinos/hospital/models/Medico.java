package br.unisinos.hospital.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Medico.all", query = "SELECT m FROM Medico m"),
        @NamedQuery(name = "Medico.byId", query = "SELECT m FROM Medico m WHERE m.id = :pId"),
        @NamedQuery(name = "Medico.byCrm", query = "SELECT m FROM Medico m WHERE m.crm = :pCrm"),
        @NamedQuery(name = "Medico.byNome", query = "SELECT m FROM Medico m WHERE m.nome = :pNome"),
        @NamedQuery(name = "Medico.byEspecializacao", query = "SELECT m FROM Medico m JOIN m.especializacao e WHERE e.id = :pEspecializacao")
})
@Table(name = "Medico", uniqueConstraints = @UniqueConstraint(columnNames = {"CRM"}))

public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMedico")
    private long id;

    @NotNull
    @Column(name = "CRM")
    private String crm;

    @NotNull
    @Column(name = "Nome")
    private String nome;

    @NotNull
    @Column(name = "Salario")
    private Double salario;

    @OneToMany(mappedBy="medico")
    private Set<Consulta> consultas = new HashSet<Consulta>();

    @NotNull
    @ManyToOne
    private Especializacao especializacao;

    public Medico(String crm, String nome, Double salario, Especializacao especializacao) {
        this.crm = crm;
        this.nome = nome;
        this.salario = salario;
        this.especializacao = especializacao;
    }

    @Deprecated
    public Medico() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Set<Consulta> getConsultas() {
        return this.consultas;
    }

    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }

    public void addConsulta(Consulta consulta) {
        this.consultas.add(consulta);
    }

    @Override
    public String toString() {
        return "Medico: " +
                "\nid: " + id +
                "\ncrm: " + crm +
                "\nnome: " + nome +
                "\nsalario: " + salario +
                "\nespecializacao: " + especializacao +
                '}';
    }
}