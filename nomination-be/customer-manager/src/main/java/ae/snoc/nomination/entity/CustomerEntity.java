package ae.snoc.nomination.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "SNOC_CUSTOMER", schema = "SNOC_NOMINATION")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;
    private String customerName;
    private String contactNumber;
    private String pointOfContact;
    private String createdUser;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdTimestamp;
    private String updatedUser;
    @UpdateTimestamp
    private Timestamp updatedTimestamp;
    private Boolean isActive = true;

}
