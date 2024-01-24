package ae.snoc.nomination.repository;

import ae.snoc.nomination.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
     boolean existsByCustomerName(String customerName);

     Page<CustomerEntity> findByIsActive(Boolean isActive, Pageable pageable);
}
