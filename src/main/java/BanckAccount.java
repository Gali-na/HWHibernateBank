import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "BanckAccount")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator",discriminatorType =DiscriminatorType.STRING )
@DiscriminatorValue(value = "B")
public class BanckAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "numberAccount", nullable = false)
    private Long numberAccount;

    @NotNull
    @Column(name = "amountMoney", nullable = false)
    private Long amountMoney;

    @NotNull
    @Column(name = "currencyName", nullable = false)
    private String currencyName;

    public BanckAccount() {

    }

    public BanckAccount(Long numberAccount, Long amountMoney, String currencyName) {
        this.numberAccount = numberAccount;
        this.amountMoney = amountMoney;
        this.currencyName = currencyName;
    }

    public Long getId() {
        return id;
    }

    public Long getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(Long numberAccount) {
        this.numberAccount = numberAccount;
    }

    public Long getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(Long amountMoney) {
        this.amountMoney = this.amountMoney+ amountMoney;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }


}
