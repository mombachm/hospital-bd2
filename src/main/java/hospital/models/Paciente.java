package hospital.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Paciente", uniqueConstraints = @UniqueConstraint(columnNames = {"CPF", "RG"}))
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPaciente")
    private long id;

    @NotNull
    @Column(name = "Nome")
    private String nome;

    @Column(name = "CPF", unique = true)
    private String cpf;

    @Column(name = "Telefone")
    private String telefone;

    @Column(name = "DtNascimento")
    private Calendar dataNascimento;

    @Column(name = "Endereco")
    private String endereco;

    @Column(name = "RG")
    private String rg;

    @OneToMany(mappedBy="paciente")
    private Set<Consulta> consultas = new HashSet<Consulta>();

    @NotNull
    @ManyToOne
    private Quarto quarto;

    public Paciente(String cpf, String nome, String telefone, Calendar dataNascimento, String endereco, String rg, Quarto quarto) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.rg = rg;
        this.quarto = quarto;
    }

    @Deprecated
    public Paciente() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Calendar getData_nascimento() {
        return dataNascimento;
    }

    public void setData_nascimento(Calendar data_nascimento) {
        this.dataNascimento = data_nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
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
