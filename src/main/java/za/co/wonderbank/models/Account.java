package za.co.wonderbank.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@Getter
@Setter

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", unique = true)
    private Long accountNumber;

    @OneToMany(fetch = FetchType.LAZY)
    private List<AccountType> accountTypes;

}
