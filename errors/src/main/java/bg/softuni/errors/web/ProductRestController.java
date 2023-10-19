package bg.softuni.errors.web;

import bg.softuni.errors.models.dto.ProductDto;
import bg.softuni.errors.models.dto.ProductErrorDto;
import bg.softuni.errors.models.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") long productId){
        throw new ProductNotFoundException(productId);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductErrorDto> onProductNotFound(ProductNotFoundException productNotFoundException){
        ProductErrorDto productErrorDto = new ProductErrorDto(productNotFoundException.getProductId(), "Product not found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productErrorDto);
    }
}
