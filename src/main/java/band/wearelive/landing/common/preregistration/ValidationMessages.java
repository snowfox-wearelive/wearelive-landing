package band.wearelive.landing.common.preregistration;

/** User-facing validation messages shared by both audiences' request DTOs. */
public final class ValidationMessages {

    public static final String NAME_REQUIRED = "이름을 입력해주세요.";
    public static final String NAME_TOO_LONG = "이름은 50자 이내로 입력해주세요.";
    public static final String EMAIL_INVALID = "올바른 이메일 주소를 입력해주세요.";
    public static final String CONSENT_REQUIRED = "소식 수신에 동의해주세요.";

    private ValidationMessages() {
    }
}
