package hospital.control;

import com.thoughtworks.xstream.XStream;
import hospital.models.Consulta;
import hospital.models.Medico;
import hospital.models.Paciente;

import java.util.Locale;

public class GeradorXML {

    private XStream xStream;

    public GeradorXML() {
        this.xStream = new XStream();
    }

    public String gerarXML(Object obj){

        this.xStream.alias("Paciente", Paciente.class);
        this.xStream.alias("Medico", Medico.class);
        this.xStream.alias("Consultas", Consulta.class);
        this.xStream.omitField(Paciente.class, "consultas");
        this.xStream.omitField(Medico.class, "consultas");
        this.xStream.setMode(XStream.NO_REFERENCES);
        this.xStream.registerConverter(new ConversorData(new Locale("pt", "br")));

        return this.xStream.toXML(obj);

    }
}
