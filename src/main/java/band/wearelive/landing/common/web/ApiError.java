package band.wearelive.landing.common.web;

import java.util.Map;

/** Error body for the pre-registration APIs. {@code fieldErrors} maps a form field name to a user-facing message. */
public record ApiError(String message, Map<String, String> fieldErrors) {

    public static ApiError of(String message) {
        return new ApiError(message, Map.of());
    }
}
