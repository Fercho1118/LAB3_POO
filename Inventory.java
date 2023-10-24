/**
 * Clase Inventory
 * Fernando Rueda - 23748
 * Descripción: Clase que representa el inventario de productos y maneja el csv
 * Fecha de creación: [21/10/2023]
 * Fecha de última modificación: [23/10/2023]
 */

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;


public class Inventory {
    private List<Product> productsList;

    // Constructor
    public Inventory() {
        this.productsList = new ArrayList<>();
    }

    public void loadFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true; 
            while ((line = br.readLine()) != null) {
                if (isFirstLine) { 
                    isFirstLine = false;
                    continue;
                }
                String[] values = line.split("\\|");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                int availableQuantity = Integer.parseInt(values[2]);
                int soldQuantity = Integer.parseInt(values[3]);
                String status = values[4];
                double price = Double.parseDouble(values[5]);
                String category = values[6];
                if (category.equalsIgnoreCase("Beverage")) {
                    int milliliters = Integer.parseInt(values[7]);
                    String type = values[8];
                    productsList.add(new Beverage(id, name, availableQuantity, price, milliliters, type));
                } else if (category.equalsIgnoreCase("Snack")) {
                    int grams = Integer.parseInt(values[9]);
                    String flavor = values[10];
                    String size = values[11];
                    productsList.add(new Snack(id, name, availableQuantity, price, grams, flavor, size));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    // Método para buscar un producto por su ID
    public Product findProductById(int id) {
        for (Product product : productsList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    // Método para listar productos por categoría
    public List<Product> listProductsByCategory(String category) {
        List<Product> categoryProducts = new ArrayList<>();
        for (Product product : productsList) {
            if (product instanceof Beverage && category.equalsIgnoreCase("Beverage")) {
                categoryProducts.add(product);
            } else if (product instanceof Snack && category.equalsIgnoreCase("Snack")) {
                categoryProducts.add(product);
            }
        }
        return categoryProducts;
    }

    public List<Product> getProductsList() {
        return productsList;
    }

    public void saveToCSV(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            // Escribe el encabezado del CSV
            pw.println("id_producto|nombre|cantidad_disponible|cantidad_vendida|estado|precio|categoria|mililitros|tipo|gramos|sabor|tamaño");
    
            for (Product product : productsList) {
                String line = product.getId() + "|" + product.getName() + "|" + product.getAvailableQuantity() + "|" + 
                              product.getSoldQuantity() + "|" + product.getStatus() + "|" + product.getPrice();
                if (product instanceof Beverage) {
                    Beverage beverage = (Beverage) product;
                    line += "|Beverage|" + beverage.getMilliliters() + "|" + beverage.getType() + "|||";
                } else if (product instanceof Snack) {
                    Snack snack = (Snack) product;
                    line += "|Snack|||" + snack.getGrams() + "|" + snack.getFlavor() + "|" + snack.getSize();
                }
                else {
                line += "|New|||||";
                }   
                pw.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void addProduct(Product product) {
        productsList.add(product);
    }

}
