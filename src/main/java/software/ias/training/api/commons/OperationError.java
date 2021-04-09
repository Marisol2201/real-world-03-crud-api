package software.ias.training.api.commons;

import java.util.Map;

public interface OperationError {
    String message();

    String businessCode();

    int httpCode();

    Map<String, Object> errorMetadata();

    default Map<String, Object> content() {
        return Map.of(
                "type", "ERROR",
                "message", message(),
                "code", businessCode(),
                "metadata", errorMetadata()
        );
    }
}
