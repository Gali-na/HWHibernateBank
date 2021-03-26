import com.sun.istack.NotNull;

import javax.persistence.*;
@Entity
@Table(name = "bankAccountEUR")
@DiscriminatorValue(value ="EUR")
public class BankAccountEUR extends BanckAccount {

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "bankAccountEUR")
    private BanckAccounts banckAccounts;

    public BankAccountEUR() {
    }

    public BankAccountEUR(Long numberAccount, Long amountMoney, String currencyName) {
        super(numberAccount, amountMoney, currencyName);
    }

    public BanckAccounts getBanckAccounts() {
        return banckAccounts;
    }

    public void setBanckAccounts(BanckAccounts banckAccounts) {
        this.banckAccounts = banckAccounts;
    }


}
