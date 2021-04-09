package software.ias.training.api.repository;

import software.ias.training.api.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    void storeProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(UUID id);

    Optional<Product> findProductById(UUID id);

    List<Product> listProducts();
}
