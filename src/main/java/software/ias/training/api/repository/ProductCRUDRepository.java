package software.ias.training.api.repository;

import software.ias.training.api.domain.Product;

import java.util.UUID;

// This is an exploration class
public interface ProductCRUDRepository extends CrudRepository<Product, UUID> {
}
