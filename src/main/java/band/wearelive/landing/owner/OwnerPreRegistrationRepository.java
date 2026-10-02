package band.wearelive.landing.owner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerPreRegistrationRepository extends JpaRepository<OwnerPreRegistration, Long> {

    boolean existsByEmail(String email);
}
