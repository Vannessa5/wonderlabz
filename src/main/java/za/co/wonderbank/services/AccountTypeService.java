package za.co.wonderbank.services;

import org.springframework.stereotype.Service;
import za.co.wonderbank.models.AccountType;

import java.util.List;

@Service
public interface AccountTypeService {

    AccountType save(AccountType accountType);

    List<AccountType> saveAll(List<AccountType> accountTypes);
}
