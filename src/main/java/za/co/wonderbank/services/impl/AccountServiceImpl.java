package za.co.wonderbank.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import za.co.wonderbank.models.Account;
import za.co.wonderbank.repositories.AccountRepository;
import za.co.wonderbank.services.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findByAccountNumber(Long accountNumber) {
        log.info("Request to get Account By account number: {}", accountNumber);
        return this.accountRepository.findByAccountNumber(accountNumber).orElse(null);
    }

    @Override
    public Account save(Account account) {
        log.info("Request to save account: {}", account);
        return this.accountRepository.save(account);
    }

    @Override
    public List<Account> saveAll(List<Account> accounts) {
        log.info("Request to save accounts: {}", accounts);

        return this.accountRepository.saveAll(accounts);
    }


}
