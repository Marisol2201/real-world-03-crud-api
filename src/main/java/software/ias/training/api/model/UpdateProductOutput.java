package software.ias.training.api.model;

import software.ias.training.api.commons.Validate;
import software.ias.training.api.domain.Product;

public class UpdateProductOutput {
    private final Product product;

    public UpdateProductOutput(Product product) {
        Validate.checkNotNull(product);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
