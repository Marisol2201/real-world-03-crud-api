package software.ias.training.api.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import software.ias.training.api.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SQLProductRepository implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SQLProductRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void storeProduct(Product product) {
        String sql = "INSERT INTO PRODUCTS(id, name, description, image) VALUES (?, ?, ?, ?)";
        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1, product.getId().toString());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getImage());
        };
        jdbcTemplate.update(sql, preparedStatementSetter);
    }


    public List<Product> listProducts() {
        String sql = "SELECT * FROM PRODUCTS";
        return jdbcTemplate.query(sql, this::productRowMapper);
    }

    private Product productRowMapper(ResultSet resultSet, int rowNum) throws SQLException {
        String idString = resultSet.getString("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        String image = resultSet.getString("image");
        UUID id = UUID.fromString(idString);
        return new Product(id, name, description, image);
    }


    public Optional<Product> findProductById(UUID id) {
        String sql = "SELECT * FROM PRODUCTS WHERE id = ?";
        PreparedStatementSetter setter = ps -> ps.setString(1, id.toString());
        List<Product> products = jdbcTemplate.query(sql, setter, this::productRowMapper);
        Product unsafeProduct = DataAccessUtils.singleResult(products);
        return Optional.ofNullable(unsafeProduct);
    }

    public void updateProduct(Product productUpdate) {
        String sql = "UPDATE PRODUCTS SET name = :name, description = :description, image = :image WHERE id = :id";
        Map<String, Object> parameters = Map.of(
                "id", productUpdate.getId().toString(),
                "name", productUpdate.getName(),
                "description", productUpdate.getDescription(),
                "image", productUpdate.getImage()
        );
        namedParameterJdbcTemplate.update(sql, parameters);
    }

    public void deleteProduct(UUID id) {
        String sql = "DELETE FROM PRODUCTS WHERE id = ?";
        PreparedStatementSetter setter = ps -> ps.setString(1, id.toString());
        jdbcTemplate.update(sql, setter);
    }
}
