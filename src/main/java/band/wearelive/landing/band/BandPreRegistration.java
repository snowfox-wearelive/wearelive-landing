package band.wearelive.landing.band;

import band.wearelive.landing.common.preregistration.PreRegistrationEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "band_pre_registration")
public class BandPreRegistration extends PreRegistrationEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Position position;

    protected BandPreRegistration() {
    }

    public BandPreRegistration(String name, String email, boolean marketingConsent, Position position) {
        super(name, email, marketingConsent);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
