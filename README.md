# Online-Shopping-Cart-System

A menu-driven Java console application that simulates an online shopping cart. Products are loaded from a file into a HashMap, cart items are stored in a Linked List, the last added item can be removed using a Stack, and the cart can be sorted by item total and checked out.

Built as a Data Structures and Algorithms (DSA) project at Rungta International Skills University.

Features

• View available products (sorted by product ID)
• Add a product to the cart by ID and quantity (repeated adds of the same product are merged)
• View the cart with item totals and the grand total
• Remove the last added item (undo) using a Stack
• Sort the cart by item total, high to low
• Checkout, which shows the order, prints the total and clears the cart
• Load products from products.txt at start and save the cart to cart.txt on exit
• Input validation: unknown product IDs and non-positive or non-numeric quantities are rejected

Data Structures and Concepts

Component
Used for
Where
HashMap<String, Product>
Product catalogue (product ID to name and price), O(1) average lookup
products, loadProducts()
LinkedList<CartItem>
Items in the cart
cart, addToCart(), viewCart()
Stack<CartAction>
History of additions so the last one can be undone (LIFO)
history, removeLastItem()
Sorting
Cart ordered by item total (high to low); product IDs ordered for display
sortCart(), viewProducts()
File handling
Read products.txt, write cart.txt
loadProducts(), saveCart()

Classes

All classes live in one file, OnlineShoppingCartSystem.java.

Class
Purpose
Product
One catalogue item: id, name, price
CartItem
One cart entry: productId, name, price, quantity; getTotal() returns price x quantity
CartAction
One recorded addition: productId, quantity; pushed on the Stack
OnlineShoppingCartSystem
Main class: holds the data structures, all menu methods and main()

Methods

Method
Description
loadProducts()
Reads products.txt into the HashMap
viewProducts()
Prints products sorted by product ID
addToCart()
Validates ID and quantity, adds or updates a cart item, pushes the action on the Stack
calculateTotal()
Sums the totals of all cart items
viewCart()
Prints the cart and its total
removeLastItem()
Pops the Stack and reduces the quantity of the matching cart item (removes it at zero)
sortCart()
Sorts the cart by item total, high to low, and displays it
checkout()
Prints the order and total, then clears the cart and the Stack
saveCart()
Writes the cart to cart.txt
main()
Menu loop for options 1 to 7

Getting Started

Requirements

• Java Development Kit (JDK 17 or later recommended)
• A terminal, or an IDE such as VS Code, IntelliJ IDEA or Eclipse

Project Files

.
├── OnlineShoppingCartSystem.java
├── products.txt
└── README.md

cart.txt is created automatically when you exit the program.

products.txt format

One product per line: ID,Name,Price

P101,Notebook,50
P102,Pen,10
P103,Bag,800

Compile and run

Run these from the folder that contains products.txt:

javac OnlineShoppingCartSystem.java
java OnlineShoppingCartSystem

Menu

==================================
     ONLINE SHOPPING CART SYSTEM
==================================
1. View Products
2. Add to Cart
3. View Cart
4. Remove Last Added Item
5. Sort Cart (High to Low)
6. Checkout
7. Exit
==================================

Sample Usage

Enter your choice: 2
Enter Product ID: P101
Enter Quantity: 2
Added: Notebook x2 = 100

Enter your choice: 2
Enter Product ID: P103
Enter Quantity: 1
Added: Bag x1 = 800

Enter your choice: 5
Cart sorted by item total (High to Low).
Product ID      Name            Price      Quantity   Total
P103            Bag             800        1          800
P101            Notebook        50         2          100
-------------------------------------
Total: 900

Enter your choice: 6
Order placed. Total: 900

Test Cases

#
Scenario
Input
Expected output
1
Normal input
Option 2, product P101, quantity 2
Added: Notebook x2 = 100
2
Boundary / minimum
Option 3 with an empty cart
Your cart is empty. and Total: 0
3
Invalid input
Option 2, product P999
Product P999 not found.
4
Different valid scenario
Cart has Notebook x2; add P103 x1, then sort, then checkout
Added: Bag x1 = 800, Bag listed before Notebook, Order placed. Total: 900

Time Complexity

Operation
Complexity
HashMap product lookup
O(1) average
Add new item at end of the linked list
O(1)
Search the cart for an existing product
O(n)
Stack push and pop
O(1)
View cart / calculate total
O(n)
Sort cart
O(n log n)
File load / save
O(n)

Here, n is the number of records being processed.

Notes

• Product IDs are not case-sensitive; input is converted to upper case.
• The cart is saved to cart.txt on exit for record keeping; it is not reloaded the next time the program starts.
• Checkout clears the cart and the undo history.

Author

Mahek Siddiqui
B.Tech in Computer Science & Engineering (AI and ML, in association with Google)
School of Computer Science and Engineering, Rungta International Skills University, Bhilai, Chhattisgarh

Guided by Miss. Naina Singh | Course: Data Structures and Algorithms (DSA) | Session 2026-27
