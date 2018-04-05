package hospital.control;

import com.thoughtworks.xstream.XStream;
import hospital.models.Consulta;
import hospital.models.Medico;
import hospital.models.Paciente;

import java.util.Locale;

public class GeradorXML extends Gerador{

    public GeradorXML() {
        this.xStream = new XStream();
    }

    public String gerarXML(Object obj){
        return super.gerarArquivo(obj);
    }
}
