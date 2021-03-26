import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banckAccounts_id")
    private BanckAccounts banckAccounts;

    public Client() {
    }

    public Client(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

   public BanckAccounts getBanckAccounts() {
        return banckAccounts;
    }

    public void setBanckAccounts(BanckAccounts banckAccounts) {
        this.banckAccounts = banckAccounts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}



