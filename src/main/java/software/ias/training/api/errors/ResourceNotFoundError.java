package software.ias.training.api.errors;

import software.ias.training.api.commons.OperationError;

import java.util.Map;

public class ResourceNotFoundError implements OperationError {
    private final String message;

    public ResourceNotFoundError(String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String businessCode() {
        return "RESOURCE_NOT_FOUND";
    }

    @Override
    public int httpCode() {
        return 404;
    }

    @Override
    public Map<String, Object> errorMetadata() {
        return Map.of();
    }
}
