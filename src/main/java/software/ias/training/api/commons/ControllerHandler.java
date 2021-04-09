package software.ias.training.api.commons;

import org.springframework.http.ResponseEntity;
import software.ias.training.api.errors.InternalError;
import software.ias.training.api.errors.InvalidDataError;

import java.util.function.Supplier;

public class ControllerHandler<INPUT, OUTPUT> {
    private final Supplier<INPUT> inputSupplier;
    private final UseCase<INPUT, OUTPUT> useCase;

    public ControllerHandler(
            Supplier<INPUT> inputSupplier,
            UseCase<INPUT, OUTPUT> useCase
    ) {
        this.inputSupplier = inputSupplier;
        this.useCase = useCase;
    }


    public ResponseEntity<Object> execute() {
        INPUT input;
        try {
            input = this.inputSupplier.get();
        } catch (RuntimeException exception) {
            OperationError error = new InvalidDataError(exception.getMessage());
            return parseError(error);
        }

        try {
            OperationResult<OUTPUT> operationResult = useCase.execute(input);
            if (operationResult.isError()) {
                OperationError error = operationResult.getError();
                return parseError(error);
            } else {
                return ResponseEntity.ok(operationResult.getValue());
            }
        } catch (RuntimeException runtimeException) {
            OperationError error = new InternalError();
            return parseError(error);
        }
    }

    private ResponseEntity<Object> parseError(OperationError error) {
        return ResponseEntity.status(error.httpCode())
                .body(error.content());
    }
}
