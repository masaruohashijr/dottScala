# Order => Item => Product

<b><h4>Below the Query applied in OrderDao object:</h4></b>
<pre>
  <code>
    select r2.time_band,sum(r2.qtd) from (select case 
        when r1.age between 1 and 3 then '1-3'
        when r1.age between 4 and 6 then '4-6'
        when r1.age between 7 and 12 then '7-12'  
        else '>12'
        end as time_band,
        count(1) as qtd from (
        select distinct c.id_order, 
        (abs(date_part('month','2020-12-31'::date)-date_part('month',a.created_at::date))
        +(date_part('year','2020-12-31'::date)-date_part('year',a.created_at::date))*12
        ) as age  
        from dott.product a 
        inner join dott.item b on a.id_product = b.id_product
        inner join dott.order c on b.id_order = c.id_order 
        where c.created_at between '2020-01-01' and '2020-12-31') r1
        group by r1.age
        order by 1) r2
        group by r2.time_band  
    </code>
</pre>
<b><h4>Below the Time Bands being parametrized into query assembling:</h4></b>
![image](https://user-images.githubusercontent.com/20522327/138982616-cafd1a43-772e-4727-a92a-f3a576f376b2.png)
<b><h4>Below the Main class calling ResourceManager for loading mock objects to Postgres database:</h4></b>
![image](https://user-images.githubusercontent.com/20522327/138982948-1bf454b0-87a9-4cfd-a2b7-a817c9f23439.png)
<b><h4>Below the Threads initializations:</h4></b>
![Main](https://user-images.githubusercontent.com/20522327/138980487-4188cc81-af88-4ca1-a3af-61ed4bfe2260.png)
<b><h4>Below the Mocking done for loading DB with samples:</h4></b>
![ResourceManager](https://user-images.githubusercontent.com/20522327/138981112-9222aa01-115d-4b83-9f85-775c9bdf0bfb.png)
<b><h4>Below the Factory Method:</h4></b>
![StockKeeper](https://user-images.githubusercontent.com/20522327/138981878-2842a26f-913e-441a-89b9-07ae66d23c03.png)
<b><h4>Below Creational Patterns:</h4></b>
![ProductFactory](https://user-images.githubusercontent.com/20522327/138982504-c2ce3364-bc88-4503-aeb9-33ecc33b29b2.png)
<b><h4>Below a Package Diagram:</h4></b>
![Packages](https://user-images.githubusercontent.com/20522327/138984495-5d566fd0-f1f7-4666-8d94-439809a48683.png)

<b><h4> Description </h4></b>

The purpose of this exercise is to check if older products are still being sold. Consider the following entities:

<b>Order:</b> contains general information about the order (customer name and contact, shipping address, grand total, date when the order was placed, ...)

<b>Item:</b> information about the purchased item (cost, shipping fee, tax amount, ...)

<b>Product:</b> information about the product (name, category, weight, price, creation date, ...)

These entities are all related: one order contains several items and each item has a product.

Please implement a tool that receives an interval and filters all orders placed during that interval.

The result should be a list of intervals (in months) that groups the orders based on the product age (creation date field in the product entity). If we have orders in the older intervals it means that older products are still being sold.

<b><h4> Example </h4></b>

$ java -jar orders.jar "2018-01-01 00:00:00" "2019-01-01 00:00:00"

<b><h4> Result: </h4></b>

1-3 months: 200 orders

4-6 months: 150 orders

7-12 months: 50 orders

&gt;12 months: 20 orders

<b><h4> Bonus feature: </h4></b>

Add an argument to this tool that allow us to pass a list of intervals instead of having the fixed intervals defined above (“1-3”, “4-6”, “7-12”, “>12”)

<b><h4> Implementation details: </h4></b>

This exercise should be implemented in Scala.


<b><h4> My results: </h4></b>
-----------------------
Armando
.......................

1-2 months: 1 orders

11-12 months: 15 orders

3-4 months: 5 orders

5-6 months: 6 orders

7-8 months: 3 orders

9-10 months: 15 orders

&gt;12 months: 483 orders

-----------------------
Filipe
.......................

1-6 months: 11 orders

7-12 months: 24 orders

&gt;12 months: 413 orders

-----------------------
Luís
.......................

1-4 months: 7 orders

5-8 months: 11 orders

9-12 months: 29 orders

&gt;12 months: 443 orders

-----------------------
Vasco
.......................

1-6 months: 12 orders

13-24 months: 89 orders

7-12 months: 33 orders

&gt;24 months: 394 orders

-----------------------
João
.......................

1-3 months: 6 orders

4-6 months: 8 orders

7-12 months: 33 orders

&gt;12 months: 443 orders




