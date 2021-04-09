package software.ias.training.api.errors;

import software.ias.training.api.commons.OperationError;

import java.util.Map;

public class InvalidDataError implements OperationError {
    private final String message;

    public InvalidDataError(String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String businessCode() {
        return "INVALID_DATA";
    }

    @Override
    public int httpCode() {
        return 400;
    }

    @Override
    public Map<String, Object> errorMetadata() {
        return Map.of();
    }
}
