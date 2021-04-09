package software.ias.training.api.repository;

import java.util.List;
import java.util.Optional;

// This is an exploration class
public interface CrudRepository<TYPE, ID> {
    void store(TYPE item);

    void update(TYPE item);

    void delete(ID id);

    Optional<TYPE> findById(ID id);

    List<TYPE> list();
}
