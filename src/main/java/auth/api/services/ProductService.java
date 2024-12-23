package auth.api.services;

import auth.api.model.product.Product;
import auth.api.model.product.ProductRequestDTO;
import auth.api.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ResponseEntity<Void> registerProduct(@RequestBody ProductRequestDTO body) {
        Product newProduct = new Product(body);

        productRepository.save(newProduct);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
