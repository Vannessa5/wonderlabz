package za.co.wonderbank.services;

import org.springframework.stereotype.Service;
import za.co.wonderbank.models.Customer;

import java.util.List;

@Service
public interface CustomerService {

    Customer save(Customer customer);

    Customer deposit(Long accountNumber, String accountType, Double amount, String reference);

    Customer withdraw(Long accountNumber, String accountType, Double amount, String reference) throws Exception;

    Customer getBalance(Long accountNumber);


    List<Customer> findAllCustomers();
}
