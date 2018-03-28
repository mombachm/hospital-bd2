package hospital.models;

import javax.persistence.*;

@Entity
@Table(name = "Medico")
public class Medico {

    @Id
    @Column(name = "CRM")
    private String crm;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Salario")
    private Double salario;

    public Medico(String crm, String nome, Double salario) {
        this.crm = crm;
        this.nome = nome;
        this.salario = salario;
    }

}