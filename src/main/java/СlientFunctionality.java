import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class СlientFunctionality {
    public static void showMenu(String password) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Top up an account ->1");
        System.out.println("Transfer of funds from one account to another ->2");
        System.out.println("checking account balances->3");
        System.out.println("balance for all accounts in UAH->4");
        System.out.println("my transactions-5");
        int choice = scanner.nextInt();
        if (choice == 1) {
            topUpAccount();
        }
        if (choice == 2) {
            moneyTransferBetweenAccounts(password);
        }

        if (choice == 3) {
            List<String> accountsUser = showFundsOnAllAccounts(password);
            for (String acoount : accountsUser) {
                System.out.println(acoount);
            }
        }
        if (choice == 4) {
            System.out.println("account balance " + getSummAllAcoountsInUAH(password) + " UAH");
        }
        if (choice == 5) {
            System.out.println(getTransactionsUser(password));
        }
    }


    private static StringBuilder getTransactionsUser(String password) {
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT с FROM Client с where с.password =:password");
        query.setParameter("password", password);
        Client c = (Client) query.getSingleResult();
        Long idAccountUSD = c.getBanckAccounts().getBankAccountUSD().getId();
        Long idAccountUAN = c.getBanckAccounts().getBankAccountUAN().getId();
        Long idAccountEUR = c.getBanckAccounts().getBankAccountEUR().getId();
        StringBuilder answerTransaction = new StringBuilder();
        answerTransaction.append("Your transactions");
        answerTransaction.append(transactionsForEachUserAccount(idAccountUSD));
        answerTransaction.append(transactionsForEachUserAccount(idAccountUAN));
        answerTransaction.append(transactionsForEachUserAccount(idAccountEUR));
        entityManager.close();
        return answerTransaction;
    }
    private static  StringBuilder  transactionsForEachUserAccount (Long idAccount) {
        StringBuilder answerTransaction = new StringBuilder();
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        Query queryUAN = entityManager.createQuery("SELECT t FROM Transaction t where t.recipient.id =:idAccount or t.sender.id=:idAccount");
        queryUAN.setParameter("idAccount", idAccount);
        List<Transaction> transactions = queryUAN.getResultList();
        Long idSender = 0L;
        for (Transaction transaction : transactions) {
            Long idRecipient = transaction.getRecipient().getId();
            if (transaction.getSender() != null) {
                idSender = transaction.getSender().getId();
            }
            Integer amountMoney = transaction.getAmountMoney();
            Query queryClientRecipient = entityManager.createQuery("SELECT c.name FROM Client c where c.banckAccounts.bankAccountUAN.id=:idRecipient");
            queryClientRecipient.setParameter("idRecipient", idRecipient);
            String nameRecipient = (String) queryClientRecipient.getSingleResult();
            String nameSender="0";
            if (idSender != 0) {
                Query queryClientSender = entityManager.createQuery("SELECT c.name FROM Client c where c.banckAccounts.bankAccountUAN.id=:idSender");
                queryClientSender.setParameter("idSender", idSender);
                nameSender  = (String) queryClientSender.getSingleResult();
            }
            entityManager.close();
            answerTransaction.append( "\n" );
            answerTransaction.append( "Sender -> " );
            answerTransaction.append( nameSender );
            answerTransaction.append( "  Recipient ->" );
            answerTransaction.append( nameRecipient);
            answerTransaction.append( "  " + amountMoney);
        }
        return answerTransaction;
    }
    private static int getSummAllAcoountsInUAH(String password) {
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT с FROM Client с where с.password =:password");
        query.setParameter("password", password);
        Client c = (Client) query.getSingleResult();
        int countMoney = (int) ((c.getBanckAccounts().getBankAccountUSD().getAmountMoney()) * coursUSD() + c.getBanckAccounts().getBankAccountUAN().getAmountMoney() + c.getBanckAccounts().getBankAccountEUR().getAmountMoney() * coursEUR());
        return countMoney;
    }

    private static List<String> showFundsOnAllAccounts(String password) {
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT с FROM Client с where с.password =:password");
        query.setParameter("password", password);
        Client c = (Client) query.getSingleResult();
        List<String> accountsUser = new ArrayList<>();
        String accountUSD = (c.getBanckAccounts().getBankAccountUSD().getNumberAccount()) + "-> " + c.getBanckAccounts().getBankAccountUSD().getAmountMoney() + " USD";
        String accountUAN = (c.getBanckAccounts().getBankAccountUAN().getNumberAccount()) + "-> " + c.getBanckAccounts().getBankAccountUAN().getAmountMoney() + " UAH";
        String accountEUR = (c.getBanckAccounts().getBankAccountUAN().getNumberAccount()) + "-> " + c.getBanckAccounts().getBankAccountEUR().getAmountMoney() + " EUR";
        accountsUser.add(accountUSD);
        accountsUser.add(accountUAN);
        accountsUser.add(accountEUR);
        entityManager.close();
        return accountsUser;
    }

    private static void moneyTransferBetweenAccounts(String password) {
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        List<Long> listNamberAccount = showAccountsUser(password);
        int i = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("select the account number from which you want to transfer funds");
        for (long number : listNamberAccount) {
            System.out.println(number + "->" + i);
            i++;
        }
        int numberAccountInList = scanner.nextInt();
        Long currentNamberAccount = listNamberAccount.get(numberAccountInList - 1);
        System.out.println("Enter the amount of money you want to transfer");
        int amountMoney = scanner.nextInt();
        Query query = entityManager.createQuery("SELECT b.amountMoney FROM BanckAccount b where b.numberAccount =:currentNamberAccount");
        query.setParameter("currentNamberAccount", currentNamberAccount);
        Long amountMoneyIncurrentNamberAccount = (Long) query.getSingleResult();
        if (amountMoneyIncurrentNamberAccount < amountMoney) {
            System.out.println("There are not enough funds in your account");
            return;
        }
        System.out.println("enter the account number to which you want to transfer funds");
        Long accountNumberForReplenishment = Long.valueOf(scanner.nextInt());
        String currencyAccountForReplenishment = definitionOfCurrency(accountNumberForReplenishment);
        String currencyCurrentNamberAccount = definitionOfCurrency(currentNamberAccount);
        entityManager.close();
        moneyСonversionWhenTransferringBetweenAccounts(currencyAccountForReplenishment, currencyCurrentNamberAccount, amountMoney, accountNumberForReplenishment, currentNamberAccount);
    }

    private static List<Long> showAccountsUser(String password) {
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT с FROM Client с where с.password =:password");
        query.setParameter("password", password);
        Client c = (Client) query.getSingleResult();
        List<Long> listNamberAccount = new ArrayList<>();
        Long nameraccountUSD = c.getBanckAccounts().getBankAccountUSD().getNumberAccount();
        Long nameraccountUAN = c.getBanckAccounts().getBankAccountUAN().getNumberAccount();
        Long nameraccountEUR = c.getBanckAccounts().getBankAccountEUR().getNumberAccount();
        listNamberAccount.add(nameraccountUSD);
        listNamberAccount.add(nameraccountUAN);
        listNamberAccount.add(nameraccountEUR);
        entityManager.close();
        return listNamberAccount;
    }

    private static void topUpAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the account number to which you want to transfer funds");
        int rezult = scanner.nextInt();
        Long namberaccount = Long.valueOf(rezult);
        System.out.println("select currency");
        System.out.println("UAH->1");
        System.out.println("EUR->2");
        System.out.println("USD->3");
        int currency = scanner.nextInt();
        System.out.println("enter the amounts");
        int amounts = scanner.nextInt();
        String paramCurrency = "";
        if (currency == 1) {
            paramCurrency = "UAN";
        }
        if (currency == 2) {
            paramCurrency = "EUR";
        }
        if (currency == 3) {
            paramCurrency = "USD";
        }
        String paramCurrencyBD = definitionOfCurrency(namberaccount);
        moneyСonversionWhenReplenishingAccount(paramCurrencyBD, paramCurrency, amounts, namberaccount);
    }

    private static String definitionOfCurrency(Long namberaccount) {
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT b.currencyName FROM BanckAccount b where b.numberAccount =:namberaccount");
        query.setParameter("namberaccount", namberaccount);
        String paramCurrencyBD = (String) query.getSingleResult();
        entityManager.close();
        return paramCurrencyBD;
    }

    private static void moneyСonversionWhenTransferringBetweenAccounts(String paramCurrencyBD, String paramCurrency, int amounts, Long namberAccounForReplenishment, Long currentNamberAccount) {
        if (paramCurrencyBD.equals("UAN") && paramCurrency.equals("UAN")) {
            transferMoneyFromOneAccountToAnother(namberAccounForReplenishment, amounts, currentNamberAccount);
        }
        if (paramCurrencyBD.equals("EUR") && paramCurrency.equals("EUR")) {
            transferMoneyFromOneAccountToAnother(namberAccounForReplenishment, amounts, currentNamberAccount);
        }
        if (paramCurrencyBD.equals("USD") && paramCurrency.equals("USD")) {
            transferMoneyFromOneAccountToAnother(namberAccounForReplenishment, amounts, currentNamberAccount);
        }
        if (paramCurrencyBD.equals("UAN") && paramCurrency.equals("EUR")) {
            transferMoneyFromOneAccountToAnother(namberAccounForReplenishment, (coursEUR() * amounts), currentNamberAccount);
        }
        if (paramCurrencyBD.equals("EUR") && paramCurrency.equals("UAN")) {
            transferMoneyFromOneAccountToAnother(namberAccounForReplenishment, (amounts / coursEUR()), currentNamberAccount);
        }
        if (paramCurrencyBD.equals("UAN") && paramCurrency.equals("USD")) {
            transferMoneyFromOneAccountToAnother(namberAccounForReplenishment, (coursUSD() * amounts), currentNamberAccount);
        }
        if (paramCurrencyBD.equals("USD") && paramCurrency.equals("UAN")) {
            transferMoneyFromOneAccountToAnother(namberAccounForReplenishment, (amounts / coursUSD()), currentNamberAccount);
        }
        if (paramCurrencyBD.equals("EUR") && paramCurrency.equals("USD")) {
            Integer updateAmountsInEUR = (amounts * coursUSD()) / coursEUR();
            transferMoneyFromOneAccountToAnother(namberAccounForReplenishment, updateAmountsInEUR, currentNamberAccount);
        }
        if (paramCurrencyBD.equals("USD") && paramCurrency.equals("EUR")) {
            Integer updateAmountsInUSD = (amounts * coursEUR()) / coursUSD();
            transferMoneyFromOneAccountToAnother(namberAccounForReplenishment, updateAmountsInUSD, currentNamberAccount);
        }
    }


    private static void moneyСonversionWhenReplenishingAccount(String paramCurrencyBD, String paramCurrency, int amounts, Long namberaccount) {
        if (paramCurrencyBD.equals("UAN") && paramCurrency.equals("UAN")) {
            transferOfMoneyToAccount(namberaccount, amounts);
        }
        if (paramCurrencyBD.equals("EUR") && paramCurrency.equals("EUR")) {
            transferOfMoneyToAccount(namberaccount, amounts);
        }
        if (paramCurrencyBD.equals("USD") && paramCurrency.equals("USD")) {
            transferOfMoneyToAccount(namberaccount, amounts);
        }
        if (paramCurrencyBD.equals("UAN") && paramCurrency.equals("EUR")) {
            transferOfMoneyToAccount(namberaccount, (coursEUR() * amounts));
        }
        if (paramCurrencyBD.equals("EUR") && paramCurrency.equals("UAN")) {
            transferOfMoneyToAccount(namberaccount, (amounts / coursEUR()));
        }
        if (paramCurrencyBD.equals("UAN") && paramCurrency.equals("USD")) {
            transferOfMoneyToAccount(namberaccount, (coursUSD() * amounts));
        }
        if (paramCurrencyBD.equals("USD") && paramCurrency.equals("UAN")) {
            transferOfMoneyToAccount(namberaccount, (amounts / coursUSD()));
        }
        if (paramCurrencyBD.equals("EUR") && paramCurrency.equals("USD")) {
            Integer updateAmountsInEUR = (amounts * coursUSD()) / coursEUR();
            transferOfMoneyToAccount(namberaccount, updateAmountsInEUR);
        }
        if (paramCurrencyBD.equals("USD") && paramCurrency.equals("EUR")) {
            Integer updateAmountsInUSD = (amounts * coursEUR()) / coursUSD();
            transferOfMoneyToAccount(namberaccount, updateAmountsInUSD);
        }
    }


    private static Integer coursEUR() {
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery("SELECT e.EUR  FROM EcxchangeRates e ");
        Integer coursEUR = (Integer) query.getSingleResult();
        entityManager.close();
        return coursEUR;
    }

    private static Integer coursUSD() {
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        Query query1 = entityManager.createQuery("SELECT e.USD  FROM EcxchangeRates e ");
        Integer coursUSD = (Integer) query1.getSingleResult();
        entityManager.close();
        return coursUSD;
    }


    private static void transferMoneyFromOneAccountToAnother(Long namberaccount, int amounts, Long currentNamberAccount) {
        Transaction transaction = new Transaction();
        transaction.setDateTime(LocalDateTime.now());
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        Query query1 = entityManager.createQuery("SELECT b.id FROM BanckAccount b where b.numberAccount =:namberaccount");
        query1.setParameter("namberaccount", namberaccount);
        Long IdAccounForReplenishment = (Long) query1.getSingleResult();

        Query query = entityManager.createQuery("SELECT b.id FROM BanckAccount b where b.numberAccount =:namberaccount");
        query.setParameter("namberaccount", currentNamberAccount);
        Long IdAccounForWithdrawing = (Long) query.getSingleResult();

        BanckAccount banckAccountCurrentForReplenishment = entityManager.find(BanckAccount.class, IdAccounForReplenishment);
        BanckAccount banckAccountCurrentWithdrawing = entityManager.find(BanckAccount.class, IdAccounForWithdrawing);
        entityManager.getTransaction().begin();
        banckAccountCurrentForReplenishment.setAmountMoney(Long.valueOf(amounts));
        banckAccountCurrentWithdrawing.setAmountMoney(Long.valueOf(amounts) * (-1));
        transaction.setRecipient(banckAccountCurrentForReplenishment);
        transaction.setSender(banckAccountCurrentWithdrawing);
        transaction.setAmountMoney(amounts);
        try {
            entityManager.persist(transaction);
            entityManager.getTransaction().commit();
        } catch (PersistenceException | IllegalArgumentException e) {
            e.getMessage();
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
    }

    private static void transferOfMoneyToAccount(Long namberaccount, int amounts) {
        Transaction transaction = new Transaction();
        transaction.setDateTime(LocalDateTime.now());
        EntityManager entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        Query query1 = entityManager.createQuery("SELECT b.id FROM BanckAccount b where b.numberAccount =:namberaccount");
        query1.setParameter("namberaccount", namberaccount);
        Long IdAccount = (Long) query1.getSingleResult();
        BanckAccount banckAccountCurrent = entityManager.find(BanckAccount.class, IdAccount);
        entityManager.getTransaction().begin();
        banckAccountCurrent.setAmountMoney(Long.valueOf(amounts));
        transaction.setRecipient(banckAccountCurrent);
        transaction.setAmountMoney(amounts);
        try {
            entityManager.persist(transaction);
            entityManager.persist(transaction);
            entityManager.getTransaction().commit();
        } catch (PersistenceException | IllegalArgumentException e) {
            e.getMessage();
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
    }
}
