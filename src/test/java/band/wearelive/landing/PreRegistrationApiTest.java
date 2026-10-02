package band.wearelive.landing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import band.wearelive.landing.band.BandPreRegistrationRepository;
import band.wearelive.landing.band.Position;
import band.wearelive.landing.owner.OwnerPreRegistrationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PreRegistrationApiTest {

    private static final String BAND_API = "/api/band/pre-registrations";
    private static final String OWNER_API = "/api/owner/pre-registrations";

    @Autowired
    MockMvc mvc;

    @Autowired
    BandPreRegistrationRepository bandRepository;

    @Autowired
    OwnerPreRegistrationRepository ownerRepository;

    @BeforeEach
    void clean() {
        bandRepository.deleteAll();
        ownerRepository.deleteAll();
    }

    private ResultActions postJson(String url, String json) throws Exception {
        return mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(json));
    }

    @Test
    void bandRegistrationIsStoredOnlyInBandTable() throws Exception {
        postJson(BAND_API, """
                {"name":" 김밴드 ","email":"Band@Example.com","marketingConsent":true,"position":"DRUMS"}""")
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("사전예약이 완료되었습니다.")));

        assertThat(ownerRepository.count()).isZero();
        assertThat(bandRepository.findAll()).singleElement().satisfies(saved -> {
            assertThat(saved.getName()).isEqualTo("김밴드");
            assertThat(saved.getEmail()).isEqualTo("band@example.com");
            assertThat(saved.getPosition()).isEqualTo(Position.DRUMS);
        });
    }

    @Test
    void ownerRegistrationIsStoredOnlyInOwnerTable() throws Exception {
        postJson(OWNER_API, """
                {"name":"박사장","email":"owner@example.com","marketingConsent":true}""")
                .andExpect(status().isCreated());

        assertThat(bandRepository.count()).isZero();
        assertThat(ownerRepository.count()).isEqualTo(1);
    }

    @Test
    void bandRequiresPosition() throws Exception {
        postJson(BAND_API, """
                {"name":"김밴드","email":"band@example.com","marketingConsent":true}""")
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors.position", is("주로 맡는 포지션을 선택해주세요.")));
    }

    @Test
    void rejectsInvalidEmailAndMissingConsent() throws Exception {
        postJson(OWNER_API, """
                {"name":"박사장","email":"not-an-email","marketingConsent":false}""")
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("올바른 이메일 주소를 입력해주세요.")))
                .andExpect(jsonPath("$.fieldErrors.email", is("올바른 이메일 주소를 입력해주세요.")))
                .andExpect(jsonPath("$.fieldErrors.marketingConsent", is("소식 수신에 동의해주세요.")));
    }

    @Test
    void rejectsDuplicateEmailWithinSameAudience() throws Exception {
        postJson(OWNER_API, """
                {"name":"박사장","email":"owner@example.com","marketingConsent":true}""")
                .andExpect(status().isCreated());
        postJson(OWNER_API, """
                {"name":"박사장","email":"OWNER@example.com","marketingConsent":true}""")
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message", is("이미 사전예약된 이메일입니다.")));
    }

    @Test
    void sameEmailMayRegisterForBothAudiences() throws Exception {
        postJson(BAND_API, """
                {"name":"겸업","email":"both@example.com","marketingConsent":true,"position":"VOCAL"}""")
                .andExpect(status().isCreated());
        postJson(OWNER_API, """
                {"name":"겸업","email":"both@example.com","marketingConsent":true}""")
                .andExpect(status().isCreated());
    }
}
