package band.wearelive.landing;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class LandingPagesTest {

    @Autowired
    MockMvc mvc;

    @Test
    void bandPageRendersOnlyBandContent() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("band/landing"))
                .andExpect(content().string(containsString("밴드 공지·일정 관리부터")))
                .andExpect(content().string(containsString("data-endpoint=\"/api/band/pre-registrations\"")))
                .andExpect(content().string(containsString("name=\"position\"")))
                .andExpect(content().string(containsString("href=\"/owner?reserve=1\"")))
                .andExpect(content().string(not(containsString("공연장·합주실의 빈 시간"))))
                .andExpect(content().string(not(containsString("/api/owner/"))))
                .andExpect(content().string(not(containsString("/assets/owner/"))));
    }

    @Test
    void ownerPageRendersOnlyOwnerContent() throws Exception {
        mvc.perform(get("/owner"))
                .andExpect(status().isOk())
                .andExpect(view().name("owner/landing"))
                .andExpect(content().string(containsString("공연장·합주실의 빈 시간")))
                .andExpect(content().string(containsString("data-endpoint=\"/api/owner/pre-registrations\"")))
                .andExpect(content().string(containsString("href=\"/?reserve=1\"")))
                .andExpect(content().string(not(containsString("name=\"position\""))))
                .andExpect(content().string(not(containsString("밴드 공지·일정 관리부터"))))
                .andExpect(content().string(not(containsString("/api/band/"))))
                .andExpect(content().string(not(containsString("/assets/band/"))));
    }
}
