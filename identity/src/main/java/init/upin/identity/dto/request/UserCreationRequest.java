package init.upin.identity.dto.request;


import init.upin.identity.validator.DobConstraint;
import init.upin.identity.validator.PasswordConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Email(message = "INVALID_EMAIL")
    @NotBlank(message = "EMAIL_IS_REQUIRED")
    String username;

    @PasswordConstraint(
            min = 8,
            message = "PASSWORD_INVALID"
    )
    String password;

    @NotBlank(message = "USERNAME_INVALID")
    String fullName;

    @DobConstraint(min = 10, message = "INVALID_DOB")
    LocalDate dob;

    String city;
}
