package za.co.wonderbank.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import za.co.wonderbank.models.Account;
import za.co.wonderbank.models.AccountType;
import za.co.wonderbank.models.Customer;
import za.co.wonderbank.models.Transaction;
import za.co.wonderbank.repositories.CustomerRepository;
import za.co.wonderbank.repositories.TransactionRepository;
import za.co.wonderbank.services.AccountService;
import za.co.wonderbank.services.CustomerService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final AccountService accountService;
    private final AccountTypeServiceImpl accountTypeService;
    private final TransactionRepository transactionRepository;


    public CustomerServiceImpl(CustomerRepository customerRepository, AccountService accountService,
                               AccountTypeServiceImpl accountTypeService, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.accountService = accountService;
        this.accountTypeService = accountTypeService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Customer save(Customer customer) {
        log.info("Request to save customer: {}", customer);

        List<AccountType> accountTypes = this.accountTypeService.saveAll(customer.getAccount().getAccountTypes());

        customer.getAccount().setAccountTypes(accountTypes);
        Account save1 = this.accountService.save(customer.getAccount());

        customer.setAccount(save1);
        Customer save = this.customerRepository.save(customer);
        return save;
    }

    @Override
    public Customer deposit(Long accountNumber, String accountType, Double amount, String reference) {
        log.info("Request to make a deposit for account number: {}", accountType);
        Account existingAccount = this.accountService.findByAccountNumber(accountNumber);
        if (existingAccount != null) {
            List<AccountType> accountTypes = existingAccount.getAccountTypes();
            for (AccountType type : accountTypes) {
                if (type.getName().equalsIgnoreCase(accountType)) {
                    Double balance = type.getBalance() + amount;
                    type.setBalance(balance);

                    this.accountTypeService.save(type);

                    Transaction transaction = new Transaction(accountNumber, "Deposit", amount, balance, reference, LocalDateTime.now(), accountType);
                    this.transactionRepository.save(transaction);
                }
            }
        }
        return this.customerRepository.findByAccountId(existingAccount.getId());
    }


    @Override
    public Customer withdraw(Long accountNumber, String accountType, Double amount, String reference) throws Exception {
        log.info("Request to make a withdrawal for account number: {}", accountType);
        Account existingAccount = this.accountService.findByAccountNumber(accountNumber);
        if (existingAccount != null) {
            List<AccountType> accountTypes = existingAccount.getAccountTypes();
            for (AccountType type : accountTypes) {
                if (type.getName().equalsIgnoreCase(accountType)
                        && type.getName().equalsIgnoreCase("Savings Account")) {

                    Double balance = type.getBalance() - amount;
                    if (balance >= 1000) {
                        type.setBalance(balance);
                        this.accountTypeService.save(type);
                        Transaction transaction = new Transaction(accountNumber, "Withdrawal", amount, balance, reference, LocalDateTime.now(), accountType);
                        this.transactionRepository.save(transaction);
                    } else {
                        throw new Exception("Insufficient Funds. Balance can't be less than 1000.0. Requested amount "+ amount+ " Available balance "+type.getBalance() );
                    }
                } else if (type.getName().equalsIgnoreCase(accountType)
                        && type.getName().equalsIgnoreCase("Current Account")) {

                    Double balance = type.getBalance() - amount;
                    if (balance >= -100000) {
                        type.setBalance(balance);
                        this.accountTypeService.save(type);
                        Transaction transaction = new Transaction(accountNumber, "Withdrawal", amount, balance, reference, LocalDateTime.now());
                        this.transactionRepository.save(transaction);
                    } else {
                        throw new Exception("Insufficient Funds. Balance can't be less than 1000.0. Requested amount "+ amount+ " Available balance "+type.getBalance() );
                    }
                }
            }
        }
        return this.customerRepository.findByAccountId(existingAccount.getId());
    }

    @Override
    public Customer getBalance(Long accountNumber) {
        log.info("Request to get customer by account number: {}", accountNumber);
        Account byAccountNumber = this.accountService.findByAccountNumber(accountNumber);
        return this.customerRepository.findByAccountId(byAccountNumber.getId());
    }

    }
