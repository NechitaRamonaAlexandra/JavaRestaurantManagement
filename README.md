# JavaRestaurantManagement
---
Application for the overall management of the restaurant, customizing orders, updating the menu, notifying the chef about orders.

In this app the main objective was to demonstrate Java and OOP knowledge, but also use and understand the Observer and Composite design patterns. It has a minimalistic ui, created through Java Swift.

---
The strategy was pretty straightforward: the restaurant has a menu, an administrator, a waiter and a chef.
* *For the menu* : It was read from/into a file via serialization. The administrator window handles changes such as adding, editing or deleting items from the menu. The waiter has access to the menu only to select the items for a new order.
* *Administrator* : he can create new menu items, delete old ones, edit items. The items are stored in a list and displayed on the administrator window as a JTable.
* *Waiter* : he handles the orders and billing. He can create a new order and select the items from the menu which are supposed to be in the order, he can compute the total due of the order and it is displayed in an non-editable text field. The orders are stored in a HashMap but they are displayed in waiter’s window in a JTable as well. He can also generate the bill for a selected order, which will also be generated in a txt format in the source folder.

The menu items are created with respect to the Composite design pattern. The classes `BaseProduct` and `CompositeProduct` are containing the information regarding the product and they both implement the `MenuItem` interface. The MenuItem interface contains methods such as `getName()`, `getPrice()` etc but most importantly it contains the `computePrice()` method, which both classes need to have. If an item in the menu is a base product, there is not much to say about it and it has a fixed price, but if it is a composite product, it contains a list of other menu items being in its composition and its price is the total sum of the prices of its parts. When using the list with the menu in the Restaurant class, the elements in the list are MenuItem objects, because only a MenuItem can be either a base or a composite product. When dealing with a menu item, appropriate casts and methods are used.

The orders are stored in a HashMap in which the object Order is the key and at that key there is a list of menu items constituting that specific order. The method `hashCode()` was overloaded in the Order class in order to provide better hashing and avoid collisions(by double hashing).



