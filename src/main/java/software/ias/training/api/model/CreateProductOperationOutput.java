package software.ias.training.api.model;

import software.ias.training.api.commons.Validate;
import software.ias.training.api.domain.Product;

public class CreateProductOperationOutput {
    private final Product product;

    public CreateProductOperationOutput(Product product) {
        Validate.checkNotNull(product);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "CreateProductOperationOutput{" +
                "product=" + product +
                '}';
    }
}
