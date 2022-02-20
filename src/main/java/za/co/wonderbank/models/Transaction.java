package za.co.wonderbank.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString
@Getter
@Setter

@Entity
@Table(name ="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountNumber;
    private String transactionType;
    private Double amount;
    private Double balance;
    private String reference;
    private LocalDateTime date;
    private String accountType;

    public Transaction() {
    }

    public Transaction(Long accountNumber, String transactionType, Double amount, Double balance, String reference, LocalDateTime date) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
        this.reference = reference;
        this.date = date;
    }
    public Transaction(Long accountNumber, String transactionType, Double amount, Double balance, String reference, LocalDateTime date, String accountType) {
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
        this.reference = reference;
        this.date = date;
        this.accountType = accountType;
    }


}
