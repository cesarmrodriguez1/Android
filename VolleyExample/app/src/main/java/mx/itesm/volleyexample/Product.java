package mx.itesm.volleyexample;

public class Product {
    String id;
    String name;
    String brand;
    String price;
    String url_image;


    public Product(String id, String name, String brand, String price, String url_image) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.url_image = url_image;
    }


    public Product() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
}