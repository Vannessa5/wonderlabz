package za.co.wonderbank.services;

import org.springframework.stereotype.Service;
import za.co.wonderbank.models.Account;

import java.util.List;

@Service
public interface AccountService {

    Account findByAccountNumber(Long accountNumber);

    Account save(Account account);

    List<Account> saveAll( List<Account> accounts);

}
