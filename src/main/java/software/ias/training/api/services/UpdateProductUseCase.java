package software.ias.training.api.services;
import org.springframework.stereotype.Service;
import software.ias.training.api.commons.OperationResult;
import software.ias.training.api.commons.UseCase;
import software.ias.training.api.domain.Product;
import software.ias.training.api.errors.ResourceNotFoundError;
import software.ias.training.api.model.*;
import software.ias.training.api.repository.ProductRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateProductUseCase implements UseCase<UpdateProductInput, UpdateProductOutput> {
    private final ProductRepository repository;

    public UpdateProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public OperationResult<UpdateProductOutput> execute(UpdateProductInput input) {
        Optional<Product> productById = repository.findProductById(input.getId());
        System.out.println("This is input: "+ input);
        if (productById.isPresent()) {
            Product product = productById.get();
            System.out.println("This is product: "+ product);
            UpdateProductOutput output = new UpdateProductOutput(product);
            return OperationResult.ofValue(output);
        } else {
            String errorMessage = "Product with id: " + input.getId() + " does not exists";
            ResourceNotFoundError notFoundError = new ResourceNotFoundError(errorMessage);
            return OperationResult.ofError(notFoundError);
        }
    }
}

