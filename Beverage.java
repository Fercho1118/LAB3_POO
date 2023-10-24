/**
 * Clase Beverage
 * Fernando Rueda - 23748
 * Descripción: Clase que representa una bebida, hereda de Product.
 * Fecha de creación: [21/10/2023]
 * Fecha de última modificación: [21/10/2023]
 */
public class Beverage extends Product {
    private int milliliters;
    private String type;

    // Constructor
    public Beverage(int id, String name, int availableQuantity, double price, int milliliters, String type) {
        super(id, name, availableQuantity, price);
        this.milliliters = milliliters;
        this.type = type;
    }


    @Override
    public String getDetails() {
        return super.getDetails() + ", Mililitros: " + milliliters + ", Tipo: " + type;
    }

    public int getMilliliters() {
        return milliliters;
    }
    
    public String getType() {
        return type;
    }
}