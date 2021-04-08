package software.ias.training.api.model;

import software.ias.training.api.commons.Validate;

import java.util.UUID;

public class ReadProductByIdInput {
    private final UUID id;

    public ReadProductByIdInput(UUID id) {
        Validate.checkNotNull(id);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
