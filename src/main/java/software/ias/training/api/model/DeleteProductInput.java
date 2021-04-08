package software.ias.training.api.model;

import software.ias.training.api.commons.Validate;

import java.util.UUID;

public class DeleteProductInput {
    private final UUID productId;

    public DeleteProductInput(UUID productId) {
        Validate.checkNotNull(productId);
        this.productId = productId;
    }

    public UUID getProductId() {
        return productId;
    }
}
