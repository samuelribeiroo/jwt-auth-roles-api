package auth.api.model.product;

public class ProductResponseDTO {
    private Long id;
    private String title;
    private  double price;

    public ProductResponseDTO(Long id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
