package br.unisinos.hospital.controller;

import br.unisinos.hospital.models.*;
import com.thoughtworks.xstream.XStream;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Locale;

abstract class GeradorArquivo {

    XStream xStream;
    BufferedWriter bufferedWriter;

    public String gerarArquivo(Object obj) throws IOException {

        configuraQuarto();
        configuraEspecializacao();
        configuraPaciente();
        configuraMedico();
        configuraConsulta();

        this.xStream.setMode(XStream.ID_REFERENCES);

        String xmlojson = this.xStream.toXML(obj);
        this.bufferedWriter.write(xmlojson);
        this.bufferedWriter.close();
        return xmlojson;

    }

    private void configuraEspecializacao() {
        this.xStream.alias("Especializacao", Especializacao.class);
    }

    private void configuraQuarto() {
        this.xStream.alias("Quarto", Quarto.class);
    }

    private void configuraConsulta() {
        this.xStream.alias("Consultas", Consulta.class);
        this.xStream.omitField(Quarto.class, "pacientes");
        this.xStream.registerConverter(new ConversorData(new Locale("pt", "br")));
        this.xStream.omitField(Consulta.class, "initialized");
    }

    private void configuraMedico() {
        this.xStream.alias("Medico", Medico.class);
        this.xStream.omitField(Medico.class, "consultas");
    }

    private void configuraPaciente() {
        this.xStream.alias("Paciente", Paciente.class);
        this.xStream.omitField(Paciente.class, "consultas");
    }



}
