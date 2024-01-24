package ae.snoc.nomination.repository;

import ae.snoc.nomination.entity.AgreementCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementCodeRepository extends JpaRepository<AgreementCodeEntity, Integer> {
    boolean existsByAgreementCode(String customerName);
}
