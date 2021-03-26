import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PasswordVerification {
    public static String searchUserInDatabase ( String password, String name) {
       String answer ="";
       EntityManager entityManager=JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
      Query query =entityManager.createQuery("SELECT c.name FROM Client c WHERE c.password=:password");
      query.setParameter("password",password);
      String nameAnswer= (String) query.getSingleResult();
      if(nameAnswer.equals(name)) {
          answer="Welcome";
      }
      else  answer="you are not a client of our bank ";
        entityManager.close();
        return  answer;
    }
}
