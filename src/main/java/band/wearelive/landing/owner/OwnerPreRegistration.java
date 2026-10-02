package band.wearelive.landing.owner;

import band.wearelive.landing.common.preregistration.PreRegistrationEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "owner_pre_registration")
public class OwnerPreRegistration extends PreRegistrationEntity {

    protected OwnerPreRegistration() {
    }

    public OwnerPreRegistration(String name, String email, boolean marketingConsent) {
        super(name, email, marketingConsent);
    }
}
