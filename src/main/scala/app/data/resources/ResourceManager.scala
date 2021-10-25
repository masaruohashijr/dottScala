package app.data.resources

import app.data.models.Customer
import app.data.models.Product
import app.data.models.Order
import app.data.dao.CustomerDao
import app.data.dao.ProductDao
import app.data.dao.OrderDao
import app.data.mock.OrderFactory
import app.data.mock.ProductFactory
import app.data.mock.CustomerFactory

object ResourceManager{

    val BATCH_SIZE = 100

    private def loadCustomers(customers: Array[Customer]):Array[Customer] = {
        CustomerDao.loadCustomers(customers)
    }
    private def loadProducts(products: Array[Product]):Array[Product] = {
        ProductDao.loadProducts(products)
    }
    private def loadOrders(orders: Array[Order]):Array[Order] = {
        OrderDao.loadOrders(orders)
    }

    def ReloadDatabase(yearsAgo:Int){
        var customers:Array[Customer] = CustomerFactory.produce(1000)
        var lCustomers = loadCustomers(customers)
        var products:Array[Product] = ProductFactory.produce(2000)
        var lProducts = loadProducts(products)
        var orders:Array[Order] = OrderFactory.produce(lCustomers, lProducts, yearsAgo, 10, 1000)
        var lOrders = loadOrders(orders)
    }

}