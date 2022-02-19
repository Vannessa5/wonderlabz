package za.co.wonderbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.wonderbank.models.Transaction;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Long> {
}
