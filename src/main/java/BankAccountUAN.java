import com.sun.istack.NotNull;

import javax.persistence.*;
@Entity
@Table(name = "bankAccountUAN")
@DiscriminatorValue(value ="UAN")
public class BankAccountUAN extends BanckAccount{

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "bankAccountUAN")
    private BanckAccounts banckAccounts;
    public BankAccountUAN() {
    }

    public BankAccountUAN(Long numberAccount, Long amountMoney, String currencyName) {
        super(numberAccount, amountMoney, currencyName);
    }

    public BanckAccounts getBanckAccounts() {
        return banckAccounts;
    }

    public void setBanckAccounts(BanckAccounts banckAccounts) {
        this.banckAccounts = banckAccounts;
    }


}
