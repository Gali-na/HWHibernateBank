import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
     //   FillingTheDatabase.clientFirst();
    //    FillingTheDatabase.clientSecond();
      //  FillingTheDatabase.clientThird();
     //   FillingTheDatabase.clientFourth();
     //   FillingTheDatabase.clientFifth();
     //   FillingTheDatabase.clientSixth();
     //   FillingTheDatabase.clientSeventh();
        EcxchangeRates ecxchangeRates = new EcxchangeRates(28, 32);
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(ecxchangeRates);
        entityManager.getTransaction().commit();
        entityManager.close();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our bank");
        System.out.println("Enter your name");
        String name = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        String answer = PasswordVerification.searchUserInDatabase(password, name);
        System.out.println(answer);
        if (answer.equals("Welcome")) {
            СlientFunctionality.showMenu(password);
        }
        JPAEntityManagerFactory.getEntityManagerFactory().close();
    }
}
