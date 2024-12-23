package auth.api.model.product;

public class ProductRequestDTO {

    private String title;
    private double price;

    public ProductRequestDTO(String  title, double price) {
        setTitle(title);
        setPrice(price);
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    // Setters

    public void setTitle(String title) {
        if (title == null || title.trim().isBlank()) {
            throw new IllegalArgumentException("O nome do produto não deve ser vazio ou nulo.");
        }

        this.title = title;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("O preço do produto não deve ser zerado.");
        }

        this.price = price;
    }

}
