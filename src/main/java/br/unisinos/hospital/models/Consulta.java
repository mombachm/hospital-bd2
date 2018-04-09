package br.unisinos.hospital.models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "Consulta.all", query = "SELECT c FROM Consulta c"),
        @NamedQuery(name = "Consulta.byId", query = "SELECT c FROM Consulta c WHERE c.id = :pId"),
        @NamedQuery(name = "Consulta.byPaciente", query = "SELECT c FROM Consulta c WHERE c.id = :pIdPaciente"),
        @NamedQuery(name = "Consulta.byMedico", query = "SELECT c FROM Consulta c WHERE c.id = :pIdMedico"),
        @NamedQuery(name = "Consulta.byDataConsulta", query = "SELECT c FROM Consulta c WHERE c.dataConsulta BETWEEN :pDataInicio AND :pDataFinal")
})
@Table(name = "consulta", uniqueConstraints = @UniqueConstraint(columnNames = {"DataConsulta", "IdMedico"}))
public class Consulta  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdConsulta", unique=true, nullable=false)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Medico.class)
    @JoinColumn(name = "IdMedico", nullable = false)
    private Medico medico;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Paciente.class)
    @JoinColumn(name = "IdPaciente", nullable = false)
    private Paciente paciente;

    @Column(name = "DataConsulta")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataConsulta;

    public Consulta(Medico medico, Paciente paciente, Calendar dataConsulta) {
        this.medico = medico;
        this.paciente = paciente;
        this.dataConsulta = dataConsulta;
    }

    @Deprecated
    public Consulta() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Calendar getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Calendar dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getDataConsultaFormatada(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        return formatter.format(this.dataConsulta.getTime());
    }

    @Override
    public String toString() {
        return "Consulta: " +
                "\nid: " + id +
                "\n medico: " + medico.toString() +
                "\n paciente: " + paciente.toString() +
                "\n dataConsulta: " + getDataConsultaFormatada();
    }
}
