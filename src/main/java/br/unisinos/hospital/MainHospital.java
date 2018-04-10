package br.unisinos.hospital;

import br.unisinos.hospital.controller.GeradorArquivoJSON;
import br.unisinos.hospital.controller.GeradorArquivoXML;
import br.unisinos.hospital.models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.*;

public class MainHospital {

    public static void main(String[] args) {

        // Criando úteis
        ArrayList<Calendar> datasNascimento = criaDatasNascimento();
        ArrayList<Calendar> datasConsulta = criaDatasConsultas();

        // Criando Entidades
        ArrayList<Quarto> quartos = criaQuartos();
        ArrayList<Especializacao> especializacoes = criaEspecializacoes();
        ArrayList<Paciente> pacientes = criaPacientes(datasNascimento, quartos);
        ArrayList<Medico> medicos = criaMedicos(especializacoes);
        ArrayList<Consulta> consultas = criaConsultas(medicos, pacientes, datasConsulta);

        // Criando conexão com o banco de dados
        EntityManager manager = buildEntityManager();
        manager.getTransaction().begin();

        // Persistindo entidades na base de dados
        persiste(manager, quartos, especializacoes, pacientes, medicos, consultas);

        // Querys (SELECTs) - NamedQuerys //
        buscaPacientePorId(manager);
        buscaConsultaPorData(manager);
        buscaQuartoPorAndar(manager);
        buscaEspecializacaoPorDescricao(manager);
        buscaMedicoPorCrm(manager);

        // Queryes (UPDATE) - Metodos
        Especializacao especializacao = manager.find(Especializacao.class, 1L);
        especializacao.setDescricao("Endocrinologia");

        // Queryes (DELETE) Metodos
        Consulta consulta = manager.find(Consulta.class, 1L);
        manager.remove(consulta);

        try {
            manager.getTransaction().commit();
            System.out.println("Commit realizado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao executar commit.");
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }

        // Criando arquivos
        criaArquivos(quartos, especializacoes, pacientes, medicos, consultas);
    }

    private static EntityManager buildEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital-mysql");
        return factory.createEntityManager();
    }

    private static ArrayList<Calendar> criaDatasConsultas() {

        Calendar calC1 = new GregorianCalendar(2018,Calendar.APRIL,12,13,30);
        Calendar calC2 = new GregorianCalendar(2018,Calendar.APRIL,12,15,20);
        Calendar calC3 = new GregorianCalendar(2018,Calendar.APRIL,12,9,10);
        Calendar calC4 = new GregorianCalendar(2018,Calendar.APRIL,12,10,00);
        Calendar calC5 = new GregorianCalendar(2018,Calendar.APRIL,12,11,30);
        Calendar calC6 = new GregorianCalendar(2018,Calendar.APRIL,12,12,50);

        ArrayList<Calendar> datasConsultas = new ArrayList<Calendar>();

        datasConsultas.add(calC1);
        datasConsultas.add(calC2);
        datasConsultas.add(calC3);
        datasConsultas.add(calC4);
        datasConsultas.add(calC5);
        datasConsultas.add(calC6);

        return datasConsultas;
    }

    private static ArrayList<Calendar> criaDatasNascimento() {
        Calendar calN1 = new GregorianCalendar(1967,Calendar.OCTOBER,20,13,33);
        Calendar calN2 = new GregorianCalendar(1952,Calendar.MARCH,19,15,20);
        Calendar calN3 = new GregorianCalendar(1978,Calendar.AUGUST,28,4,22);
        Calendar calN4 = new GregorianCalendar(1995,Calendar.JANUARY,11,2,54);
        Calendar calN5 = new GregorianCalendar(1966,Calendar.FEBRUARY,12,9,16);
        Calendar calN6 = new GregorianCalendar(1981,Calendar.DECEMBER,25,12,41);

        ArrayList<Calendar> datasNascimento = new ArrayList<Calendar>();

        datasNascimento.add(calN1);
        datasNascimento.add(calN2);
        datasNascimento.add(calN3);
        datasNascimento.add(calN4);
        datasNascimento.add(calN5);
        datasNascimento.add(calN6);

        return datasNascimento;
    }

    private static ArrayList<Consulta> criaConsultas(ArrayList<Medico> medicos, ArrayList<Paciente> pacientes, ArrayList<Calendar> datasConsulta) {
        Consulta consulta1 = new Consulta(medicos.get(0), pacientes.get(0), datasConsulta.get(0));
        Consulta consulta2 = new Consulta(medicos.get(1), pacientes.get(0), datasConsulta.get(1));
        Consulta consulta3 = new Consulta(medicos.get(2), pacientes.get(1), datasConsulta.get(0));
        Consulta consulta4 = new Consulta(medicos.get(2), pacientes.get(3), datasConsulta.get(1));
        Consulta consulta5 = new Consulta(medicos.get(4), pacientes.get(3), datasConsulta.get(2));
        Consulta consulta6 = new Consulta(medicos.get(5), pacientes.get(3), datasConsulta.get(4));

        ArrayList<Consulta> consultas = new ArrayList<Consulta>();

        consultas.add(consulta1);
        consultas.add(consulta2);
        consultas.add(consulta3);
        consultas.add(consulta4);
        consultas.add(consulta5);
        consultas.add(consulta6);

        return consultas;
    }

    private static ArrayList<Medico> criaMedicos(ArrayList<Especializacao> especializacoes) {

        Medico medico1 = new Medico("1035", "Carlos", 3500.00, especializacoes.get(0));
        Medico medico2 = new Medico("0156", "Pedro", 4500.00, especializacoes.get(1));
        Medico medico3 = new Medico("2568", "Leticia", 3700.00, especializacoes.get(2));
        Medico medico4 = new Medico("3541", "Sonia", 2500.00, especializacoes.get(3));
        Medico medico5 = new Medico("5985", "Paulo", 2500.00, especializacoes.get(4));
        Medico medico6 = new Medico("0354", "Jaqueline", 3000.00, especializacoes.get(5));

        ArrayList<Medico> medicos = new ArrayList<Medico>();

        medicos.add(medico1);
        medicos.add(medico2);
        medicos.add(medico3);
        medicos.add(medico4);
        medicos.add(medico5);
        medicos.add(medico6);

        return medicos;
    }

    private static ArrayList<Especializacao> criaEspecializacoes() {

        Especializacao especializacao1 = new Especializacao("Urologia");
        Especializacao especializacao2 = new Especializacao("Cardiologia");
        Especializacao especializacao3 = new Especializacao("Geriatria");
        Especializacao especializacao4 = new Especializacao("Neurologia");
        Especializacao especializacao5 = new Especializacao("Dermatologia");
        Especializacao especializacao6 = new Especializacao("Radioterapia");

        ArrayList<Especializacao> especializacoes = new ArrayList<Especializacao>();

        especializacoes.add(especializacao1);
        especializacoes.add(especializacao2);
        especializacoes.add(especializacao3);
        especializacoes.add(especializacao4);
        especializacoes.add(especializacao5);
        especializacoes.add(especializacao6);

        return especializacoes;
    }

    private static ArrayList<Paciente> criaPacientes(ArrayList<Calendar> datasNascimento, ArrayList<Quarto> quartos) {

        Paciente paciente1 = new Paciente("12345678911","Pedro", "(051) 3549-3568", datasNascimento.get(0), "Rua A", "1234567891", quartos.get(0));
        Paciente paciente2 = new Paciente("12345678912","Joao", "(051) 8648-3344", datasNascimento.get(1), "Rua B", "1234567892", quartos.get(0));
        Paciente paciente3 = new Paciente("12345678913","Maria", "(051) 1122-2256", datasNascimento.get(2), "Rua C", "1234567893", quartos.get(0));
        Paciente paciente4 = new Paciente("12345678914","Roberto", "(051) 3896-3541", datasNascimento.get(3), "Rua D", "1234567894", quartos.get(2));
        Paciente paciente5 = new Paciente("12345678915","Lucas", "(051) 3214-2548", datasNascimento.get(4), "Rua E", "1234567895", quartos.get(3));
        Paciente paciente6 = new Paciente("12345678916","Carolina", "(051) 9863-5786", datasNascimento.get(5), "Rua J", "1234567896", quartos.get(4));

        ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

        pacientes.add(paciente1);
        pacientes.add(paciente2);
        pacientes.add(paciente3);
        pacientes.add(paciente4);
        pacientes.add(paciente5);
        pacientes.add(paciente6);

        return pacientes;
    }

    private static ArrayList<Quarto> criaQuartos() {

        Quarto quarto1 = new Quarto(new QuartoID("10", "2B"));
        Quarto quarto2 = new Quarto(new QuartoID("5", "2B"));
        Quarto quarto3 = new Quarto(new QuartoID("7", "2B"));
        Quarto quarto4 = new Quarto(new QuartoID("12", "2B"));
        Quarto quarto5 = new Quarto(new QuartoID("1", "2B"));

        ArrayList<Quarto> quartos = new ArrayList<Quarto>();

        quartos.add(quarto1);
        quartos.add(quarto2);
        quartos.add(quarto3);
        quartos.add(quarto4);
        quartos.add(quarto5);

        return quartos;
    }

    private static void persiste(EntityManager manager, ArrayList<Quarto> quartos, ArrayList<Especializacao> especializacoes, ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<Consulta> consultas) {
        for (Quarto quarto : quartos) {
            manager.persist(quarto);
        }

        for (Especializacao especializacao: especializacoes) {
            manager.persist(especializacao);
        }

        for (Paciente paciente : pacientes) {
            manager.persist(paciente);
        }

        for (Medico medico : medicos) {
            manager.persist(medico);
        }

        for (Consulta consulta : consultas) {
            manager.persist(consulta);
        }
    }

    private static void buscaPacientePorId(EntityManager manager) {

        List<Paciente> resultList = manager
                .createNamedQuery("Paciente.byId", Paciente.class)
                .setParameter("pId", 2L)
                .getResultList();

        for (Paciente paciente : resultList) {
            System.out.println(paciente.toString());
            System.out.print("\n");
        }
    }

    private static void buscaConsultaPorData(EntityManager manager) {

        List<Consulta> consultaPorDataResultList = manager
                .createNamedQuery("Consulta.byDataConsulta", Consulta.class)
                .setParameter("pDataInicio", new GregorianCalendar(2018, Calendar.APRIL, 12, 11, 00))
                .setParameter("pDataFinal", new GregorianCalendar(2018, Calendar.APRIL, 12, 18, 00))
                .getResultList();

        for (Consulta c : consultaPorDataResultList) {
            System.out.println("Paciente: " + c.getPaciente().getNome() +
                    "\nData da Consulta: " + c.getDataConsultaFormatada() +
                    "\nMedico: " + c.getMedico().getNome());
            System.out.print("\n");
        }
    }

    private static void buscaEspecializacaoPorDescricao(EntityManager manager) {
        List<Especializacao> resultList = manager.createNamedQuery("Especializacao.byDescricao", Especializacao.class).setParameter("pDescricao", "Car").getResultList();

        for (Especializacao especializacao : resultList) {
            System.out.print(especializacao.getDescricao());
        }
    }

    private static void buscaQuartoPorAndar(EntityManager manager) {
        List<Quarto> buscaQuartoPorAndarResultList = manager
                .createNamedQuery("Quarto.byId", Quarto.class)
                .setParameter("pAndar", "2B")
                .getResultList();

        for (Quarto q : buscaQuartoPorAndarResultList) {
            System.out.print("\nAndar: " + q.getQuartoID().getAndar() +
                    ", Numero: " + q.getQuartoID().getNumero());
        }
    }

    private static void buscaMedicoPorCrm(EntityManager manager) {
        List<Medico> resultList = manager.createNamedQuery("Medico.byCrm", Medico.class).setParameter("pCrm", "1035").getResultList();

        for (Medico medico :
                resultList) {
            System.out.println("Id: " + medico.getId() +
                    "\nNome: " + medico.getNome());
        }
    }

    private static void criaArquivos(ArrayList<Quarto> quartos, ArrayList<Especializacao> especializacoes, ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<Consulta> consultas) {
        try {
            // XML
            System.out.println(new GeradorArquivoXML("Quarto.xml").gerarArquivo(quartos));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoXML("Especializacao.xml").gerarArquivo(especializacoes));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoXML("Paciente.xml").gerarArquivo(pacientes));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoXML("Medico.xml").gerarArquivo(medicos));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoXML("Consulta.xml").gerarArquivo(consultas));
            System.out.println("------------------------------------------------------------------");

            // JSON
            System.out.println(new GeradorArquivoJSON("Quarto.json").gerarArquivo(quartos));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoJSON("Especializacao.json").gerarArquivo(especializacoes));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoJSON("Paciente.json").gerarArquivo(pacientes));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoJSON("Medico.json").gerarArquivo(medicos));
            System.out.println("------------------------------------------------------------------");
            System.out.println(new GeradorArquivoJSON("Consulta.json").gerarArquivo(consultas));
            System.out.println("------------------------------------------------------------------");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}