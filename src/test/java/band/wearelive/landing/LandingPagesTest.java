package band.wearelive.landing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class LandingPagesTest {

    /** Google Form IDs — each page must link only to its own audience's form. */
    private static final String BAND_FORM = "1k9AXAFiB6ekKuRddNuYwUhi_EzoPl2kFMu6Gv_m4BY8";
    private static final String OWNER_FORM = "1S69UsyIjmmL6baxdhBonj4veTa6yZE4fCjFLa_l6gJY";

    @Autowired
    MockMvc mvc;

    @Value("${wearelive.band.pre-registration-url}")
    String bandFormUrl;

    @Value("${wearelive.owner.pre-registration-url}")
    String ownerFormUrl;

    @Test
    void bandPageRendersOnlyBandContent() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("band/landing"))
                .andExpect(content().string(containsString("밴드 공지·일정 관리부터")))
                .andExpect(content().string(not(containsString("공연장·합주실의 빈 시간"))))
                .andExpect(content().string(not(containsString("/assets/owner/"))));
    }

    @Test
    void ownerPageRendersOnlyOwnerContent() throws Exception {
        mvc.perform(get("/owner"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/landing"))
                .andExpect(content().string(containsString("공연장·합주실의 빈 시간")))
                .andExpect(content().string(not(containsString("밴드 공지·일정 관리부터"))))
                .andExpect(content().string(not(containsString("/assets/band/"))));
    }

    @Test
    void bandPreRegistrationButtonsLinkToBandForm() throws Exception {
        String html = mvc.perform(get("/")).andReturn().getResponse().getContentAsString();

        assertThat(bandFormUrl).contains(BAND_FORM);
        // Header, hero and final CTA.
        assertThat(html.split(Pattern.quote("href=\"" + bandFormUrl + "\""), -1)).hasSize(4);
        assertThat(html).doesNotContain(OWNER_FORM).doesNotContain("<dialog").doesNotContain("data-reserve");
    }

    @Test
    void ownerPreRegistrationButtonsLinkToOwnerForm() throws Exception {
        String html = mvc.perform(get("/owner")).andReturn().getResponse().getContentAsString();

        assertThat(ownerFormUrl).contains(OWNER_FORM);
        assertThat(html.split(Pattern.quote("href=\"" + ownerFormUrl + "\""), -1)).hasSize(4);
        assertThat(html).doesNotContain(BAND_FORM).doesNotContain("<dialog").doesNotContain("data-reserve");
    }

    @Test
    void ownerPageHasNoBlankPhoneMockups() throws Exception {
        String html = mvc.perform(get("/owner")).andReturn().getResponse().getContentAsString();

        // Only the two final-CTA phones remain, and both show a real app screen.
        assertThat(html.split("class=\"phone-mockup\"", -1)).hasSize(3);
        assertThat(html).contains("solution-row--text-only");
    }
}
