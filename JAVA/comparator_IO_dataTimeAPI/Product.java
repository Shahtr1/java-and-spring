package comparator_IO_dataTimeAPI;

public class Product implements Comparable<Product> {
    private String name;
    private int price;
    private Category category;

    public Product(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int compareTo(Product o) {
        return this.price - o.price;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", price=" + price + ", category=" + category + "]\n";
    }

}
