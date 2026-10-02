package band.wearelive.landing.common.web;

/**
 * The two audiences live on separate URLs. This is the only place that knows both paths, so
 * cross-links (audience switch, footer) never depend on the other audience's code.
 */
public final class SiteRoutes {

    public static final String BAND = "/";
    public static final String OWNER = "/owner";

    private SiteRoutes() {
    }
}
