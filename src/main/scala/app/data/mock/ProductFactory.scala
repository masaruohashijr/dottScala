package app.data.mock
import _root_.app.data.models.Product
object ProductFactory {

    def produce(qtt:Int):Array[Product]={
        var products = new Array[Product](qtt)
        for (i <- 0 to qtt-1) {
            var p = ProductMock.create()
            // println(p)
            products(i) = p
        }
        products
    }

}