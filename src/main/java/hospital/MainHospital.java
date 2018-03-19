package hospital;

import hospital.models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainHospital {

    public static void main(String[] args) {

        Pessoa pessoa = new Pessoa("Michael", "Jordan");

        EntityManager manager = buildEntityManager();

        manager.getTransaction().begin();
        manager.persist(pessoa);
        try {
            manager.getTransaction().commit();
            System.out.println("Commit realizado com sucesso.");
        }catch (Exception e) {
            System.out.println("Erro ao executar commit.");
        }
    }

    public static EntityManager buildEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital");
        return factory.createEntityManager();
    }

}