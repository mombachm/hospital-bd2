package hospital;

import hospital.control.GeradorXML;
import hospital.models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;

public class MainHospital {

    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        cal.set(1995,Calendar.MARCH,13);

        Quarto quarto = new Quarto("245", "2B");
        Especializacao especializacao = new Especializacao("Urologia");
        Paciente paciente = new Paciente("12345678911","Pedro", "(051) 1122-3344", cal, "Rua sem nome", "1234567891", quarto);
        Medico medico = new Medico("0001", "Carlos", 2500.45, especializacao);
        Consulta consulta = new Consulta(medico, paciente, cal);

        System.out.println(new GeradorXML().gerarXML(consulta));

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
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital-mysql");
        return factory.createEntityManager();
    }

}