
import java.io.*;
import java.util.*;

public class OnlineShoppingCartSystem {

    // Product details
    static class Product {
        String id;
        String name;
        int price;

        Product(String id, String name, int price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }

    // Cart item details
    static class CartItem {
        String productId;
        String name;
        int price;
        int quantity;

        CartItem(Product p, int quantity) {
            this.productId = p.id;
            this.name = p.name;
            this.price = p.price;
            this.quantity = quantity;
        }

        int getTotal() {
            return price * quantity;
        }
    }

    // Stack stores each addition for undo
    static class CartAction {
        String productId;
        int quantity;

        CartAction(String productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }
    }

    // Required data structures
    static HashMap<String, Product> products = new HashMap<>();
    static LinkedList<CartItem> cart = new LinkedList<>();
    static Stack<CartAction> history = new Stack<>();

    static Scanner sc = new Scanner(System.in);

    // Load products from file
    static void loadProducts() {
        try (BufferedReader br = new BufferedReader(
                new FileReader("products.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 3) {
                    String id = data[0].trim();
                    String name = data[1].trim();
                    int price = Integer.parseInt(data[2].trim());

                    products.put(id, new Product(id, name, price));
                }
            }

            System.out.println("Products loaded successfully.");

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading products: " + e.getMessage());
        }
    }

    // Display available products
    static void viewProducts() {
        System.out.println("\n--------- AVAILABLE PRODUCTS ---------");
        System.out.printf("%-12s %-20s %s%n",
                "Product ID", "Product Name", "Price");

        ArrayList<String> ids = new ArrayList<>(products.keySet());
        Collections.sort(ids);

        for (String id : ids) {
            Product p = products.get(id);
            System.out.printf("%-12s %-20s %d%n",
                    p.id, p.name, p.price);
        }
    }

    // Add product to cart
    static void addToCart() {
        System.out.print("Enter Product ID: ");
        String id = sc.nextLine().trim().toUpperCase();

        if (!products.containsKey(id)) {
            System.out.println("Product " + id + " not found.");
            return;
        }

        int quantity;

        try {
            System.out.print("Enter Quantity: ");
            quantity = Integer.parseInt(sc.nextLine().trim());

            if (quantity <= 0) {
                System.out.println("Quantity must be a positive whole number.");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity. Enter a whole number.");
            return;
        }

        Product p = products.get(id);

        CartItem existing = null;

        for (CartItem item : cart) {
            if (item.productId.equals(id)) {
                existing = item;
                break;
            }
        }

        if (existing != null) {
            existing.quantity += quantity;
        } else {
            cart.add(new CartItem(p, quantity));
        }

        history.push(new CartAction(id, quantity));

        System.out.println("Added: " + p.name + " x" +
                quantity + " = " + (p.price * quantity));
    }

    // Calculate cart total
    static int calculateTotal() {
        int total = 0;

        for (CartItem item : cart) {
            total += item.getTotal();
        }

        return total;
    }

    // Display shopping cart
    static void viewCart() {
        System.out.println("\n------------- YOUR CART -------------");

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            System.out.println("Total: 0");
            return;
        }

        System.out.printf("%-15s %-15s %-10s %-10s %-10s%n",
                "Product ID", "Name", "Price", "Quantity", "Total");

        for (CartItem item : cart) {
            System.out.printf("%-15s %-15s %-10d %-10d %-10d%n",
                    item.productId, item.name, item.price,
                    item.quantity, item.getTotal());
        }

        System.out.println("-------------------------------------");
        System.out.println("Total: " + calculateTotal());
    }

    // Remove last added item using Stack
    static void removeLastItem() {
        if (history.isEmpty()) {
            System.out.println("No item available to remove.");
            return;
        }

        CartAction action = history.pop();

        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);

            if (item.productId.equals(action.productId)) {
                item.quantity -= action.quantity;

                if (item.quantity == 0) {
                    cart.remove(i);
                }

                System.out.println("Last added item removed successfully.");
                return;
            }
        }
    }

    // Sort items by total price: high to low
    static void sortCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Nothing to sort.");
            return;
        }

        cart.sort((a, b) ->
                Integer.compare(b.getTotal(), a.getTotal()));

        System.out.println("\nCart sorted by item total (High to Low).");
        viewCart();
    }

    // Checkout
    static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Cannot checkout.");
            return;
        }

        System.out.println("\n------------- CHECKOUT -------------");

        for (CartItem item : cart) {
            System.out.println(item.name + " x" +
                    item.quantity + " = " + item.getTotal());
        }

        System.out.println("Order placed. Total: " + calculateTotal());

        cart.clear();
        history.clear();
    }

    // Save cart data to file
    static void saveCart() {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("cart.txt"))) {

            for (CartItem item : cart) {
                bw.write(item.productId + "," +
                        item.name + "," +
                        item.price + "," +
                        item.quantity + "," +
                        item.getTotal());

                bw.newLine();
            }

            System.out.println("Cart data saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving cart: " + e.getMessage());
        }
    }

    // Main menu
    public static void main(String[] args) {

        loadProducts();

        if (products.isEmpty()) {
            System.out.println("No products available. Check products.txt.");
            return;
        }

        int choice;

        do {
            System.out.println("\n==================================");
            System.out.println("     ONLINE SHOPPING CART SYSTEM");
            System.out.println("==================================");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Remove Last Added Item");
            System.out.println("5. Sort Cart (High to Low)");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.println("==================================");

            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {
                    case 1:
                        viewProducts();
                        break;

                    case 2:
                        addToCart();
                        break;

                    case 3:
                        viewCart();
                        break;

                    case 4:
                        removeLastItem();
                        break;

                    case 5:
                        sortCart();
                        break;

                    case 6:
                        checkout();
                        break;

                    case 7:
                        saveCart();
                        System.out.println("Thank you for shopping!");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } catch (NumberFormatException e) {
                choice = 0;
                System.out.println("Please enter a valid menu number.");
            }

        } while (choice != 7);

        sc.close();
    }
}

