package band.wearelive.landing.common.preregistration;

public record PreRegistrationResponse(Long id, String message) {

    public static PreRegistrationResponse created(Long id) {
        return new PreRegistrationResponse(id, "사전예약이 완료되었습니다.");
    }
}
