package software.ias.training.api.services;

import org.springframework.stereotype.Service;
import software.ias.training.api.commons.OperationResult;
import software.ias.training.api.commons.UseCase;
import software.ias.training.api.domain.Product;
import software.ias.training.api.model.CreateProductOperationInput;
import software.ias.training.api.model.CreateProductOperationOutput;
import software.ias.training.api.repository.ProductRepository;

import java.util.UUID;

@Service
public class CreateProductUseCase implements UseCase<CreateProductOperationInput, CreateProductOperationOutput> {
    private final ProductRepository repository;

    public CreateProductUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public OperationResult<CreateProductOperationOutput> execute(CreateProductOperationInput input) {
        UUID productId = UUID.randomUUID();
        Product product = new Product(
                productId,
                input.getName(),
                input.getDescription(),
                input.getImage()
        );
        repository.storeProduct(product);
        CreateProductOperationOutput output = new CreateProductOperationOutput(product);
        return OperationResult.ofValue(output);
    }
}
