import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "ecxchange_rates")
public class EcxchangeRates {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;
    @NotNull
    @Column(name = "usd", nullable = false)
    private Integer USD;
    @NotNull
    @Column(name = "eur", nullable = false)
    private Integer EUR;

    public EcxchangeRates() {
    }

    public EcxchangeRates(Integer USD, Integer EUR) {
        this.USD = USD;
        this.EUR = EUR;
    }

    public Integer getId() {
        return Id;
    }
    public Integer getUSD() {
        return USD;
    }

    public void setUSD(Integer USD) {
        this.USD = USD;
    }

    public Integer getEUR() {
        return EUR;
    }

    public void setEUR(Integer EUR) {
        this.EUR = EUR;
    }
}
