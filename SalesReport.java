/**
 * Clase SalesReport
 * Fernando Rueda - 23748
 * Descripción: Clase que representa el infomre de ventas
 * Fecha de creación: [21/10/2023]
 * Fecha de última modificación: [23/10/2023]
 */

import java.util.List;


public class SalesReport {
    private double totalSales;
    private double commission;
    private double totalCommission;


    // Constructor
    public SalesReport() {
        this.totalSales = 0;
        this.commission = 0;
        this.totalCommission = 0;
    }

    public void calculateTotalSales(List<Product> products) {
        for (Product product : products) {
            totalSales += product.getSoldQuantity();
        }
    }

    public void calculateCommission() {
        commission = totalSales * 0.20; // 20% commission
        totalCommission += commission; // Acumula la comisión recién calculada
        System.out.println("Comisión: Q" + commission);
    }

   
    public void displayReport() {
        System.out.println("Ventas totales: " + totalSales);
        System.out.println("Comisión total acumulada: Q" + totalCommission);
    }

}
