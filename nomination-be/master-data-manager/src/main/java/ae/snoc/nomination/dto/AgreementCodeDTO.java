package ae.snoc.nomination.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgreementCodeDTO {

    private Integer agreementId;
    private Integer customerId;
    @NotBlank
    private String agreementCode;
    @NotNull(message = "EffectiveTo must not be null")
    private LocalDate effectiveFrom;
    @NotNull(message = "EffectiveTo must not be null")
    private LocalDate effectiveTo;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$", message = "created user must not contain digits or special characters, and leading/trailing spaces are not allowed")
    private String createdUser;
    @JsonIgnore
    private Timestamp createdTimestamp;
    @Pattern(regexp = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$", message = "created user must not contain digits or special characters, and leading/trailing spaces are not allowed")
    private String updatedUser;
    @JsonIgnore
    private Timestamp updatedTimestamp;
    private Boolean isActive = true;

}
