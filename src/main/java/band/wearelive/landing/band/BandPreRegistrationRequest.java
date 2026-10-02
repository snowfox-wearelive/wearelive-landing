package band.wearelive.landing.band;

import static band.wearelive.landing.common.preregistration.ValidationMessages.CONSENT_REQUIRED;
import static band.wearelive.landing.common.preregistration.ValidationMessages.EMAIL_INVALID;
import static band.wearelive.landing.common.preregistration.ValidationMessages.NAME_REQUIRED;
import static band.wearelive.landing.common.preregistration.ValidationMessages.NAME_TOO_LONG;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BandPreRegistrationRequest(
        @NotBlank(message = NAME_REQUIRED) @Size(max = 50, message = NAME_TOO_LONG) String name,
        @NotBlank(message = EMAIL_INVALID) @Email(message = EMAIL_INVALID) @Size(max = 254, message = EMAIL_INVALID) String email,
        @AssertTrue(message = CONSENT_REQUIRED) boolean marketingConsent,
        @NotNull(message = "주로 맡는 포지션을 선택해주세요.") Position position) {
}
