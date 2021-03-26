import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class FillingTheDatabase {
    public  static void clientFirst () {
        Client client = new Client();
        client.setPassword("olgapetrova");
        client.setName("Olga");
        client.setSurname("Petrova");
        BankAccountEUR bankAccountEUR = new BankAccountEUR(12345L,560L ,"EUR");
        BankAccountUAN bankAccountUAN = new BankAccountUAN(1345L,230L,"UAN");
        BankAccountUSD bankAccountUSD = new BankAccountUSD(123123L,67L,"USD");
        BanckAccounts banckAccounts = new BanckAccounts();
        banckAccounts.setBankAccountEUR(bankAccountEUR);
        banckAccounts.setBankAccountUAN(bankAccountUAN);
        banckAccounts.setBankAccountUSD(bankAccountUSD);
        banckAccounts.setClient(client);
        client.setBanckAccounts(banckAccounts);
        EntityManager em =JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        try {
        em.getTransaction().begin();
        em.persist(client);
        em.persist(bankAccountEUR);
        em.persist(bankAccountUAN);
        em.persist(bankAccountUSD);
        em.persist(banckAccounts);
        em.getTransaction().commit();
        }catch (PersistenceException | IllegalArgumentException e)  {
            e.getMessage();
            em.getTransaction().rollback();
        }
        em.close();
    }

    public  static void clientSecond () {
        Client client = new Client();
        client.setPassword("andreypopov");
        client.setName("Andrey");
        client.setSurname("Popov");
        BankAccountEUR bankAccountEUR = new BankAccountEUR(123451L,760L ,"EUR");
        BankAccountUAN bankAccountUAN = new BankAccountUAN(13451L,630L,"UAN");
        BankAccountUSD bankAccountUSD = new BankAccountUSD(1231231L,650L,"USD");
        BanckAccounts banckAccounts = new BanckAccounts();
        banckAccounts.setBankAccountEUR(bankAccountEUR);
        banckAccounts.setBankAccountUAN(bankAccountUAN);
        banckAccounts.setBankAccountUSD(bankAccountUSD);
        banckAccounts.setClient(client);
        client.setBanckAccounts(banckAccounts);
        EntityManager em =JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
        em.persist(client);
        em.persist(bankAccountEUR);
        em.persist(bankAccountUAN);
        em.persist(bankAccountUSD);
        em.persist(banckAccounts);
        em.getTransaction().commit();
    }catch (PersistenceException | IllegalArgumentException e)  {
        e.getMessage();
        em.getTransaction().rollback();
    }
        em.close();
    }

    public  static void clientThird() {
        Client client = new Client();
        client.setPassword("nataliyzolotarev");
        client.setName("Nataliy");
        client.setSurname("Zolotarev");
        BankAccountEUR bankAccountEUR = new BankAccountEUR(123452L,460L ,"EUR");
        BankAccountUAN bankAccountUAN = new BankAccountUAN(13452L,830L,"UAN");
        BankAccountUSD bankAccountUSD = new BankAccountUSD(1231232L,150L,"USD");
        BanckAccounts banckAccounts = new BanckAccounts();
        banckAccounts.setBankAccountEUR(bankAccountEUR);
        banckAccounts.setBankAccountUAN(bankAccountUAN);
        banckAccounts.setBankAccountUSD(bankAccountUSD);
        banckAccounts.setClient(client);
        client.setBanckAccounts(banckAccounts);
        EntityManager em =JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
        em.persist(client);
        em.persist(bankAccountEUR);
        em.persist(bankAccountUAN);
        em.persist(bankAccountUSD);
        em.persist(banckAccounts);
        em.getTransaction().commit();
        }catch (PersistenceException | IllegalArgumentException e)  {
            e.getMessage();
            em.getTransaction().rollback();
        }
        em.close();
    }
    public  static void clientFourth() {
        Client client = new Client();
        client.setPassword("mashakotova");
        client.setName("Masha");
        client.setSurname("Kotova");
        BankAccountEUR bankAccountEUR = new BankAccountEUR(123453L,570L ,"EUR");
        BankAccountUAN bankAccountUAN = new BankAccountUAN(13453L,980L,"UAN");
        BankAccountUSD bankAccountUSD = new BankAccountUSD(1231233L,240L,"USD");
        BanckAccounts banckAccounts = new BanckAccounts();
        banckAccounts.setBankAccountEUR(bankAccountEUR);
        banckAccounts.setBankAccountUAN(bankAccountUAN);
        banckAccounts.setBankAccountUSD(bankAccountUSD);
        banckAccounts.setClient(client);
        client.setBanckAccounts(banckAccounts);
        EntityManager em =JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
        em.persist(client);
        em.persist(bankAccountEUR);
        em.persist(bankAccountUAN);
        em.persist(bankAccountUSD);
        em.persist(banckAccounts);
        em.getTransaction().commit();
    }catch (PersistenceException | IllegalArgumentException e)  {
        e.getMessage();
        em.getTransaction().rollback();
    }
        em.close();
    }

    public  static void clientFifth() {
        Client client = new Client();
        client.setPassword("antonpopova");
        client.setName("Anton");
        client.setSurname("Popova");
        BankAccountEUR bankAccountEUR = new BankAccountEUR(123454L,970L ,"EUR");
        BankAccountUAN bankAccountUAN = new BankAccountUAN(13454L,780L,"UAN");
        BankAccountUSD bankAccountUSD = new BankAccountUSD(1231234L,340L,"USD");
        BanckAccounts banckAccounts = new BanckAccounts();
        banckAccounts.setBankAccountEUR(bankAccountEUR);
        banckAccounts.setBankAccountUAN(bankAccountUAN);
        banckAccounts.setBankAccountUSD(bankAccountUSD);
        banckAccounts.setClient(client);
        client.setBanckAccounts(banckAccounts);
        EntityManager em =JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
        em.persist(client);
        em.persist(bankAccountEUR);
        em.persist(bankAccountUAN);
        em.persist(bankAccountUSD);
        em.persist(banckAccounts);
        em.getTransaction().commit();
        }catch (PersistenceException | IllegalArgumentException e)  {
            e.getMessage();
            em.getTransaction().rollback();
        }
        em.close();
    }

    public  static void clientSixth() {
        Client client = new Client();
        client.setPassword("olgaamelina");
        client.setName("Olga");
        client.setSurname("Amelina");
        BankAccountEUR bankAccountEUR = new BankAccountEUR(123455L,270L ,"EUR");
        BankAccountUAN bankAccountUAN = new BankAccountUAN(13455L,340L,"UAN");
        BankAccountUSD bankAccountUSD = new BankAccountUSD(1231235L,990L,"USD");
        BanckAccounts banckAccounts = new BanckAccounts();
        banckAccounts.setBankAccountEUR(bankAccountEUR);
        banckAccounts.setBankAccountUAN(bankAccountUAN);
        banckAccounts.setBankAccountUSD(bankAccountUSD);
        banckAccounts.setClient(client);
        client.setBanckAccounts(banckAccounts);
        EntityManager em =JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
        em.persist(client);
        em.persist(bankAccountEUR);
        em.persist(bankAccountUAN);
        em.persist(bankAccountUSD);
        em.persist(banckAccounts);
        em.getTransaction().commit();
    }catch (PersistenceException | IllegalArgumentException e)  {
        e.getMessage();
        em.getTransaction().rollback();
    }
        em.close();
    }

    public  static void clientSeventh() {
        Client client = new Client();
        client.setPassword("olgaamelina");
        client.setName("Konstantin");
        client.setSurname("Kalugnuy");
        BankAccountEUR bankAccountEUR = new BankAccountEUR(123456L,987L ,"EUR");
        BankAccountUAN bankAccountUAN = new BankAccountUAN(13456L,453L,"UAN");
        BankAccountUSD bankAccountUSD = new BankAccountUSD(1231236L,897L,"USD");
        BanckAccounts banckAccounts = new BanckAccounts();
        banckAccounts.setBankAccountEUR(bankAccountEUR);
        banckAccounts.setBankAccountUAN(bankAccountUAN);
        banckAccounts.setBankAccountUSD(bankAccountUSD);
        banckAccounts.setClient(client);
        client.setBanckAccounts(banckAccounts);
        EntityManager em =JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
        em.persist(client);
        em.persist(bankAccountEUR);
        em.persist(bankAccountUAN);
        em.persist(bankAccountUSD);
        em.persist(banckAccounts);
        em.getTransaction().commit();
        }catch (PersistenceException | IllegalArgumentException e)  {
            e.getMessage();
            em.getTransaction().rollback();
        }
        em.close();
    }
}
