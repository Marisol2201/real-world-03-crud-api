package software.ias.training.api.commons;

public class OperationResult<V> {
    public static <VALUE> OperationResult<VALUE> ofValue(VALUE value) {
        return new OperationResult<>(null, value);
    }

    public static <VALUE> OperationResult<VALUE> ofError(OperationError error) {
        return new OperationResult<>(error, null);
    }

    private final OperationError error;
    private final V value;

    private OperationResult(OperationError error, V value) {
        this.error = error;
        this.value = value;
    }

    public boolean isError() {
        return this.error != null;
    }

    public boolean isSuccess() {
        return this.value != null;
    }

    public OperationError getError() {
        return error;
    }

    public V getValue() {
        return value;
    }
}
