package band.wearelive.landing.band;

import band.wearelive.landing.common.content.LandingContent;
import band.wearelive.landing.common.web.SiteRoutes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** Figma "01 랜딩 – 밴드 탭" — general user (band member) landing page. */
@Controller
class BandLandingController {

    private static final LandingContent CONTENT = BandLandingContent.create();

    @GetMapping(SiteRoutes.BAND)
    String landing(Model model) {
        model.addAttribute("page", CONTENT);
        model.addAttribute("positions", Position.values());
        model.addAttribute("defaultPosition", Position.VOCAL);
        model.addAttribute("apiEndpoint", SiteRoutes.BAND_API);
        return "band/landing";
    }
}
