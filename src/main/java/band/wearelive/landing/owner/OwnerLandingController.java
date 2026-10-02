package band.wearelive.landing.owner;

import band.wearelive.landing.common.content.LandingContent;
import band.wearelive.landing.common.web.SiteRoutes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** Figma "02 랜딩 – 사장님 탭" — rental venue owner landing page. */
@Controller
class OwnerLandingController {

    private static final LandingContent CONTENT = OwnerLandingContent.create();

    @GetMapping(SiteRoutes.OWNER)
    String landing(Model model) {
        model.addAttribute("page", CONTENT);
        model.addAttribute("apiEndpoint", SiteRoutes.OWNER_API);
        return "owner/landing";
    }
}
