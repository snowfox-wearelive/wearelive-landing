package band.wearelive.landing.band;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BandPreRegistrationRepository extends JpaRepository<BandPreRegistration, Long> {

    boolean existsByEmail(String email);
}
