package band.wearelive.landing.owner;

import band.wearelive.landing.common.preregistration.DuplicateRegistrationException;
import band.wearelive.landing.common.preregistration.PreRegistrationEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OwnerPreRegistrationService {

    private final OwnerPreRegistrationRepository repository;

    public OwnerPreRegistrationService(OwnerPreRegistrationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public OwnerPreRegistration register(OwnerPreRegistrationRequest request) {
        if (repository.existsByEmail(PreRegistrationEntity.normalizeEmail(request.email()))) {
            throw new DuplicateRegistrationException();
        }
        try {
            return repository.saveAndFlush(new OwnerPreRegistration(
                    request.name(), request.email(), request.marketingConsent()));
        } catch (DataIntegrityViolationException e) {
            // Concurrent submit with the same email slipped past the exists-check.
            throw new DuplicateRegistrationException();
        }
    }
}
