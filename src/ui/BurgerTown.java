package ui;

import java.util.Scanner;

public class BurgerTown {

    public static Scanner reader;
    public static double[] prices;
    public static int[] amounts;

    public static void main(String[] args) {
        initializeGlobals();
        menu(); 
    }
 
    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */

    public static void initializeGlobals() {
        reader = new Scanner(System.in);
    }
  /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
   
    public static void menu() {
        System.out.println("BurgerTown");

        boolean exit = false;

        do {
            System.out.println("\nMain Menu (please first type 1 to add data):");
            System.out.println("1. Enter prices and amounts of each dish sold during the day");
            System.out.println("2. The dishes sold during the day");
            System.out.println("3. Calculate the average price of dishes sold ");
            System.out.println("4. Calculate the total sales ");
            System.out.println("5. Dishes that exceeded limit");
            System.out.println("6. Exit");
            System.out.println("\nEnter the option ");
            int option = reader.nextInt();

            switch (option) {
                case 1:
                    putAmountSold();
                    requestData();
                    break;
                case 2:
                    System.out.println("\nThe total number of dishes sold was: " + calculateTotalDishesSold());
                    break;
                case 3:
                    System.out.println("\nThe average price of dishes sold was: " + calculateAveragePrice());
                    break;
                case 4:
                    System.out.println("\nThe total sales for the day were: " + calculateTotalSales());
                    break;
                case 5:
                    System.out.println("\nEnter the  limit");
                    double limit = reader.nextDouble();
                    
                    System.out.println("\nFrom " + prices.length + " dishes, " + DishesAboveLimit(limit) + " exceeded " + limit);
                    break;
                case 6:
                    System.out.println("\nBye thank u");
                    exit = true;
                    reader.close();
                    break;
                default:
                    System.out.println("\nnot valid");
                    break;
            }
        } while (!exit);
    }

   /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de platos diferentes 
     * vendidos en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void putAmountSold() {
        
        System.out.println("\nEnter the different dishes sold:");
        int dishes = reader.nextInt();

        prices = new double[dishes];
        amounts = new int[dishes];
    }

   
    public static void requestData() {
        for (int i = 0; i < prices.length; i++) {
            System.out.println("Enter the price of dish " + (i + 1) + ":");
            prices[i] = reader.nextDouble();

            System.out.println("Enter the amount of dish " + (i + 1) + ":");
            amounts[i] = reader.nextInt();
        }
    }

    /**
     * Calculates the total number of dishes sold.
     * @return total of dishes sold.
     */
    public static int calculateTotalDishesSold() {
        int total = 0; 
        for (int amount : amounts) {
            total += amount;
        }
        return total;
    }

    /**
     * Calculates the average price of the dishes sold.
     * @return the average price of the dishes sold.
     */
    public static double calculateAveragePrice() {
        double totalSales = 0;
        int totalAmount = 0;
        for (int i = 0; i < prices.length; i++) {
            totalSales += prices[i] * amounts[i];
            totalAmount += amounts[i];
        }
        return totalAmount == 0 ? 0 : totalSales / totalAmount; // ternary operador
    }

    /**
     * Calculates the total sales .
     * @return The total sales
     */
    public static double calculateTotalSales() {
        double totalSales = 0;
        for (int i = 0; i < prices.length; i++) {
            totalSales += prices[i] * amounts[i];
        }
        return totalSales;
    }

    /**
     * Calculates the dishes that exceeded limit
     * @param limit The minimum sales limit
     * @return The number of dishes that exceeded the limit
     */
    public static int DishesAboveLimit(double limit) {
        int count = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] * amounts[i] > limit) {
                count++;
            }
        }
        return count;
    }
}
