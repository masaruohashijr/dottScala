package app.data.mock

import _root_.app.data.models.Order
import _root_.app.data.models.Customer
import _root_.app.data.models.Product
import _root_.app.data.models.Item
import java.time.LocalDate
import _root_.app.util.Utils

object OrderFactory{
    
    private val r = new scala.util.Random
    
    private var items = new Array[Item](0)
    
    var order:Order = new Order()

    def produce(customers:Array[Customer], products:Array[Product], yearsAgo:Int, maxQttItems:Int, qttOrders:Int):Array[Order]={
        var orders = new Array[Order](qttOrders)
        for (i <- 0 to qttOrders-1) {
            var customer = mockCustomer(customers)
            var createdAt = mockCreatedAt(yearsAgo)
            order = OrderMock.create(customer, null, createdAt)
            var items = mockItems(order, products, maxQttItems)
            println(order)
            orders(i) = order
        }
        orders
    }

    def mockCustomer(customers:Array[Customer]):Customer={
        val ri = r.nextInt(customers.length)
        val customer = customers(ri)
        customer
    }

    def mockItems(order:Order, products:Array[Product], maxQttItems:Int):Array[Item]={
        val qttItems = r.nextInt(maxQttItems)
        var rp = r.nextInt(products.length)
        val qttProduct = r.nextInt(10)
        items = new Array[Item](qttItems)
        var usedProducts: Set[Product] = Set()
        for (i <- 0 to qttItems-1) {
            while (products(rp).creation_date.compareTo(order.creation_date)>0
                || usedProducts.contains(products(rp))){
                rp = r.nextInt(products.length)
            }
            println(products(rp).creation_date)
            items(i) = new Item(products(rp), qttProduct)
            usedProducts += products(rp)
        }
        if (items.length == 0 ){
            mockItems(order, products, maxQttItems)
        }
        println("Quantidade de items: ",items.length)
        order.items = items
        items
    }

    def mockCreatedAt(yearsAgo:Int):LocalDate={
        Utils.randomDate(yearsAgo)
    }
}