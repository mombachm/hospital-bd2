package br.unisinos.hospital;

import br.unisinos.hospital.controller.GeradorArquivoJSON;
import br.unisinos.hospital.controller.GeradorArquivoXML;
import br.unisinos.hospital.models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainHospital {

    public static void main(String[] args) {

        // Criando Entidades
        Calendar cal = new GregorianCalendar(1967,Calendar.OCTOBER,28,13,24);

        Quarto quarto = new Quarto("245", "2B");
        Especializacao especializacao = new Especializacao("Urologia");
        Paciente paciente = new Paciente("12345678911","Pedro", "(051) 1122-3344", cal, "Rua sem nome", "1234567891", quarto);
        Medico medico = new Medico("0001", "Carlos", 2500.45, especializacao);
        Consulta consulta = new Consulta(medico, paciente, cal);

        try {
            // XML
            System.out.println(new GeradorArquivoXML("Quarto.xml").gerarArquivo(quarto));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoXML("Especializacao.xml").gerarArquivo(especializacao));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoXML("Paciente.xml").gerarArquivo(paciente));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoXML("Medico.xml").gerarArquivo(medico));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoXML("Consulta.xml").gerarArquivo(consulta));
            System.out.println("------------------------------------------------------------------");

            // JSON
            System.out.println(new GeradorArquivoJSON("Quarto.json").gerarArquivo(quarto));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoJSON("Especializacao.json").gerarArquivo(especializacao));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoJSON("Paciente.json").gerarArquivo(paciente));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoJSON("Medico.json").gerarArquivo(medico));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoJSON("Consulta.json").gerarArquivo(consulta));
            System.out.println("------------------------------------------------------------------");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        // Pesistindo Entidades
        EntityManager manager = buildEntityManager();

        manager.getTransaction().begin();
        manager.persist(quarto);
        manager.persist(especializacao);
        manager.persist(consulta);
        try {
            manager.getTransaction().commit();
            System.out.println("Commit realizado com sucesso.");
        }catch (Exception e) {
            System.out.println("Erro ao executar commit.");
            manager.getTransaction().rollback();
        }
        finally {
            manager.close();
        }
    }

    private static EntityManager buildEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital-hsql");
        return factory.createEntityManager();
    }

}