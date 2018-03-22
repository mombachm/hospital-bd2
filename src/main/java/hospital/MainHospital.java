package hospital;

import hospital.models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;

public class MainHospital {

    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        cal.set(1995,Calendar.MARCH,13);
        Paciente paciente = new Paciente("Pedro","12345678911", "(051) 1122-3344", cal, "Rua sem nome", "1234567891");

        EntityManager manager = buildEntityManager();

        manager.getTransaction().begin();
        manager.persist(paciente);
        try {
            manager.getTransaction().commit();
            System.out.println("Commit realizado com sucesso.");
        }catch (Exception e) {
            System.out.println("Erro ao executar commit.");
        }
    }

    public static EntityManager buildEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital-mysql");
        return factory.createEntityManager();
    }

}