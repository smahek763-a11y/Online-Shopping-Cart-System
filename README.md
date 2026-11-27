

#  Online Shopping Cart System

##  Project Overview
The **Online Shopping Cart System** is a console-based Java application that allows users to manage products and perform basic shopping cart operations. It uses Java Collections Framework and File Handling to store product information, manage cart items, and save cart details.

##  Project Objectives
- Display available products.
- Add products to the shopping cart.
- View cart items and calculate the total price.
- Remove the last added item using Stack.
- Sort cart items by total price (High to Low).
- Checkout and place an order.
- Load product details from a file and save cart data on exit.

##  Technologies Used
- **Programming Language:** Java
- **IDE:** Visual Studio Code
- **Concepts:** Object-Oriented Programming, HashMap, LinkedList, Stack, Sorting, Methods, File Handling.

##  Project Structure

```text
Online-Shopping-Cart-System/
│
├── OnlineShoppingCartSystem.java
├── products.txt
├── README.md
├── Online_Shopping_Cart_System_Presentation.pptx
└── Online_Shopping_Cart_System_Report.pdf
```

##  Product Details

Products are loaded from `products.txt`.

| Product ID | Product Name | Price |
|---|---|---:|
| P101 | Notebook | 50 |
| P102 | Pen | 10 |
| P103 | Bag | 800 |

##  Features

1. **View Products:** Displays all available products with their IDs, names, and prices.
2. **Add to Cart:** Adds products with the requested quantity and validates user input.
3. **View Cart:** Displays cart items, quantities, individual totals, and the final amount.
4. **Remove Last Added Item:** Uses Stack to undo the most recent cart addition.
5. **Sort Cart:** Sorts products according to their total price in descending order.
6. **Checkout:** Calculates the final bill and places the order.
7. **File Handling:** Reads product details from `products.txt` and saves cart information to `cart.txt` when exiting.

##  How to Run the Project

### Prerequisites
- Java JDK installed.
- Visual Studio Code installed.
- Java Extension Pack installed in VS Code (recommended).

### Steps

1. Clone the repository:

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

2. Open the project folder in VS Code.

3. Open the terminal.

4. Compile the Java program:

```bash
javac OnlineShoppingCartSystem.java
```

5. Run the application:

```bash
java OnlineShoppingCartSystem
```

## 📋 Main Menu

```text
1. View Products
2. Add to Cart
3. View Cart
4. Remove Last Added Item
5. Sort Cart (High to Low)
6. Checkout
7. Exit
```

##  Test Cases

| Test Case | Scenario | Expected Result |
|---|---|---|
| 1 | Add Notebook (P101), Quantity 2 | Added: Notebook x2 = 100 |
| 2 | View an empty cart | Your cart is empty. Total: 0 |
| 3 | Enter invalid Product ID P999 | Product P999 not found. |
| 4 | Add Notebook x2 and Bag x1, sort and checkout | Sorted cart and Order placed. Total: 900 |

##  Concepts Implemented

- **HashMap:** Stores product details using Product ID as the key.
- **LinkedList:** Maintains shopping cart items.
- **Stack:** Tracks cart additions to remove the most recently added quantity.
- **Sorting:** Arranges cart items by their total price.
- **File Handling:** Reads and writes product and cart information.
- **Methods:** Divides the application into reusable functions.

##  Future Enhancements
- Add user login and registration.
- Implement product search functionality.
- Add payment gateway integration.
- Maintain order history.
- Introduce a graphical user interface.

##  Author
**Mahek Siddiqui**

**Project:** Online Shopping Cart System  
**PS No.:** 11

---

 *This project demonstrates the practical implementation of Java programming, data structures, and file handling through a real-world shopping cart application.*
