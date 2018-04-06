package br.unisinos.hospital.controller;

import com.thoughtworks.xstream.XStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GeradorArquivoXML extends GeradorArquivo {

    public GeradorArquivoXML(String arquivo) throws IOException {
        this.xStream = new XStream();
        this.bufferedWriter = new BufferedWriter(new FileWriter(new File(arquivo)));
    }

}
