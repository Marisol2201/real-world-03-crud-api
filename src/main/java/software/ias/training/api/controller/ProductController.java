package software.ias.training.api.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import software.ias.training.api.domain.Product;
import software.ias.training.api.model.*;
import software.ias.training.api.repository.ProductRepository;
import software.ias.training.api.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public CreateProductOperationOutput createProduct(
            @RequestBody CreateProductOperationInput input
    ) {
        return service.createProduct(input);
    }

    @GetMapping
    public List<Product> listProducts() {
        return service.listProducts();
    }

    @GetMapping("/{id}")
    public Optional<ReadProductByIdOutput> readProductById(
            @PathVariable("id") String id
    ) {
        UUID uuid = UUID.fromString(id);
        ReadProductByIdInput input = new ReadProductByIdInput(uuid);
        return service.readProductByIdOperation(input);
    }

    @PutMapping
    public UpdateProductOutput updateProduct(
            @RequestBody UpdateProductInput input
    ) {
        return service.updateProductOperation(input);
    }

    @DeleteMapping("/{productId}")
    public DeleteProductOutput deleteProduct(@PathVariable("productId") String unsafeProductId) {
        UUID productId = UUID.fromString(unsafeProductId);
        DeleteProductInput input = new DeleteProductInput(productId);
        return service.deleteProductOperation(input);
    }
}
