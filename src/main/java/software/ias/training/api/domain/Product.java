package software.ias.training.api.domain;

import software.ias.training.api.commons.Validate;

import java.util.UUID;

public class Product {
    private final UUID id;
    private final String name;
    private final String description;
    private final String image;

    public Product(UUID id, String name, String description, String image) {
        Validate.checkNotNull(id);
        Validate.checkNotNull(name);
        Validate.checkNotNull(description);
        Validate.checkNotNull(image);
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
