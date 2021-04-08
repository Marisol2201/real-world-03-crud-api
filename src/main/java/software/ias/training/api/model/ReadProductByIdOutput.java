package software.ias.training.api.model;

import software.ias.training.api.commons.Validate;
import software.ias.training.api.domain.Product;

public class ReadProductByIdOutput {
    private final Product product;

    public ReadProductByIdOutput(Product product) {
        Validate.checkNotNull(product);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
