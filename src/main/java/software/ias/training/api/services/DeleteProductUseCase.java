package software.ias.training.api.services;
import org.springframework.stereotype.Service;
import software.ias.training.api.commons.OperationResult;
import software.ias.training.api.commons.UseCase;
import software.ias.training.api.domain.Product;
import software.ias.training.api.errors.ResourceNotFoundError;
import software.ias.training.api.model.DeleteProductInput;
import software.ias.training.api.model.DeleteProductOutput;
import software.ias.training.api.repository.ProductRepository;

import java.util.Optional;

@Service
public class DeleteProductUseCase implements UseCase<DeleteProductInput, DeleteProductOutput> {
    private final ProductRepository repository;

    public DeleteProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public OperationResult<DeleteProductOutput> execute(DeleteProductInput input) {
        Optional<Product> productById = repository.findProductById(input.getProductId());
        if (productById.isPresent()) {
            Product product = productById.get();
            DeleteProductOutput output = new DeleteProductOutput(product);
            return OperationResult.ofValue(output);
        } else {
            String errorMessage = "Product with id: " + input.getProductId() + " does not exists";
            ResourceNotFoundError notFoundError = new ResourceNotFoundError(errorMessage);
            return OperationResult.ofError(notFoundError);
        }
    }
}
