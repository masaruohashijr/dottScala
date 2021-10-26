package app.data.mock
import _root_.app.data.models.Customer

object CustomerFactory{
    def produce(qtt:Int):Array[Customer]={
        var customers = new Array[Customer](qtt)
        for (i <- 0 to qtt-1) {
            var customer = CustomerMock.create()
            //println(customer)
            customers(i) = customer
        }
        customers
    }
}