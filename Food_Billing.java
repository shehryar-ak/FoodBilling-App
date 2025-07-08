import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Project {

    public static void main(String[] args) {
        // Exception handling for main logic
        try (Scanner scanner = new Scanner(System.in)) {  // Scanner closed properly
            // Loop for multiple customers
            while (true) {
                CustomerReport c = new CustomerReport();

                // Get customer details and store them in CustomerReport object
                System.out.println("Enter name of customer:");
                String Name = scanner.nextLine();
                c.setName(Name);

                System.out.println("Enter Phone Number of customer:");
                String Number = scanner.nextLine();
                c.setNumber(Number);

                System.out.println("Enter age of customer:");
                int Age = scanner.nextInt();
                c.setAge(Age);

                System.out.println("Enter id of customer:");
                int Id = scanner.nextInt();
                c.setId(Id);

                // Display customer report
                c.Customer_Report();

                // Order processing
                Order o = new Order();
                o.purchaseItems();

                // Ask if the user wants to process another customer
                System.out.println("Do you want to process another customer (y/n)?");
                char choice = scanner.next().charAt(0);
                scanner.nextLine();  // Consume newline character left by next()
                
                if (Character.toLowerCase(choice) != 'y') {
                    System.out.println("Thank you! Exiting program.");
                    break;  // Exit loop and terminate program
                }
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

abstract class Customer {
    public String name;
    public int id;
    public int Age;
    public String Number;

    abstract void Customer_Report();
}

class CustomerReport extends Customer {
    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    int GetAge() {
        return Age;
    }

    void setAge(int Age) {
        this.Age = Age;
    }

    String GetNumber() {
        return Number;
    }

    void setNumber(String Number) {
        this.Number = Number;
    }

    @Override
    void Customer_Report() {
        System.out.println("Name of the Customer is " + name);
        System.out.println("Phone Number is " + Number);
        System.out.println("Id of the Customer is " + id);
        System.out.println("Age of the Customer is " + Age);
    }
}

class Order extends showMenu {
    public void purchaseItems() {
        Scanner scanner = new Scanner(System.in);
        char choice = 'y';  // Initialize choice variable
        int[] price = new int[100];
        int l = 0;
        int sum = 0;
        String[] productStore = new String[100];
        int[] QuantityStore = new int[100];

        int priceOfBurger = 300;
        int priceOfPizza = 1200;
        int priceOfColdDrink = 50;
        int priceOfFriedChicken = 500;
        int priceOfFrenchFries = 100;
        int priceOfJuices = 150;
        int priceOfBiryani = 500;
        int priceOfsamosas = 100;

        try {
            do {
                int item_Choice;
                int Quantity;
                menu();
                item_Choice = scanner.nextInt();

                switch (item_Choice) {
                    case 1:
                        System.out.println("Enter quantity of the product:");
                        Quantity = scanner.nextInt();

                        price[l] = priceOfBurger * Quantity;
                        productStore[l] = "Burger";
                        QuantityStore[l] = Quantity;
                        l++;
                        break;
                    case 2:
                        System.out.println("Enter quantity of the product:");
                        Quantity = scanner.nextInt();

                        price[l] = priceOfPizza * Quantity;
                        productStore[l] = "Pizza";
                        QuantityStore[l] = Quantity;
                        l++;
                        break;
                    case 3:
                        System.out.println("Enter quantity of the product:");
                        Quantity = scanner.nextInt();

                        price[l] = priceOfColdDrink * Quantity;
                        productStore[l] = "Cold Drink";
                        QuantityStore[l] = Quantity;
                        l++;
                        break;
                    case 4:
                        System.out.println("Enter quantity of the product:");
                        Quantity = scanner.nextInt();

                        price[l] = priceOfFriedChicken * Quantity;
                        productStore[l] = "Fried Chicken";
                        QuantityStore[l] = Quantity;
                        l++;
                        break;
                    case 5:
                        System.out.println("Enter quantity of the product:");
                        Quantity = scanner.nextInt();

                        price[l] = priceOfFrenchFries * Quantity;
                        productStore[l] = "French Fries";
                        QuantityStore[l] = Quantity;
                        l++;
                        break;
                    case 6:
                        System.out.println("Enter quantity of the product:");
                        Quantity = scanner.nextInt();

                        price[l] = priceOfJuices * Quantity;
                        productStore[l] = "Juices";
                        QuantityStore[l] = Quantity;
                        l++;
                        break;
                    case 7:
                        System.out.println("Enter quantity of the product:");
                        Quantity = scanner.nextInt();

                        price[l] = priceOfBiryani * Quantity;
                        productStore[l] = "Biryani";
                        QuantityStore[l] = Quantity;
                        l++;
                        break;
                    case 8:
                        System.out.println("Enter quantity of the product:");
                        Quantity = scanner.nextInt();

                        price[l] = priceOfsamosas * Quantity;
                        productStore[l] = "Samosas";
                        QuantityStore[l] = Quantity;
                        l++;
                        break;
                    default:
                        System.out.println("Invalid Input");
                        continue;
                }

                System.out.println("Do you want to purchase another item (y/n):");
                choice = scanner.next().charAt(0);
            } while (Character.toLowerCase(choice) == 'y');

            // Generate and display the bill
            System.out.println("\nITEMS            QUANTITY");
            for (int i = 0; i < l; i++) {
                System.out.printf("%-16s%d\n", productStore[i], QuantityStore[i]);
                sum += price[i];
            }

            System.out.println("\nTotal bill is: PKR " + sum);
            System.out.println("\nThank you for your purchase!");

            // Save the bill to a file
            try (FileWriter writer = new FileWriter("CustomerBill.txt")) {
                writer.write("Customer Bill:\n");
                writer.write("ITEMS            QUANTITY\n");
                for (int i = 0; i < l; i++) {
                    writer.write(String.format("%-16s%d\n", productStore[i], QuantityStore[i]));
                }
                writer.write("\nTotal bill is: PKR " + sum + "\n");
                writer.write("Thank you for your purchase!");
                System.out.println("\nBill saved to CustomerBill.txt.");
            }

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}

interface Menu {
    void menu();
}

class showMenu implements Menu {
    @Override
    public void menu() {
        System.out.println("\n\n\n\n\t\t\t\tWelcome to our Food point ");
        System.out.println("\n\n\t\t\t\tFollowing is the Menu ");
        System.out.println("\t\t\t\t----------Menu-----------");
        System.out.println("\t\t\t\t1.Burger--------------PKR 300");
        System.out.println("\t\t\t\t2.Pizza---------------PKR 1200");
        System.out.println("\t\t\t\t3.Cold drink----------PKR 50");
        System.out.println("\t\t\t\t4.Fried Chicken-------PKR 500");
        System.out.println("\t\t\t\t5.French Fries--------PKR 100");
        System.out.println("\t\t\t\t6.Juices--------------PKR 150");
        System.out.println("\t\t\t\t7.Biryani-------------PKR 500");
        System.out.println("\t\t\t\t8.Samosas-------------PKR 100");
        System.out.println("\t\t\t\t-----------------------------");
        System.out.println("\t\t\t\tSelect your item from the menu");
    }
}
