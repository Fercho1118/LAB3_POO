/**
 * Clase Main
 * Fernando Rueda - 23748
 * Descripción: Clase principal para ejecutar el sistema.
 * Fecha de creación: [21/10/2023]
 * Fecha de última modificación: [23/10/2023]
 */

import java.util.Scanner;
import java.util.List;


public class Main {
    private static Inventory inventory = new Inventory();
    private static SalesReport salesReport = new SalesReport();
    /**
     * Método principal para ejecutar el sistema.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;

            while (!exit) {
                displayMenu();
                int option = scanner.nextInt();
                scanner.nextLine(); 
                executeOption(option);
            }
        }
    }
    /**
     * Muestra el menú principal al usuario.
     */
    public static void displayMenu() {
        System.out.println("1. Cargar productos desde CSV");
        System.out.println("2. Agregar producto");
        System.out.println("3. Guardar productos en CSV");
        System.out.println("4. Buscar producto por ID");
        System.out.println("5. Listar productos por categoría");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }
    /**
     * Ejecuta la opción seleccionada por el usuario.
     * @param option Opción seleccionada por el usuario.
     */
    public static void executeOption(int option) {
        Scanner scanner = new Scanner(System.in);
        switch (option) {
            case 1:
                System.out.print("Ingrese el nombre del archivo CSV: ");
                String filename = scanner.nextLine();
                inventory.loadFromCSV(filename);
                break;
            case 2:
                addProductManually();
                break;
            case 3:
                System.out.print("Ingrese el nombre del archivo CSV donde guardar: ");
                String saveFilename = scanner.nextLine();
                inventory.saveToCSV(saveFilename);
                System.out.println("Productos guardados exitosamente.");
                break;
            case 4:
                System.out.print("Ingrese el ID del producto: ");
                int id = scanner.nextInt();
                Product product = inventory.findProductById(id);
                if (product != null) {
                    System.out.println(product.getDetails());
                } else {
                    System.out.println("Producto no encontrado.");
                }
                break;
            case 5:
                System.out.print("Ingrese la categoría (Beverage/Snack): ");
                String category = scanner.nextLine();
                List<Product> products = inventory.listProductsByCategory(category);
                for (Product p : products) {
                    System.out.println(p.getDetails());
                }
                break;
            case 6:
                System.out.println("Saliendo...");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
    
    /**
     * Método que permite al usuario agregar un producto manualmente.
     */
    public static void addProductManually() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Ingrese el nombre del producto: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la cantidad disponible: ");
        int availableQuantity = scanner.nextInt();
        System.out.print("Ingrese el precio: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.print("Ingrese la categoría del producto: ");
        String category = scanner.nextLine();
    
        if (category.equalsIgnoreCase("Beverage")) {
            System.out.print("Ingrese los mililitros: ");
            int milliliters = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Ingrese el tipo (energética, con licor, natural, agua pura): ");
            String type = scanner.nextLine();
            inventory.addProduct(new Beverage(id, name, availableQuantity, price, milliliters, type));
        } else if (category.equalsIgnoreCase("Snack")) {
            System.out.print("Ingrese los gramos: ");
            int grams = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Ingrese el sabor (barbacoa, picante, natural): ");
            String flavor = scanner.nextLine();
            System.out.print("Ingrese el tamaño (individual, familiar): ");
            String size = scanner.nextLine();
            inventory.addProduct(new Snack(id, name, availableQuantity, price, grams, flavor, size));
        } else {
            // Si es una categoría nueva
            System.out.print("Ingrese la cantidad de ventas de este producto: ");
            int soldQuantity = scanner.nextInt();
            Product newProduct = new Product(id, name, availableQuantity, price);
            newProduct.setSoldQuantity(soldQuantity); 
            inventory.addProduct(newProduct);
        }
    
        // Se utiliza la instancia salesReport para calcular las ventas y la comisión
        salesReport.calculateTotalSales(inventory.getProductsList());
        salesReport.calculateCommission();
        salesReport.displayReport();
    
        System.out.println("Producto agregado exitosamente.");
    }
    
    
}
