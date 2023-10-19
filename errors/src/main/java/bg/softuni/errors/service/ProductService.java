package bg.softuni.errors.service;

import bg.softuni.errors.models.dto.ProductDto;
import bg.softuni.errors.models.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

        public ProductDto getProductById(long productId){
            throw new ProductNotFoundException(productId);
        }
}
