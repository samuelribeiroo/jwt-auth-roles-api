package auth.api.model.product;

import jakarta.persistence.*;

@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private double price;

    // Getters
    public String getTitle() { return title; }

    public double getPrice() { return price; }

    // Setters
    public void setTitle(String title) { this.title = title; }

    public void setPrice(double price) { this.price = price; }
}
