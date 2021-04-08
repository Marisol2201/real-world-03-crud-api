package software.ias.training.api.services;

import org.springframework.stereotype.Service;
import software.ias.training.api.domain.Product;
import software.ias.training.api.model.*;
import software.ias.training.api.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public CreateProductOperationOutput createProduct(CreateProductOperationInput input) {
        UUID productId = UUID.randomUUID();
        Product product = new Product(
                productId,
                input.getName(),
                input.getDescription(),
                input.getImage()
        );
        repository.storeProduct(product);
        return new CreateProductOperationOutput(product);
    }

    public List<Product> listProducts() {
        return repository.listProducts();
    }

    public Optional<ReadProductByIdOutput> readProductByIdOperation(ReadProductByIdInput input) {
        Optional<Product> productById = repository.findProductById(input.getId());
        if (productById.isPresent()) {
            Product product = productById.get();
            ReadProductByIdOutput output = new ReadProductByIdOutput(product);
            return Optional.of(output);
        } else {
            return Optional.empty();
        }
    }

    public UpdateProductOutput updateProductOperation(
            UpdateProductInput input
    ) {
        UUID productId = input.getId();
        Optional<Product> productById = repository.findProductById(productId);
        if (productById.isPresent()) {
            Product dbProduct = productById.get();
            Product productUpdate = new Product(
                    dbProduct.getId(),
                    input.getName(),
                    input.getDescription(),
                    input.getImage()
            );
            repository.updateProduct(productUpdate);
            return new UpdateProductOutput(productUpdate);
        } else {
            throw new IllegalArgumentException("Product " + productId + " does not exist.");
        }
    }

    public DeleteProductOutput deleteProductOperation(
            DeleteProductInput input
    ) {
        UUID productId = input.getProductId();
        Optional<Product> productById = repository.findProductById(productId);
        if (productById.isPresent()) {
            Product dbProduct = productById.get();
            repository.deleteProduct(dbProduct.getId());
            return new DeleteProductOutput(dbProduct);
        } else {
            throw new IllegalArgumentException("Product " + productId + " does not exist.");
        }
    }
}
