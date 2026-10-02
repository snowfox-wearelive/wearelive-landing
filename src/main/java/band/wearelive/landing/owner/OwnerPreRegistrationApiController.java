package band.wearelive.landing.owner;

import band.wearelive.landing.common.preregistration.PreRegistrationResponse;
import band.wearelive.landing.common.web.PreRegistrationApi;
import band.wearelive.landing.common.web.SiteRoutes;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreRegistrationApi
class OwnerPreRegistrationApiController {

    private final OwnerPreRegistrationService service;

    OwnerPreRegistrationApiController(OwnerPreRegistrationService service) {
        this.service = service;
    }

    @PostMapping(SiteRoutes.OWNER_API)
    @ResponseStatus(HttpStatus.CREATED)
    PreRegistrationResponse register(@Valid @RequestBody OwnerPreRegistrationRequest request) {
        return PreRegistrationResponse.created(service.register(request).getId());
    }
}
