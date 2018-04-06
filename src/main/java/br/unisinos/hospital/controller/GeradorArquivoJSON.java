package br.unisinos.hospital.controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

import java.io.*;

public class GeradorArquivoJSON extends GeradorArquivo {

    public GeradorArquivoJSON(String arquivo) throws IOException {
        this.xStream = new XStream(new JsonHierarchicalStreamDriver());
        this.bufferedWriter = new BufferedWriter(new FileWriter(new File(arquivo)));
    }

}
