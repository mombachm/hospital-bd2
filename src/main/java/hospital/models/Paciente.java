package hospital.models;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "Paciente")
public class Paciente {

    @Id
    @Column(name = "CPF", unique = true)
    private String cpf;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Telefone")
    private String telefone;

    @Column(name = "DtNascimento")
    private Calendar data_nascimento;

    @Column(name = "Endereco")
    private String endereco;

    @Column(name = "RG")
    private String rg;

    public Paciente(String cpf, String nome, String telefone, Calendar data_nascimento, String endereco, String rg) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.data_nascimento = data_nascimento;
        this.endereco = endereco;
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
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
        return data_nascimento;
    }

    public void setData_nascimento(Calendar data_nascimento) {
        this.data_nascimento = data_nascimento;
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


}
