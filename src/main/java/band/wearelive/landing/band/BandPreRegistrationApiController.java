package band.wearelive.landing.band;

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
class BandPreRegistrationApiController {

    private final BandPreRegistrationService service;

    BandPreRegistrationApiController(BandPreRegistrationService service) {
        this.service = service;
    }

    @PostMapping(SiteRoutes.BAND_API)
    @ResponseStatus(HttpStatus.CREATED)
    PreRegistrationResponse register(@Valid @RequestBody BandPreRegistrationRequest request) {
        return PreRegistrationResponse.created(service.register(request).getId());
    }
}
