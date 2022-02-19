package za.co.wonderbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.wonderbank.models.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {

    @Query(value = "select * from transactions where account_number=?1 order by date desc", nativeQuery = true)
    List<Transaction> findByAccountNumber(Long accountNumber);
}
