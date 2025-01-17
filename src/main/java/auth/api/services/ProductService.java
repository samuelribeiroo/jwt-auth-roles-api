package auth.api.services;

import auth.api.interfaces.IProduct;
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
public class ProductService  {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ResponseEntity<?> registerProduct(ProductRequestDTO body) {
        try {
            if (body == null || body.getTitle() == null || body.getPrice() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos.");
            }

            Product newProduct = new Product(body);
            System.out.println("Novo produto criado: " + newProduct);

            Product savedProduct = productRepository.save(newProduct);
            System.out.println("Produto salvo no banco de dados: " + savedProduct);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct.getTitle());
        } catch (Exception error) {
            System.err.println("Erro ao salvar o produto: " + error.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a requisição.");
        }
    }
}

