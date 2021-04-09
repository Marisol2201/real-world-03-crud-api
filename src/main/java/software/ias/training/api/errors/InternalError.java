package software.ias.training.api.errors;

import software.ias.training.api.commons.OperationError;

import java.util.Map;

public class InternalError implements OperationError {

    @Override
    public String message() {
        return "There is a problem in the server. please try again later.";
    }

    @Override
    public String businessCode() {
        return "INTERNAL_ERROR";
    }

    @Override
    public int httpCode() {
        return 500;
    }

    @Override
    public Map<String, Object> errorMetadata() {
        return Map.of();
    }
}
