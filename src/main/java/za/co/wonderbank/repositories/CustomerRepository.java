package za.co.wonderbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.wonderbank.models.Customer;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * from customer where account_id=?1", nativeQuery = true)
    Customer findByAccountId(Long accountId);
}
