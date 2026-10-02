package band.wearelive.landing.band;

import band.wearelive.landing.common.content.LandingContent;
import band.wearelive.landing.common.web.SiteRoutes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** Figma "01 랜딩 – 밴드 탭" — general user (band member) landing page. */
@Controller
class BandLandingController {

    private static final LandingContent CONTENT = BandLandingContent.create();

    private final String preRegistrationUrl;

    BandLandingController(@Value("${wearelive.band.pre-registration-url}") String preRegistrationUrl) {
        this.preRegistrationUrl = preRegistrationUrl;
    }

    @GetMapping(SiteRoutes.BAND)
    String landing(Model model) {
        model.addAttribute("page", CONTENT);
        model.addAttribute("preRegistrationUrl", preRegistrationUrl);
        return "band/landing";
    }
}
