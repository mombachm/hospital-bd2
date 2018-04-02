package hospital.control;

import com.thoughtworks.xstream.XStream;
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
        xStream.registerConverter(new ConversorData(new Locale("pt", "br")));

        return this.xStream.toXML(obj);

    }
}
