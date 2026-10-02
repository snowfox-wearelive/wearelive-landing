package band.wearelive.landing.common.preregistration;

public class DuplicateRegistrationException extends RuntimeException {

    public DuplicateRegistrationException() {
        super("이미 사전예약된 이메일입니다.");
    }
}
