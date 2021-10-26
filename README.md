# Adentis Exercise: 2021-10-25

<b><h4> Description </h4></b>

The purpose of this exercise is to check if older products are still being sold. Consider the following entities:

Order: contains general information about the order (customer name and contact,

shipping address, grand total, date when the order was placed, ...)

Item: information about the purchased item (cost, shipping fee, tax amount, ...)

Product: information about the product (name, category, weight, price, creation date, ...)

These entities are all related: one order contains several items and each item has a product.

Please implement a tool that receives an interval and filters all orders placed during that interval.

The result should be a list of intervals (in months) that groups the orders based on the product

age (creation date field in the product entity). If we have orders in the older intervals it means

that older products are still being sold.

<b><h4> Example </h4></b>

$ java -jar orders.jar "2018-01-01 00:00:00" "2019-01-01 00:00:00"

<b><h4> Result: </h4></b>

1-3 months: 200 orders

4-6 months: 150 orders

7-12 months: 50 orders

>12 months: 20 orders

<b><h4> Bonus feature: </h4></b>

Add an argument to this tool that allow us to pass a list of intervals instead of having the fixed intervals defined above (“1-3”, “4-6”, “7-12”, “>12”)

