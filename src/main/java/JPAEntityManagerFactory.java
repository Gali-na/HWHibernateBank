import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAEntityManagerFactory {
    public  static EntityManagerFactory getEntityManagerFactory () {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("JPAMySQLnew");
    return  emf;
    }
}
