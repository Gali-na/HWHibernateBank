import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "dateTime", nullable = false)
    private LocalDateTime dateTime;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "banckAccount_recipient_id")
    private BanckAccount recipient;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "banckAccount_sender_id")
    private BanckAccount sender;
    @NotNull
    @Column(name = "amount_money", nullable = false)
    private Integer amountMoney;

    public Integer getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(Integer amountMoney) {
        this.amountMoney = amountMoney;
    }

    public Transaction() {
    }

    public Transaction(LocalDateTime dateTime, BanckAccount recipient, BanckAccount sender) {

        this.dateTime = dateTime;
        this.recipient = recipient;
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BanckAccount getRecipient() {
        return recipient;
    }

    public void setRecipient(BanckAccount recipient) {
        this.recipient = recipient;
    }

    public BanckAccount getSender() {
        return sender;
    }

    public void setSender(BanckAccount sender) {
        this.sender = sender;
    }
}
