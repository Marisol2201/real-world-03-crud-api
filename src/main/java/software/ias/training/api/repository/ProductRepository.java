package software.ias.training.api.repository;

import org.springframework.stereotype.Repository;
import software.ias.training.api.domain.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepository {
    private final DataSource dataSource;

    public ProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void storeProduct(Product product) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO PRODUCTS(id, name, description, image) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getId().toString());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getImage());

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throw new RuntimeException("Error creating product");
        }
    }


    public List<Product> listProducts() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM PRODUCTS";
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = getProductFromResultset(resultSet);
                products.add(product);
            }

            return products;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return List.of();
        }
    }

    private Product getProductFromResultset(ResultSet resultSet) throws SQLException {
        String idString = resultSet.getString("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        String image = resultSet.getString("image");
        UUID id = UUID.fromString(idString);
        return new Product(id, name, description, image);
    }


    public Optional<Product> findProductById(UUID id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM PRODUCTS WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product productFromResultset = getProductFromResultset(resultSet);
                return Optional.of(productFromResultset);
            } else {
                return Optional.empty();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Error query product by id = " + id);
        }
    }

    public void updateProduct(Product productUpdate) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE PRODUCTS SET name = ?, description = ?, image = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productUpdate.getName());
            preparedStatement.setString(2, productUpdate.getDescription());
            preparedStatement.setString(3, productUpdate.getImage());
            preparedStatement.setString(4, productUpdate.getId().toString());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Error while updating product: " + productUpdate, throwables);
        }
    }

    public void deleteProduct(UUID id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM PRODUCTS WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id.toString());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Error while deleting product: " + id, throwables);
        }
    }
}
