package band.wearelive.landing.band;

import band.wearelive.landing.common.preregistration.DuplicateRegistrationException;
import band.wearelive.landing.common.preregistration.PreRegistrationEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BandPreRegistrationService {

    private final BandPreRegistrationRepository repository;

    public BandPreRegistrationService(BandPreRegistrationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public BandPreRegistration register(BandPreRegistrationRequest request) {
        if (repository.existsByEmail(PreRegistrationEntity.normalizeEmail(request.email()))) {
            throw new DuplicateRegistrationException();
        }
        try {
            return repository.saveAndFlush(new BandPreRegistration(
                    request.name(), request.email(), request.marketingConsent(), request.position()));
        } catch (DataIntegrityViolationException e) {
            // Concurrent submit with the same email slipped past the exists-check.
            throw new DuplicateRegistrationException();
        }
    }
}
