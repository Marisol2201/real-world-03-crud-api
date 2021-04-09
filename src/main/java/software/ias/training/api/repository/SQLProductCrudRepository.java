package software.ias.training.api.repository;

import software.ias.training.api.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// This is an exploration class
public class SQLProductCrudRepository implements ProductCRUDRepository {
    @Override
    public void store(Product item) {

    }

    @Override
    public void update(Product item) {

    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Optional<Product> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public List<Product> list() {
        return null;
    }
}
