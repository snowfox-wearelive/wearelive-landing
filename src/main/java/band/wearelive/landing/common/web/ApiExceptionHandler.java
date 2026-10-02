package band.wearelive.landing.common.web;

import band.wearelive.landing.common.preregistration.DuplicateRegistrationException;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = PreRegistrationApi.class)
class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError invalid(MethodArgumentNotValidException ex) {
        // Report errors in the request's declared field order so `message` matches the first invalid form field.
        List<String> order = fieldOrder(ex.getBindingResult().getTarget());
        Map<String, String> fields = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().stream()
                .sorted(Comparator.comparingInt((FieldError e) -> {
                    int i = order.indexOf(e.getField());
                    return i < 0 ? Integer.MAX_VALUE : i;
                }))
                .forEach(error -> fields.putIfAbsent(error.getField(), error.getDefaultMessage()));
        String first = fields.values().stream().findFirst().orElse("입력값을 확인해주세요.");
        return new ApiError(first, fields);
    }

    private static List<String> fieldOrder(Object target) {
        if (target == null || !target.getClass().isRecord()) {
            return List.of();
        }
        return Arrays.stream(target.getClass().getRecordComponents()).map(RecordComponent::getName).toList();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError unreadable() {
        return ApiError.of("입력값을 확인해주세요.");
    }

    @ExceptionHandler(DuplicateRegistrationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ApiError duplicate(DuplicateRegistrationException ex) {
        return ApiError.of(ex.getMessage());
    }
}
