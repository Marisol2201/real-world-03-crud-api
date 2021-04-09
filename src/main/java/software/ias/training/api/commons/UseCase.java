package software.ias.training.api.commons;

public interface UseCase<I, O> {
    OperationResult<O> execute(I input);
}
