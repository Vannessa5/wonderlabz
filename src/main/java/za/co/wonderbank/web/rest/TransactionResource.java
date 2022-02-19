package za.co.wonderbank.web.rest;
import org.slf4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.wonderbank.models.Transaction;
import za.co.wonderbank.repositories.TransactionRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TransactionResource {


    private static final Logger log = LoggerFactory.getLogger(TransactionResource.class);


    private TransactionRepository transactionRepository;

    public TransactionResource(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    // here you would love to add a start and end date to get transactions. (in future)
    @GetMapping("/transactions/{accountNumber}")
    public ResponseEntity getTransactionForAccount(@PathVariable Long accountNumber) {
        log.info("Request to get all transaction by account number");
        List<Transaction> byAccountNumber = this.transactionRepository.findByAccountNumber(accountNumber);
        return ResponseEntity.ok(byAccountNumber);
    }
}
