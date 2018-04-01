package hospital.models;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "consulta")
public class Consulta  implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IdConsulta", unique=true, nullable=false)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Medico.class)
    @JoinColumn(name = "IdMedico")
    private Medico medico;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Paciente.class)
    @JoinColumn(name = "IdPaciente")
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
}
