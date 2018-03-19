package hospital.models;

import javax.persistence.*;

@Entity
@Table(name = "PESSOA")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int codigo;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SOBRENOME")
    private String sobrenome;

    @Column(name = "EMAIL")
    private String email;

    public Pessoa( String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }
}
