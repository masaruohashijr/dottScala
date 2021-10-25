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

    def produce(customers:Array[Customer], products:Array[Product], yearsAgo:Int, maxQttItems:Int, qttOrders:Int):Array[Order]={
        var orders = new Array[Order](qttOrders)
        for (i <- 0 to qttOrders-1) {
            var customer = mockCustomer(customers)
            var items = mockItems(products, maxQttItems)
            var createdAt = mockCreatedAt(yearsAgo)
            var order = OrderMock.create(customer, items, createdAt)
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

    def mockItems(products:Array[Product], maxQttItems:Int):Array[Item]={
        val qttItems = r.nextInt(maxQttItems)
        val rp = r.nextInt(products.length)
        val qttProduct = r.nextInt(10)
        items = new Array[Item](qttItems)
        for (i <- 0 to qttItems-1) {
            items(i) = new Item(products(rp), qttProduct)
        }
        if (items.length == 0 ){
            mockItems(products, maxQttItems)
        }
        println("Quantidade de items: ",items.length)
        items
    }

    def mockCreatedAt(yearsAgo:Int):LocalDate={
        Utils.randomDate(yearsAgo)
    }
}