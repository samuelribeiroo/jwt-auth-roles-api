package auth.api.interfaces;

import auth.api.model.product.ProductRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IProduct {
    ResponseEntity<Void> registerProduct(@RequestBody ProductRequestDTO body);
}
