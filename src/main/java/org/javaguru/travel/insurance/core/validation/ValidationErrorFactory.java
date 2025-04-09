package org.javaguru.travel.insurance.core.validation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.util.ErrorCodeUtil;
import org.javaguru.travel.insurance.core.util.Placeholder;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ValidationErrorFactory {

    private final ErrorCodeUtil errorCodeBuild;

     ValidationError buildError(String errorCode) {
        String errorDescription = errorCodeBuild.getErrorDescription(errorCode);
        return new ValidationError(errorCode, errorDescription);
    }

    ValidationError buildError(String errorCode, List<Placeholder> placeholders) {
        String errorDescription = errorCodeBuild.getErrorDescription(errorCode, placeholders);
        return new ValidationError(errorCode, errorDescription);
    }
}
