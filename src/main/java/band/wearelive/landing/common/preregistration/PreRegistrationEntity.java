package band.wearelive.landing.common.preregistration;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.Locale;

/** Columns common to every audience's pre-registration table. Each audience maps its own table. */
@MappedSuperclass
public abstract class PreRegistrationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Column(nullable = false)
    private boolean marketingConsent;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    protected PreRegistrationEntity() {
    }

    protected PreRegistrationEntity(String name, String email, boolean marketingConsent) {
        this.name = name.strip();
        this.email = normalizeEmail(email);
        this.marketingConsent = marketingConsent;
        this.createdAt = Instant.now();
    }

    public static String normalizeEmail(String email) {
        return email.strip().toLowerCase(Locale.ROOT);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isMarketingConsent() {
        return marketingConsent;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
