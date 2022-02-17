package za.co.wonderbank.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.wonderbank.models.AccountType;
import za.co.wonderbank.repositories.AccountTypeRepository;
import za.co.wonderbank.services.AccountTypeService;

import java.util.List;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    private static final Logger log = LoggerFactory.getLogger(AccountTypeServiceImpl.class);

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Override
    public AccountType save(AccountType accountType) {
        log.info("Request to save Account type: {}", accountType);
        return this.accountTypeRepository.save(accountType);
    }

    @Override
    public List<AccountType> saveAll(List<AccountType> accountTypes) {
        log.info("Request to save AccountTypes: {}", accountTypes);
        return this.accountTypeRepository.saveAll(accountTypes);
    }
}
