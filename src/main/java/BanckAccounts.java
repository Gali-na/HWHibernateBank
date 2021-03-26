import javax.persistence.*;

@Entity
@Table(name = "banckAccounts")
public class BanckAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "banckAccounts")
   private Client client;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankAccountUAN_id")
    private BankAccountUAN bankAccountUAN;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankAccountUSD_id")
    private BankAccountUSD bankAccountUSD;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bankAccountEUR_id")
    private BankAccountEUR bankAccountEUR;


    public BanckAccounts() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BankAccountUAN getBankAccountUAN() {
        return bankAccountUAN;
    }

    public void setBankAccountUAN(BankAccountUAN bankAccountUAN) {
        this.bankAccountUAN = bankAccountUAN;
    }

    public BankAccountUSD getBankAccountUSD() {
        return bankAccountUSD;
    }

    public void setBankAccountUSD(BankAccountUSD bankAccountUSD) {
        this.bankAccountUSD = bankAccountUSD;
    }

    public BankAccountEUR getBankAccountEUR() {
        return bankAccountEUR;
    }

    public void setBankAccountEUR(BankAccountEUR bankAccountEUR) {
        this.bankAccountEUR = bankAccountEUR;
    }

    @Override
    public String toString() {
        return "BanckAccounts{" +
                "id=" + id +
                ", client=" + client +
                ", bankAccountUAN=" + bankAccountUAN +
                ", bankAccountUSD=" + bankAccountUSD +
                ", bankAccountEUR=" + bankAccountEUR +
                '}';
    }
}
