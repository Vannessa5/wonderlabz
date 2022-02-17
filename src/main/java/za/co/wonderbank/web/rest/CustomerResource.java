package za.co.wonderbank.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import za.co.wonderbank.models.AccountType;
import za.co.wonderbank.models.Customer;
import za.co.wonderbank.models.dto.AccountDto;
import za.co.wonderbank.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // cross origin will only allow for specific entry
public class CustomerResource {

    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity openAccount(@RequestBody Customer customer) throws Exception {
        log.info("Rest request to save customer: {}", customer);
        if (customer.getId() != null) {
            throw new Exception("Can't create new customer with id: " + customer.getId());
        }

        List<AccountType> accountTypes = customer.getAccount().getAccountTypes();

        this.validateAccountTypes(accountTypes);

        for (AccountType accountType : accountTypes) {
            if (accountType.getName().equalsIgnoreCase("Savings Account")) {
                if (accountType.getBalance() < 1000) {
                    throw new Exception("Can't Open a saving account with balance less than 1000");
                } else {
                    customer = this.customerService.save(customer);
                }
            } else {
                customer = this.customerService.save(customer);
            }
        }

        return ResponseEntity.ok(customer);

    }

    @PutMapping("/customers")
    public ResponseEntity updateAccount(@RequestBody Customer customer) throws Exception {
        log.info("Rest request to update customer: {}", customer);
        if (customer.getId() == null) {
            throw new Exception("Can't update customer with id.null");
        }

        List<AccountType> accountTypes = customer.getAccount().getAccountTypes();

        this.validateAccountTypes(accountTypes);

        for (AccountType accountType : accountTypes) {
            if (accountType.getName().equalsIgnoreCase("Savings Account")) {
                if (accountType.getBalance() < 1000) {
                    throw new Exception("Can't Open a saving account with balance less than 1000");
                } else {
                    customer = this.customerService.save(customer);
                }
            } else {
                customer = this.customerService.save(customer);
            }
        }
        return ResponseEntity.ok(customer);

    }

    private void validateAccountTypes(List<AccountType> accountTypes) throws Exception {
        if (accountTypes.size() > 2) {
            throw new Exception("Customer Can not have more than 2 account");
        }
        if (accountTypes.size() == 2) {
            if ((accountTypes.get(0).getName().equalsIgnoreCase("Savings Account") && accountTypes.get(1).getName().equalsIgnoreCase("Savings Account"))
                    || (accountTypes.get(0).getName().equalsIgnoreCase("Current Account") && accountTypes.get(1).getName().equalsIgnoreCase("Current Account"))) {
                throw new Exception("Invalid Account Types. A Customer is not allowed to have 2 account of same type.");
            }
        }
    }

    @PutMapping("/customers/deposit")
    public ResponseEntity deposit(@Validated @RequestBody AccountDto accountDto) {
        log.info("Rest request to deposit: {}", accountDto);
        Customer deposit = this.customerService.deposit(accountDto.getAccountNumber(), accountDto.getAccountType(), accountDto.getAmount(), accountDto.getReference());
        return ResponseEntity.ok(deposit);
    }

    @PutMapping("/customers/withdrawal")
    public ResponseEntity withdrawral(@Validated @RequestBody AccountDto accountDto) throws Exception {
        log.info("Rest request to withdraw: {}", accountDto);
        Customer withdraw = this.customerService.withdraw(accountDto.getAccountNumber(), accountDto.getAccountType(), accountDto.getAmount(), accountDto.getReference());
        return ResponseEntity.ok(withdraw);
    }

    @GetMapping("/customers/balance/{accountNumber}")
    public ResponseEntity getBalance(@PathVariable Long accountNumber) {
        log.info("Rest request to get balance: {}", accountNumber);
        Customer balance = this.customerService.getBalance(accountNumber);
        return ResponseEntity.ok(balance);
    }
}
