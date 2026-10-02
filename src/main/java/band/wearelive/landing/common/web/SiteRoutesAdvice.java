package band.wearelive.landing.common.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/** Exposes the route table to every template as {@code ${routes}}. */
@ControllerAdvice
class SiteRoutesAdvice {

    /**
     * @param bandReserve  band page URL that opens its pre-registration modal on arrival
     * @param ownerReserve owner page URL that opens its pre-registration modal on arrival
     */
    public record Routes(String band, String owner, String bandReserve, String ownerReserve) {
    }

    private static final Routes ROUTES = new Routes(
            SiteRoutes.BAND,
            SiteRoutes.OWNER,
            SiteRoutes.BAND + "?" + SiteRoutes.RESERVE_PARAM + "=1",
            SiteRoutes.OWNER + "?" + SiteRoutes.RESERVE_PARAM + "=1");

    @ModelAttribute("routes")
    Routes routes() {
        return ROUTES;
    }
}
