/**
 * Clase Product
 * Fernando Rueda - 23748
 * Descripción: Clase que representa un producto.
 * Fecha de creación: [21/10/2023]
 * Fecha de última modificación: [23/10/2023]
 */
public class Product {
    private int id;
    private String name;
    private int availableQuantity;
    private int soldQuantity;
    private String status;
    private double price;

    // Constructor
    public Product(int id, String name, int availableQuantity, double price) {
        this.id = id;
        this.name = name;
        this.availableQuantity = availableQuantity;
        this.soldQuantity = 0;
        this.status = (availableQuantity > 0) ? "disponible" : "no disponible";
        this.price = price;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public String getStatus() {
        return status;
    }


    public double getPrice() {
        return price;
    }


    public String getDetails() {
        return "ID: " + id + ", Nombre: " + name + ", Precio: " + price;
    }
    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
        // Actualizar el estado del producto basado en la cantidad disponible y vendida
        this.status = (this.availableQuantity - this.soldQuantity > 0) ? "disponible" : "no disponible";
    }
    
    
}
