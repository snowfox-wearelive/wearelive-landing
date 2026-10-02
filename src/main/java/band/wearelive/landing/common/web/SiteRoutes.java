package band.wearelive.landing.common.web;

/**
 * The two audiences live on separate URLs. This is the only place that knows both paths, so
 * cross-links (audience switch, footer, modal user-type toggle) never depend on the other audience's code.
 */
public final class SiteRoutes {

    public static final String BAND = "/";
    public static final String OWNER = "/owner";

    public static final String BAND_API = "/api/band/pre-registrations";
    public static final String OWNER_API = "/api/owner/pre-registrations";

    /** {@code ?reserve=1} opens the pre-registration modal on arrival (used when hopping between audiences). */
    public static final String RESERVE_PARAM = "reserve";

    private SiteRoutes() {
    }
}
