package ae.snoc.nomination.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    public static final String REGEX_FOR_NAME = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$";

    @Schema(description = "Customer ID(For reference purposes only. Should not be passed while creating customer)", example = "SEWA")
    private Integer customerId;

    @NotBlank
    @Pattern(regexp = REGEX_FOR_NAME, message = "Customer name must not contain digits or special characters, and leading/trailing spaces are not allowed")
    @Schema(description = "Customer Name", example = "SEWA")
    private String customerName;

    @Pattern(regexp = "^[+\\d-]+$", message = "Contact number must contain only digits, +, or - ")
    @Schema(description = "Customer Contact Number", example = "+971-6-5199700")
    private String contactNumber;

    @Pattern(regexp = REGEX_FOR_NAME, message = "Point of contact must not contain digits or special characters, and leading/trailing spaces are not allowed")
    @Schema(description = "Person to Contact from the customer side for any queries", example = "John Doe")
    private String pointOfContact;

    @JsonIgnore
    @Pattern(regexp = REGEX_FOR_NAME, message = "created user must not contain digits or special characters, and leading/trailing spaces are not allowed")
    private String createdUser;

    @JsonIgnore
    private Timestamp createdTimestamp;

    @JsonIgnore
    @Pattern(regexp = REGEX_FOR_NAME, message = "Update user name must not contain digits or special characters, and leading/trailing spaces are not allowed")
    private String updatedUser;

    @JsonIgnore
    private Timestamp updatedTimestamp;

    @Schema(description = "Status of the User", example = "true")
    private Boolean isActive = true;

}



