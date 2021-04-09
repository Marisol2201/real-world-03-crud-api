package software.ias.training.api.services;

import org.springframework.stereotype.Service;
import software.ias.training.api.commons.OperationResult;
import software.ias.training.api.commons.UseCase;
import software.ias.training.api.domain.Product;
import software.ias.training.api.errors.ResourceNotFoundError;
import software.ias.training.api.model.ReadProductByIdInput;
import software.ias.training.api.model.ReadProductByIdOutput;
import software.ias.training.api.repository.ProductRepository;

import java.util.Optional;

@Service
public class ReadProductByIdUseCase implements UseCase<ReadProductByIdInput, ReadProductByIdOutput> {
    private final ProductRepository repository;

    public ReadProductByIdUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public OperationResult<ReadProductByIdOutput> execute(ReadProductByIdInput input) {
        Optional<Product> productById = repository.findProductById(input.getId());
        if (productById.isPresent()) {
            Product product = productById.get();
            ReadProductByIdOutput output = new ReadProductByIdOutput(product);
            return OperationResult.ofValue(output);
        } else {
            String errorMessage = "Product with id: " + input.getId() + " does not exists";
            ResourceNotFoundError notFoundError = new ResourceNotFoundError(errorMessage);
            return OperationResult.ofError(notFoundError);
        }
    }
}
