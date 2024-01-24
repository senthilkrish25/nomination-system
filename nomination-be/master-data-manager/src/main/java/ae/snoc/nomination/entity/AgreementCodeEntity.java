package ae.snoc.nomination.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "SNOC_AGREEMENT_CODE", schema = "SNOC_NOMINATION")
public class AgreementCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer agreementId;
    private Integer customerId;
    private String agreementCode;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private String createdUser;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdTimestamp;
    private String updatedUser;
    @UpdateTimestamp
    private Timestamp updatedTimestamp;
    private Boolean isActive = true;

}
