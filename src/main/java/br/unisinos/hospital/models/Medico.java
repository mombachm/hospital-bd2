package br.unisinos.hospital.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
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


}