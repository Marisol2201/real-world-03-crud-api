package software.ias.training.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.ias.training.api.commons.ControllerHandler;
import software.ias.training.api.commons.OperationError;
import software.ias.training.api.commons.OperationResult;
import software.ias.training.api.domain.Product;
import software.ias.training.api.model.*;
import software.ias.training.api.services.CreateProductUseCase;
import software.ias.training.api.services.ProductService;
import software.ias.training.api.services.ReadProductByIdUseCase;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    private final CreateProductUseCase createProduct;
    private final ReadProductByIdUseCase readProductByIdUseCase;

    public ProductController(
            ProductService service,
            CreateProductUseCase createProduct,
            ReadProductByIdUseCase readProductByIdUseCase
    ) {
        this.service = service;
        this.createProduct = createProduct;
        this.readProductByIdUseCase = readProductByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(
            @RequestBody CreateProductOperationInput input
    ) {
        return new ControllerHandler<>(
                () -> input,
                createProduct
        )
                .execute();
    }

    @GetMapping
    public List<Product> listProducts() {
        return service.listProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> readProductById(
            @PathVariable("id") String id
    ) {
        return new ControllerHandler<>(
                () -> {
                    UUID uuid = UUID.fromString(id);
                    return new ReadProductByIdInput(uuid);
                },
                readProductByIdUseCase
        )
                .execute();
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