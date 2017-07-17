package Test;

import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by azhilin on 25.01.2017.
 */
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.jpa");
        EntityManager entityManager = sessionFactory.createEntityManager();

        entityManager.getTransaction().begin();
        MyUsers myUser = new MyUsers();
        myUser.setName("Alex");
        myUser.setSurname("Zhilin");
        entityManager.persist(myUser);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        List<MyUsers> myUsers = entityManager
                .createQuery("FROM Test.MyUsers p " +
                        "WHERE name = :p_name", MyUsers.class)
                .setParameter("p_name", "Alex")
                .getResultList();
        for (MyUsers m : myUsers) {
            System.out.println(m.getSurname());
        }
        entityManager.getTransaction().commit();

        entityManager.close();
        sessionFactory.close();


    }
}
