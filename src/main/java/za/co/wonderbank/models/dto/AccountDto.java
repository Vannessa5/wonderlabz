package za.co.wonderbank.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AccountDto {
    private String reference;
    private Double amount;
    private Long accountNumber;
    private String accountType;
}
