/**
 * Clase Snack
 * Fernando Rueda - 23748
 * Descripción: Clase que representa una golosina, hereda de Product.
 * Fecha de creación: [21/10/2023]
 * Fecha de última modificación: [21/10/2023]
 */
public class Snack extends Product {
    private int grams;
    private String flavor;
    private String size;

    // Constructor
    public Snack(int id, String name, int availableQuantity, double price, int grams, String flavor, String size) {
        super(id, name, availableQuantity, price);
        this.grams = grams;
        this.flavor = flavor;
        this.size = size;
    }

   
    @Override
    public String getDetails() {
        return super.getDetails() + ", Gramos: " + grams + ", Sabor: " + flavor + ", Tamaño: " + size;
    }

    public int getGrams() {
        return grams;
    }
    
    public String getFlavor() {
        return flavor;
    }
    
    public String getSize() {
        return size;
    }
}