package auth.api.model.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "products")
public class Product {

    public Product(ProductRequestDTO body) {
        this.title = body.getTitle();
        this.price = body.getPrice();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    private String title;

    @Column(nullable = false)
    @NotNull
    private double price;

    // Getters
    public String getTitle() { return title; }

    public double getPrice() { return price; }

    // Setters
    public void setTitle(String title) { this.title = title; }

    public void setPrice(double price) { this.price = price; }
}
