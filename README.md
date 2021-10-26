# Adentis Exercise: 2021-01-10

<b><h4> Description </h4></b>

The purpose of this exercise is to check if older products are still being sold. Consider the following entities:

Order: contains general information about the order (customer name and contact,
shipping address, grand total, date when the order was placed, ...)

Item: information about the purchased item (cost, shipping fee, tax amount, ...)

Product: information about the product (name, category, weight, price, creation date, ...)

These entities are all related: one order contains several items and each item has a product.
Please implement a tool that receives an interval and filters all orders placed during that interval.
The result should be a list of intervals (in months) that groups the orders based on the product
age (creation date field in the product entity). 
If we have orders in the older intervals it means that olders products are still being sold.

<b><h4> Examples </h4></b>
(1) By specific period  (method filterAllOrders)

$ java -jar orders.jar "2018-01-01 00:00:00" "2019-01-01 00:00:00"

Result:

1-3 months: 3 orders

4-6 months: 4 orders

7-12 months: 3 orders

and >12 months: 3 orders

(2) By Months (method filterOrdersBySpecificInterval)

$ java -jar orders.jar 1-3 // it means between January and March

1-3 months: 3 orders

<b><h4> Data Sample</h4></b>

Period of the orders: 2018-01-01 and 2018-12-31

Product: 3 (Iphone 12, Samsung A1, Nokia 1100)

Item: 3

Orders: 12

6 Iphone 12; 4 Samsung A1; 2 Nokia 1100

More info: src/data-sample.txt

<b><h4> Ps.:</h4></b>
It could be done by Filter.