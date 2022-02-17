package za.co.wonderbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.wonderbank.models.AccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
}
