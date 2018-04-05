package hospital.control;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class GeradorJSON extends Gerador{

    public GeradorJSON() {
        this.xStream = new XStream(new JsonHierarchicalStreamDriver());
    }

    public String gerarJSON(Object obj) {
        return super.gerarArquivo(obj);
    }
}
