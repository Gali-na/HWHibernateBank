import com.sun.istack.NotNull;

import javax.persistence.*;
@Entity
@Table(name = "bankAccountUSD")
@DiscriminatorValue(value ="USD")
public class BankAccountUSD extends BanckAccount {

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "bankAccountUSD")
    private BanckAccounts banckAccounts;

    public BankAccountUSD() {
    }

    public BankAccountUSD(Long numberAccount, Long amountMoney, String currencyName) {
        super(numberAccount, amountMoney, currencyName);
    }

    public BanckAccounts getBanckAccounts() {
        return banckAccounts;
    }

    public void setBanckAccounts(BanckAccounts banckAccounts) {
        this.banckAccounts = banckAccounts;
    }


}
