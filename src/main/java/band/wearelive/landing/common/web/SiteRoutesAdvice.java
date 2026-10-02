package band.wearelive.landing.common.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/** Exposes the route table to every template as {@code ${routes}}. */
@ControllerAdvice
class SiteRoutesAdvice {

    public record Routes(String band, String owner) {
    }

    private static final Routes ROUTES = new Routes(SiteRoutes.BAND, SiteRoutes.OWNER);

    @ModelAttribute("routes")
    Routes routes() {
        return ROUTES;
    }
}
